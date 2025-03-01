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

package com.liferay.portal.search.internal.spi.model.index.contributor;

import com.liferay.portal.kernel.model.AttachedModel;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.DocumentContributor;
import com.liferay.portal.kernel.search.DocumentHelper;

import org.osgi.service.component.annotations.Component;

/**
 * @author Michael C. Han
 */
@Component(service = DocumentContributor.class)
public class AttachedModelDocumentContributor
	implements DocumentContributor<AttachedModel> {

	@Override
	public void contribute(
		Document document, BaseModel<AttachedModel> baseModel) {

		if (!(baseModel instanceof AttachedModel)) {
			return;
		}

		DocumentHelper documentHelper = new DocumentHelper(document);

		AttachedModel attachedModel = (AttachedModel)baseModel;

		documentHelper.setAttachmentOwnerKey(
			attachedModel.getClassNameId(), attachedModel.getClassPK());
	}

}