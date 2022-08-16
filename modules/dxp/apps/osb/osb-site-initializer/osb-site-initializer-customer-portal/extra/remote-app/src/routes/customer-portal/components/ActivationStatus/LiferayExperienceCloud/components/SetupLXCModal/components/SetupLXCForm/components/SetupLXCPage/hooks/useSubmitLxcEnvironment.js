/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 */

import {useMutation} from '@apollo/client';
import {useAppPropertiesContext} from '../../../../../../../../../../../../common/contexts/AppPropertiesContext';
import {
	createAdminsLiferayExperienceCloud,
	createLiferayExperienceCloudEnvironments,
} from '../../../../../../../../../../../../common/services/liferay/graphql/liferay-experience-cloud-environments/';
import {
	getLiferayExperienceCloudEnvironments,
	updateAccountSubscriptionGroups,
} from '../../../../../../../../../../../../common/services/liferay/graphql/queries';
import {STATUS_TAG_TYPE_NAMES} from '../../../../../../../../../../utils/constants';

export default function useSubmitLxcEnvironment(
	handleChangeForm,
	project,
	setFormAlreadySubmitted,
	subscriptionGroupLxcId,
	values
) {
	const {client} = useAppPropertiesContext();

	const [createLiferayExperienceCloudEnvironment] = useMutation(
		createLiferayExperienceCloudEnvironments
	);
	const [updateAccountSubscriptionGroupsInfo] = useMutation(
		updateAccountSubscriptionGroups
	);
	const [createAdminLiferayExperienceCloud] = useMutation(
		createAdminsLiferayExperienceCloud
	);

	const handleSubmitLxcEnvironment = async () => {
		const lxcActivationFields = values?.lxc;

		const liferayExperienceCloudStatus = async () => {
			const {data} = await client.query({
				query: getLiferayExperienceCloudEnvironments,
				variables: {
					filter: `accountKey eq '${project.accountKey}'`,
				},
			});
			if (data) {
				const status = !!data?.c?.liferayExperienceCloudEnvironments
					?.items?.length;

				return status;
			}

			return false;
		};

		const alreadySubmitted = await liferayExperienceCloudStatus();

		if (alreadySubmitted) {
			setFormAlreadySubmitted(true);
		}

		if (!alreadySubmitted) {
			const {data} = await createLiferayExperienceCloudEnvironment({
				variables: {
					LiferayExperienceCloudEnvironment: {
						accountKey: project.accountKey,
						incidentManagementEmailAddress:
							lxcActivationFields.incidentManagementEmail,
						incidentManagementFullName:
							lxcActivationFields.incidentManagementfullName,
						primaryRegion: lxcActivationFields.primaryRegion,
						projectId: lxcActivationFields.projectId,
					},
				},
			});

			if (data) {
				const liferayExperienceCloudEnvironmentId =
					data.c?.createLiferayExperienceCloudEnvironment
						?.liferayExperienceCloudEnvironmentId;

				await updateAccountSubscriptionGroupsInfo({
					variables: {
						accountSubscriptionGroup: {
							accountKey: project.accountKey,
							activationStatus: STATUS_TAG_TYPE_NAMES.inProgress,
						},
						id: subscriptionGroupLxcId,
					},
				});

				await Promise.all(
					lxcActivationFields?.admins?.map(
						({email, fullName, github}) => {
							return createAdminLiferayExperienceCloud({
								variables: {
									AdminLiferayExperienceCloud: {
										emailAddress: email,
										fullName,
										githubUsername: github,
										liferayExperienceCloudEnvironmentId,
									},
								},
							});
						}
					)
				);
			}

			handleChangeForm(true);
		}
	};

	return handleSubmitLxcEnvironment;
}
