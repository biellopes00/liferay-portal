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

import {ClayButtonWithIcon} from '@clayui/button';
import ClayLayout from '@clayui/layout';
import ClayLink from '@clayui/link';
import React, {useContext, useEffect, useState} from 'react';

import lang from '../../../../../util/lang';
import {DiagramBuilderContext} from '../../../../DiagramBuilderContext';
import {options} from './SelectAssignment';
import {getAssignmentType} from './utils';

const CurrentAssignments = ({assignments, setContentName}) => {
	const {setSelectedItem} = useContext(DiagramBuilderContext);
	const assignmentType = getAssignmentType(assignments);

	const [assignmentsDetails, setAssignmentsDetails] = useState(null);

	const deleteCurrentAssignments = () => {
		setSelectedItem((previousValue) => ({
			...previousValue,
			data: {
				...previousValue.data,
				assignments: null,
			},
		}));
	};

	const optionFilter = (option) => option.assignmentType === assignmentType;

	useEffect(() => {
		if (assignmentType === 'resourceActions') {
			const resourceActionsArray = assignments.resourceAction.split(' ');

			setAssignmentsDetails({
				assignmentsCount: resourceActionsArray.length,
				firstName: resourceActionsArray[0],
			});
		}
		if (assignmentType === 'roleId') {
			setAssignmentsDetails({
				assignmentsCount: 1,
				firstName: assignments.sectionsData.name,
			});
		}
		if (assignmentType === 'user') {
			setAssignmentsDetails({
				assignmentsCount: assignments.sectionsData.length,
				firstName: assignments.sectionsData
					.sort((firstElement, secondElement) => {
						if (firstElement.name < secondElement.name) {
							return -1;
						}
						if (firstElement.name > secondElement.name) {
							return 1;
						}

						return 0;
					})[0]
					.name.split(' ')[0],
			});
		}
	}, [assignmentType, assignments]);

	const getAssignmentsDetails = () => {
		if (assignmentType === 'assetCreator') {
			return [''];
		}
		else if (assignmentsDetails) {
			const result = [': ' + assignmentsDetails.firstName || ''];

			if (assignmentsDetails.assignmentsCount !== 1) {
				result.push(
					' ' +
						lang.sub(Liferay.Language.get('and-x-more'), [
							assignmentsDetails.assignmentsCount - 1,
						])
				);
			}

			return result;
		}
		else {
			return [`: ${assignments[Object.keys(assignments)[1]]}`];
		}
	};

	return (
		<ClayLayout.ContentCol className="current-assignments-area" float>
			<ClayLayout.Row
				className="current-assignments-row"
				justify="between"
			>
				<ClayLink
					button={false}
					className="truncate-container"
					displayType="secondary"
					href="#"
					onClick={() => setContentName('assignments')}
				>
					{options.find(optionFilter)?.label}

					{getAssignmentsDetails().map((content, index) => (
						<span key={index}>{content}</span>
					))}
				</ClayLink>

				<ClayButtonWithIcon
					className="delete-button text-secondary trash-button"
					displayType="unstyled"
					onClick={deleteCurrentAssignments}
					symbol="trash"
				/>
			</ClayLayout.Row>
		</ClayLayout.ContentCol>
	);
};

export default CurrentAssignments;
