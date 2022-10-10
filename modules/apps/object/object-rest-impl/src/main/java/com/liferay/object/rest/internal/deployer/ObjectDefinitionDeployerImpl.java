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

package com.liferay.object.rest.internal.deployer;

import com.liferay.object.deployer.ObjectDefinitionDeployer;
import com.liferay.object.model.ObjectDefinition;
import com.liferay.object.rest.deployer.ObjectDefinitionDeployerUtil;
import com.liferay.object.rest.dto.v1_0.ObjectEntry;
import com.liferay.object.rest.internal.graphql.dto.v1_0.ObjectDefinitionGraphQLDTOContributor;
import com.liferay.object.rest.internal.jaxrs.context.provider.ObjectDefinitionContextProvider;
import com.liferay.object.rest.internal.jaxrs.exception.mapper.ObjectEntryManagerHttpExceptionMapper;
import com.liferay.object.rest.internal.jaxrs.exception.mapper.ObjectEntryValuesExceptionMapper;
import com.liferay.object.rest.internal.jaxrs.exception.mapper.ObjectValidationRuleEngineExceptionMapper;
import com.liferay.object.rest.internal.jaxrs.exception.mapper.RequiredObjectRelationshipExceptionMapper;
import com.liferay.object.rest.internal.resource.v1_0.BaseObjectEntryResourceImpl;
import com.liferay.object.rest.internal.resource.v1_0.ObjectEntryResourceImpl;
import com.liferay.object.rest.manager.v1_0.ObjectEntryManagerTracker;
import com.liferay.object.rest.petra.sql.dsl.expression.FilterPredicateFactory;
import com.liferay.object.rest.resource.v1_0.ObjectEntryResource;
import com.liferay.object.scope.ObjectScopeProvider;
import com.liferay.object.scope.ObjectScopeProviderRegistry;
import com.liferay.object.service.ObjectDefinitionLocalService;
import com.liferay.object.service.ObjectEntryLocalService;
import com.liferay.object.service.ObjectFieldLocalService;
import com.liferay.object.service.ObjectRelationshipLocalService;
import com.liferay.object.service.ObjectRelationshipService;
import com.liferay.object.system.SystemObjectDefinitionMetadata;
import com.liferay.object.system.SystemObjectDefinitionMetadataTracker;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.HashMapDictionaryBuilder;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.vulcan.graphql.dto.GraphQLDTOContributor;

import java.lang.reflect.Method;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Path;
import javax.ws.rs.ext.ExceptionMapper;

import org.apache.cxf.jaxrs.ext.ContextProvider;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.PrototypeServiceFactory;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.cm.Configuration;
import org.osgi.service.cm.ConfigurationAdmin;
import org.osgi.service.component.ComponentFactory;
import org.osgi.service.component.ComponentInstance;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Brian Wing Shun Chan
 */
@Component(service = ObjectDefinitionDeployer.class)
public class ObjectDefinitionDeployerImpl implements ObjectDefinitionDeployer {

	@Override
	public synchronized List<ServiceRegistration<?>> deploy(
		ObjectDefinition objectDefinition) {

		if (objectDefinition.isSystem()) {
			SystemObjectDefinitionMetadata systemObjectDefinitionMetadata =
				_systemObjectDefinitionMetadataTracker.
					getSystemObjectDefinitionMetadata(
						objectDefinition.getName());

			_componentInstancesMap.computeIfAbsent(
				systemObjectDefinitionMetadata.getRESTContextPath(),
				key -> Arrays.asList(
					_relatedObjectEntryResourceFactory.newInstance(
						HashMapDictionaryBuilder.<String, Object>put(
							"api.version", "v1.0"
						).put(
							"osgi.jaxrs.application.select",
							() -> {
								String jaxRsApplicationName =
									systemObjectDefinitionMetadata.
										getJaxRsApplicationName();

								return "(osgi.jaxrs.name=" +
									jaxRsApplicationName + ")";
							}
						).put(
							"osgi.jaxrs.resource", "true"
						).build())));

			return Collections.emptyList();
		}

		String objectDefinitionInstanceKey =
			ObjectDefinitionDeployerUtil.createObjectDefinitionInstanceKey(
				objectDefinition.getCompanyId(),
				objectDefinition.getRESTContextPath());

		ObjectScopeProvider objectScopeProvider =
			_objectScopeProviderRegistry.getObjectScopeProvider(
				objectDefinition.getScope());

		Map<Long, ObjectDefinition> objectDefinitions =
			_objectDefinitionsMap.get(objectDefinitionInstanceKey);

		if (objectDefinitions == null) {
			objectDefinitions = new HashMap<>();

			_objectDefinitionsMap.put(
				objectDefinitionInstanceKey, objectDefinitions);

			_excludeScopedMethods(objectDefinition, objectScopeProvider);

			String osgiJAXRsName =
				ObjectDefinitionDeployerUtil.createOSGIJAXRsName(
					objectDefinition.getCompanyId(),
					objectDefinition.getName());

			_componentInstancesMap.put(
				objectDefinitionInstanceKey,
				Arrays.asList(
					_objectEntryApplicationComponentFactory.newInstance(
						HashMapDictionaryBuilder.<String, Object>put(
							"liferay.jackson", false
						).put(
							"osgi.jaxrs.application.base",
							objectDefinition.getRESTContextPath()
						).put(
							"osgi.jaxrs.extension.select",
							"(osgi.jaxrs.name=Liferay.Vulcan)"
						).put(
							"osgi.jaxrs.name", osgiJAXRsName
						).build())));

			_serviceRegistrationsMap.put(
				objectDefinitionInstanceKey,
				Arrays.asList(
					_bundleContext.registerService(
						ContextProvider.class,
						new ObjectDefinitionContextProvider(this, _portal),
						HashMapDictionaryBuilder.<String, Object>put(
							"enabled", "false"
						).put(
							"osgi.jaxrs.application.select",
							"(osgi.jaxrs.name=" + osgiJAXRsName + ")"
						).put(
							"osgi.jaxrs.extension", "true"
						).put(
							"osgi.jaxrs.name",
							ObjectDefinitionDeployerUtil.createOSGIJAXRsName(
								"ObjectDefinitionContextProvider",
								objectDefinition.getCompanyId(),
								objectDefinition.getName())
						).build()),
					_bundleContext.registerService(
						ExceptionMapper.class,
						new ObjectEntryManagerHttpExceptionMapper(),
						HashMapDictionaryBuilder.<String, Object>put(
							"osgi.jaxrs.application.select",
							"(osgi.jaxrs.name=" + osgiJAXRsName + ")"
						).put(
							"osgi.jaxrs.extension", "true"
						).put(
							"osgi.jaxrs.name",
							ObjectDefinitionDeployerUtil.createOSGIJAXRsName(
								"ObjectEntryManagerHttpExceptionMapper",
								objectDefinition.getCompanyId(),
								objectDefinition.getName())
						).build()),
					_bundleContext.registerService(
						ExceptionMapper.class,
						new ObjectEntryValuesExceptionMapper(),
						HashMapDictionaryBuilder.<String, Object>put(
							"osgi.jaxrs.application.select",
							"(osgi.jaxrs.name=" + osgiJAXRsName + ")"
						).put(
							"osgi.jaxrs.extension", "true"
						).put(
							"osgi.jaxrs.name",
							ObjectDefinitionDeployerUtil.createOSGIJAXRsName(
								"ObjectEntryValuesExceptionMapper",
								objectDefinition.getCompanyId(),
								objectDefinition.getName())
						).build()),
					_bundleContext.registerService(
						ExceptionMapper.class,
						new ObjectValidationRuleEngineExceptionMapper(),
						HashMapDictionaryBuilder.<String, Object>put(
							"osgi.jaxrs.application.select",
							"(osgi.jaxrs.name=" + osgiJAXRsName + ")"
						).put(
							"osgi.jaxrs.extension", "true"
						).put(
							"osgi.jaxrs.name",
							ObjectDefinitionDeployerUtil.createOSGIJAXRsName(
								"ObjectValidationRuleEngineExceptionMapper",
								objectDefinition.getCompanyId(),
								objectDefinition.getName())
						).build()),
					_bundleContext.registerService(
						ExceptionMapper.class,
						new RequiredObjectRelationshipExceptionMapper(),
						HashMapDictionaryBuilder.<String, Object>put(
							"osgi.jaxrs.application.select",
							"(osgi.jaxrs.name=" + osgiJAXRsName + ")"
						).put(
							"osgi.jaxrs.extension", "true"
						).put(
							"osgi.jaxrs.name",
							ObjectDefinitionDeployerUtil.createOSGIJAXRsName(
								"RequiredObjectRelationshipExceptionMapper",
								objectDefinition.getCompanyId(),
								objectDefinition.getName())
						).build()),
					_bundleContext.registerService(
						ObjectEntryResource.class,
						new PrototypeServiceFactory<ObjectEntryResource>() {

							@Override
							public ObjectEntryResource getService(
								Bundle bundle,
								ServiceRegistration<ObjectEntryResource>
									serviceRegistration) {

								return new ObjectEntryResourceImpl(
									_filterPredicateFactory,
									_objectDefinitionLocalService,
									_objectEntryLocalService,
									_objectEntryManagerTracker,
									_objectFieldLocalService,
									_objectRelationshipService,
									_objectScopeProviderRegistry);
							}

							@Override
							public void ungetService(
								Bundle bundle,
								ServiceRegistration<ObjectEntryResource>
									serviceRegistration,
								ObjectEntryResource objectEntryResource) {
							}

						},
						HashMapDictionaryBuilder.<String, Object>put(
							"api.version", "v1.0"
						).put(
							"batch.engine.entity.class.name",
							ObjectEntry.class.getName() + "#" +
								objectDefinition.getName()
						).put(
							"batch.engine.task.item.delegate", "true"
						).put(
							"batch.engine.task.item.delegate.name",
							osgiJAXRsName
						).put(
							"batch.planner.export.enabled", "true"
						).put(
							"batch.planner.import.enabled", "true"
						).put(
							"entity.class.name",
							ObjectEntry.class.getName() + "#" +
								objectDefinition.getName()
						).put(
							"osgi.jaxrs.application.select",
							"(osgi.jaxrs.name=" + osgiJAXRsName + ")"
						).put(
							"osgi.jaxrs.resource", "true"
						).build())));
		}

		objectDefinitions.put(
			objectDefinition.getCompanyId(), objectDefinition);

		return Collections.singletonList(
			_bundleContext.registerService(
				GraphQLDTOContributor.class,
				ObjectDefinitionGraphQLDTOContributor.of(
					_filterPredicateFactory, objectDefinition,
					_objectEntryManagerTracker.getObjectEntryManager(
						objectDefinition.getStorageType()),
					_objectFieldLocalService, _objectRelationshipLocalService,
					objectScopeProvider),
				HashMapDictionaryBuilder.<String, Object>put(
					"dto.name", objectDefinition.getDBTableName()
				).build()));
	}

	public ObjectDefinition getObjectDefinition(
		long companyId, String restContextPath) {

		Map<Long, ObjectDefinition> objectDefinitions =
			_objectDefinitionsMap.get(
				ObjectDefinitionDeployerUtil.createObjectDefinitionInstanceKey(
					companyId, restContextPath));

		if (objectDefinitions != null) {
			return objectDefinitions.get(companyId);
		}

		return null;
	}

	@Override
	public synchronized void undeploy(ObjectDefinition objectDefinition) {
		String objectDefinitionInstanceKey =
			ObjectDefinitionDeployerUtil.createObjectDefinitionInstanceKey(
				objectDefinition.getCompanyId(),
				objectDefinition.getRESTContextPath());

		Map<Long, ObjectDefinition> objectDefinitions =
			_objectDefinitionsMap.get(objectDefinitionInstanceKey);

		if (objectDefinitions != null) {
			objectDefinitions.remove(objectDefinition.getCompanyId());

			if (objectDefinitions.isEmpty()) {
				_objectDefinitionsMap.remove(objectDefinitionInstanceKey);
			}
		}

		if (_objectDefinitionsMap.containsKey(objectDefinitionInstanceKey)) {
			return;
		}

		List<ComponentInstance> componentInstances =
			_componentInstancesMap.remove(objectDefinitionInstanceKey);

		if (componentInstances != null) {
			for (ComponentInstance componentInstance : componentInstances) {
				componentInstance.dispose();
			}
		}

		List<ServiceRegistration<?>> serviceRegistrations =
			_serviceRegistrationsMap.remove(objectDefinitionInstanceKey);

		if (serviceRegistrations != null) {
			for (ServiceRegistration<?> serviceRegistration :
					serviceRegistrations) {

				serviceRegistration.unregister();
			}
		}
	}

	@Activate
	protected void activate(BundleContext bundleContext) {
		_bundleContext = bundleContext;
	}

	private void _excludeScopedMethods(
		ObjectDefinition objectDefinition,
		ObjectScopeProvider objectScopeProvider) {

		try {
			String factoryPid =
				"com.liferay.portal.vulcan.internal.configuration." +
					"VulcanConfiguration";

			Configuration configuration =
				_configurationAdmin.createFactoryConfiguration(factoryPid, "?");

			Method[] methods = BaseObjectEntryResourceImpl.class.getMethods();

			List<String> excludedOperationIds = new ArrayList<>();

			for (Method method : methods) {
				Path path = method.getAnnotation(Path.class);

				if (path == null) {
					continue;
				}

				String value = path.value();

				boolean groupAware = objectScopeProvider.isGroupAware();
				boolean hasScope = value.contains("scopes");

				if ((!groupAware && hasScope) ||
					(groupAware && !hasScope &&
					 !value.startsWith("/{objectEntryId}"))) {

					excludedOperationIds.add(method.getName());
				}
			}

			configuration.update(
				HashMapDictionaryBuilder.put(
					"excludedOperationIds",
					StringUtil.merge(excludedOperationIds, ",")
				).put(
					"path", objectDefinition.getRESTContextPath()
				).build());
		}
		catch (Exception exception) {
			if (_log.isDebugEnabled()) {
				_log.debug(exception);
			}
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ObjectDefinitionDeployerImpl.class);

	private BundleContext _bundleContext;
	private final Map<String, List<ComponentInstance>> _componentInstancesMap =
		new HashMap<>();

	@Reference
	private ConfigurationAdmin _configurationAdmin;

	@Reference
	private FilterPredicateFactory _filterPredicateFactory;

	@Reference
	private ObjectDefinitionLocalService _objectDefinitionLocalService;

	private final Map<String, Map<Long, ObjectDefinition>>
		_objectDefinitionsMap = new HashMap<>();

	@Reference(
		target = "(component.factory=com.liferay.object.internal.jaxrs.application.ObjectEntryApplication)"
	)
	private ComponentFactory _objectEntryApplicationComponentFactory;

	@Reference
	private ObjectEntryLocalService _objectEntryLocalService;

	@Reference
	private ObjectEntryManagerTracker _objectEntryManagerTracker;

	@Reference
	private ObjectFieldLocalService _objectFieldLocalService;

	@Reference
	private ObjectRelationshipLocalService _objectRelationshipLocalService;

	@Reference
	private ObjectRelationshipService _objectRelationshipService;

	@Reference
	private ObjectScopeProviderRegistry _objectScopeProviderRegistry;

	@Reference
	private Portal _portal;

	@Reference(
		target = "(component.factory=com.liferay.object.rest.internal.resource.v1_0.RelatedObjectEntryResource)"
	)
	private ComponentFactory _relatedObjectEntryResourceFactory;

	private final Map<String, List<ServiceRegistration<?>>>
		_serviceRegistrationsMap = new HashMap<>();

	@Reference
	private SystemObjectDefinitionMetadataTracker
		_systemObjectDefinitionMetadataTracker;

}