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

package com.liferay.list.type.service.impl;

import com.liferay.list.type.exception.DuplicateListTypeEntryException;
import com.liferay.list.type.exception.DuplicateListTypeEntryExternalReferenceCodeException;
import com.liferay.list.type.exception.ListTypeEntryKeyException;
import com.liferay.list.type.exception.ListTypeEntryNameException;
import com.liferay.list.type.model.ListTypeEntry;
import com.liferay.list.type.service.base.ListTypeEntryLocalServiceBaseImpl;
import com.liferay.list.type.service.persistence.ListTypeDefinitionPersistence;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.util.PropsUtil;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Gabriel Albuquerque
 */
@Component(
	property = "model.class.name=com.liferay.list.type.model.ListTypeEntry",
	service = AopService.class
)
public class ListTypeEntryLocalServiceImpl
	extends ListTypeEntryLocalServiceBaseImpl {

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public ListTypeEntry addListTypeEntry(
			String externalReferenceCode, long userId,
			long listTypeDefinitionId, String key, Map<Locale, String> nameMap)
		throws PortalException {

		User user = _userLocalService.getUser(userId);

		_validateExternalReferenceCode(
			externalReferenceCode, user.getCompanyId(), listTypeDefinitionId,
			0);

		_validateKey(listTypeDefinitionId, key);
		_validateName(nameMap);

		ListTypeEntry listTypeEntry = listTypeEntryPersistence.create(
			counterLocalService.increment());

		listTypeEntry.setExternalReferenceCode(externalReferenceCode);
		listTypeEntry.setCompanyId(user.getCompanyId());
		listTypeEntry.setUserId(user.getUserId());
		listTypeEntry.setUserName(user.getFullName());
		listTypeEntry.setListTypeDefinitionId(listTypeDefinitionId);
		listTypeEntry.setKey(key);
		listTypeEntry.setNameMap(nameMap);

		return listTypeEntryPersistence.update(listTypeEntry);
	}

	@Override
	public void deleteListTypeEntryByListTypeDefinitionId(
		long listTypeDefinitionId) {

		for (ListTypeEntry listTypeEntry :
				listTypeEntryPersistence.findByListTypeDefinitionId(
					listTypeDefinitionId)) {

			listTypeEntryLocalService.deleteListTypeEntry(listTypeEntry);
		}
	}

	@Override
	public ListTypeEntry fetchListTypeEntry(
		long listTypeDefinitionId, String key) {

		return listTypeEntryPersistence.fetchByLTDI_K(
			listTypeDefinitionId, key);
	}

	@Override
	public ListTypeEntry fetchListTypeEntryByExternalReferenceCode(
		String externalReferenceCode, long companyId) {

		return listTypeEntryPersistence.fetchByERC_C(
			externalReferenceCode, companyId);
	}

	@Override
	public ListTypeEntry fetchListTypeEntryByExternalReferenceCode(
		String externalReferenceCode, long companyId,
		long listTypeDefinitionId) {

		return listTypeEntryPersistence.fetchByERC_C_LTDI(
			externalReferenceCode, companyId, listTypeDefinitionId);
	}

	@Override
	public List<ListTypeEntry> getListTypeEntries(long listTypeDefinitionId) {
		return listTypeEntryPersistence.findByListTypeDefinitionId(
			listTypeDefinitionId);
	}

	@Override
	public List<ListTypeEntry> getListTypeEntries(
		long listTypeDefinitionId, int start, int end) {

		return listTypeEntryPersistence.findByListTypeDefinitionId(
			listTypeDefinitionId, start, end);
	}

	@Override
	public int getListTypeEntriesCount(long listTypeDefinitionId) {
		return listTypeEntryPersistence.countByListTypeDefinitionId(
			listTypeDefinitionId);
	}

	@Override
	public ListTypeEntry getListTypeEntry(long listTypeDefinitionId, String key)
		throws PortalException {

		return listTypeEntryPersistence.findByLTDI_K(listTypeDefinitionId, key);
	}

	@Override
	public ListTypeEntry getListTypeEntryByExternalReferenceCode(
			String externalReferenceCode, long companyId)
		throws PortalException {

		return listTypeEntryPersistence.findByERC_C(
			externalReferenceCode, companyId);
	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public ListTypeEntry updateListTypeEntry(
			String externalReferenceCode, long listTypeEntryId,
			Map<Locale, String> nameMap)
		throws PortalException {

		ListTypeEntry listTypeEntry = listTypeEntryPersistence.findByPrimaryKey(
			listTypeEntryId);

		_validateExternalReferenceCode(
			externalReferenceCode, listTypeEntry.getCompanyId(),
			listTypeEntry.getListTypeDefinitionId(), listTypeEntryId);

		_validateName(nameMap);

		listTypeEntry.setExternalReferenceCode(externalReferenceCode);
		listTypeEntry.setNameMap(nameMap);

		return listTypeEntryPersistence.update(listTypeEntry);
	}

	private void _validateExternalReferenceCode(
		String externalReferenceCode, long companyId, long listTypeDefinitionId,
		long listTypeEntryId) {

		if (Validator.isNull(externalReferenceCode) ||
			!GetterUtil.getBoolean(PropsUtil.get("feature.flag.LPS-168886"))) {

			return;
		}

		ListTypeEntry listTypeEntry =
			listTypeEntryPersistence.fetchByERC_C_LTDI(
				externalReferenceCode, companyId, listTypeDefinitionId);

		if ((listTypeEntry != null) &&
			(listTypeEntry.getListTypeEntryId() != listTypeEntryId)) {

			throw new DuplicateListTypeEntryExternalReferenceCodeException(
				"Duplicate external reference code " + externalReferenceCode);
		}
	}

	private void _validateKey(long listTypeDefinitionId, String key)
		throws PortalException {

		_listTypeDefinitionPersistence.findByPrimaryKey(listTypeDefinitionId);

		if (Validator.isNull(key)) {
			throw new ListTypeEntryKeyException("Key is null");
		}

		char[] keyCharArray = key.toCharArray();

		for (char c : keyCharArray) {
			if (!Validator.isChar(c) && !Validator.isDigit(c)) {
				throw new ListTypeEntryKeyException(
					"Key must only contain letters and digits");
			}
		}

		ListTypeEntry listTypeEntry = listTypeEntryPersistence.fetchByLTDI_K(
			listTypeDefinitionId, key);

		if (listTypeEntry != null) {
			throw new DuplicateListTypeEntryException("Duplicate key " + key);
		}
	}

	private void _validateName(Map<Locale, String> nameMap)
		throws PortalException {

		Locale locale = LocaleUtil.getSiteDefault();

		if ((nameMap == null) || Validator.isNull(nameMap.get(locale))) {
			throw new ListTypeEntryNameException(
				"Name is null for locale " + locale.getDisplayName());
		}
	}

	@Reference
	private ListTypeDefinitionPersistence _listTypeDefinitionPersistence;

	@Reference
	private UserLocalService _userLocalService;

}