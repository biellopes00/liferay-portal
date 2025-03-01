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

package com.liferay.mail.reader.internal.mailbox;

import com.liferay.mail.reader.attachment.AttachmentHandler;
import com.liferay.mail.reader.constants.MailConstants;
import com.liferay.mail.reader.exception.MailException;
import com.liferay.mail.reader.exception.NoSuchFolderException;
import com.liferay.mail.reader.exception.NoSuchMessageException;
import com.liferay.mail.reader.internal.attachment.DefaultAttachmentHandler;
import com.liferay.mail.reader.internal.imap.IMAPAccessor;
import com.liferay.mail.reader.internal.imap.IMAPConnection;
import com.liferay.mail.reader.internal.util.AccountLock;
import com.liferay.mail.reader.internal.util.InternetAddressUtil;
import com.liferay.mail.reader.model.Account;
import com.liferay.mail.reader.model.Attachment;
import com.liferay.mail.reader.model.Folder;
import com.liferay.mail.reader.model.MailFile;
import com.liferay.mail.reader.model.Message;
import com.liferay.mail.reader.model.MessagesDisplay;
import com.liferay.mail.reader.service.AccountLocalServiceUtil;
import com.liferay.mail.reader.service.AttachmentLocalServiceUtil;
import com.liferay.mail.reader.service.FolderLocalServiceUtil;
import com.liferay.mail.reader.service.MessageLocalServiceUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import javax.mail.Address;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

/**
 * @author Scott Lee
 */
public class IMAPMailbox extends BaseMailbox {

	public IMAPMailbox(User user, Account account, String password) {
		setUser(user);
		setAccount(account);

		if (account != null) {
			_imapAccessor = new IMAPAccessor(user, account, password);
		}
		else {
			_imapAccessor = null;
		}
	}

	@Override
	public Folder addFolder(String displayName) throws PortalException {
		String[] names = _imapAccessor.addFolder(displayName);

		return FolderLocalServiceUtil.addFolder(
			user.getUserId(), account.getAccountId(), names[0], names[1], 0);
	}

	@Override
	public void deleteAttachment(long attachmentId) throws PortalException {
		AttachmentLocalServiceUtil.deleteAttachment(attachmentId);
	}

	@Override
	public void deleteFolder(long folderId) throws PortalException {
		if ((account.getDraftFolderId() == folderId) ||
			(account.getInboxFolderId() == folderId) ||
			(account.getSentFolderId() == folderId) ||
			(account.getTrashFolderId() == folderId)) {

			throw new MailException(MailException.FOLDER_REQUIRED);
		}

		_imapAccessor.deleteFolder(folderId);

		FolderLocalServiceUtil.deleteFolder(folderId);
	}

	@Override
	public void deleteMessages(long folderId, long[] messageIds)
		throws PortalException {

		if ((account.getDraftFolderId() != folderId) &&
			(account.getTrashFolderId() != folderId)) {

			Folder trashFolder = FolderLocalServiceUtil.getFolder(
				account.getTrashFolderId());

			_imapAccessor.moveMessages(
				folderId, trashFolder.getFolderId(), messageIds, true);
		}
		else {
			_imapAccessor.deleteMessages(folderId, messageIds);
		}
	}

	@Override
	public AttachmentHandler getAttachment(long attachmentId)
		throws IOException, PortalException {

		Attachment attachment = AttachmentLocalServiceUtil.getAttachment(
			attachmentId);

		if (account.getDraftFolderId() == attachment.getFolderId()) {
			return new DefaultAttachmentHandler(
				AttachmentLocalServiceUtil.getInputStream(attachmentId), null);
		}

		Message message = MessageLocalServiceUtil.getMessage(
			attachment.getMessageId());

		return _imapAccessor.getAttachment(
			attachment.getFolderId(), message.getRemoteMessageId(),
			attachment.getContentPath());
	}

	@Override
	public Message getMessage(
			long folderId, String keywords, int messageNumber,
			String orderByField, String orderByType)
		throws PortalException {

		MessagesDisplay messagesDisplay = getMessagesDisplay(
			folderId, keywords, messageNumber, 1, orderByField, orderByType);

		List<Message> messages = messagesDisplay.getMessages();

		return messages.get(0);
	}

	@Override
	public MessagesDisplay getMessagesDisplay(
			long folderId, String keywords, int pageNumber, int messagesPerPage,
			String orderByField, String orderByType)
		throws PortalException {

		if (orderByField.equals(MailConstants.ORDER_BY_ADDRESS)) {
			orderByField = "sender";

			if (account.getSentFolderId() == folderId) {
				orderByField = "to";
			}
		}
		else if (!orderByField.equals(MailConstants.ORDER_BY_SENT_DATE) &&
				 !orderByField.equals(MailConstants.ORDER_BY_SIZE) &&
				 !orderByField.equals(MailConstants.ORDER_BY_SUBJECT)) {

			orderByField = MailConstants.ORDER_BY_SENT_DATE;
		}

		List<Message> messages = new ArrayList<>(messagesPerPage);

		int messageCount = MessageLocalServiceUtil.populateMessages(
			messages, folderId, keywords, pageNumber, messagesPerPage,
			orderByField, orderByType);

		if (Validator.isNull(keywords) &&
			(account.getDraftFolderId() != folderId)) {

			Folder folder = FolderLocalServiceUtil.getFolder(folderId);

			messageCount = folder.getRemoteMessageCount();
		}

		return new MessagesDisplay(
			messages, pageNumber, messagesPerPage, messageCount);
	}

	@Override
	public boolean hasNewMessages(long folderId) throws PortalException {
		return _imapAccessor.hasNewMessages(folderId);
	}

	@Override
	public void moveMessages(long folderId, long[] messageIds)
		throws PortalException {

		for (long messageId : messageIds) {
			Message message = MessageLocalServiceUtil.getMessage(messageId);

			Account account = AccountLocalServiceUtil.getAccount(
				message.getAccountId());

			long sourceFolderId = message.getFolderId();

			if ((account.getDraftFolderId() == sourceFolderId) ||
				(account.getSentFolderId() == sourceFolderId)) {

				throw new MailException(
					MailException.FOLDER_INVALID_DESTINATION);
			}

			_imapAccessor.moveMessages(
				sourceFolderId, folderId, new long[] {messageId}, true);
		}
	}

	@Override
	public InternetAddress[] parseAddresses(String addresses)
		throws PortalException {

		InternetAddress[] internetAddresses = new InternetAddress[0];

		try {
			internetAddresses = InternetAddress.parse(addresses, true);

			for (InternetAddress internetAddress : internetAddresses) {
				if (!Validator.isEmailAddress(internetAddress.getAddress())) {
					throw new MailException(
						MailException.MESSAGE_INVALID_ADDRESS,
						StringBundler.concat(
							internetAddress.getPersonal(), StringPool.LESS_THAN,
							internetAddress.getAddress(),
							StringPool.GREATER_THAN));
				}
			}
		}
		catch (AddressException addressException) {
			throw new MailException(
				MailException.MESSAGE_INVALID_ADDRESS, addressException,
				addresses);
		}

		return internetAddresses;
	}

	@Override
	public void renameFolder(long folderId, String displayName)
		throws PortalException {

		Folder folder = FolderLocalServiceUtil.getFolder(folderId);

		String[] names = _imapAccessor.renameFolder(folderId, displayName);

		FolderLocalServiceUtil.updateFolder(
			folderId, names[0], names[1], folder.getRemoteMessageCount());
	}

	@Override
	public Message saveDraft(
			long accountId, long messageId, String to, String cc, String bcc,
			String subject, String body, List<MailFile> mailFiles)
		throws PortalException {

		Address[] toAddresses = parseAddresses(to);
		Address[] ccAddresses = parseAddresses(cc);
		Address[] bccAddresses = parseAddresses(bcc);

		if ((toAddresses.length == 0) && (ccAddresses.length == 0) &&
			(bccAddresses.length == 0)) {

			throw new MailException(MailException.MESSAGE_HAS_NO_RECIPIENTS);
		}

		Account account = AccountLocalServiceUtil.getAccount(accountId);

		String sender = StringBundler.concat(
			user.getFullName(), " <", account.getAddress(),
			StringPool.GREATER_THAN);

		Message message = null;

		if (messageId != 0) {
			message = MessageLocalServiceUtil.updateMessage(
				messageId, account.getDraftFolderId(), sender,
				InternetAddressUtil.toString(toAddresses),
				InternetAddressUtil.toString(ccAddresses),
				InternetAddressUtil.toString(bccAddresses), null, subject, body,
				String.valueOf(MailConstants.FLAG_DRAFT), 0);
		}
		else {
			message = MessageLocalServiceUtil.addMessage(
				user.getUserId(), account.getDraftFolderId(), sender, to, cc,
				bcc, null, subject, body,
				String.valueOf(MailConstants.FLAG_DRAFT), 0, null);
		}

		if (mailFiles == null) {
			return message;
		}

		for (MailFile mailFile : mailFiles) {
			AttachmentLocalServiceUtil.addAttachment(
				user.getUserId(), message.getMessageId(), null,
				mailFile.getFileName(), mailFile.getSize(), mailFile.getFile());
		}

		return message;
	}

	@Override
	public void sendMessage(long accountId, long messageId)
		throws PortalException {

		Message message = MessageLocalServiceUtil.getMessage(messageId);

		Address[] toAddresses = parseAddresses(message.getTo());
		Address[] ccAddresses = parseAddresses(message.getCc());
		Address[] bccAddresses = parseAddresses(message.getBcc());

		if ((toAddresses.length == 0) && (ccAddresses.length == 0) &&
			(bccAddresses.length == 0)) {

			throw new MailException(MailException.MESSAGE_HAS_NO_RECIPIENTS);
		}

		Account account = AccountLocalServiceUtil.getAccount(accountId);

		List<Attachment> attachments =
			AttachmentLocalServiceUtil.getAttachments(messageId);

		List<MailFile> mailFiles = new ArrayList<>();

		for (Attachment attachment : attachments) {
			File file = AttachmentLocalServiceUtil.getFile(
				attachment.getAttachmentId());

			MailFile mailFile = new MailFile(
				file, attachment.getFileName(), attachment.getSize());

			mailFiles.add(mailFile);
		}

		_imapAccessor.sendMessage(
			account.getPersonalName(), account.getAddress(), toAddresses,
			ccAddresses, bccAddresses, message.getSubject(), message.getBody(),
			mailFiles);

		MessageLocalServiceUtil.deleteMessage(messageId);
	}

	@Override
	public void synchronize() throws PortalException {
		if (_log.isDebugEnabled()) {
			_log.debug(
				"Synchronizing all folders for accountId " +
					account.getAccountId());
		}

		updateFolders();

		String key = AccountLock.getKey(
			user.getUserId(), account.getAccountId());

		if (AccountLock.acquireLock(key)) {
			List<Folder> folders = FolderLocalServiceUtil.getFolders(
				account.getAccountId());

			try {
				for (Folder folder : folders) {
					_imapAccessor.storeEnvelopes(folder.getFolderId(), true);
				}
			}
			finally {
				AccountLock.releaseLock(key);
			}
		}
	}

	@Override
	public void synchronizeFolder(long folderId) throws PortalException {
		if (_log.isDebugEnabled()) {
			_log.debug("Synchronizing folder " + folderId);
		}

		String key = AccountLock.getKey(
			user.getUserId(), account.getAccountId());

		if (AccountLock.acquireLock(key)) {
			try {
				_imapAccessor.storeEnvelopes(folderId, false);
			}
			finally {
				AccountLock.releaseLock(key);
			}
		}
	}

	@Override
	public void synchronizeMessage(long messageId) throws PortalException {
		Message message = MessageLocalServiceUtil.getMessage(messageId);

		long remoteMessageId = message.getRemoteMessageId();

		if (remoteMessageId == 0) {
			return;
		}

		try {
			_imapAccessor.storeContents(
				message.getFolderId(),
				new long[] {message.getRemoteMessageId()});
		}
		catch (IOException ioException) {
			throw new MailException(ioException);
		}
	}

	@Override
	public void synchronizePage(
			long folderId, int pageNumber, int messagesPerPage)
		throws PortalException {

		long[] remoteMessageUIDs = _imapAccessor.getMessageUIDs(
			folderId, pageNumber, messagesPerPage);

		List<Long> missingRemoteMessageIdsList = new ArrayList<>();

		for (long remoteMessageUID : remoteMessageUIDs) {
			try {
				MessageLocalServiceUtil.getMessage(folderId, remoteMessageUID);
			}
			catch (NoSuchMessageException noSuchMessageException) {
				if (_log.isDebugEnabled()) {
					_log.debug(noSuchMessageException);
				}

				missingRemoteMessageIdsList.add(remoteMessageUID);
			}
		}

		if (!missingRemoteMessageIdsList.isEmpty()) {
			long[] missingRemoteMessageIds = ArrayUtil.toArray(
				missingRemoteMessageIdsList.toArray(new Long[0]));

			_imapAccessor.storeEnvelopes(folderId, missingRemoteMessageIds);
		}
	}

	@Override
	public void updateFlags(
			long folderId, long[] messageIds, int flag, boolean value)
		throws PortalException {

		Folder folder = FolderLocalServiceUtil.getFolder(folderId);

		Account account = AccountLocalServiceUtil.getAccount(
			folder.getAccountId());

		for (long messageId : messageIds) {
			MessageLocalServiceUtil.updateFlag(messageId, flag, value);
		}

		if (account.getDraftFolderId() == folderId) {
			_imapAccessor.updateFlags(folderId, messageIds, flag, value, false);
		}
		else {
			_imapAccessor.updateFlags(folderId, messageIds, flag, value, true);
		}
	}

	@Override
	public void updateFolders() throws PortalException {
		if (_log.isDebugEnabled()) {
			_log.debug("Updating folders");
		}

		List<javax.mail.Folder> jxFolders = _imapAccessor.getFolders();

		for (javax.mail.Folder jxFolder : jxFolders) {
			try {
				FolderLocalServiceUtil.getFolder(
					account.getAccountId(), jxFolder.getFullName());
			}
			catch (NoSuchFolderException noSuchFolderException) {
				if (_log.isDebugEnabled()) {
					_log.debug(noSuchFolderException);
				}

				FolderLocalServiceUtil.addFolder(
					user.getUserId(), account.getAccountId(),
					jxFolder.getFullName(), jxFolder.getName(), 0);
			}
		}

		long draftFolderId = account.getDraftFolderId();
		long inboxFolderId = account.getInboxFolderId();
		long sentFolderId = account.getSentFolderId();
		long trashFolderId = account.getTrashFolderId();

		if (draftFolderId <= 0) {
			draftFolderId = _getFolderId("draft");
		}

		if (inboxFolderId <= 0) {
			inboxFolderId = _getFolderId("inbox");
		}

		if (sentFolderId <= 0) {
			sentFolderId = _getFolderId("sent");
		}

		if (sentFolderId <= 0) {
			sentFolderId = _getFolderId("sent-mail");
		}

		if (trashFolderId <= 0) {
			trashFolderId = _getFolderId("trash");
		}

		updateFolders(
			inboxFolderId, draftFolderId, sentFolderId, trashFolderId);
	}

	@Override
	public void validateAccount(
			String incomingHostName, int incomingPort, boolean incomingSecure,
			String outgoingHostName, int outgoingPort, boolean outgoingSecure,
			String login, String password)
		throws PortalException {

		IMAPConnection imapConnection = new IMAPConnection(
			incomingHostName, incomingPort, incomingSecure, outgoingHostName,
			outgoingPort, outgoingSecure, login, password);

		imapConnection.testConnection();
	}

	private long _getFolderId(String type) {
		List<String> words = new ArrayList<>();

		for (Locale locale : LanguageUtil.getAvailableLocales()) {
			String translation = StringUtil.toLowerCase(
				LanguageUtil.get(locale, type));

			Collections.addAll(words, translation.split(StringPool.SPACE));
		}

		List<Folder> folders = FolderLocalServiceUtil.getFolders(
			account.getAccountId());

		for (String word : words) {
			for (Folder folder : folders) {
				String folderName = StringUtil.toLowerCase(
					folder.getDisplayName());

				if (folderName.contains(word)) {
					return folder.getFolderId();
				}
			}
		}

		return 0;
	}

	private static final Log _log = LogFactoryUtil.getLog(IMAPMailbox.class);

	private final IMAPAccessor _imapAccessor;

}