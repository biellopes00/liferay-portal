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

import {axios} from '../../../common/services/liferay/api';
import {Liferay} from '../../../common/utils/liferay';

export function getTaxonomyCategories(id, taxonomyCategoryName) {
	const taxonomyCategories = axios.get(
		`/o/headless-admin-taxonomy/v1.0/taxonomy-vocabularies/${id}/taxonomy-categories?search='${taxonomyCategoryName}'`
	);

	return taxonomyCategories;
}

export function getTaxonomyVocabularies() {
	const taxonomyVocabularyName = 'Raylife Industry Type';

	const taxonomyVocabularies = axios.get(
		`o/headless-admin-taxonomy/v1.0/sites/${Liferay.ThemeDisplay.getCompanyGroupId()}/taxonomy-vocabularies?filter=name eq '${taxonomyVocabularyName}'`
	);

	return taxonomyVocabularies;
}
