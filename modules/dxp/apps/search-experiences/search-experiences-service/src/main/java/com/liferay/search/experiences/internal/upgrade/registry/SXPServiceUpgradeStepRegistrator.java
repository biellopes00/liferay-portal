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

package com.liferay.search.experiences.internal.upgrade.registry;

import com.liferay.portal.kernel.upgrade.BaseExternalReferenceCodeUpgradeProcess;
import com.liferay.portal.kernel.upgrade.UpgradeProcessFactory;
import com.liferay.portal.upgrade.registry.UpgradeStepRegistrator;

import org.osgi.service.component.annotations.Component;

/**
 * @author Petteri Karttunen
 */
@Component(enabled = true, service = UpgradeStepRegistrator.class)
public class SXPServiceUpgradeStepRegistrator
	implements UpgradeStepRegistrator {

	@Override
	public void register(Registry registry) {
		registry.register(
			"1.0.0", "1.1.0",
			UpgradeProcessFactory.addColumns(
				"SXPElement", "key_ VARCHAR(75) null",
				"version VARCHAR(75) null"),
			UpgradeProcessFactory.addColumns(
				"SXPBlueprint", "key_ VARCHAR(75) null",
				"version VARCHAR(75) null"));

		registry.register(
			"1.1.0", "1.2.0",
			new BaseExternalReferenceCodeUpgradeProcess() {

				@Override
				protected String[][] getTableAndPrimaryKeyColumnNames() {
					return new String[][] {
						{"SXPBlueprint", "sxpBlueprintId"},
						{"SXPElement", "sxpElementId"}
					};
				}

			});

		registry.register(
			"1.2.0", "1.3.0",
			new com.liferay.search.experiences.internal.upgrade.v1_3_0.
				SXPBlueprintUpgradeProcess());

		registry.register(
			"1.3.0", "1.3.1",
			new com.liferay.search.experiences.internal.upgrade.v1_3_1.
				SXPBlueprintUpgradeProcess());

		registry.register(
			"1.3.1", "1.3.2",
			new com.liferay.search.experiences.internal.upgrade.v1_3_2.
				SXPBlueprintUpgradeProcess());
	}

}