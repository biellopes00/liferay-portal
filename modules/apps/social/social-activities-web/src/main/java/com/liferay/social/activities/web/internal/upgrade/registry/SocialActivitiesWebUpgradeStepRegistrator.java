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

package com.liferay.social.activities.web.internal.upgrade.registry;

import com.liferay.portal.kernel.upgrade.BaseReplacePortletId;
import com.liferay.portal.kernel.upgrade.UpgradeStep;
import com.liferay.portal.upgrade.registry.UpgradeStepRegistrator;
import com.liferay.social.activities.constants.SocialActivitiesPortletKeys;

import org.osgi.service.component.annotations.Component;

/**
 * @author Raymond Augé
 * @author Peter Fellwock
 */
@Component(service = UpgradeStepRegistrator.class)
public class SocialActivitiesWebUpgradeStepRegistrator
	implements UpgradeStepRegistrator {

	@Override
	public void register(Registry registry) {
		registry.registerInitialization();

		UpgradeStep upgradePortletId = new BaseReplacePortletId() {

			@Override
			protected String[][] getRenamePortletIdsArray() {
				return new String[][] {
					{
						"1_WAR_soportlet",
						SocialActivitiesPortletKeys.SOCIAL_ACTIVITIES
					},
					{"116", SocialActivitiesPortletKeys.SOCIAL_ACTIVITIES}
				};
			}

		};

		registry.register("0.0.1", "1.0.0", upgradePortletId);
	}

}