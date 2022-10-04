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

package com.liferay.account.service;

import com.liferay.account.model.AccountEntryUserRel;
import com.liferay.portal.kernel.exception.PortalException;

/**
 * Provides the remote service utility for AccountEntryUserRel. This utility wraps
 * <code>com.liferay.account.service.impl.AccountEntryUserRelServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see AccountEntryUserRelService
 * @generated
 */
public class AccountEntryUserRelServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.account.service.impl.AccountEntryUserRelServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static AccountEntryUserRel addAccountEntryUserRel(
			long accountEntryId, long creatorUserId, String screenName,
			String emailAddress, java.util.Locale locale, String firstName,
			String middleName, String lastName, long prefixListTypeId,
			long suffixListTypeId, String jobTitle,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().addAccountEntryUserRel(
			accountEntryId, creatorUserId, screenName, emailAddress, locale,
			firstName, middleName, lastName, prefixListTypeId, suffixListTypeId,
			jobTitle, serviceContext);
	}

	public static AccountEntryUserRel addAccountEntryUserRelByEmailAddress(
			long accountEntryId, String emailAddress, long[] accountRoleIds,
			String userExternalReferenceCode,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().addAccountEntryUserRelByEmailAddress(
			accountEntryId, emailAddress, accountRoleIds,
			userExternalReferenceCode, serviceContext);
	}

	public static void addAccountEntryUserRels(
			long accountEntryId, long[] accountUserIds)
		throws PortalException {

		getService().addAccountEntryUserRels(accountEntryId, accountUserIds);
	}

	public static AccountEntryUserRel addPersonTypeAccountEntryUserRel(
			long accountEntryId, long creatorUserId, String screenName,
			String emailAddress, java.util.Locale locale, String firstName,
			String middleName, String lastName, long prefixListTypeId,
			long suffixListTypeId, String jobTitle,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().addPersonTypeAccountEntryUserRel(
			accountEntryId, creatorUserId, screenName, emailAddress, locale,
			firstName, middleName, lastName, prefixListTypeId, suffixListTypeId,
			jobTitle, serviceContext);
	}

	public static void deleteAccountEntryUserRelByEmailAddress(
			long accountEntryId, String emailAddress)
		throws PortalException {

		getService().deleteAccountEntryUserRelByEmailAddress(
			accountEntryId, emailAddress);
	}

	public static void deleteAccountEntryUserRels(
			long accountEntryId, long[] accountUserIds)
		throws PortalException {

		getService().deleteAccountEntryUserRels(accountEntryId, accountUserIds);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	 * Invites users to the account by email address. If the email address is
	 * associated with a registered user, the user will be added to the account
	 * immediately. Otherwise, an email will be sent to the user to sign up.
	 * The user will be added to the account upon registration.
	 *
	 * @param accountEntryId the primary key of the account
	 * @param accountRoleIds the primary keys of the account roles that will be
	 assigned to the user
	 * @param emailAddress the invited user's email address
	 * @param inviter the user that makes the invitation request
	 * @param serviceContext the service context to be applied. Can set the
	 request used to send the invitation email
	 */
	public static void inviteUser(
			long accountEntryId, long[] accountRoleIds, String emailAddress,
			com.liferay.portal.kernel.model.User inviter,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		getService().inviteUser(
			accountEntryId, accountRoleIds, emailAddress, inviter,
			serviceContext);
	}

	public static void setPersonTypeAccountEntryUser(
			long accountEntryId, long userId)
		throws PortalException {

		getService().setPersonTypeAccountEntryUser(accountEntryId, userId);
	}

	public static AccountEntryUserRelService getService() {
		return _service;
	}

	private static volatile AccountEntryUserRelService _service;

}