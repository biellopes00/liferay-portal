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

import {EventHandler} from 'frontend-js-web';
import PropTypes from 'prop-types';
import React, {useEffect, useRef, useState} from 'react';

import {config} from '../../app/config/index';

export default function Editor({
	autoFocus = false,
	configurationName,
	id,
	initialValue,
	label,
	onChange,
	placeholder,
}) {
	const editorConfig =
		config.defaultEditorConfigurations[configurationName].editorConfig;

	const [editor, setEditor] = useState(null);

	const wrapperRef = useRef(null);

	useEffect(() => {
		if (editor) {
			const nativeEditor = editor.get('nativeEditor');

			if (!nativeEditor.getData() || !initialValue) {
				nativeEditor.setData(initialValue);
			}
		}
	}, [editor, initialValue]);

	useEffect(() => {
		const editorEventHandler = new EventHandler();

		if (editor && onChange) {
			const nativeEditor = editor.get('nativeEditor');

			editorEventHandler.add(
				nativeEditor.on('change', () =>
					onChange(nativeEditor.getData())
				)
			);

			editorEventHandler.add(
				nativeEditor.on('actionPerformed', () =>
					onChange(nativeEditor.getData())
				)
			);
		}

		return () => {
			editorEventHandler.removeAllListeners();
			editorEventHandler.dispose();
		};
	}, [editor, onChange]);

	useEffect(() => {
		const newEditor = AlloyEditor.editable(wrapperRef.current, {
			...editorConfig,
			enterMode: 1,
			startupFocus: autoFocus,
			title: label,
		});

		let ready = false;

		const instanceReadyEventHandler = newEditor
			.get('nativeEditor')
			.once('instanceReady', () => {
				ready = true;

				wrapperRef.current.removeAttribute('title');

				setEditor(newEditor);
			});

		return () => {
			try {
				if (ready) {
					newEditor.destroy();
					setEditor(null);
				}
				else {
					instanceReadyEventHandler.removeListener();

					newEditor.get('nativeEditor').once('instanceReady', () => {
						newEditor.destroy();
					});
				}
			}
			catch (_err) {

				// https://github.com/liferay/alloy-editor/issues/1306

			}
		};
	}, [autoFocus, editorConfig, label]);

	return (
		<div className="alloy-editor-container">
			<div
				className="alloy-editor form-control form-control-sm page-editor__editor page-editor__editor-placeholder"
				contentEditable={false}
				data-placeholder={placeholder}
				data-required={false}
				id={id}
				name={id}
				ref={wrapperRef}
			/>
		</div>
	);
}

Editor.propTypes = {
	autoFocus: PropTypes.bool,
	configurationName: PropTypes.oneOf(['rich-text', 'text', 'comment'])
		.isRequired,
	editorConfig: PropTypes.object,
	id: PropTypes.string.isRequired,
	initialValue: PropTypes.string.isRequired,
	onChange: PropTypes.func.isRequired,
	placeholder: PropTypes.string.isRequired,
};
