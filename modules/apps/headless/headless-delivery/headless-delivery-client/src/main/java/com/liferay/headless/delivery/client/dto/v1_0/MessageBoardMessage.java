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

package com.liferay.headless.delivery.client.dto.v1_0;

import com.liferay.headless.delivery.client.function.UnsafeSupplier;
import com.liferay.headless.delivery.client.serdes.v1_0.MessageBoardMessageSerDes;

import java.io.Serializable;

import java.util.Date;
import java.util.Map;
import java.util.Objects;

import javax.annotation.Generated;

/**
 * @author Javier Gamarra
 * @generated
 */
@Generated("")
public class MessageBoardMessage implements Cloneable, Serializable {

	public static MessageBoardMessage toDTO(String json) {
		return MessageBoardMessageSerDes.toDTO(json);
	}

	public Map<String, Map<String, String>> getActions() {
		return actions;
	}

	public void setActions(Map<String, Map<String, String>> actions) {
		this.actions = actions;
	}

	public void setActions(
		UnsafeSupplier<Map<String, Map<String, String>>, Exception>
			actionsUnsafeSupplier) {

		try {
			actions = actionsUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Map<String, Map<String, String>> actions;

	public AggregateRating getAggregateRating() {
		return aggregateRating;
	}

	public void setAggregateRating(AggregateRating aggregateRating) {
		this.aggregateRating = aggregateRating;
	}

	public void setAggregateRating(
		UnsafeSupplier<AggregateRating, Exception>
			aggregateRatingUnsafeSupplier) {

		try {
			aggregateRating = aggregateRatingUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected AggregateRating aggregateRating;

	public Boolean getAnonymous() {
		return anonymous;
	}

	public void setAnonymous(Boolean anonymous) {
		this.anonymous = anonymous;
	}

	public void setAnonymous(
		UnsafeSupplier<Boolean, Exception> anonymousUnsafeSupplier) {

		try {
			anonymous = anonymousUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Boolean anonymous;

	public String getArticleBody() {
		return articleBody;
	}

	public void setArticleBody(String articleBody) {
		this.articleBody = articleBody;
	}

	public void setArticleBody(
		UnsafeSupplier<String, Exception> articleBodyUnsafeSupplier) {

		try {
			articleBody = articleBodyUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String articleBody;

	public Creator getCreator() {
		return creator;
	}

	public void setCreator(Creator creator) {
		this.creator = creator;
	}

	public void setCreator(
		UnsafeSupplier<Creator, Exception> creatorUnsafeSupplier) {

		try {
			creator = creatorUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Creator creator;

	public CreatorStatistics getCreatorStatistics() {
		return creatorStatistics;
	}

	public void setCreatorStatistics(CreatorStatistics creatorStatistics) {
		this.creatorStatistics = creatorStatistics;
	}

	public void setCreatorStatistics(
		UnsafeSupplier<CreatorStatistics, Exception>
			creatorStatisticsUnsafeSupplier) {

		try {
			creatorStatistics = creatorStatisticsUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected CreatorStatistics creatorStatistics;

	public CustomField[] getCustomFields() {
		return customFields;
	}

	public void setCustomFields(CustomField[] customFields) {
		this.customFields = customFields;
	}

	public void setCustomFields(
		UnsafeSupplier<CustomField[], Exception> customFieldsUnsafeSupplier) {

		try {
			customFields = customFieldsUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected CustomField[] customFields;

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public void setDateCreated(
		UnsafeSupplier<Date, Exception> dateCreatedUnsafeSupplier) {

		try {
			dateCreated = dateCreatedUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Date dateCreated;

	public Date getDateModified() {
		return dateModified;
	}

	public void setDateModified(Date dateModified) {
		this.dateModified = dateModified;
	}

	public void setDateModified(
		UnsafeSupplier<Date, Exception> dateModifiedUnsafeSupplier) {

		try {
			dateModified = dateModifiedUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Date dateModified;

	public String getEncodingFormat() {
		return encodingFormat;
	}

	public void setEncodingFormat(String encodingFormat) {
		this.encodingFormat = encodingFormat;
	}

	public void setEncodingFormat(
		UnsafeSupplier<String, Exception> encodingFormatUnsafeSupplier) {

		try {
			encodingFormat = encodingFormatUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String encodingFormat;

	public String getExternalReferenceCode() {
		return externalReferenceCode;
	}

	public void setExternalReferenceCode(String externalReferenceCode) {
		this.externalReferenceCode = externalReferenceCode;
	}

	public void setExternalReferenceCode(
		UnsafeSupplier<String, Exception> externalReferenceCodeUnsafeSupplier) {

		try {
			externalReferenceCode = externalReferenceCodeUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String externalReferenceCode;

	public String getFriendlyUrlPath() {
		return friendlyUrlPath;
	}

	public void setFriendlyUrlPath(String friendlyUrlPath) {
		this.friendlyUrlPath = friendlyUrlPath;
	}

	public void setFriendlyUrlPath(
		UnsafeSupplier<String, Exception> friendlyUrlPathUnsafeSupplier) {

		try {
			friendlyUrlPath = friendlyUrlPathUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String friendlyUrlPath;

	public Boolean getHasCompanyMx() {
		return hasCompanyMx;
	}

	public void setHasCompanyMx(Boolean hasCompanyMx) {
		this.hasCompanyMx = hasCompanyMx;
	}

	public void setHasCompanyMx(
		UnsafeSupplier<Boolean, Exception> hasCompanyMxUnsafeSupplier) {

		try {
			hasCompanyMx = hasCompanyMxUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Boolean hasCompanyMx;

	public String getHeadline() {
		return headline;
	}

	public void setHeadline(String headline) {
		this.headline = headline;
	}

	public void setHeadline(
		UnsafeSupplier<String, Exception> headlineUnsafeSupplier) {

		try {
			headline = headlineUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String headline;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setId(UnsafeSupplier<Long, Exception> idUnsafeSupplier) {
		try {
			id = idUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Long id;

	public String[] getKeywords() {
		return keywords;
	}

	public void setKeywords(String[] keywords) {
		this.keywords = keywords;
	}

	public void setKeywords(
		UnsafeSupplier<String[], Exception> keywordsUnsafeSupplier) {

		try {
			keywords = keywordsUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String[] keywords;

	public Long getMessageBoardSectionId() {
		return messageBoardSectionId;
	}

	public void setMessageBoardSectionId(Long messageBoardSectionId) {
		this.messageBoardSectionId = messageBoardSectionId;
	}

	public void setMessageBoardSectionId(
		UnsafeSupplier<Long, Exception> messageBoardSectionIdUnsafeSupplier) {

		try {
			messageBoardSectionId = messageBoardSectionIdUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Long messageBoardSectionId;

	public Long getMessageBoardThreadId() {
		return messageBoardThreadId;
	}

	public void setMessageBoardThreadId(Long messageBoardThreadId) {
		this.messageBoardThreadId = messageBoardThreadId;
	}

	public void setMessageBoardThreadId(
		UnsafeSupplier<Long, Exception> messageBoardThreadIdUnsafeSupplier) {

		try {
			messageBoardThreadId = messageBoardThreadIdUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Long messageBoardThreadId;

	public Boolean getModified() {
		return modified;
	}

	public void setModified(Boolean modified) {
		this.modified = modified;
	}

	public void setModified(
		UnsafeSupplier<Boolean, Exception> modifiedUnsafeSupplier) {

		try {
			modified = modifiedUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Boolean modified;

	public Integer getNumberOfMessageBoardAttachments() {
		return numberOfMessageBoardAttachments;
	}

	public void setNumberOfMessageBoardAttachments(
		Integer numberOfMessageBoardAttachments) {

		this.numberOfMessageBoardAttachments = numberOfMessageBoardAttachments;
	}

	public void setNumberOfMessageBoardAttachments(
		UnsafeSupplier<Integer, Exception>
			numberOfMessageBoardAttachmentsUnsafeSupplier) {

		try {
			numberOfMessageBoardAttachments =
				numberOfMessageBoardAttachmentsUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Integer numberOfMessageBoardAttachments;

	public Integer getNumberOfMessageBoardMessages() {
		return numberOfMessageBoardMessages;
	}

	public void setNumberOfMessageBoardMessages(
		Integer numberOfMessageBoardMessages) {

		this.numberOfMessageBoardMessages = numberOfMessageBoardMessages;
	}

	public void setNumberOfMessageBoardMessages(
		UnsafeSupplier<Integer, Exception>
			numberOfMessageBoardMessagesUnsafeSupplier) {

		try {
			numberOfMessageBoardMessages =
				numberOfMessageBoardMessagesUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Integer numberOfMessageBoardMessages;

	public Long getParentMessageBoardMessageId() {
		return parentMessageBoardMessageId;
	}

	public void setParentMessageBoardMessageId(
		Long parentMessageBoardMessageId) {

		this.parentMessageBoardMessageId = parentMessageBoardMessageId;
	}

	public void setParentMessageBoardMessageId(
		UnsafeSupplier<Long, Exception>
			parentMessageBoardMessageIdUnsafeSupplier) {

		try {
			parentMessageBoardMessageId =
				parentMessageBoardMessageIdUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Long parentMessageBoardMessageId;

	public RelatedContent[] getRelatedContents() {
		return relatedContents;
	}

	public void setRelatedContents(RelatedContent[] relatedContents) {
		this.relatedContents = relatedContents;
	}

	public void setRelatedContents(
		UnsafeSupplier<RelatedContent[], Exception>
			relatedContentsUnsafeSupplier) {

		try {
			relatedContents = relatedContentsUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected RelatedContent[] relatedContents;

	public Boolean getShowAsAnswer() {
		return showAsAnswer;
	}

	public void setShowAsAnswer(Boolean showAsAnswer) {
		this.showAsAnswer = showAsAnswer;
	}

	public void setShowAsAnswer(
		UnsafeSupplier<Boolean, Exception> showAsAnswerUnsafeSupplier) {

		try {
			showAsAnswer = showAsAnswerUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Boolean showAsAnswer;

	public Long getSiteId() {
		return siteId;
	}

	public void setSiteId(Long siteId) {
		this.siteId = siteId;
	}

	public void setSiteId(
		UnsafeSupplier<Long, Exception> siteIdUnsafeSupplier) {

		try {
			siteId = siteIdUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Long siteId;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setStatus(
		UnsafeSupplier<String, Exception> statusUnsafeSupplier) {

		try {
			status = statusUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String status;

	public Boolean getSubscribed() {
		return subscribed;
	}

	public void setSubscribed(Boolean subscribed) {
		this.subscribed = subscribed;
	}

	public void setSubscribed(
		UnsafeSupplier<Boolean, Exception> subscribedUnsafeSupplier) {

		try {
			subscribed = subscribedUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Boolean subscribed;

	public ViewableBy getViewableBy() {
		return viewableBy;
	}

	public String getViewableByAsString() {
		if (viewableBy == null) {
			return null;
		}

		return viewableBy.toString();
	}

	public void setViewableBy(ViewableBy viewableBy) {
		this.viewableBy = viewableBy;
	}

	public void setViewableBy(
		UnsafeSupplier<ViewableBy, Exception> viewableByUnsafeSupplier) {

		try {
			viewableBy = viewableByUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected ViewableBy viewableBy;

	@Override
	public MessageBoardMessage clone() throws CloneNotSupportedException {
		return (MessageBoardMessage)super.clone();
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof MessageBoardMessage)) {
			return false;
		}

		MessageBoardMessage messageBoardMessage = (MessageBoardMessage)object;

		return Objects.equals(toString(), messageBoardMessage.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		return MessageBoardMessageSerDes.toJSON(this);
	}

	public static enum ViewableBy {

		ANYONE("Anyone"), MEMBERS("Members"), OWNER("Owner");

		public static ViewableBy create(String value) {
			for (ViewableBy viewableBy : values()) {
				if (Objects.equals(viewableBy.getValue(), value) ||
					Objects.equals(viewableBy.name(), value)) {

					return viewableBy;
				}
			}

			return null;
		}

		public String getValue() {
			return _value;
		}

		@Override
		public String toString() {
			return _value;
		}

		private ViewableBy(String value) {
			_value = value;
		}

		private final String _value;

	}

}