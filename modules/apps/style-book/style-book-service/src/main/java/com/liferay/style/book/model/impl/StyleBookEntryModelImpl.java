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

package com.liferay.style.book.model.impl;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.style.book.model.StyleBookEntry;
import com.liferay.style.book.model.StyleBookEntryModel;
import com.liferay.style.book.model.StyleBookEntrySoap;
import com.liferay.style.book.model.StyleBookEntryVersion;

import java.io.Serializable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * The base model implementation for the StyleBookEntry service. Represents a row in the &quot;StyleBookEntry&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>StyleBookEntryModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link StyleBookEntryImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see StyleBookEntryImpl
 * @generated
 */
@JSON(strict = true)
public class StyleBookEntryModelImpl
	extends BaseModelImpl<StyleBookEntry> implements StyleBookEntryModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a style book entry model instance should use the <code>StyleBookEntry</code> interface instead.
	 */
	public static final String TABLE_NAME = "StyleBookEntry";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"headId", Types.BIGINT},
		{"head", Types.BOOLEAN}, {"styleBookEntryId", Types.BIGINT},
		{"groupId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"userId", Types.BIGINT}, {"userName", Types.VARCHAR},
		{"createDate", Types.TIMESTAMP},
		{"defaultStyleBookEntry", Types.BOOLEAN},
		{"frontendTokensValues", Types.CLOB}, {"name", Types.VARCHAR},
		{"previewFileEntryId", Types.BIGINT},
		{"styleBookEntryKey", Types.VARCHAR}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("headId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("head", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("styleBookEntryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("defaultStyleBookEntry", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("frontendTokensValues", Types.CLOB);
		TABLE_COLUMNS_MAP.put("name", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("previewFileEntryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("styleBookEntryKey", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE =
		"create table StyleBookEntry (mvccVersion LONG default 0 not null,headId LONG,head BOOLEAN,styleBookEntryId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,defaultStyleBookEntry BOOLEAN,frontendTokensValues TEXT null,name VARCHAR(75) null,previewFileEntryId LONG,styleBookEntryKey VARCHAR(75) null)";

	public static final String TABLE_SQL_DROP = "drop table StyleBookEntry";

	public static final String ORDER_BY_JPQL =
		" ORDER BY styleBookEntry.createDate DESC";

	public static final String ORDER_BY_SQL =
		" ORDER BY StyleBookEntry.createDate DESC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)
	 */
	@Deprecated
	public static final long DEFAULTSTYLEBOOKENTRY_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)
	 */
	@Deprecated
	public static final long GROUPID_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)
	 */
	@Deprecated
	public static final long HEAD_COLUMN_BITMASK = 4L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)
	 */
	@Deprecated
	public static final long HEADID_COLUMN_BITMASK = 8L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)
	 */
	@Deprecated
	public static final long NAME_COLUMN_BITMASK = 16L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)
	 */
	@Deprecated
	public static final long STYLEBOOKENTRYKEY_COLUMN_BITMASK = 32L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)
	 */
	@Deprecated
	public static final long CREATEDATE_COLUMN_BITMASK = 64L;

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static void setEntityCacheEnabled(boolean entityCacheEnabled) {
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static void setFinderCacheEnabled(boolean finderCacheEnabled) {
	}

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static StyleBookEntry toModel(StyleBookEntrySoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		StyleBookEntry model = new StyleBookEntryImpl();

		model.setMvccVersion(soapModel.getMvccVersion());
		model.setHeadId(soapModel.getHeadId());
		model.setStyleBookEntryId(soapModel.getStyleBookEntryId());
		model.setGroupId(soapModel.getGroupId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setDefaultStyleBookEntry(soapModel.isDefaultStyleBookEntry());
		model.setFrontendTokensValues(soapModel.getFrontendTokensValues());
		model.setName(soapModel.getName());
		model.setPreviewFileEntryId(soapModel.getPreviewFileEntryId());
		model.setStyleBookEntryKey(soapModel.getStyleBookEntryKey());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static List<StyleBookEntry> toModels(
		StyleBookEntrySoap[] soapModels) {

		if (soapModels == null) {
			return null;
		}

		List<StyleBookEntry> models = new ArrayList<StyleBookEntry>(
			soapModels.length);

		for (StyleBookEntrySoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public StyleBookEntryModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _styleBookEntryId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setStyleBookEntryId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _styleBookEntryId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return StyleBookEntry.class;
	}

	@Override
	public String getModelClassName() {
		return StyleBookEntry.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<StyleBookEntry, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<StyleBookEntry, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<StyleBookEntry, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((StyleBookEntry)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<StyleBookEntry, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<StyleBookEntry, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(StyleBookEntry)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<StyleBookEntry, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<StyleBookEntry, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, StyleBookEntry>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			StyleBookEntry.class.getClassLoader(), StyleBookEntry.class,
			ModelWrapper.class);

		try {
			Constructor<StyleBookEntry> constructor =
				(Constructor<StyleBookEntry>)proxyClass.getConstructor(
					InvocationHandler.class);

			return invocationHandler -> {
				try {
					return constructor.newInstance(invocationHandler);
				}
				catch (ReflectiveOperationException
							reflectiveOperationException) {

					throw new InternalError(reflectiveOperationException);
				}
			};
		}
		catch (NoSuchMethodException noSuchMethodException) {
			throw new InternalError(noSuchMethodException);
		}
	}

	private static final Map<String, Function<StyleBookEntry, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<StyleBookEntry, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<StyleBookEntry, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<StyleBookEntry, Object>>();
		Map<String, BiConsumer<StyleBookEntry, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<StyleBookEntry, ?>>();

		attributeGetterFunctions.put(
			"mvccVersion", StyleBookEntry::getMvccVersion);
		attributeSetterBiConsumers.put(
			"mvccVersion",
			(BiConsumer<StyleBookEntry, Long>)StyleBookEntry::setMvccVersion);
		attributeGetterFunctions.put("headId", StyleBookEntry::getHeadId);
		attributeSetterBiConsumers.put(
			"headId",
			(BiConsumer<StyleBookEntry, Long>)StyleBookEntry::setHeadId);
		attributeGetterFunctions.put(
			"styleBookEntryId", StyleBookEntry::getStyleBookEntryId);
		attributeSetterBiConsumers.put(
			"styleBookEntryId",
			(BiConsumer<StyleBookEntry, Long>)
				StyleBookEntry::setStyleBookEntryId);
		attributeGetterFunctions.put("groupId", StyleBookEntry::getGroupId);
		attributeSetterBiConsumers.put(
			"groupId",
			(BiConsumer<StyleBookEntry, Long>)StyleBookEntry::setGroupId);
		attributeGetterFunctions.put("companyId", StyleBookEntry::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId",
			(BiConsumer<StyleBookEntry, Long>)StyleBookEntry::setCompanyId);
		attributeGetterFunctions.put("userId", StyleBookEntry::getUserId);
		attributeSetterBiConsumers.put(
			"userId",
			(BiConsumer<StyleBookEntry, Long>)StyleBookEntry::setUserId);
		attributeGetterFunctions.put("userName", StyleBookEntry::getUserName);
		attributeSetterBiConsumers.put(
			"userName",
			(BiConsumer<StyleBookEntry, String>)StyleBookEntry::setUserName);
		attributeGetterFunctions.put(
			"createDate", StyleBookEntry::getCreateDate);
		attributeSetterBiConsumers.put(
			"createDate",
			(BiConsumer<StyleBookEntry, Date>)StyleBookEntry::setCreateDate);
		attributeGetterFunctions.put(
			"defaultStyleBookEntry", StyleBookEntry::getDefaultStyleBookEntry);
		attributeSetterBiConsumers.put(
			"defaultStyleBookEntry",
			(BiConsumer<StyleBookEntry, Boolean>)
				StyleBookEntry::setDefaultStyleBookEntry);
		attributeGetterFunctions.put(
			"frontendTokensValues", StyleBookEntry::getFrontendTokensValues);
		attributeSetterBiConsumers.put(
			"frontendTokensValues",
			(BiConsumer<StyleBookEntry, String>)
				StyleBookEntry::setFrontendTokensValues);
		attributeGetterFunctions.put("name", StyleBookEntry::getName);
		attributeSetterBiConsumers.put(
			"name",
			(BiConsumer<StyleBookEntry, String>)StyleBookEntry::setName);
		attributeGetterFunctions.put(
			"previewFileEntryId", StyleBookEntry::getPreviewFileEntryId);
		attributeSetterBiConsumers.put(
			"previewFileEntryId",
			(BiConsumer<StyleBookEntry, Long>)
				StyleBookEntry::setPreviewFileEntryId);
		attributeGetterFunctions.put(
			"styleBookEntryKey", StyleBookEntry::getStyleBookEntryKey);
		attributeSetterBiConsumers.put(
			"styleBookEntryKey",
			(BiConsumer<StyleBookEntry, String>)
				StyleBookEntry::setStyleBookEntryKey);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@Override
	public void populateVersionModel(
		StyleBookEntryVersion styleBookEntryVersion) {

		styleBookEntryVersion.setGroupId(getGroupId());
		styleBookEntryVersion.setCompanyId(getCompanyId());
		styleBookEntryVersion.setUserId(getUserId());
		styleBookEntryVersion.setUserName(getUserName());
		styleBookEntryVersion.setCreateDate(getCreateDate());
		styleBookEntryVersion.setDefaultStyleBookEntry(
			getDefaultStyleBookEntry());
		styleBookEntryVersion.setFrontendTokensValues(
			getFrontendTokensValues());
		styleBookEntryVersion.setName(getName());
		styleBookEntryVersion.setPreviewFileEntryId(getPreviewFileEntryId());
		styleBookEntryVersion.setStyleBookEntryKey(getStyleBookEntryKey());
	}

	@JSON
	@Override
	public long getMvccVersion() {
		return _mvccVersion;
	}

	@Override
	public void setMvccVersion(long mvccVersion) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_mvccVersion = mvccVersion;
	}

	@JSON
	@Override
	public long getHeadId() {
		return _headId;
	}

	@Override
	public void setHeadId(long headId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		if (headId >= 0) {
			setHead(false);
		}
		else {
			setHead(true);
		}

		_headId = headId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalHeadId() {
		return GetterUtil.getLong(getColumnOriginalValue("headId"));
	}

	@JSON(include = false)
	public boolean getHead() {
		return _head;
	}

	@Override
	public boolean isHead() {
		return _head;
	}

	public void setHead(boolean head) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_head = head;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public boolean getOriginalHead() {
		return GetterUtil.getBoolean(getColumnOriginalValue("head"));
	}

	@JSON
	@Override
	public long getStyleBookEntryId() {
		return _styleBookEntryId;
	}

	@Override
	public void setStyleBookEntryId(long styleBookEntryId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_styleBookEntryId = styleBookEntryId;
	}

	@JSON
	@Override
	public long getGroupId() {
		return _groupId;
	}

	@Override
	public void setGroupId(long groupId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_groupId = groupId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalGroupId() {
		return GetterUtil.getLong(getColumnOriginalValue("groupId"));
	}

	@JSON
	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_companyId = companyId;
	}

	@JSON
	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_userId = userId;
	}

	@Override
	public String getUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getUserId());

			return user.getUuid();
		}
		catch (PortalException portalException) {
			return "";
		}
	}

	@Override
	public void setUserUuid(String userUuid) {
	}

	@JSON
	@Override
	public String getUserName() {
		if (_userName == null) {
			return "";
		}
		else {
			return _userName;
		}
	}

	@Override
	public void setUserName(String userName) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_userName = userName;
	}

	@JSON
	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_createDate = createDate;
	}

	@JSON
	@Override
	public boolean getDefaultStyleBookEntry() {
		return _defaultStyleBookEntry;
	}

	@JSON
	@Override
	public boolean isDefaultStyleBookEntry() {
		return _defaultStyleBookEntry;
	}

	@Override
	public void setDefaultStyleBookEntry(boolean defaultStyleBookEntry) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_defaultStyleBookEntry = defaultStyleBookEntry;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public boolean getOriginalDefaultStyleBookEntry() {
		return GetterUtil.getBoolean(
			getColumnOriginalValue("defaultStyleBookEntry"));
	}

	@JSON
	@Override
	public String getFrontendTokensValues() {
		if (_frontendTokensValues == null) {
			return "";
		}
		else {
			return _frontendTokensValues;
		}
	}

	@Override
	public void setFrontendTokensValues(String frontendTokensValues) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_frontendTokensValues = frontendTokensValues;
	}

	@JSON
	@Override
	public String getName() {
		if (_name == null) {
			return "";
		}
		else {
			return _name;
		}
	}

	@Override
	public void setName(String name) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_name = name;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public String getOriginalName() {
		return getColumnOriginalValue("name");
	}

	@JSON
	@Override
	public long getPreviewFileEntryId() {
		return _previewFileEntryId;
	}

	@Override
	public void setPreviewFileEntryId(long previewFileEntryId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_previewFileEntryId = previewFileEntryId;
	}

	@JSON
	@Override
	public String getStyleBookEntryKey() {
		if (_styleBookEntryKey == null) {
			return "";
		}
		else {
			return _styleBookEntryKey;
		}
	}

	@Override
	public void setStyleBookEntryKey(String styleBookEntryKey) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_styleBookEntryKey = styleBookEntryKey;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public String getOriginalStyleBookEntryKey() {
		return getColumnOriginalValue("styleBookEntryKey");
	}

	public long getColumnBitmask() {
		if (_columnBitmask > 0) {
			return _columnBitmask;
		}

		if ((_columnOriginalValues == null) ||
			(_columnOriginalValues == Collections.EMPTY_MAP)) {

			return 0;
		}

		for (Map.Entry<String, Object> entry :
				_columnOriginalValues.entrySet()) {

			if (entry.getValue() != getColumnValue(entry.getKey())) {
				_columnBitmask |= _columnBitmasks.get(entry.getKey());
			}
		}

		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), StyleBookEntry.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public StyleBookEntry toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, StyleBookEntry>
				escapedModelProxyProviderFunction =
					EscapedModelProxyProviderFunctionHolder.
						_escapedModelProxyProviderFunction;

			_escapedModel = escapedModelProxyProviderFunction.apply(
				new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		StyleBookEntryImpl styleBookEntryImpl = new StyleBookEntryImpl();

		styleBookEntryImpl.setMvccVersion(getMvccVersion());
		styleBookEntryImpl.setHeadId(getHeadId());
		styleBookEntryImpl.setStyleBookEntryId(getStyleBookEntryId());
		styleBookEntryImpl.setGroupId(getGroupId());
		styleBookEntryImpl.setCompanyId(getCompanyId());
		styleBookEntryImpl.setUserId(getUserId());
		styleBookEntryImpl.setUserName(getUserName());
		styleBookEntryImpl.setCreateDate(getCreateDate());
		styleBookEntryImpl.setDefaultStyleBookEntry(isDefaultStyleBookEntry());
		styleBookEntryImpl.setFrontendTokensValues(getFrontendTokensValues());
		styleBookEntryImpl.setName(getName());
		styleBookEntryImpl.setPreviewFileEntryId(getPreviewFileEntryId());
		styleBookEntryImpl.setStyleBookEntryKey(getStyleBookEntryKey());

		styleBookEntryImpl.resetOriginalValues();

		return styleBookEntryImpl;
	}

	@Override
	public int compareTo(StyleBookEntry styleBookEntry) {
		int value = 0;

		value = DateUtil.compareTo(
			getCreateDate(), styleBookEntry.getCreateDate());

		value = value * -1;

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof StyleBookEntry)) {
			return false;
		}

		StyleBookEntry styleBookEntry = (StyleBookEntry)object;

		long primaryKey = styleBookEntry.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	@Override
	public boolean isEntityCacheEnabled() {
		return true;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	@Override
	public boolean isFinderCacheEnabled() {
		return true;
	}

	@Override
	public void resetOriginalValues() {
		_columnOriginalValues = Collections.emptyMap();

		_columnBitmask = 0;
	}

	@Override
	public CacheModel<StyleBookEntry> toCacheModel() {
		StyleBookEntryCacheModel styleBookEntryCacheModel =
			new StyleBookEntryCacheModel();

		styleBookEntryCacheModel.mvccVersion = getMvccVersion();

		styleBookEntryCacheModel.headId = getHeadId();

		styleBookEntryCacheModel.head = isHead();

		styleBookEntryCacheModel.styleBookEntryId = getStyleBookEntryId();

		styleBookEntryCacheModel.groupId = getGroupId();

		styleBookEntryCacheModel.companyId = getCompanyId();

		styleBookEntryCacheModel.userId = getUserId();

		styleBookEntryCacheModel.userName = getUserName();

		String userName = styleBookEntryCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			styleBookEntryCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			styleBookEntryCacheModel.createDate = createDate.getTime();
		}
		else {
			styleBookEntryCacheModel.createDate = Long.MIN_VALUE;
		}

		styleBookEntryCacheModel.defaultStyleBookEntry =
			isDefaultStyleBookEntry();

		styleBookEntryCacheModel.frontendTokensValues =
			getFrontendTokensValues();

		String frontendTokensValues =
			styleBookEntryCacheModel.frontendTokensValues;

		if ((frontendTokensValues != null) &&
			(frontendTokensValues.length() == 0)) {

			styleBookEntryCacheModel.frontendTokensValues = null;
		}

		styleBookEntryCacheModel.name = getName();

		String name = styleBookEntryCacheModel.name;

		if ((name != null) && (name.length() == 0)) {
			styleBookEntryCacheModel.name = null;
		}

		styleBookEntryCacheModel.previewFileEntryId = getPreviewFileEntryId();

		styleBookEntryCacheModel.styleBookEntryKey = getStyleBookEntryKey();

		String styleBookEntryKey = styleBookEntryCacheModel.styleBookEntryKey;

		if ((styleBookEntryKey != null) && (styleBookEntryKey.length() == 0)) {
			styleBookEntryCacheModel.styleBookEntryKey = null;
		}

		return styleBookEntryCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<StyleBookEntry, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<StyleBookEntry, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<StyleBookEntry, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((StyleBookEntry)this));
			sb.append(", ");
		}

		if (sb.index() > 1) {
			sb.setIndex(sb.index() - 1);
		}

		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		Map<String, Function<StyleBookEntry, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<StyleBookEntry, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<StyleBookEntry, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((StyleBookEntry)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, StyleBookEntry>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private long _mvccVersion;
	private long _headId;
	private boolean _head;
	private long _styleBookEntryId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private boolean _defaultStyleBookEntry;
	private String _frontendTokensValues;
	private String _name;
	private long _previewFileEntryId;
	private String _styleBookEntryKey;

	public static long getColumnBitmask(String columnName) {
		return _columnBitmasks.get(columnName);
	}

	public <T> T getColumnValue(String columnName) {
		if (columnName.equals("head")) {
			return (T)(Object)getHead();
		}

		Function<StyleBookEntry, Object> function =
			_attributeGetterFunctions.get(columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((StyleBookEntry)this);
	}

	public <T> T getColumnOriginalValue(String columnName) {
		if (_columnOriginalValues == null) {
			return null;
		}

		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		return (T)_columnOriginalValues.get(columnName);
	}

	private void _setColumnOriginalValues() {
		_columnOriginalValues = new HashMap<String, Object>();

		_columnOriginalValues.put("mvccVersion", _mvccVersion);
		_columnOriginalValues.put("headId", _headId);
		_columnOriginalValues.put("head", _head);
		_columnOriginalValues.put("styleBookEntryId", _styleBookEntryId);
		_columnOriginalValues.put("groupId", _groupId);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("userId", _userId);
		_columnOriginalValues.put("userName", _userName);
		_columnOriginalValues.put("createDate", _createDate);
		_columnOriginalValues.put(
			"defaultStyleBookEntry", _defaultStyleBookEntry);
		_columnOriginalValues.put(
			"frontendTokensValues", _frontendTokensValues);
		_columnOriginalValues.put("name", _name);
		_columnOriginalValues.put("previewFileEntryId", _previewFileEntryId);
		_columnOriginalValues.put("styleBookEntryKey", _styleBookEntryKey);
	}

	private static final Map<String, Long> _columnBitmasks;

	static {
		Map<String, Long> columnBitmasks = new LinkedHashMap<>();

		columnBitmasks.put("mvccVersion", 1L);

		columnBitmasks.put("headId", 2L);

		columnBitmasks.put("head", 4L);

		columnBitmasks.put("styleBookEntryId", 8L);

		columnBitmasks.put("groupId", 16L);

		columnBitmasks.put("companyId", 32L);

		columnBitmasks.put("userId", 64L);

		columnBitmasks.put("userName", 128L);

		columnBitmasks.put("createDate", 256L);

		columnBitmasks.put("defaultStyleBookEntry", 512L);

		columnBitmasks.put("frontendTokensValues", 1024L);

		columnBitmasks.put("name", 2048L);

		columnBitmasks.put("previewFileEntryId", 4096L);

		columnBitmasks.put("styleBookEntryKey", 8192L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private transient Map<String, Object> _columnOriginalValues;
	private long _columnBitmask;
	private StyleBookEntry _escapedModel;

}