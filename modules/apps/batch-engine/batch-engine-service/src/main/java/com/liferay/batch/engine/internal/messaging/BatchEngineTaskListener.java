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

package com.liferay.batch.engine.internal.messaging;

import com.liferay.batch.engine.BatchEngineTaskExecuteStatus;
import com.liferay.batch.engine.BatchEngineTaskExecutor;
import com.liferay.batch.engine.model.BatchEngineTask;
import com.liferay.batch.engine.service.BatchEngineTaskLocalService;
import com.liferay.petra.concurrent.NoticeableExecutorService;
import com.liferay.petra.executor.PortalExecutorManager;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.scheduler.SchedulerEngineHelper;
import com.liferay.portal.kernel.scheduler.SchedulerEntryImpl;
import com.liferay.portal.kernel.scheduler.TimeUnit;
import com.liferay.portal.kernel.scheduler.Trigger;
import com.liferay.portal.kernel.scheduler.TriggerFactory;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Time;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ExecutorService;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Ivica Cardic
 */
@Component(
	immediate = true, property = {"heartbeat.period=300", "interval=60"},
	service = MessageListener.class
)
public class BatchEngineTaskListener extends BaseMessageListener {

	@Activate
	protected void activate(Map<String, Object> properties) {
		_heartbeatInterval =
			GetterUtil.getLong(properties.get("heartbeat.period")) *
				Time.SECOND;

		String className = BatchEngineTaskListener.class.getName();

		Trigger trigger = _triggerFactory.createTrigger(
			className, className, null, null,
			GetterUtil.getInteger(properties.get("interval")), TimeUnit.SECOND);

		_schedulerEngineHelper.register(
			this, new SchedulerEntryImpl(className, trigger),
			DestinationNames.SCHEDULER_DISPATCH);
	}

	@Deactivate
	protected void deactivate() {
		ExecutorService executorService =
			_portalExecutorManager.getPortalExecutor(
				BatchEngineTaskListener.class.getName());

		executorService.shutdownNow();

		_schedulerEngineHelper.unregister(this);
	}

	@Override
	protected void doReceive(Message message) {
		NoticeableExecutorService noticeableExecutorService =
			_portalExecutorManager.getPortalExecutor(
				BatchEngineTaskListener.class.getName());

		long currentTime = System.currentTimeMillis();

		for (BatchEngineTask batchEngineTask :
				_batchEngineTaskLocalService.getBatchEngineTasks(
					BatchEngineTaskExecuteStatus.STARTED)) {

			Date modifiedDate = batchEngineTask.getModifiedDate();

			if ((currentTime - modifiedDate.getTime()) > _heartbeatInterval) {
				noticeableExecutorService.submit(
					() -> _batchEngineTaskExecutor.execute(batchEngineTask));
			}
		}
	}

	@Reference
	private BatchEngineTaskExecutor _batchEngineTaskExecutor;

	@Reference
	private BatchEngineTaskLocalService _batchEngineTaskLocalService;

	private long _heartbeatInterval;

	@Reference(target = ModuleServiceLifecycle.PORTAL_INITIALIZED)
	private ModuleServiceLifecycle _moduleServiceLifecycle;

	@Reference
	private PortalExecutorManager _portalExecutorManager;

	@Reference
	private SchedulerEngineHelper _schedulerEngineHelper;

	@Reference
	private TriggerFactory _triggerFactory;

}