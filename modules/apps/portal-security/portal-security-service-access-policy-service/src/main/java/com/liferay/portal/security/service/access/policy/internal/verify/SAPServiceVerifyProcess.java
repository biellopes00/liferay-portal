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

package com.liferay.portal.security.service.access.policy.internal.verify;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.util.LoggingTimer;
import com.liferay.portal.security.service.access.policy.service.SAPEntryLocalService;
import com.liferay.portal.verify.VerifyProcess;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author     Mika Koivisto
 * @deprecated As of Mueller (7.2.x), with no direct replacement
 */
@Component(
	property = "initial.deployment=true",
	service = {SAPServiceVerifyProcess.class, VerifyProcess.class}
)
@Deprecated
public class SAPServiceVerifyProcess extends VerifyProcess {

	public void verifyDefaultSAPEntry() {
		try (LoggingTimer loggingTimer = new LoggingTimer()) {
			_companyLocalService.forEachCompanyId(
				companyId -> {
					try {
						_sapEntryLocalService.checkSystemSAPEntries(companyId);
					}
					catch (PortalException portalException) {
						_log.error(
							"Unable to add default service access policy for " +
								"company " + companyId,
							portalException);
					}
				});
		}
	}

	@Override
	protected void doVerify() throws Exception {
		verifyDefaultSAPEntry();
	}

	private static final Log _log = LogFactoryUtil.getLog(
		SAPServiceVerifyProcess.class);

	@Reference
	private CompanyLocalService _companyLocalService;

	@Reference
	private SAPEntryLocalService _sapEntryLocalService;

}