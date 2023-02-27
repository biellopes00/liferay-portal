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

package com.liferay.jenkins.plugin.events;

import hudson.model.Build;
import hudson.model.Computer;
import hudson.model.Executor;
import hudson.model.Job;
import hudson.model.ParameterValue;
import hudson.model.ParametersAction;
import hudson.model.Queue;

import java.util.HashMap;
import java.util.Map;

import jenkins.model.Jenkins;

import org.json.JSONObject;

/**
 * @author Michael Hashimoto
 */
public class JenkinsEventsConfigurationUtil {

	public static void publish(
		JenkinsWebHook.EventTrigger eventTrigger, Object eventObject) {

		Jenkins jenkins = Jenkins.getInstanceOrNull();

		if (jenkins == null) {
			return;
		}

		JSONObject payloadJSONObject = new JSONObject();

		payloadJSONObject.put(
			"build", _getBuildJSONObject(_getBuild(eventObject)));
		payloadJSONObject.put(
			"computer",
			_getComputerJSONObject(_getComputer(eventObject), eventTrigger));
		payloadJSONObject.put("eventTrigger", eventTrigger);
		payloadJSONObject.put("jenkins", _getJenkinsJSONObject(jenkins));
		payloadJSONObject.put("job", _getJobJSONObject(_getJob(eventObject)));
		payloadJSONObject.put(
			"queueItem", _getQueueItemJSONObject(_getQueueItem(eventObject)));

		JenkinsEventsRootAction jenkinsEventsRootAction =
			jenkins.getDescriptorByType(JenkinsEventsRootAction.class);

		for (JenkinsWebHook jenkinsWebHook :
				jenkinsEventsRootAction.getJenkinsWebHooks()) {

			if (!jenkinsWebHook.containsEventTrigger(eventTrigger)) {
				continue;
			}

			jenkinsWebHook.publish(payloadJSONObject.toString(), eventTrigger);
		}
	}

	private static Build _getBuild(Object eventObject) {
		if (eventObject instanceof Build) {
			return (Build)eventObject;
		}

		return null;
	}

	private static JSONObject _getBuildJSONObject(Build build) {
		if (build == null) {
			return null;
		}

		JSONObject jsonObject = new JSONObject();

		jsonObject.put("duration", build.getDuration());
		jsonObject.put("number", build.getNumber());
		jsonObject.put("result", build.getResult());

		return jsonObject;
	}

	private static Computer _getComputer(Object eventObject) {
		if (eventObject instanceof Computer) {
			return (Computer)eventObject;
		}

		Build build = _getBuild(eventObject);

		if (build != null) {
			Executor executor = build.getExecutor();

			return executor.getOwner();
		}

		return null;
	}

	private static JSONObject _getComputerJSONObject(
		Computer computer, JenkinsWebHook.EventTrigger eventTrigger) {

		if (computer == null) {
			return null;
		}

		JSONObject jsonObject = new JSONObject();

		if (eventTrigger == JenkinsWebHook.EventTrigger.COMPUTER_IDLE) {
			jsonObject.put("busy", false);
		}
		else if (eventTrigger == JenkinsWebHook.EventTrigger.COMPUTER_BUSY) {
			jsonObject.put("busy", true);
		}
		else {
			jsonObject.put("busy", !computer.isIdle());
		}

		jsonObject.put("name", computer.getDisplayName());
		jsonObject.put("online", computer.isOnline());

		return jsonObject;
	}

	private static JSONObject _getJenkinsJSONObject(Jenkins jenkins) {
		if (jenkins == null) {
			return null;
		}

		JSONObject jsonObject = new JSONObject();

		jsonObject.put("url", jenkins.getRootUrl());

		return jsonObject;
	}

	private static Job _getJob(Object eventObject) {
		if (eventObject instanceof Job) {
			return (Job)eventObject;
		}

		Build build = _getBuild(eventObject);

		if (build != null) {
			return build.getParent();
		}

		return null;
	}

	private static JSONObject _getJobJSONObject(Job job) {
		if (job == null) {
			return null;
		}

		JSONObject jsonObject = new JSONObject();

		jsonObject.put("name", job.getName());

		return jsonObject;
	}

	private static Queue.Item _getQueueItem(Object eventObject) {
		if (eventObject instanceof Queue.Item) {
			return (Queue.Item)eventObject;
		}

		return null;
	}

	private static JSONObject _getQueueItemJSONObject(Queue.Item queueItem) {
		if (queueItem == null) {
			return null;
		}

		JSONObject jsonObject = new JSONObject();

		jsonObject.put("id", queueItem.getId());

		if (queueItem instanceof Queue.BuildableItem) {
			Queue.BuildableItem buildableItem = (Queue.BuildableItem)queueItem;

			jsonObject.put("pending", buildableItem.isPending());
			jsonObject.put("stuck", buildableItem.isStuck());
		}
		else if (queueItem instanceof Queue.LeftItem) {
			Queue.LeftItem leftItem = (Queue.LeftItem)queueItem;

			jsonObject.put("canceled", leftItem.isCancelled());
		}

		Map<String, Object> parameters = new HashMap<>();

		for (ParametersAction parametersAction :
				queueItem.getActions(ParametersAction.class)) {

			for (ParameterValue parameterValue :
					parametersAction.getParameters()) {

				parameters.put(
					parameterValue.getName(), parameterValue.getValue());
			}
		}

		jsonObject.put("parameters", parameters);

		jsonObject.put("task", _getQueueTaskJSONObject(queueItem.task));

		return jsonObject;
	}

	private static JSONObject _getQueueTaskJSONObject(Queue.Task queueTask) {
		if (queueTask == null) {
			return null;
		}

		JSONObject jsonObject = new JSONObject();

		jsonObject.put("concurrent", queueTask.isConcurrentBuild());
		jsonObject.put("name", queueTask.getDisplayName());

		return jsonObject;
	}

}