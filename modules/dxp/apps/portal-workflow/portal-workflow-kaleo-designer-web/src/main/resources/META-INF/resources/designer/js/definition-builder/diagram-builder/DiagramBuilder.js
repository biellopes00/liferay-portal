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

import React, {
	useCallback,
	useContext,
	useEffect,
	useRef,
	useState,
} from 'react';
import ReactFlow, {
	Background,
	Controls,
	addEdge,
	isEdge,
} from 'react-flow-renderer';
import {v4 as uuidv4} from 'uuid';

import {DefinitionBuilderContext} from '../DefinitionBuilderContext';
import {defaultLanguageId} from '../constants';
import DeserializeUtil from '../source-builder/deserializeUtil';
import {singleEventObserver} from '../util/EventObserver';
import {retrieveDefinitionRequest} from '../util/fetchUtil';
import {DiagramBuilderContextProvider} from './DiagramBuilderContext';
import {nodeTypes} from './components/nodes/utils';
import Sidebar from './components/sidebar/Sidebar';
import {isIdDuplicated} from './components/sidebar/utils';
import edgeTypes from './components/transitions/Edge';
import FloatingConnectionLine from './components/transitions/FloatingConnectionLine';
import getCollidingElements from './util/collisionDetection';
import populateAssignmentsData from './util/populateAssignmentsData';
import populateNotificationsData from './util/populateNotificationsData';

const deserializeUtil = new DeserializeUtil();

export default function DiagramBuilder() {
	const {
		accountEntryId,
		currentEditor,
		definitionId,
		deserialize,
		elements,
		functionActionExecutors,
		selectedLanguageId,
		setActive,
		setBlockingErrors,
		setDefinitionDescription,
		setDefinitionInfo,
		setDefinitionName,
		setDeserialize,
		setElements,
		setShowDefinitionInfo,
		statuses,
		version,
	} = useContext(DefinitionBuilderContext);
	const reactFlowWrapperRef = useRef(null);
	const [collidingElements, setCollidingElements] = useState(null);
	const [elementRectangle, setElementRectangle] = useState(null);
	const [reactFlowInstance, setReactFlowInstance] = useState(null);
	const [selectedItem, setSelectedItem] = useState(null);
	const [selectedItemNewId, setSelectedItemNewId] = useState(null);
	const [defaultPosition, setDefaultPosition] = useState(null);

	const onConnect = (params) => {
		if (
			elements.filter(
				(element) =>
					isEdge(element) &&
					element.source === params.source &&
					element.target === params.target
			).length
		) {
			return;
		}

		const defaultEdge = !elements.filter(
			(element) =>
				isEdge(element) &&
				element.source === params.source &&
				element.data.defaultEdge
		).length;

		const newEdge = {
			...params,
			arrowHeadType: 'arrowclosed',
			data: {
				defaultEdge,
				label: {
					[defaultLanguageId]: Liferay.Language.get(
						'transition-label'
					),
				},
			},
			id: uuidv4(),
			type: 'transition',
		};

		setElements((previousElements) => addEdge(newEdge, previousElements));
		setSelectedItem(newEdge);
	};

	const onConnectEnd = () => {
		singleEventObserver.notify('handle-connect-end', true);
	};

	const onConnectStart = (_, {nodeId}) => {
		singleEventObserver.notify('handle-connect-start', nodeId);
	};

	const onDragOver = (event) => {
		const reactFlowBounds = reactFlowWrapperRef.current.getBoundingClientRect();

		const position = reactFlowInstance.project({
			x:
				event.clientX -
				reactFlowBounds.left -
				elementRectangle.mouseXInRectangle,
			y:
				event.clientY -
				reactFlowBounds.top -
				elementRectangle.mouseYInRectangle,
		});

		setCollidingElements(
			getCollidingElements(elements, elementRectangle, position)
		);

		event.preventDefault();

		event.dataTransfer.dropEffect = 'move';
	};

	const onDrop = useCallback(
		(event) => {
			const reactFlowBounds = reactFlowWrapperRef.current.getBoundingClientRect();

			const position = reactFlowInstance.project({
				x:
					event.clientX -
					reactFlowBounds.left -
					elementRectangle.mouseXInRectangle,
				y:
					event.clientY -
					reactFlowBounds.top -
					elementRectangle.mouseYInRectangle,
			});

			if (
				!getCollidingElements(elements, elementRectangle, position)
					.length
			) {
				event.preventDefault();

				const type = event.dataTransfer.getData(
					'application/reactflow'
				);

				const newNode = {
					data: {
						newNode: true,
					},
					id: uuidv4(),
					position,
					type,
				};

				setElements((elements) => elements.concat(newNode));
			}
			setCollidingElements(null);
		},
		[elements, elementRectangle, reactFlowInstance, setElements]
	);

	const onLoad = (reactFlowInstance) => {
		reactFlowInstance.fitView({maxZoom: 1});
		setReactFlowInstance(reactFlowInstance);
	};

	const onNodeDragStart = (event) => {
		const elementRectangle = event.currentTarget.getBoundingClientRect();
		const reactFlowBounds = reactFlowWrapperRef.current.getBoundingClientRect();

		const position = reactFlowInstance.project({
			x: elementRectangle.left - reactFlowBounds.left,
			y: elementRectangle.top - reactFlowBounds.top,
		});

		setDefaultPosition(position);

		setElementRectangle({
			mouseXInRectangle: event.clientX - elementRectangle.left,
			mouseYInRectangle: event.clientY - elementRectangle.top,
			rectangleHeight: elementRectangle.height,
			rectangleWidth: elementRectangle.width,
		});
	};

	const onNodeDragStop = (event, node) => {
		const reactFlowBounds = reactFlowWrapperRef.current.getBoundingClientRect();

		const position = reactFlowInstance.project({
			x:
				event.clientX -
				reactFlowBounds.left -
				elementRectangle.mouseXInRectangle,
			y:
				event.clientY -
				reactFlowBounds.top -
				elementRectangle.mouseYInRectangle,
		});

		setElements((elements) =>
			elements.map((element) => {
				if (element.id === node.id) {
					element = {
						...element,
						position,
					};
				}

				return element;
			})
		);

		const newElements = elements.filter(
			(element) => element.id !== node.id
		);

		if (
			getCollidingElements(newElements, elementRectangle, position).length
		) {
			setElements((elements) =>
				elements.map((element) => {
					if (element.id === node.id) {
						element.position = defaultPosition;
					}

					return element;
				})
			);
		}
	};

	useEffect(() => {
		if (
			selectedItem &&
			(selectedLanguageId
				? selectedItem.data.label[selectedLanguageId] !== ''
				: selectedItem.data.label[defaultLanguageId] !== '')
		) {
			setElements((elements) =>
				elements.map((element) => {
					if (element.id === selectedItem.id) {
						element = {
							...element,
							data: {
								...element.data,
								...selectedItem.data,
							},
						};
					}

					return element;
				})
			);
		}

		setShowDefinitionInfo(false);

		// eslint-disable-next-line react-hooks/exhaustive-deps
	}, [selectedItem]);

	useEffect(() => {
		if (
			selectedItemNewId &&
			selectedItemNewId.trim() !== '' &&
			!isIdDuplicated(elements, selectedItemNewId.trim())
		) {
			setElements((elements) =>
				elements.map((element) => {
					if (element.id === selectedItem.id) {
						element = {
							...element,
							id: selectedItemNewId,
						};

						setSelectedItemNewId(null);

						setSelectedItem(element);
					}
					else if (isEdge(element)) {
						element = {
							...element,
							...(selectedItem.id === element.source && {
								source: selectedItemNewId,
							}),
							...(selectedItem.id === element.target && {
								target: selectedItemNewId,
							}),
						};
					}

					return element;
				})
			);
		}

		// eslint-disable-next-line react-hooks/exhaustive-deps
	}, [selectedItem, selectedItemNewId]);

	useEffect(() => {
		if (deserialize && currentEditor) {
			const xmlDefinition = currentEditor.getData();

			deserializeUtil.updateXMLDefinition(xmlDefinition);

			const elements = deserializeUtil.getElements();

			const metadata = deserializeUtil.getMetadata();

			setDefinitionDescription(metadata.description);
			setDefinitionName(metadata.name);

			setElements(elements);

			populateAssignmentsData(
				accountEntryId,
				elements,
				setElements,
				setBlockingErrors
			);
			populateNotificationsData(accountEntryId, elements, setElements);

			setDeserialize(false);
		}
		// eslint-disable-next-line react-hooks/exhaustive-deps
	}, [currentEditor, deserialize, version]);

	useEffect(() => {
		if (definitionId && version !== 0 && !deserialize) {
			retrieveDefinitionRequest(definitionId)
				.then((response) => response.json())
				.then(
					({
						active,
						content,
						dateCreated,
						dateModified,
						description,
						version,
					}) => {
						setActive(active);
						setDefinitionDescription(description);
						setDefinitionInfo({
							dateCreated,
							dateModified,
							totalModifications: version,
						});

						deserializeUtil.updateXMLDefinition(
							encodeURIComponent(content)
						);

						const metadata = deserializeUtil.getMetadata();

						setDefinitionDescription(metadata.description);
						setDefinitionName(metadata.name);

						const elements = deserializeUtil.getElements();

						setElements(elements);

						populateAssignmentsData(
							accountEntryId,
							elements,
							setElements
						);
						populateNotificationsData(
							accountEntryId,
							elements,
							setElements
						);
					}
				);
		}

		// eslint-disable-next-line react-hooks/exhaustive-deps
	}, [definitionId, version]);

	const contextProps = {
		collidingElements,
		elementRectangle,
		functionActionExecutors,
		selectedItem,
		selectedItemNewId,
		setCollidingElements,
		setElementRectangle,
		setSelectedItem,
		setSelectedItemNewId,
		statuses,
	};

	return (
		<DiagramBuilderContextProvider {...contextProps}>
			<div className="diagram-builder">
				<div className="diagram-area" ref={reactFlowWrapperRef}>
					<ReactFlow
						connectionLineComponent={FloatingConnectionLine}
						edgeTypes={edgeTypes}
						elements={elements}
						minZoom="0.1"
						nodeTypes={nodeTypes}
						onConnect={onConnect}
						onConnectEnd={onConnectEnd}
						onConnectStart={onConnectStart}
						onDragOver={onDragOver}
						onDrop={onDrop}
						onLoad={onLoad}
						onNodeDragStart={onNodeDragStart}
						onNodeDragStop={onNodeDragStop}
					/>

					<Controls showInteractive={false} />

					<Background size={1} />
				</div>

				<Sidebar />
			</div>
		</DiagramBuilderContextProvider>
	);
}
