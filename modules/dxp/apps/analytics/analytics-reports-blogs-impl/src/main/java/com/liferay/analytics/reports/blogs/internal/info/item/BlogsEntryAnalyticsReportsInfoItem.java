/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.analytics.reports.blogs.internal.info.item;

import com.liferay.analytics.reports.info.item.AnalyticsReportsInfoItem;
import com.liferay.analytics.reports.layout.display.page.info.item.LayoutDisplayPageObjectProviderAnalyticsReportsInfoItem;
import com.liferay.blogs.model.BlogsEntry;
import com.liferay.info.field.InfoFieldValue;
import com.liferay.info.item.InfoItemFieldValues;
import com.liferay.info.item.InfoItemReference;
import com.liferay.info.item.InfoItemServiceRegistry;
import com.liferay.info.item.provider.InfoItemFieldValuesProvider;
import com.liferay.info.type.WebImage;
import com.liferay.layout.display.page.LayoutDisplayPageObjectProvider;
import com.liferay.layout.display.page.LayoutDisplayPageProvider;
import com.liferay.layout.display.page.LayoutDisplayPageProviderRegistry;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.Portal;

import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Cristiona González
 */
@Component(service = AnalyticsReportsInfoItem.class)
public class BlogsEntryAnalyticsReportsInfoItem
	implements AnalyticsReportsInfoItem<BlogsEntry> {

	@Override
	public String getAuthorName(BlogsEntry blogsEntry) {
		User user = _userLocalService.fetchUser(blogsEntry.getUserId());

		if (user == null) {
			return StringPool.BLANK;
		}

		return user.getFullName();
	}

	@Override
	public long getAuthorUserId(BlogsEntry blogsEntry) {
		User user = _userLocalService.fetchUser(blogsEntry.getUserId());

		if (user == null) {
			return 0L;
		}

		return user.getUserId();
	}

	@Override
	public WebImage getAuthorWebImage(BlogsEntry blogsEntry, Locale locale) {
		InfoItemFieldValuesProvider<Object> infoItemFieldValuesProvider =
			_infoItemServiceRegistry.getFirstInfoItemService(
				InfoItemFieldValuesProvider.class, BlogsEntry.class.getName());

		InfoItemFieldValues infoItemFieldValues =
			infoItemFieldValuesProvider.getInfoItemFieldValues(blogsEntry);

		InfoFieldValue<Object> authorProfileImageInfoFieldValue =
			infoItemFieldValues.getInfoFieldValue("authorProfileImage");

		return (WebImage)authorProfileImageInfoFieldValue.getValue(locale);
	}

	@Override
	public List<Locale> getAvailableLocales(BlogsEntry blogsEntry) {
		return _layoutDisplayPageObjectProviderAnalyticsReportsInfoItem.
			getAvailableLocales(
				_getLayoutDisplayPageObjectProvider(blogsEntry));
	}

	@Override
	public String getCanonicalURL(BlogsEntry blogsEntry, Locale locale) {
		return _layoutDisplayPageObjectProviderAnalyticsReportsInfoItem.
			getCanonicalURL(
				_getLayoutDisplayPageObjectProvider(blogsEntry), locale);
	}

	@Override
	public Locale getDefaultLocale(BlogsEntry blogsEntry) {
		return _layoutDisplayPageObjectProviderAnalyticsReportsInfoItem.
			getDefaultLocale(_getLayoutDisplayPageObjectProvider(blogsEntry));
	}

	@Override
	public Date getPublishDate(BlogsEntry blogsEntry) {
		return _layoutDisplayPageObjectProviderAnalyticsReportsInfoItem.
			getPublishDate(_getLayoutDisplayPageObjectProvider(blogsEntry));
	}

	@Override
	public String getTitle(BlogsEntry blogsEntry, Locale locale) {
		return _layoutDisplayPageObjectProviderAnalyticsReportsInfoItem.
			getTitle(_getLayoutDisplayPageObjectProvider(blogsEntry), locale);
	}

	@Override
	public boolean isShow(BlogsEntry blogsEntry) {
		return _layoutDisplayPageObjectProviderAnalyticsReportsInfoItem.isShow(
			_getLayoutDisplayPageObjectProvider(blogsEntry));
	}

	private LayoutDisplayPageObjectProvider<BlogsEntry>
		_getLayoutDisplayPageObjectProvider(BlogsEntry blogsEntry) {

		LayoutDisplayPageProvider<?> layoutDisplayPageProvider =
			_layoutDisplayPageProviderRegistry.
				getLayoutDisplayPageProviderByClassName(
					BlogsEntry.class.getName());

		if (layoutDisplayPageProvider == null) {
			return null;
		}

		return (LayoutDisplayPageObjectProvider<BlogsEntry>)
			layoutDisplayPageProvider.getLayoutDisplayPageObjectProvider(
				new InfoItemReference(
					BlogsEntry.class.getName(), blogsEntry.getEntryId()));
	}

	@Reference
	private InfoItemServiceRegistry _infoItemServiceRegistry;

	@Reference
	private LayoutDisplayPageObjectProviderAnalyticsReportsInfoItem
		_layoutDisplayPageObjectProviderAnalyticsReportsInfoItem;

	@Reference
	private LayoutDisplayPageProviderRegistry
		_layoutDisplayPageProviderRegistry;

	@Reference
	private Portal _portal;

	@Reference
	private UserLocalService _userLocalService;

}