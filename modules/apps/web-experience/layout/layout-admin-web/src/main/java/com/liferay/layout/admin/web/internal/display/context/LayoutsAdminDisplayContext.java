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

package com.liferay.layout.admin.web.internal.display.context;

import com.liferay.exportimport.kernel.staging.LayoutStagingUtil;
import com.liferay.exportimport.kernel.staging.StagingUtil;
import com.liferay.layout.admin.web.internal.constants.LayoutAdminPortletKeys;
import com.liferay.portal.kernel.dao.search.EmptyOnClickRowChecker;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutConstants;
import com.liferay.portal.kernel.model.LayoutRevision;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.PortalPreferences;
import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.LayoutLocalServiceUtil;
import com.liferay.portal.kernel.service.permission.LayoutPermissionUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.util.LayoutDescription;
import com.liferay.portal.util.LayoutListUtil;
import com.liferay.portlet.layoutsadmin.display.context.GroupDisplayContextHelper;

import java.util.List;
import java.util.Locale;
import java.util.Objects;

import javax.portlet.PortletURL;

/**
 * @author Eudaldo Alonso
 */
public class LayoutsAdminDisplayContext extends BaseLayoutDisplayContext {

	public LayoutsAdminDisplayContext(
		LiferayPortletRequest liferayPortletRequest,
		LiferayPortletResponse liferayPortletResponse) {

		super(liferayPortletRequest, liferayPortletResponse);

		_groupDisplayContextHelper = new GroupDisplayContextHelper(
			PortalUtil.getHttpServletRequest(liferayPortletRequest));

		this.liferayPortletRequest.setAttribute(
			com.liferay.portal.kernel.util.WebKeys.LAYOUT_DESCRIPTIONS,
			getLayoutDescriptions());
	}

	public PortletURL getAddLayoutURL() {
		return super.getAddLayoutURL(
			LayoutConstants.DEFAULT_PLID, isPrivatePages());
	}

	@Override
	public PortletURL getAddLayoutURL(long selPlid, Boolean privateLayout) {
		PortletURL addLayoutURL = super.getAddLayoutURL(selPlid, privateLayout);

		addLayoutURL.setParameter(
			"backURL",
			PortalUtil.getCurrentURL(
				PortalUtil.getHttpServletRequest(liferayPortletRequest)));

		return addLayoutURL;
	}

	public String getDisplayStyle() {
		if (Validator.isNotNull(_displayStyle)) {
			return _displayStyle;
		}

		PortalPreferences portalPreferences =
			PortletPreferencesFactoryUtil.getPortalPreferences(
				liferayPortletRequest);

		_displayStyle = portalPreferences.getValue(
			LayoutAdminPortletKeys.GROUP_PAGES, "display-style", "list");

		return _displayStyle;
	}

	public Group getGroup() {
		return _groupDisplayContextHelper.getGroup();
	}

	public Long getGroupId() {
		return _groupDisplayContextHelper.getGroupId();
	}

	public UnicodeProperties getGroupTypeSettings() {
		return _groupDisplayContextHelper.getGroupTypeSettings();
	}

	public List<LayoutDescription> getLayoutDescriptions() {
		if (_layoutDescriptions != null) {
			return _layoutDescriptions;
		}

		_layoutDescriptions = LayoutListUtil.getLayoutDescriptions(
			getGroupId(), isPrivateLayout(), getRootNodeName(),
			themeDisplay.getLocale());

		return _layoutDescriptions;
	}

	public SearchContainer getLayoutsSearchContainer() throws PortalException {
		if (_layoutsSearchContainer != null) {
			return _layoutsSearchContainer;
		}

		String emptyResultMessage = "there-are-no-public-pages";

		if (isPrivatePages()) {
			emptyResultMessage = "there-are-no-private-pages";
		}

		SearchContainer layoutsSearchContainer = new SearchContainer(
			liferayPortletRequest, getPortletURL(), null, emptyResultMessage);

		if (isShowAddRootLayoutButton()) {
			layoutsSearchContainer.setEmptyResultsMessageCssClass(
				"there-are-no-layouts.-you-can-add-a-layout-by-clicking-the-" +
					"plus-button-on-the-bottom-right-corner");
			layoutsSearchContainer.setEmptyResultsMessageCssClass(
				"taglib-empty-result-message-header-has-plus-btn");
		}

		EmptyOnClickRowChecker emptyOnClickRowChecker =
			new EmptyOnClickRowChecker(liferayPortletResponse);

		layoutsSearchContainer.setRowChecker(emptyOnClickRowChecker);

		int layoutsCount = LayoutLocalServiceUtil.getLayoutsCount(
			getSelGroup(), isPrivatePages());
		List<Layout> layouts = LayoutLocalServiceUtil.getLayouts(
			getSelGroupId(), isPrivatePages());

		layoutsSearchContainer.setTotal(layoutsCount);
		layoutsSearchContainer.setResults(layouts);

		_layoutsSearchContainer = layoutsSearchContainer;

		return _layoutsSearchContainer;
	}

	@Override
	public Group getLiveGroup() {
		return _groupDisplayContextHelper.getLiveGroup();
	}

	public Long getLiveGroupId() {
		return _groupDisplayContextHelper.getLiveGroupId();
	}

	public String getNavigation() {
		if (_navigation != null) {
			return _navigation;
		}

		String defaultNavigation = "public-pages";

		if (!isShowPublicPages()) {
			defaultNavigation = "private-pages";
		}

		_navigation = ParamUtil.getString(
			liferayPortletRequest, "navigation", defaultNavigation);

		return _navigation;
	}

	public String[] getNavigationKeys() {
		if (_navigationKeys != null) {
			return _navigationKeys;
		}

		_navigationKeys = new String[] {"public-pages", "private-pages"};

		if (!isShowPublicPages()) {
			_navigationKeys = new String[] {"private-pages"};
		}

		return _navigationKeys;
	}

	public String getOrderByCol() {
		if (Validator.isNotNull(_orderByCol)) {
			return _orderByCol;
		}

		_orderByCol = ParamUtil.getString(
			liferayPortletRequest, "orderByCol", "create-date");

		return _orderByCol;
	}

	public String getOrderByType() {
		if (Validator.isNotNull(_orderByType)) {
			return _orderByType;
		}

		_orderByType = ParamUtil.getString(
			liferayPortletRequest, "orderByType", "asc");

		return _orderByType;
	}

	public String[] getOrderColumns() {
		return new String[] {"create-date"};
	}

	public String getPagesName() {
		if (_pagesName != null) {
			return _pagesName;
		}

		Group liveGroup = getLiveGroup();

		if (liveGroup.isLayoutPrototype() || liveGroup.isLayoutSetPrototype() ||
			liveGroup.isUserGroup()) {

			_pagesName = "pages";
		}
		else if (isPrivateLayout()) {
			if (liveGroup.isUser()) {
				_pagesName = "my-dashboard";
			}
			else {
				_pagesName = "private-pages";
			}
		}
		else {
			if (liveGroup.isUser()) {
				_pagesName = "my-profile";
			}
			else {
				_pagesName = "public-pages";
			}
		}

		return _pagesName;
	}

	public String getPath(Layout layout, Locale locale) throws PortalException {
		List<Layout> layouts = layout.getAncestors();

		StringBundler sb = new StringBundler(layouts.size() * 4);

		for (Layout curLayout : layouts) {
			sb.append(curLayout.getName(locale));
			sb.append(StringPool.SPACE);
			sb.append(StringPool.GREATER_THAN);
			sb.append(StringPool.SPACE);
		}

		return sb.toString();
	}

	public PortletURL getPortletURL() {
		PortletURL portletURL = liferayPortletResponse.createRenderURL();

		portletURL.setParameter("mvcPath", "/view.jsp");
		portletURL.setParameter("navigation", getNavigation());
		portletURL.setParameter("orderByCol", getOrderByCol());
		portletURL.setParameter("orderByType", getOrderByType());

		return portletURL;
	}

	public String getRedirect() {
		if (_redirect != null) {
			return _redirect;
		}

		_redirect = ParamUtil.getString(liferayPortletRequest, "redirect");

		return _redirect;
	}

	public PortletURL getRedirectURL() {
		PortletURL portletURL = liferayPortletResponse.createRenderURL();

		portletURL.setParameter("mvcPath", "/view.jsp");
		portletURL.setParameter("redirect", getRedirect());
		portletURL.setParameter("groupId", String.valueOf(getSelGroupId()));

		return portletURL;
	}

	@Override
	public Group getSelGroup() {
		return _groupDisplayContextHelper.getSelGroup();
	}

	@Override
	public long getSelGroupId() {
		Group selGroup = getSelGroup();

		if (selGroup != null) {
			return selGroup.getGroupId();
		}

		return 0;
	}

	@Override
	public Group getStagingGroup() {
		return _groupDisplayContextHelper.getStagingGroup();
	}

	public Long getStagingGroupId() {
		return _groupDisplayContextHelper.getStagingGroupId();
	}

	public boolean isPrivatePages() {
		if (Objects.equals(getNavigation(), "private-pages")) {
			return true;
		}

		return false;
	}

	public boolean isPublicPages() {
		if (Objects.equals(getNavigation(), "public-pages")) {
			return true;
		}

		return false;
	}

	public boolean isShowPublicPages() {
		Group selGroup = getSelGroup();

		if (selGroup.isLayoutSetPrototype() || selGroup.isLayoutPrototype()) {
			return false;
		}

		return true;
	}

	public boolean showCopyApplicationsAction(Layout layout)
		throws PortalException {

		// Check if layout is incomplete

		LayoutRevision layoutRevision = LayoutStagingUtil.getLayoutRevision(
			layout);

		boolean incomplete = false;

		if (layoutRevision != null) {
			long layoutSetBranchId = layoutRevision.getLayoutSetBranchId();

			incomplete = StagingUtil.isIncomplete(layout, layoutSetBranchId);
		}

		if (incomplete) {
			return false;
		}

		// Check if layout is a layout prototype

		Group group = layout.getGroup();

		if (group.isLayoutPrototype()) {
			return false;
		}

		return LayoutPermissionUtil.contains(
			themeDisplay.getPermissionChecker(), layout, ActionKeys.UPDATE);
	}

	private String _displayStyle;
	private final GroupDisplayContextHelper _groupDisplayContextHelper;
	private List<LayoutDescription> _layoutDescriptions;
	private SearchContainer _layoutsSearchContainer;
	private String _navigation;
	private String[] _navigationKeys;
	private String _orderByCol;
	private String _orderByType;
	private String _pagesName;
	private String _redirect;

}