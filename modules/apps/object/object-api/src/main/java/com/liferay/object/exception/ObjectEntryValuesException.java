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

package com.liferay.object.exception;

import com.liferay.object.model.ObjectState;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Marco Leo
 */
public class ObjectEntryValuesException extends PortalException {

	public static class ExceedsIntegerSize extends ObjectEntryValuesException {

		public ExceedsIntegerSize(int maxLength, String objectFieldName) {
			super("Object entry value exceeds integer field allowed size");

			_maxLength = maxLength;
			_objectFieldName = objectFieldName;
		}

		public int getMaxLength() {
			return _maxLength;
		}

		public String getObjectFieldName() {
			return _objectFieldName;
		}

		private final int _maxLength;
		private final String _objectFieldName;

	}

	public static class ExceedsLongMaxSize extends ObjectEntryValuesException {

		public ExceedsLongMaxSize(long maxValue, String objectFieldName) {
			super("Object entry value exceeds maximum long field allowed size");

			_maxValue = maxValue;
			_objectFieldName = objectFieldName;
		}

		public long getMaxValue() {
			return _maxValue;
		}

		public String getObjectFieldName() {
			return _objectFieldName;
		}

		private final long _maxValue;
		private final String _objectFieldName;

	}

	public static class ExceedsLongMinSize extends ObjectEntryValuesException {

		public ExceedsLongMinSize(long minValue, String objectFieldName) {
			super(
				"Object entry value falls below minimum long field allowed " +
					"size");

			_minValue = minValue;
			_objectFieldName = objectFieldName;
		}

		public long getMinValue() {
			return _minValue;
		}

		public String getObjectFieldName() {
			return _objectFieldName;
		}

		private final long _minValue;
		private final String _objectFieldName;

	}

	public static class ExceedsLongSize extends ObjectEntryValuesException {

		public ExceedsLongSize(int maxLength, String objectFieldName) {
			super("Object entry value exceeds long field allowed size");

			_maxLength = maxLength;
			_objectFieldName = objectFieldName;
		}

		public int getMaxLength() {
			return _maxLength;
		}

		public String getObjectFieldName() {
			return _objectFieldName;
		}

		private final int _maxLength;
		private final String _objectFieldName;

	}

	public static class ExceedsMaxFileSize extends ObjectEntryValuesException {

		public ExceedsMaxFileSize(long maxFileSize, String objectFieldName) {
			super(
				String.format(
					"File exceeds the maximum permitted size of %s MB for " +
						"object field \"%s\"",
					maxFileSize, objectFieldName));

			_maxFileSize = maxFileSize;
			_objectFieldName = objectFieldName;
		}

		public long getMaxFileSize() {
			return _maxFileSize;
		}

		public String getObjectFieldName() {
			return _objectFieldName;
		}

		private final long _maxFileSize;
		private final String _objectFieldName;

	}

	public static class ExceedsTextMaxLength
		extends ObjectEntryValuesException {

		public ExceedsTextMaxLength(int maxLength, String objectFieldName) {
			super(
				String.format(
					"Object entry value exceeds the maximum length of %s " +
						"characters for object field \"%s\"",
					maxLength, objectFieldName));

			_maxLength = maxLength;
			_objectFieldName = objectFieldName;
		}

		public int getMaxLength() {
			return _maxLength;
		}

		public String getObjectFieldName() {
			return _objectFieldName;
		}

		private final int _maxLength;
		private final String _objectFieldName;

	}

	public static class InvalidFileExtension
		extends ObjectEntryValuesException {

		public InvalidFileExtension(
			String fileExtension, String objectFieldName) {

			super(
				String.format(
					"The file extension %s is invalid for object field \"%s\"",
					fileExtension, objectFieldName));

			_fileExtension = fileExtension;
			_objectFieldName = objectFieldName;
		}

		public String getFileExtension() {
			return _fileExtension;
		}

		public String getObjectFieldName() {
			return _objectFieldName;
		}

		private final String _fileExtension;
		private final String _objectFieldName;

	}

	public static class InvalidObjectStateTransition
		extends ObjectEntryValuesException {

		public InvalidObjectStateTransition(
			ObjectState sourceObjectState, ObjectState targetObjectState) {

			super(
				String.format(
					"Object state ID %d cannot be transitioned to object " +
						"state ID %d",
					sourceObjectState.getObjectStateId(),
					targetObjectState.getObjectStateId()));

			_sourceObjectState = sourceObjectState;
			_targetObjectState = targetObjectState;
		}

		public ObjectState getSourceObjectState() {
			return _sourceObjectState;
		}

		public ObjectState getTargetObjectState() {
			return _targetObjectState;
		}

		private final ObjectState _sourceObjectState;
		private final ObjectState _targetObjectState;

	}

	public static class ListTypeEntry extends ObjectEntryValuesException {

		public ListTypeEntry(String objectFieldName) {
			super(
				String.format(
					"Object field name \"%s\" is not mapped to a valid list " +
						"type entry",
					objectFieldName));

			_objectFieldName = objectFieldName;
		}

		public String getObjectFieldName() {
			return _objectFieldName;
		}

		private final String _objectFieldName;

	}

	public static class MustNotBeDuplicate extends ObjectEntryValuesException {

		public MustNotBeDuplicate(String value) {
			super("Duplicate value " + value);
		}

	}

	public static class OneToOneConstraintViolation
		extends ObjectEntryValuesException {

		public OneToOneConstraintViolation(
			String columnName, long columnValue, String tableName) {

			super(
				String.format(
					"One to one constraint violation for %s.%s with value %s",
					tableName, columnName, columnValue));
		}

	}

	public static class Required extends ObjectEntryValuesException {

		public Required(String objectFieldName) {
			super(
				String.format(
					"No value was provided for required object field \"%s\"",
					objectFieldName));

			_objectFieldName = objectFieldName;
		}

		public String getObjectFieldName() {
			return _objectFieldName;
		}

		private String _objectFieldName;

	}

	public static class UnmodifiableAccountEntryObjectField
		extends ObjectEntryValuesException {

		public UnmodifiableAccountEntryObjectField(String objectFieldName) {
			super(
				StringBundler.concat(
					"The object field ", objectFieldName,
					" is unmodifiable because it is the account entry ",
					"restrictor"));
		}

	}

	private ObjectEntryValuesException(String message) {
		super(message);
	}

}