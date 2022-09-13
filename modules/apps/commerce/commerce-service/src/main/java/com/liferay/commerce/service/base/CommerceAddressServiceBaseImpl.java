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

package com.liferay.commerce.service.base;

import com.liferay.commerce.model.CommerceAddress;
import com.liferay.commerce.service.CommerceAddressService;
import com.liferay.commerce.service.CommerceAddressServiceUtil;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.service.BaseServiceImpl;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.lang.reflect.Field;

/**
 * Provides the base implementation for the commerce address remote service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.commerce.service.impl.CommerceAddressServiceImpl}.
 * </p>
 *
 * @author Alessio Antonio Rendina
 * @see com.liferay.commerce.service.impl.CommerceAddressServiceImpl
 * @generated
 */
public abstract class CommerceAddressServiceBaseImpl
	extends BaseServiceImpl
	implements CommerceAddressService, IdentifiableOSGiService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>CommerceAddressService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>CommerceAddressServiceUtil</code>.
	 */

	/**
	 * Returns the commerce address local service.
	 *
	 * @return the commerce address local service
	 */
	public com.liferay.commerce.service.CommerceAddressLocalService
		getCommerceAddressLocalService() {

		return commerceAddressLocalService;
	}

	/**
	 * Sets the commerce address local service.
	 *
	 * @param commerceAddressLocalService the commerce address local service
	 */
	public void setCommerceAddressLocalService(
		com.liferay.commerce.service.CommerceAddressLocalService
			commerceAddressLocalService) {

		this.commerceAddressLocalService = commerceAddressLocalService;
	}

	/**
	 * Returns the commerce address remote service.
	 *
	 * @return the commerce address remote service
	 */
	public CommerceAddressService getCommerceAddressService() {
		return commerceAddressService;
	}

	/**
	 * Sets the commerce address remote service.
	 *
	 * @param commerceAddressService the commerce address remote service
	 */
	public void setCommerceAddressService(
		CommerceAddressService commerceAddressService) {

		this.commerceAddressService = commerceAddressService;
	}

	/**
	 * Returns the counter local service.
	 *
	 * @return the counter local service
	 */
	public com.liferay.counter.kernel.service.CounterLocalService
		getCounterLocalService() {

		return counterLocalService;
	}

	/**
	 * Sets the counter local service.
	 *
	 * @param counterLocalService the counter local service
	 */
	public void setCounterLocalService(
		com.liferay.counter.kernel.service.CounterLocalService
			counterLocalService) {

		this.counterLocalService = counterLocalService;
	}

	public void afterPropertiesSet() {
		_setServiceUtilService(commerceAddressService);
	}

	public void destroy() {
		_setServiceUtilService(null);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return CommerceAddressService.class.getName();
	}

	protected Class<?> getModelClass() {
		return CommerceAddress.class;
	}

	protected String getModelClassName() {
		return CommerceAddress.class.getName();
	}

	private void _setServiceUtilService(
		CommerceAddressService commerceAddressService) {

		try {
			Field field = CommerceAddressServiceUtil.class.getDeclaredField(
				"_service");

			field.setAccessible(true);

			field.set(null, commerceAddressService);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@BeanReference(
		type = com.liferay.commerce.service.CommerceAddressLocalService.class
	)
	protected com.liferay.commerce.service.CommerceAddressLocalService
		commerceAddressLocalService;

	@BeanReference(type = CommerceAddressService.class)
	protected CommerceAddressService commerceAddressService;

	@ServiceReference(
		type = com.liferay.counter.kernel.service.CounterLocalService.class
	)
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceAddressLocalServiceBaseImpl.class);

}