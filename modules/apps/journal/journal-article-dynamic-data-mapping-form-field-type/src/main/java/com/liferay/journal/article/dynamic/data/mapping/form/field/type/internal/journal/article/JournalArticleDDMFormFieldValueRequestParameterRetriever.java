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

package com.liferay.journal.article.dynamic.data.mapping.form.field.type.internal.journal.article;

import com.liferay.dynamic.data.mapping.form.field.type.DDMFormFieldValueRequestParameterRetriever;
import com.liferay.journal.article.dynamic.data.mapping.form.field.type.constants.JournalArticleDDMFormFieldTypeConstants;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;

/**
 * @author Rodrigo Paulino
 */
@Component(
	property = "ddm.form.field.type.name=" + JournalArticleDDMFormFieldTypeConstants.JOURNAL_ARTICLE,
	service = DDMFormFieldValueRequestParameterRetriever.class
)
public class JournalArticleDDMFormFieldValueRequestParameterRetriever
	implements DDMFormFieldValueRequestParameterRetriever {

	@Override
	public String get(
		HttpServletRequest httpServletRequest, String ddmFormFieldParameterName,
		String defaultDDMFormFieldParameterValue) {

		String parameterValue = httpServletRequest.getParameter(
			ddmFormFieldParameterName);

		if (!Validator.isBlank(parameterValue)) {
			parameterValue = String.valueOf(
				getJSONObject(_log, parameterValue));
		}

		if (parameterValue != null) {
			return parameterValue;
		}

		return defaultDDMFormFieldParameterValue;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		JournalArticleDDMFormFieldValueRequestParameterRetriever.class);

}