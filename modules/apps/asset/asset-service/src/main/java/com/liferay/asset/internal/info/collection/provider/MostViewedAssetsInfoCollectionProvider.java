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

package com.liferay.asset.internal.info.collection.provider;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryService;
import com.liferay.asset.kernel.service.persistence.AssetEntryQuery;
import com.liferay.info.collection.provider.CollectionQuery;
import com.liferay.info.collection.provider.InfoCollectionProvider;
import com.liferay.info.pagination.InfoPage;
import com.liferay.info.sort.Sort;
import com.liferay.portal.kernel.language.Language;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;

import java.util.Collections;
import java.util.Locale;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Pavel Savinov
 */
@Component(service = InfoCollectionProvider.class)
public class MostViewedAssetsInfoCollectionProvider
	extends BaseAssetsInfoCollectionProvider
	implements InfoCollectionProvider<AssetEntry> {

	@Override
	public InfoPage<AssetEntry> getCollectionInfoPage(
		CollectionQuery collectionQuery) {

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		AssetEntryQuery assetEntryQuery = getAssetEntryQuery(
			serviceContext.getCompanyId(), serviceContext.getScopeGroupId(),
			collectionQuery.getPagination(), new Sort("viewCount", true),
			new Sort("title", true));

		try {
			return InfoPage.of(
				_assetEntryService.getEntries(assetEntryQuery),
				collectionQuery.getPagination(),
				_assetEntryService.getEntriesCount(assetEntryQuery));
		}
		catch (Exception exception) {
			_log.error("Unable to get asset entries", exception);
		}

		return InfoPage.of(
			Collections.emptyList(), collectionQuery.getPagination(), 0);
	}

	@Override
	public String getLabel(Locale locale) {
		return _language.get(locale, "most-viewed-assets");
	}

	private static final Log _log = LogFactoryUtil.getLog(
		MostViewedAssetsInfoCollectionProvider.class);

	@Reference
	private AssetEntryService _assetEntryService;

	@Reference
	private Language _language;

}