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

import ClayAlert from '@clayui/alert';
import ClayPanel from '@clayui/panel';
import ClayTable from '@clayui/table';
import React, {useEffect, useState} from 'react';

const getIntlNumberFormat = () =>
	new Intl.NumberFormat(Liferay.ThemeDisplay.getBCP47LanguageId(), {
		currency: 'USD',
		style: 'currency',
	});

const getBooleanValue = (value) => (value ? 'Yes' : 'No');

const BudgetBreakdownTable = ({actToBgts}) => {
	return (
		<div>
			{!!actToBgts.length && (
				<Table
					items={actToBgts.map((budget) => ({
						title: budget.expense.name,
						value: getIntlNumberFormat().format(budget.cost),
					}))}
					title="Budget Breakdown"
				/>
			)}
		</div>
	);
};

const LeadListTable = ({mdfRequestActivity}) => (
	<Table
		items={[
			{
				title: 'Is a lead list an outcome of this activity?',
				value: getBooleanValue(mdfRequestActivity.leadGenerated),
			},
			{
				title: 'Target # of Leads',
				value: mdfRequestActivity.targetOfLeads,
			},
			{
				title: 'Lead Follow Up strategy',
				value: mdfRequestActivity.leadFollowUpStrategies,
			},
			{
				title: 'Details on Lead Follow Up',
				value: mdfRequestActivity.detailsLeadFollowUp,
			},
		]}
		title="Lead List"
	/>
);

const getDigitalMarketFields = (mdfRequestActivity) => [
	{
		title: 'Overall message, content or CTA',
		value: mdfRequestActivity.overallMessageContentCTA,
	},
	{
		title: 'Specific sites to be used',
		value: mdfRequestActivity.specificSites,
	},
	{
		title: 'Keywords for PPC campaigns',
		value: mdfRequestActivity.keywordsForPPCCampaigns,
	},
	{
		title: 'Ad (any size/type)',
		value: mdfRequestActivity.ad,
	},
	{
		title: 'Do you require any assets from Liferay?',
		value: getBooleanValue(mdfRequestActivity.assetsLiferayRequired),
	},
	{
		title: 'How will the Liferay brand be used in the campaign?',
		value: mdfRequestActivity.howLiferayBrandUsed,
	},
];

const getContentMarketFields = (mdfRequestActivity) => [
	{
		title: 'Will this content be gated and have a landing page?',
		value: getBooleanValue(mdfRequestActivity.gatedLandingPage),
	},
	{
		title: 'Primary theme or message of your content',
		value: mdfRequestActivity.primaryThemeOrMessage,
	},

	{
		title: 'Goal of Content',
		value: mdfRequestActivity.goalOfContent,
	},
	{
		title:
			'Are you hiring an outside writer or agency to prepare the content?',
		value: getBooleanValue(mdfRequestActivity.hiringOutsideWriterOrAgency),
	},
];

const getEventFields = (mdfRequestActivity) => [
	{
		title: 'Activity Description',
		value: mdfRequestActivity.description,
	},
	{
		title: 'Venue Name',
		value: mdfRequestActivity.venueName,
	},
	{
		title: 'Liferay Branding',
		value: mdfRequestActivity.liferayBranding,
	},
	{
		title: 'Liferay Participation / Requirements',
		value: mdfRequestActivity.liferayParticipationRequirements,
	},
	{
		title: 'Source and Size of Invitee List',
		value: mdfRequestActivity.sourceAndSizeOfInviteeList,
	},
	{
		title: 'Activity Promotion',
		value: mdfRequestActivity.activityPromotion,
	},
];

const getMiscellaneousMarketingField = (mdfRequestActivity) => [
	{
		title: 'Marketing activity',
		value: mdfRequestActivity.marketingActivity,
	},
];

const TypeActivityKey = {
	CONTENT_MARKETING: 'prmtact003',
	DIGITAL_MARKETING: 'prmtact002',
	EVENT: 'prmtact001',
	MISCELLANEOUS_MARKETING: 'prmtact004',
};

const ActivityStatus = {
	ACTIVE: 'active',
	APPROVED: 'approved',
	CLAIMED: 'claimed',
	EXPIRED: 'expired',
	SUBMITTED: 'submitted',
	UNCLAIMED: 'unclaimed',
};

const activityStatusClassName = {
	[ActivityStatus.ACTIVE]: 'label label-tonal-info ml-2',
	[ActivityStatus.SUBMITTED]: 'label label-tonal-warning ml-2',
	[ActivityStatus.APPROVED]: 'label label-tonal-success ml-2',
	[ActivityStatus.EXPIRED]: 'label label-tonal-danger ml-2',
};

const activityClaimStatusClassName = {
	[ActivityStatus.CLAIMED]: 'ml-3 label label-tonal-info ml-2',
	[ActivityStatus.UNCLAIMED]: 'ml-3 label label-tonal-warning ml-2',
};

const CampaignActivityTable = ({mdfRequestActivity}) => {
	const fieldsByTypeActivity = {
		[TypeActivityKey.DIGITAL_MARKETING]: getDigitalMarketFields(
			mdfRequestActivity
		),
		[TypeActivityKey.CONTENT_MARKETING]: getContentMarketFields(
			mdfRequestActivity
		),
		[TypeActivityKey.EVENT]: getEventFields(mdfRequestActivity),
		[TypeActivityKey.MISCELLANEOUS_MARKETING]: getMiscellaneousMarketingField(
			mdfRequestActivity
		),
	};

	const options = {timeZone: 'UTC'};

	return (
		<Table
			items={[
				{
					title: 'Activity name',
					value: mdfRequestActivity.name,
				},
				{
					title: 'Type of Activity',
					value: mdfRequestActivity.typeActivity.name,
				},
				{
					title: 'Tactic',
					value: mdfRequestActivity.tactic.name,
				},
				...fieldsByTypeActivity[mdfRequestActivity.typeActivity.key],
				{
					title: 'Start Date',
					value: new Date(
						mdfRequestActivity.startDate
					).toLocaleDateString(
						Liferay.ThemeDisplay.getBCP47LanguageId(),
						options
					),
				},
				{
					title: 'End Date',
					value: new Date(
						mdfRequestActivity.endDate
					).toLocaleDateString(
						Liferay.ThemeDisplay.getBCP47LanguageId(),
						options
					),
				},
			]}
			title="Campaign Activity"
		/>
	);
};

const Table = ({items, title}) => (
	<ClayTable className="bg-brand-primary-lighten-6 border-0 table-striped">
		<ClayTable.Head>
			<ClayTable.Row>
				<ClayTable.Cell
					className="border-neutral-2 border-top rounded-0 w-50"
					expanded
					headingCell
				>
					<p className="mt-4 text-neutral-10">{title}</p>
				</ClayTable.Cell>

				<ClayTable.Cell className="border-neutral-2 border-top rounded-0 w-50"></ClayTable.Cell>
			</ClayTable.Row>
		</ClayTable.Head>

		<ClayTable.Body>
			{items.map((item, index) => (
				<ClayTable.Row key={index}>
					<ClayTable.Cell className="border-0 w-50">
						<p className="text-neutral-10">{item.title}</p>
					</ClayTable.Cell>

					<ClayTable.Cell className="border-0 w-50">
						<p className="text-neutral-10">{item.value}</p>
					</ClayTable.Cell>
				</ClayTable.Row>
			))}
		</ClayTable.Body>
	</ClayTable>
);

const DATE_FORMAT_OPTION = {
	day: 'numeric',
	month: 'short',
	timeZone: 'UTC',
};

const RangeDate = ({endDate, startDate}) => (
	<div className="mb-1 text-neutral-7 text-paragraph-sm">
		{new Date(startDate).toLocaleString(
			Liferay.ThemeDisplay.getBCP47LanguageId(),
			DATE_FORMAT_OPTION
		)}
		&nbsp; - &nbsp;
		{new Date(endDate).toLocaleString(
			Liferay.ThemeDisplay.getBCP47LanguageId(),
			DATE_FORMAT_OPTION
		)}
		, &nbsp;
		{new Date(endDate).getFullYear()}
	</div>
);

const Panel = ({children, mdfRequestActivity}) => {
	const activityClaimStatus = mdfRequestActivity.actToMDFClmActs
		.map((mdfClaimActivity) => {
			return (
				mdfClaimActivity.r_mdfClmToMDFClmActs_c_mdfClaim.mdfClaimStatus
					.key !== 'draft'
			);
		})
		.includes(true);

	const daysLeftToClaim = Math.ceil(
		(new Date(mdfRequestActivity.endDate) - new Date()) / (1000 * 3600 * 24)
	);

	return (
		<ClayPanel
			className="border-brand-primary-lighten-4"
			collapsable
			displayTitle={
				<ClayPanel.Title className="py-2 text-dark">
					<RangeDate
						endDate={mdfRequestActivity.endDate}
						startDate={mdfRequestActivity.startDate}
					/>

					<h4 className="mb-1">
						{mdfRequestActivity.name} ({mdfRequestActivity.id})
					</h4>

					<div className="align-items-center d-sm-flex mb-1 text-neutral-7 text-weight-semi-bold">
						<p className="mb-0">
							Claim Status:
							<div
								className={
									activityClaimStatusClassName[
										activityClaimStatus
											? 'claimed'
											: 'unclaimed'
									]
								}
							>
								{activityClaimStatus ? 'Claimed' : 'Unclaimed'}
							</div>
						</p>
					</div>

					<div className="align-items-center d-sm-flex mb-1 text-neutral-7 text-weight-semi-bold">
						<p className="mb-0">
							Request Status:
							<div
								className={
									activityStatusClassName[
										mdfRequestActivity.activityStatus.key
									]
								}
							>
								{mdfRequestActivity.activityStatus.name}
							</div>
						</p>

						<div className="font-weight-light ml-sm-2">
							{daysLeftToClaim > 0 &&
								`${daysLeftToClaim} days left to claim`}
						</div>
					</div>
				</ClayPanel.Title>
			}
			showCollapseIcon
			spritemap
		>
			<ClayPanel.Body>{children}</ClayPanel.Body>
		</ClayPanel>
	);
};

export default function () {
	const [activities, setActivities] = useState();

	const [loading, setLoading] = useState(true);

	const findRequestIdUrl = (paramsUrl) => {
		const splitParamsUrl = paramsUrl.split('?');

		return splitParamsUrl[0];
	};

	const currentPath = Liferay.currentURL.split('/');
	const mdfRequestId = findRequestIdUrl(currentPath.at(-1));

	useEffect(() => {
		const getActivities = async () => {
			// eslint-disable-next-line @liferay/portal/no-global-fetch
			const response = await fetch(
				`/o/c/mdfrequests/${mdfRequestId}/mdfReqToActs?nestedFields=actToBgts,actToMDFClmActs,r_mdfClmToMDFClmActs_c_mdfClaimId&nestedFieldsDepth=3`,
				{
					headers: {
						'accept': 'application/json',
						'x-csrf-token': Liferay.authToken,
					},
				}
			);

			if (response.ok) {
				setActivities(await response.json());

				setLoading(false);

				return;
			}

			Liferay.Util.openToast({
				message: 'An unexpected error occured.',
				type: 'danger',
			});
		};

		if (!isNaN(mdfRequestId)) {
			getActivities();
		}
		// eslint-disable-next-line react-hooks/exhaustive-deps
	}, []);

	if (loading) {
		return <>Loading...</>;
	}

	return (
		<div>
			{!activities?.items.length ? (
				<ClayAlert displayType="info" title="Info:">
					No entries were found
				</ClayAlert>
			) : (
				activities?.items.map((mdfRequestActivity, index) => (
					<Panel key={index} mdfRequestActivity={mdfRequestActivity}>
						<CampaignActivityTable
							mdfRequestActivity={mdfRequestActivity}
						/>

						<BudgetBreakdownTable
							actToBgts={mdfRequestActivity.actToBgts}
						/>

						<LeadListTable
							mdfRequestActivity={mdfRequestActivity}
						/>
					</Panel>
				))
			)}
		</div>
	);
}
