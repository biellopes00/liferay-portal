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

package com.liferay.headless.commerce.delivery.order.dto.v1_0;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.liferay.petra.function.UnsafeSupplier;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLField;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLName;
import com.liferay.portal.vulcan.util.ObjectMapperUtil;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javax.annotation.Generated;

import javax.validation.Valid;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Andrea Sbarra
 * @generated
 */
@Generated("")
@GraphQLName("PlacedOrderItem")
@JsonFilter("Liferay.Vulcan")
@XmlRootElement(name = "PlacedOrderItem")
public class PlacedOrderItem implements Serializable {

	public static PlacedOrderItem toDTO(String json) {
		return ObjectMapperUtil.readValue(PlacedOrderItem.class, json);
	}

	public static PlacedOrderItem unsafeToDTO(String json) {
		return ObjectMapperUtil.unsafeReadValue(PlacedOrderItem.class, json);
	}

	@Schema
	public String getAdaptiveMediaImageHTMLTag() {
		return adaptiveMediaImageHTMLTag;
	}

	public void setAdaptiveMediaImageHTMLTag(String adaptiveMediaImageHTMLTag) {
		this.adaptiveMediaImageHTMLTag = adaptiveMediaImageHTMLTag;
	}

	@JsonIgnore
	public void setAdaptiveMediaImageHTMLTag(
		UnsafeSupplier<String, Exception>
			adaptiveMediaImageHTMLTagUnsafeSupplier) {

		try {
			adaptiveMediaImageHTMLTag =
				adaptiveMediaImageHTMLTagUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	protected String adaptiveMediaImageHTMLTag;

	@Schema
	@Valid
	public Map<String, ?> getCustomFields() {
		return customFields;
	}

	public void setCustomFields(Map<String, ?> customFields) {
		this.customFields = customFields;
	}

	@JsonIgnore
	public void setCustomFields(
		UnsafeSupplier<Map<String, ?>, Exception> customFieldsUnsafeSupplier) {

		try {
			customFields = customFieldsUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	protected Map<String, ?> customFields;

	@Schema
	public String[] getErrorMessages() {
		return errorMessages;
	}

	public void setErrorMessages(String[] errorMessages) {
		this.errorMessages = errorMessages;
	}

	@JsonIgnore
	public void setErrorMessages(
		UnsafeSupplier<String[], Exception> errorMessagesUnsafeSupplier) {

		try {
			errorMessages = errorMessagesUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	protected String[] errorMessages;

	@Schema
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@JsonIgnore
	public void setId(UnsafeSupplier<Long, Exception> idUnsafeSupplier) {
		try {
			id = idUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	protected Long id;

	@Schema
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@JsonIgnore
	public void setName(UnsafeSupplier<String, Exception> nameUnsafeSupplier) {
		try {
			name = nameUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	protected String name;

	@Schema
	public String getOptions() {
		return options;
	}

	public void setOptions(String options) {
		this.options = options;
	}

	@JsonIgnore
	public void setOptions(
		UnsafeSupplier<String, Exception> optionsUnsafeSupplier) {

		try {
			options = optionsUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	protected String options;

	@Schema
	public Long getParentOrderItemId() {
		return parentOrderItemId;
	}

	public void setParentOrderItemId(Long parentOrderItemId) {
		this.parentOrderItemId = parentOrderItemId;
	}

	@JsonIgnore
	public void setParentOrderItemId(
		UnsafeSupplier<Long, Exception> parentOrderItemIdUnsafeSupplier) {

		try {
			parentOrderItemId = parentOrderItemIdUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	protected Long parentOrderItemId;

	@Schema
	@Valid
	public PlacedOrderItemShipment[] getPlacedOrderItemShipments() {
		return placedOrderItemShipments;
	}

	public void setPlacedOrderItemShipments(
		PlacedOrderItemShipment[] placedOrderItemShipments) {

		this.placedOrderItemShipments = placedOrderItemShipments;
	}

	@JsonIgnore
	public void setPlacedOrderItemShipments(
		UnsafeSupplier<PlacedOrderItemShipment[], Exception>
			placedOrderItemShipmentsUnsafeSupplier) {

		try {
			placedOrderItemShipments =
				placedOrderItemShipmentsUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	protected PlacedOrderItemShipment[] placedOrderItemShipments;

	@Schema
	@Valid
	public PlacedOrderItem[] getPlacedOrderItems() {
		return placedOrderItems;
	}

	public void setPlacedOrderItems(PlacedOrderItem[] placedOrderItems) {
		this.placedOrderItems = placedOrderItems;
	}

	@JsonIgnore
	public void setPlacedOrderItems(
		UnsafeSupplier<PlacedOrderItem[], Exception>
			placedOrderItemsUnsafeSupplier) {

		try {
			placedOrderItems = placedOrderItemsUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	protected PlacedOrderItem[] placedOrderItems;

	@Schema
	@Valid
	public Price getPrice() {
		return price;
	}

	public void setPrice(Price price) {
		this.price = price;
	}

	@JsonIgnore
	public void setPrice(UnsafeSupplier<Price, Exception> priceUnsafeSupplier) {
		try {
			price = priceUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	protected Price price;

	@Schema
	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	@JsonIgnore
	public void setProductId(
		UnsafeSupplier<Long, Exception> productIdUnsafeSupplier) {

		try {
			productId = productIdUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	protected Long productId;

	@Schema(
		example = "{en_US=product-url-us, hr_HR=product-url-hr, hu_HU=product-url-hu}"
	)
	@Valid
	public Map<String, String> getProductURLs() {
		return productURLs;
	}

	public void setProductURLs(Map<String, String> productURLs) {
		this.productURLs = productURLs;
	}

	@JsonIgnore
	public void setProductURLs(
		UnsafeSupplier<Map<String, String>, Exception>
			productURLsUnsafeSupplier) {

		try {
			productURLs = productURLsUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	protected Map<String, String> productURLs;

	@Schema
	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@JsonIgnore
	public void setQuantity(
		UnsafeSupplier<Integer, Exception> quantityUnsafeSupplier) {

		try {
			quantity = quantityUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	protected Integer quantity;

	@Schema
	@Valid
	public Settings getSettings() {
		return settings;
	}

	public void setSettings(Settings settings) {
		this.settings = settings;
	}

	@JsonIgnore
	public void setSettings(
		UnsafeSupplier<Settings, Exception> settingsUnsafeSupplier) {

		try {
			settings = settingsUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	protected Settings settings;

	@Schema
	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	@JsonIgnore
	public void setSku(UnsafeSupplier<String, Exception> skuUnsafeSupplier) {
		try {
			sku = skuUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	protected String sku;

	@Schema
	public Long getSkuId() {
		return skuId;
	}

	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}

	@JsonIgnore
	public void setSkuId(UnsafeSupplier<Long, Exception> skuIdUnsafeSupplier) {
		try {
			skuId = skuIdUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	protected Long skuId;

	@Schema(example = "true")
	public Boolean getSubscription() {
		return subscription;
	}

	public void setSubscription(Boolean subscription) {
		this.subscription = subscription;
	}

	@JsonIgnore
	public void setSubscription(
		UnsafeSupplier<Boolean, Exception> subscriptionUnsafeSupplier) {

		try {
			subscription = subscriptionUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	protected Boolean subscription;

	@Schema
	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	@JsonIgnore
	public void setThumbnail(
		UnsafeSupplier<String, Exception> thumbnailUnsafeSupplier) {

		try {
			thumbnail = thumbnailUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	protected String thumbnail;

	@Schema
	public Boolean getValid() {
		return valid;
	}

	public void setValid(Boolean valid) {
		this.valid = valid;
	}

	@JsonIgnore
	public void setValid(
		UnsafeSupplier<Boolean, Exception> validUnsafeSupplier) {

		try {
			valid = validUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	protected Boolean valid;

	@Schema
	public String[] getVirtualItemURLs() {
		return virtualItemURLs;
	}

	public void setVirtualItemURLs(String[] virtualItemURLs) {
		this.virtualItemURLs = virtualItemURLs;
	}

	@JsonIgnore
	public void setVirtualItemURLs(
		UnsafeSupplier<String[], Exception> virtualItemURLsUnsafeSupplier) {

		try {
			virtualItemURLs = virtualItemURLsUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	protected String[] virtualItemURLs;

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof PlacedOrderItem)) {
			return false;
		}

		PlacedOrderItem placedOrderItem = (PlacedOrderItem)object;

		return Objects.equals(toString(), placedOrderItem.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		StringBundler sb = new StringBundler();

		sb.append("{");

		if (adaptiveMediaImageHTMLTag != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"adaptiveMediaImageHTMLTag\": ");

			sb.append("\"");

			sb.append(_escape(adaptiveMediaImageHTMLTag));

			sb.append("\"");
		}

		if (customFields != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"customFields\": ");

			sb.append(_toJSON(customFields));
		}

		if (errorMessages != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"errorMessages\": ");

			sb.append("[");

			for (int i = 0; i < errorMessages.length; i++) {
				sb.append("\"");

				sb.append(_escape(errorMessages[i]));

				sb.append("\"");

				if ((i + 1) < errorMessages.length) {
					sb.append(", ");
				}
			}

			sb.append("]");
		}

		if (id != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"id\": ");

			sb.append(id);
		}

		if (name != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"name\": ");

			sb.append("\"");

			sb.append(_escape(name));

			sb.append("\"");
		}

		if (options != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"options\": ");

			sb.append("\"");

			sb.append(_escape(options));

			sb.append("\"");
		}

		if (parentOrderItemId != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"parentOrderItemId\": ");

			sb.append(parentOrderItemId);
		}

		if (placedOrderItemShipments != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"placedOrderItemShipments\": ");

			sb.append("[");

			for (int i = 0; i < placedOrderItemShipments.length; i++) {
				sb.append(String.valueOf(placedOrderItemShipments[i]));

				if ((i + 1) < placedOrderItemShipments.length) {
					sb.append(", ");
				}
			}

			sb.append("]");
		}

		if (placedOrderItems != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"placedOrderItems\": ");

			sb.append("[");

			for (int i = 0; i < placedOrderItems.length; i++) {
				sb.append(String.valueOf(placedOrderItems[i]));

				if ((i + 1) < placedOrderItems.length) {
					sb.append(", ");
				}
			}

			sb.append("]");
		}

		if (price != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"price\": ");

			sb.append(String.valueOf(price));
		}

		if (productId != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"productId\": ");

			sb.append(productId);
		}

		if (productURLs != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"productURLs\": ");

			sb.append(_toJSON(productURLs));
		}

		if (quantity != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"quantity\": ");

			sb.append(quantity);
		}

		if (settings != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"settings\": ");

			sb.append(String.valueOf(settings));
		}

		if (sku != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"sku\": ");

			sb.append("\"");

			sb.append(_escape(sku));

			sb.append("\"");
		}

		if (skuId != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"skuId\": ");

			sb.append(skuId);
		}

		if (subscription != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"subscription\": ");

			sb.append(subscription);
		}

		if (thumbnail != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"thumbnail\": ");

			sb.append("\"");

			sb.append(_escape(thumbnail));

			sb.append("\"");
		}

		if (valid != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"valid\": ");

			sb.append(valid);
		}

		if (virtualItemURLs != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"virtualItemURLs\": ");

			sb.append("[");

			for (int i = 0; i < virtualItemURLs.length; i++) {
				sb.append("\"");

				sb.append(_escape(virtualItemURLs[i]));

				sb.append("\"");

				if ((i + 1) < virtualItemURLs.length) {
					sb.append(", ");
				}
			}

			sb.append("]");
		}

		sb.append("}");

		return sb.toString();
	}

	@Schema(
		accessMode = Schema.AccessMode.READ_ONLY,
		defaultValue = "com.liferay.headless.commerce.delivery.order.dto.v1_0.PlacedOrderItem",
		name = "x-class-name"
	)
	public String xClassName;

	private static String _escape(Object object) {
		return StringUtil.replace(
			String.valueOf(object), _JSON_ESCAPE_STRINGS[0],
			_JSON_ESCAPE_STRINGS[1]);
	}

	private static boolean _isArray(Object value) {
		if (value == null) {
			return false;
		}

		Class<?> clazz = value.getClass();

		return clazz.isArray();
	}

	private static String _toJSON(Map<String, ?> map) {
		StringBuilder sb = new StringBuilder("{");

		@SuppressWarnings("unchecked")
		Set set = map.entrySet();

		@SuppressWarnings("unchecked")
		Iterator<Map.Entry<String, ?>> iterator = set.iterator();

		while (iterator.hasNext()) {
			Map.Entry<String, ?> entry = iterator.next();

			sb.append("\"");
			sb.append(_escape(entry.getKey()));
			sb.append("\": ");

			Object value = entry.getValue();

			if (_isArray(value)) {
				sb.append("[");

				Object[] valueArray = (Object[])value;

				for (int i = 0; i < valueArray.length; i++) {
					if (valueArray[i] instanceof String) {
						sb.append("\"");
						sb.append(valueArray[i]);
						sb.append("\"");
					}
					else {
						sb.append(valueArray[i]);
					}

					if ((i + 1) < valueArray.length) {
						sb.append(", ");
					}
				}

				sb.append("]");
			}
			else if (value instanceof Map) {
				sb.append(_toJSON((Map<String, ?>)value));
			}
			else if (value instanceof String) {
				sb.append("\"");
				sb.append(_escape(value));
				sb.append("\"");
			}
			else {
				sb.append(value);
			}

			if (iterator.hasNext()) {
				sb.append(", ");
			}
		}

		sb.append("}");

		return sb.toString();
	}

	private static final String[][] _JSON_ESCAPE_STRINGS = {
		{"\\", "\"", "\b", "\f", "\n", "\r", "\t"},
		{"\\\\", "\\\"", "\\b", "\\f", "\\n", "\\r", "\\t"}
	};

}