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

import updateColSize from '../actions/updateColSize';
import LayoutService from '../services/LayoutService';

export default function resizeColumns({layoutData, rowItemId}) {
	return (dispatch, getState) => {
		return LayoutService.updateLayoutData({
			layoutData,
			onNetworkStatus: dispatch,
			segmentsExperienceId: getState().segmentsExperienceId,
		}).then(() => {
			dispatch(updateColSize({layoutData, rowItemId}));
		});
	};
}
