/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.fragment.internal.file.install;

import com.liferay.fragment.importer.FragmentsImporter;
import com.liferay.layout.importer.LayoutsImporter;
import com.liferay.petra.string.CharPool;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.file.install.FileInstaller;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.CompanyConstants;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.role.RoleConstants;
import com.liferay.portal.kernel.security.auth.PrincipalThreadLocal;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.staging.StagingGroupHelper;

import java.io.File;
import java.io.IOException;

import java.net.URL;

import java.util.Enumeration;
import java.util.List;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jürgen Kappler
 */
@Component(service = FileInstaller.class)
public class FragmentFileInstaller implements FileInstaller {

	@Override
	public boolean canTransformURL(File file) {
		String fileName = file.getName();

		if (!StringUtil.endsWith(fileName, ".zip")) {
			return false;
		}

		try {
			JSONObject deployJSONObject = _getDeployJSONObject(file);

			if (deployJSONObject != null) {
				return true;
			}
		}
		catch (Exception exception) {
			if (_log.isDebugEnabled()) {
				_log.debug(exception);
			}
		}

		return false;
	}

	@Override
	public URL transformURL(File file) throws Exception {
		PermissionChecker currentPermissionChecker =
			PermissionThreadLocal.getPermissionChecker();
		String currentName = PrincipalThreadLocal.getName();
		ServiceContext currentServiceContext =
			ServiceContextThreadLocal.getServiceContext();

		try {
			_deploy(file);
		}
		finally {
			file.delete();

			PermissionThreadLocal.setPermissionChecker(
				currentPermissionChecker);
			PrincipalThreadLocal.setName(currentName);
			ServiceContextThreadLocal.pushServiceContext(currentServiceContext);
		}

		return null;
	}

	@Override
	public void uninstall(File file) {
	}

	private void _deploy(File file) throws Exception {
		JSONObject deployJSONObject = _getDeployJSONObject(file);

		if (deployJSONObject == null) {
			throw new Exception();
		}

		Company company = null;
		Group group = null;

		String companyWebId = deployJSONObject.getString("companyWebId");

		if (Validator.isNotNull(companyWebId) &&
			!Objects.equals(companyWebId, StringPool.STAR)) {

			company = _companyLocalService.getCompanyByWebId(companyWebId);
		}

		if ((company != null) && deployJSONObject.has("groupKey")) {
			group = _getDeploymentGroup(
				company.getCompanyId(), deployJSONObject.getString("groupKey"));
		}
		else if (company != null) {
			group = _groupLocalService.getCompanyGroup(company.getCompanyId());
		}
		else {
			List<Company> companies = _companyLocalService.getCompanies(0, 1);

			if (ListUtil.isEmpty(companies)) {
				throw new Exception();
			}

			company = companies.get(0);
		}

		User user = _getUser(company, group);

		if (user == null) {
			throw new Exception();
		}

		PermissionThreadLocal.setPermissionChecker(
			PermissionCheckerFactoryUtil.create(user));

		PrincipalThreadLocal.setName(user.getUserId());

		ServiceContext serviceContext = new ServiceContext();

		if (company != null) {
			serviceContext.setCompanyId(company.getCompanyId());
		}
		else {
			serviceContext.setCompanyId(CompanyConstants.SYSTEM);
		}

		serviceContext.setUserId(user.getUserId());

		ServiceContextThreadLocal.pushServiceContext(serviceContext);

		long groupId = 0;

		if (group != null) {
			groupId = group.getGroupId();
		}

		_fragmentsImporter.importFragmentEntries(
			user.getUserId(), groupId, 0, file, true);

		if ((company != null) && (group != null) &&
			(company.getGroupId() != group.getGroupId())) {

			_layoutsImporter.importFile(
				user.getUserId(), groupId, 0L, file, true);
		}
	}

	private JSONObject _getDeployJSONObject(File file)
		throws IOException, JSONException {

		try (ZipFile zipFile = new ZipFile(file)) {
			ZipEntry zipEntry = _getDeployZipEntry(zipFile);

			if (zipEntry == null) {
				return null;
			}

			return _jsonFactory.createJSONObject(
				StringUtil.read(zipFile.getInputStream(zipEntry)));
		}
	}

	private Group _getDeploymentGroup(long companyId, String groupKey)
		throws Exception {

		Group group = _groupLocalService.getGroup(companyId, groupKey);

		if (group == null) {
			return null;
		}

		if (_stagingGroupHelper.isLocalLiveGroup(group) ||
			_stagingGroupHelper.isRemoteLiveGroup(group)) {

			return group.getStagingGroup();
		}

		return group;
	}

	private ZipEntry _getDeployZipEntry(ZipFile zipFile) {
		Enumeration<? extends ZipEntry> enumeration = zipFile.entries();

		while (enumeration.hasMoreElements()) {
			ZipEntry zipEntry = enumeration.nextElement();

			if (Objects.equals(
					_getFileName(zipEntry.getName()),
					"liferay-deploy-fragments.json")) {

				return zipEntry;
			}
		}

		return null;
	}

	private String _getFileName(String path) {
		int pos = path.lastIndexOf(CharPool.SLASH);

		if (pos > 0) {
			return path.substring(pos + 1);
		}

		return path;
	}

	private User _getUser(Company company, Group group) throws Exception {
		long companyId = CompanyConstants.SYSTEM;
		long userId = 0;

		if (group != null) {
			companyId = group.getCompanyId();
			userId = group.getCreatorUserId();
		}
		else if (company != null) {
			companyId = company.getCompanyId();
		}

		User user = _userLocalService.fetchUserById(userId);

		if ((user == null) || user.isDefaultUser()) {
			Role role = _roleLocalService.getRole(
				companyId, RoleConstants.ADMINISTRATOR);

			long[] userIds = _userLocalService.getRoleUserIds(role.getRoleId());

			return _userLocalService.getUser(userIds[0]);
		}

		return user;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		FragmentFileInstaller.class);

	@Reference
	private CompanyLocalService _companyLocalService;

	@Reference
	private FragmentsImporter _fragmentsImporter;

	@Reference
	private GroupLocalService _groupLocalService;

	@Reference
	private JSONFactory _jsonFactory;

	@Reference
	private LayoutsImporter _layoutsImporter;

	@Reference
	private RoleLocalService _roleLocalService;

	@Reference
	private StagingGroupHelper _stagingGroupHelper;

	@Reference
	private UserLocalService _userLocalService;

}