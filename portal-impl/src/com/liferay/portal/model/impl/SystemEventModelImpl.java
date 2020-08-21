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

package com.liferay.portal.model.impl;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.SystemEvent;
import com.liferay.portal.kernel.model.SystemEventModel;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.Serializable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;

import java.sql.Types;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * The base model implementation for the SystemEvent service. Represents a row in the &quot;SystemEvent&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>SystemEventModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link SystemEventImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SystemEventImpl
 * @generated
 */
public class SystemEventModelImpl
	extends BaseModelImpl<SystemEvent> implements SystemEventModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a system event model instance should use the <code>SystemEvent</code> interface instead.
	 */
	public static final String TABLE_NAME = "SystemEvent";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"ctCollectionId", Types.BIGINT},
		{"systemEventId", Types.BIGINT}, {"groupId", Types.BIGINT},
		{"companyId", Types.BIGINT}, {"userId", Types.BIGINT},
		{"userName", Types.VARCHAR}, {"createDate", Types.TIMESTAMP},
		{"classNameId", Types.BIGINT}, {"classPK", Types.BIGINT},
		{"classUuid", Types.VARCHAR}, {"referrerClassNameId", Types.BIGINT},
		{"parentSystemEventId", Types.BIGINT},
		{"systemEventSetKey", Types.BIGINT}, {"type_", Types.INTEGER},
		{"extraData", Types.CLOB}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("ctCollectionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("systemEventId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("classNameId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("classPK", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("classUuid", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("referrerClassNameId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("parentSystemEventId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("systemEventSetKey", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("type_", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("extraData", Types.CLOB);
	}

	public static final String TABLE_SQL_CREATE =
		"create table SystemEvent (mvccVersion LONG default 0 not null,ctCollectionId LONG default 0 not null,systemEventId LONG not null,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,classNameId LONG,classPK LONG,classUuid VARCHAR(75) null,referrerClassNameId LONG,parentSystemEventId LONG,systemEventSetKey LONG,type_ INTEGER,extraData TEXT null,primary key (systemEventId, ctCollectionId))";

	public static final String TABLE_SQL_DROP = "drop table SystemEvent";

	public static final String ORDER_BY_JPQL =
		" ORDER BY systemEvent.createDate DESC";

	public static final String ORDER_BY_SQL =
		" ORDER BY SystemEvent.createDate DESC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static final boolean ENTITY_CACHE_ENABLED = true;

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static final boolean FINDER_CACHE_ENABLED = true;

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static final boolean COLUMN_BITMASK_ENABLED = true;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)
	 */
	@Deprecated
	public static final long CLASSNAMEID_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)
	 */
	@Deprecated
	public static final long CLASSPK_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)
	 */
	@Deprecated
	public static final long GROUPID_COLUMN_BITMASK = 4L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)
	 */
	@Deprecated
	public static final long SYSTEMEVENTSETKEY_COLUMN_BITMASK = 8L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)
	 */
	@Deprecated
	public static final long TYPE_COLUMN_BITMASK = 16L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)
	 */
	@Deprecated
	public static final long CREATEDATE_COLUMN_BITMASK = 32L;

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.portal.util.PropsUtil.get(
			"lock.expiration.time.com.liferay.portal.kernel.model.SystemEvent"));

	public SystemEventModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _systemEventId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setSystemEventId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _systemEventId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return SystemEvent.class;
	}

	@Override
	public String getModelClassName() {
		return SystemEvent.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<SystemEvent, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<SystemEvent, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<SystemEvent, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((SystemEvent)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<SystemEvent, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<SystemEvent, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(SystemEvent)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<SystemEvent, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<SystemEvent, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, SystemEvent>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			SystemEvent.class.getClassLoader(), SystemEvent.class,
			ModelWrapper.class);

		try {
			Constructor<SystemEvent> constructor =
				(Constructor<SystemEvent>)proxyClass.getConstructor(
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

	private static final Map<String, Function<SystemEvent, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<SystemEvent, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<SystemEvent, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<SystemEvent, Object>>();
		Map<String, BiConsumer<SystemEvent, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<SystemEvent, ?>>();

		attributeGetterFunctions.put(
			"mvccVersion", SystemEvent::getMvccVersion);
		attributeSetterBiConsumers.put(
			"mvccVersion",
			(BiConsumer<SystemEvent, Long>)SystemEvent::setMvccVersion);
		attributeGetterFunctions.put(
			"ctCollectionId", SystemEvent::getCtCollectionId);
		attributeSetterBiConsumers.put(
			"ctCollectionId",
			(BiConsumer<SystemEvent, Long>)SystemEvent::setCtCollectionId);
		attributeGetterFunctions.put(
			"systemEventId", SystemEvent::getSystemEventId);
		attributeSetterBiConsumers.put(
			"systemEventId",
			(BiConsumer<SystemEvent, Long>)SystemEvent::setSystemEventId);
		attributeGetterFunctions.put("groupId", SystemEvent::getGroupId);
		attributeSetterBiConsumers.put(
			"groupId", (BiConsumer<SystemEvent, Long>)SystemEvent::setGroupId);
		attributeGetterFunctions.put("companyId", SystemEvent::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId",
			(BiConsumer<SystemEvent, Long>)SystemEvent::setCompanyId);
		attributeGetterFunctions.put("userId", SystemEvent::getUserId);
		attributeSetterBiConsumers.put(
			"userId", (BiConsumer<SystemEvent, Long>)SystemEvent::setUserId);
		attributeGetterFunctions.put("userName", SystemEvent::getUserName);
		attributeSetterBiConsumers.put(
			"userName",
			(BiConsumer<SystemEvent, String>)SystemEvent::setUserName);
		attributeGetterFunctions.put("createDate", SystemEvent::getCreateDate);
		attributeSetterBiConsumers.put(
			"createDate",
			(BiConsumer<SystemEvent, Date>)SystemEvent::setCreateDate);
		attributeGetterFunctions.put(
			"classNameId", SystemEvent::getClassNameId);
		attributeSetterBiConsumers.put(
			"classNameId",
			(BiConsumer<SystemEvent, Long>)SystemEvent::setClassNameId);
		attributeGetterFunctions.put("classPK", SystemEvent::getClassPK);
		attributeSetterBiConsumers.put(
			"classPK", (BiConsumer<SystemEvent, Long>)SystemEvent::setClassPK);
		attributeGetterFunctions.put("classUuid", SystemEvent::getClassUuid);
		attributeSetterBiConsumers.put(
			"classUuid",
			(BiConsumer<SystemEvent, String>)SystemEvent::setClassUuid);
		attributeGetterFunctions.put(
			"referrerClassNameId", SystemEvent::getReferrerClassNameId);
		attributeSetterBiConsumers.put(
			"referrerClassNameId",
			(BiConsumer<SystemEvent, Long>)SystemEvent::setReferrerClassNameId);
		attributeGetterFunctions.put(
			"parentSystemEventId", SystemEvent::getParentSystemEventId);
		attributeSetterBiConsumers.put(
			"parentSystemEventId",
			(BiConsumer<SystemEvent, Long>)SystemEvent::setParentSystemEventId);
		attributeGetterFunctions.put(
			"systemEventSetKey", SystemEvent::getSystemEventSetKey);
		attributeSetterBiConsumers.put(
			"systemEventSetKey",
			(BiConsumer<SystemEvent, Long>)SystemEvent::setSystemEventSetKey);
		attributeGetterFunctions.put("type", SystemEvent::getType);
		attributeSetterBiConsumers.put(
			"type", (BiConsumer<SystemEvent, Integer>)SystemEvent::setType);
		attributeGetterFunctions.put("extraData", SystemEvent::getExtraData);
		attributeSetterBiConsumers.put(
			"extraData",
			(BiConsumer<SystemEvent, String>)SystemEvent::setExtraData);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

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

	@Override
	public long getCtCollectionId() {
		return _ctCollectionId;
	}

	@Override
	public void setCtCollectionId(long ctCollectionId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_ctCollectionId = ctCollectionId;
	}

	@Override
	public long getSystemEventId() {
		return _systemEventId;
	}

	@Override
	public void setSystemEventId(long systemEventId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_systemEventId = systemEventId;
	}

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

	@Override
	public String getClassName() {
		if (getClassNameId() <= 0) {
			return "";
		}

		return PortalUtil.getClassName(getClassNameId());
	}

	@Override
	public void setClassName(String className) {
		long classNameId = 0;

		if (Validator.isNotNull(className)) {
			classNameId = PortalUtil.getClassNameId(className);
		}

		setClassNameId(classNameId);
	}

	@Override
	public long getClassNameId() {
		return _classNameId;
	}

	@Override
	public void setClassNameId(long classNameId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_classNameId = classNameId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalClassNameId() {
		return GetterUtil.getLong(getColumnOriginalValue("classNameId"));
	}

	@Override
	public long getClassPK() {
		return _classPK;
	}

	@Override
	public void setClassPK(long classPK) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_classPK = classPK;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalClassPK() {
		return GetterUtil.getLong(getColumnOriginalValue("classPK"));
	}

	@Override
	public String getClassUuid() {
		if (_classUuid == null) {
			return "";
		}
		else {
			return _classUuid;
		}
	}

	@Override
	public void setClassUuid(String classUuid) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_classUuid = classUuid;
	}

	@Override
	public long getReferrerClassNameId() {
		return _referrerClassNameId;
	}

	@Override
	public void setReferrerClassNameId(long referrerClassNameId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_referrerClassNameId = referrerClassNameId;
	}

	@Override
	public long getParentSystemEventId() {
		return _parentSystemEventId;
	}

	@Override
	public void setParentSystemEventId(long parentSystemEventId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_parentSystemEventId = parentSystemEventId;
	}

	@Override
	public long getSystemEventSetKey() {
		return _systemEventSetKey;
	}

	@Override
	public void setSystemEventSetKey(long systemEventSetKey) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_systemEventSetKey = systemEventSetKey;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalSystemEventSetKey() {
		return GetterUtil.getLong(getColumnOriginalValue("systemEventSetKey"));
	}

	@Override
	public int getType() {
		return _type;
	}

	@Override
	public void setType(int type) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_type = type;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public int getOriginalType() {
		return GetterUtil.getInteger(getColumnOriginalValue("type_"));
	}

	@Override
	public String getExtraData() {
		if (_extraData == null) {
			return "";
		}
		else {
			return _extraData;
		}
	}

	@Override
	public void setExtraData(String extraData) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_extraData = extraData;
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
			getCompanyId(), SystemEvent.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public SystemEvent toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, SystemEvent>
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
		SystemEventImpl systemEventImpl = new SystemEventImpl();

		systemEventImpl.setMvccVersion(getMvccVersion());
		systemEventImpl.setCtCollectionId(getCtCollectionId());
		systemEventImpl.setSystemEventId(getSystemEventId());
		systemEventImpl.setGroupId(getGroupId());
		systemEventImpl.setCompanyId(getCompanyId());
		systemEventImpl.setUserId(getUserId());
		systemEventImpl.setUserName(getUserName());
		systemEventImpl.setCreateDate(getCreateDate());
		systemEventImpl.setClassNameId(getClassNameId());
		systemEventImpl.setClassPK(getClassPK());
		systemEventImpl.setClassUuid(getClassUuid());
		systemEventImpl.setReferrerClassNameId(getReferrerClassNameId());
		systemEventImpl.setParentSystemEventId(getParentSystemEventId());
		systemEventImpl.setSystemEventSetKey(getSystemEventSetKey());
		systemEventImpl.setType(getType());
		systemEventImpl.setExtraData(getExtraData());

		systemEventImpl.resetOriginalValues();

		return systemEventImpl;
	}

	@Override
	public int compareTo(SystemEvent systemEvent) {
		int value = 0;

		value = DateUtil.compareTo(
			getCreateDate(), systemEvent.getCreateDate());

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

		if (!(object instanceof SystemEvent)) {
			return false;
		}

		SystemEvent systemEvent = (SystemEvent)object;

		long primaryKey = systemEvent.getPrimaryKey();

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
		return ENTITY_CACHE_ENABLED;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	@Override
	public boolean isFinderCacheEnabled() {
		return FINDER_CACHE_ENABLED;
	}

	@Override
	public void resetOriginalValues() {
		_columnOriginalValues = Collections.emptyMap();

		_columnBitmask = 0;
	}

	@Override
	public CacheModel<SystemEvent> toCacheModel() {
		SystemEventCacheModel systemEventCacheModel =
			new SystemEventCacheModel();

		systemEventCacheModel.mvccVersion = getMvccVersion();

		systemEventCacheModel.ctCollectionId = getCtCollectionId();

		systemEventCacheModel.systemEventId = getSystemEventId();

		systemEventCacheModel.groupId = getGroupId();

		systemEventCacheModel.companyId = getCompanyId();

		systemEventCacheModel.userId = getUserId();

		systemEventCacheModel.userName = getUserName();

		String userName = systemEventCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			systemEventCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			systemEventCacheModel.createDate = createDate.getTime();
		}
		else {
			systemEventCacheModel.createDate = Long.MIN_VALUE;
		}

		systemEventCacheModel.classNameId = getClassNameId();

		systemEventCacheModel.classPK = getClassPK();

		systemEventCacheModel.classUuid = getClassUuid();

		String classUuid = systemEventCacheModel.classUuid;

		if ((classUuid != null) && (classUuid.length() == 0)) {
			systemEventCacheModel.classUuid = null;
		}

		systemEventCacheModel.referrerClassNameId = getReferrerClassNameId();

		systemEventCacheModel.parentSystemEventId = getParentSystemEventId();

		systemEventCacheModel.systemEventSetKey = getSystemEventSetKey();

		systemEventCacheModel.type = getType();

		systemEventCacheModel.extraData = getExtraData();

		String extraData = systemEventCacheModel.extraData;

		if ((extraData != null) && (extraData.length() == 0)) {
			systemEventCacheModel.extraData = null;
		}

		return systemEventCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<SystemEvent, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<SystemEvent, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<SystemEvent, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((SystemEvent)this));
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
		Map<String, Function<SystemEvent, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<SystemEvent, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<SystemEvent, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((SystemEvent)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, SystemEvent>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private long _mvccVersion;
	private long _ctCollectionId;
	private long _systemEventId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private long _classNameId;
	private long _classPK;
	private String _classUuid;
	private long _referrerClassNameId;
	private long _parentSystemEventId;
	private long _systemEventSetKey;
	private int _type;
	private String _extraData;

	public static long getColumnBitmask(String columnName) {
		return _columnBitmasks.get(columnName);
	}

	public <T> T getColumnValue(String columnName) {
		if (_attributeNames.containsKey(columnName)) {
			columnName = _attributeNames.get(columnName);
		}

		Function<SystemEvent, Object> function = _attributeGetterFunctions.get(
			columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((SystemEvent)this);
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
		_columnOriginalValues.put("ctCollectionId", _ctCollectionId);
		_columnOriginalValues.put("systemEventId", _systemEventId);
		_columnOriginalValues.put("groupId", _groupId);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("userId", _userId);
		_columnOriginalValues.put("userName", _userName);
		_columnOriginalValues.put("createDate", _createDate);
		_columnOriginalValues.put("classNameId", _classNameId);
		_columnOriginalValues.put("classPK", _classPK);
		_columnOriginalValues.put("classUuid", _classUuid);
		_columnOriginalValues.put("referrerClassNameId", _referrerClassNameId);
		_columnOriginalValues.put("parentSystemEventId", _parentSystemEventId);
		_columnOriginalValues.put("systemEventSetKey", _systemEventSetKey);
		_columnOriginalValues.put("type_", _type);
		_columnOriginalValues.put("extraData", _extraData);
	}

	private static final Map<String, Long> _columnBitmasks;
	private static final Map<String, String> _attributeNames;

	static {
		Map<String, Long> columnBitmasks = new LinkedHashMap<>();

		columnBitmasks.put("mvccVersion", 1L);

		columnBitmasks.put("ctCollectionId", 2L);

		columnBitmasks.put("systemEventId", 4L);

		columnBitmasks.put("groupId", 8L);

		columnBitmasks.put("companyId", 16L);

		columnBitmasks.put("userId", 32L);

		columnBitmasks.put("userName", 64L);

		columnBitmasks.put("createDate", 128L);

		columnBitmasks.put("classNameId", 256L);

		columnBitmasks.put("classPK", 512L);

		columnBitmasks.put("classUuid", 1024L);

		columnBitmasks.put("referrerClassNameId", 2048L);

		columnBitmasks.put("parentSystemEventId", 4096L);

		columnBitmasks.put("systemEventSetKey", 8192L);

		columnBitmasks.put("type_", 16384L);

		columnBitmasks.put("extraData", 32768L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);

		Map<String, String> attributeNames = new LinkedHashMap<>();

		attributeNames.put("type_", "type");

		_attributeNames = Collections.unmodifiableMap(attributeNames);
	}

	private transient Map<String, Object> _columnOriginalValues;
	private long _columnBitmask;
	private SystemEvent _escapedModel;

}