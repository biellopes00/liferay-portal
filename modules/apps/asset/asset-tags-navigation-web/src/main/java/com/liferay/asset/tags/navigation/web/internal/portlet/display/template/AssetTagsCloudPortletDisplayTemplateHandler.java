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

package com.liferay.asset.tags.navigation.web.internal.portlet.display.template;

import com.liferay.asset.kernel.model.AssetTag;
import com.liferay.asset.tags.navigation.constants.AssetTagsNavigationPortletKeys;
import com.liferay.portal.kernel.language.Language;
import com.liferay.portal.kernel.template.TemplateHandler;

import java.util.Locale;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jürgen Kappler
 */
@Component(
	property = "javax.portlet.name=" + AssetTagsNavigationPortletKeys.ASSET_TAGS_CLOUD,
	service = TemplateHandler.class
)
public class AssetTagsCloudPortletDisplayTemplateHandler
	extends AssetTagsNavigationPortletDisplayTemplateHandler {

	@Override
	public String getClassName() {
		return AssetTag.class.getName();
	}

	@Override
	public String getName(Locale locale) {
		return _language.format(
			locale, "x-template",
			portal.getPortletTitle(
				AssetTagsNavigationPortletKeys.ASSET_TAGS_CLOUD, locale),
			false);
	}

	@Override
	public String getResourceName() {
		return AssetTagsNavigationPortletKeys.ASSET_TAGS_CLOUD;
	}

	@Reference
	private Language _language;

}