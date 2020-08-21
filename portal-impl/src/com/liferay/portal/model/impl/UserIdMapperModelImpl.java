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
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.UserIdMapper;
import com.liferay.portal.kernel.model.UserIdMapperModel;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;

import java.io.Serializable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;

import java.sql.Types;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * The base model implementation for the UserIdMapper service. Represents a row in the &quot;UserIdMapper&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>UserIdMapperModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link UserIdMapperImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see UserIdMapperImpl
 * @generated
 */
public class UserIdMapperModelImpl
	extends BaseModelImpl<UserIdMapper> implements UserIdMapperModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a user ID mapper model instance should use the <code>UserIdMapper</code> interface instead.
	 */
	public static final String TABLE_NAME = "UserIdMapper";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"userIdMapperId", Types.BIGINT},
		{"companyId", Types.BIGINT}, {"userId", Types.BIGINT},
		{"type_", Types.VARCHAR}, {"description", Types.VARCHAR},
		{"externalUserId", Types.VARCHAR}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userIdMapperId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("type_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("description", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("externalUserId", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE =
		"create table UserIdMapper (mvccVersion LONG default 0 not null,userIdMapperId LONG not null primary key,companyId LONG,userId LONG,type_ VARCHAR(75) null,description VARCHAR(75) null,externalUserId VARCHAR(75) null)";

	public static final String TABLE_SQL_DROP = "drop table UserIdMapper";

	public static final String ORDER_BY_JPQL =
		" ORDER BY userIdMapper.userIdMapperId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY UserIdMapper.userIdMapperId ASC";

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
	public static final long EXTERNALUSERID_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)
	 */
	@Deprecated
	public static final long TYPE_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)
	 */
	@Deprecated
	public static final long USERID_COLUMN_BITMASK = 4L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)
	 */
	@Deprecated
	public static final long USERIDMAPPERID_COLUMN_BITMASK = 8L;

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.portal.util.PropsUtil.get(
			"lock.expiration.time.com.liferay.portal.kernel.model.UserIdMapper"));

	public UserIdMapperModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _userIdMapperId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setUserIdMapperId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _userIdMapperId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return UserIdMapper.class;
	}

	@Override
	public String getModelClassName() {
		return UserIdMapper.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<UserIdMapper, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<UserIdMapper, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<UserIdMapper, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((UserIdMapper)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<UserIdMapper, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<UserIdMapper, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(UserIdMapper)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<UserIdMapper, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<UserIdMapper, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, UserIdMapper>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			UserIdMapper.class.getClassLoader(), UserIdMapper.class,
			ModelWrapper.class);

		try {
			Constructor<UserIdMapper> constructor =
				(Constructor<UserIdMapper>)proxyClass.getConstructor(
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

	private static final Map<String, Function<UserIdMapper, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<UserIdMapper, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<UserIdMapper, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<UserIdMapper, Object>>();
		Map<String, BiConsumer<UserIdMapper, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<UserIdMapper, ?>>();

		attributeGetterFunctions.put(
			"mvccVersion", UserIdMapper::getMvccVersion);
		attributeSetterBiConsumers.put(
			"mvccVersion",
			(BiConsumer<UserIdMapper, Long>)UserIdMapper::setMvccVersion);
		attributeGetterFunctions.put(
			"userIdMapperId", UserIdMapper::getUserIdMapperId);
		attributeSetterBiConsumers.put(
			"userIdMapperId",
			(BiConsumer<UserIdMapper, Long>)UserIdMapper::setUserIdMapperId);
		attributeGetterFunctions.put("companyId", UserIdMapper::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId",
			(BiConsumer<UserIdMapper, Long>)UserIdMapper::setCompanyId);
		attributeGetterFunctions.put("userId", UserIdMapper::getUserId);
		attributeSetterBiConsumers.put(
			"userId", (BiConsumer<UserIdMapper, Long>)UserIdMapper::setUserId);
		attributeGetterFunctions.put("type", UserIdMapper::getType);
		attributeSetterBiConsumers.put(
			"type", (BiConsumer<UserIdMapper, String>)UserIdMapper::setType);
		attributeGetterFunctions.put(
			"description", UserIdMapper::getDescription);
		attributeSetterBiConsumers.put(
			"description",
			(BiConsumer<UserIdMapper, String>)UserIdMapper::setDescription);
		attributeGetterFunctions.put(
			"externalUserId", UserIdMapper::getExternalUserId);
		attributeSetterBiConsumers.put(
			"externalUserId",
			(BiConsumer<UserIdMapper, String>)UserIdMapper::setExternalUserId);

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
	public long getUserIdMapperId() {
		return _userIdMapperId;
	}

	@Override
	public void setUserIdMapperId(long userIdMapperId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_userIdMapperId = userIdMapperId;
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

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalUserId() {
		return GetterUtil.getLong(getColumnOriginalValue("userId"));
	}

	@Override
	public String getType() {
		if (_type == null) {
			return "";
		}
		else {
			return _type;
		}
	}

	@Override
	public void setType(String type) {
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
	public String getOriginalType() {
		return getColumnOriginalValue("type_");
	}

	@Override
	public String getDescription() {
		if (_description == null) {
			return "";
		}
		else {
			return _description;
		}
	}

	@Override
	public void setDescription(String description) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_description = description;
	}

	@Override
	public String getExternalUserId() {
		if (_externalUserId == null) {
			return "";
		}
		else {
			return _externalUserId;
		}
	}

	@Override
	public void setExternalUserId(String externalUserId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_externalUserId = externalUserId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public String getOriginalExternalUserId() {
		return getColumnOriginalValue("externalUserId");
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
			getCompanyId(), UserIdMapper.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public UserIdMapper toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, UserIdMapper>
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
		UserIdMapperImpl userIdMapperImpl = new UserIdMapperImpl();

		userIdMapperImpl.setMvccVersion(getMvccVersion());
		userIdMapperImpl.setUserIdMapperId(getUserIdMapperId());
		userIdMapperImpl.setCompanyId(getCompanyId());
		userIdMapperImpl.setUserId(getUserId());
		userIdMapperImpl.setType(getType());
		userIdMapperImpl.setDescription(getDescription());
		userIdMapperImpl.setExternalUserId(getExternalUserId());

		userIdMapperImpl.resetOriginalValues();

		return userIdMapperImpl;
	}

	@Override
	public int compareTo(UserIdMapper userIdMapper) {
		long primaryKey = userIdMapper.getPrimaryKey();

		if (getPrimaryKey() < primaryKey) {
			return -1;
		}
		else if (getPrimaryKey() > primaryKey) {
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof UserIdMapper)) {
			return false;
		}

		UserIdMapper userIdMapper = (UserIdMapper)object;

		long primaryKey = userIdMapper.getPrimaryKey();

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
	public CacheModel<UserIdMapper> toCacheModel() {
		UserIdMapperCacheModel userIdMapperCacheModel =
			new UserIdMapperCacheModel();

		userIdMapperCacheModel.mvccVersion = getMvccVersion();

		userIdMapperCacheModel.userIdMapperId = getUserIdMapperId();

		userIdMapperCacheModel.companyId = getCompanyId();

		userIdMapperCacheModel.userId = getUserId();

		userIdMapperCacheModel.type = getType();

		String type = userIdMapperCacheModel.type;

		if ((type != null) && (type.length() == 0)) {
			userIdMapperCacheModel.type = null;
		}

		userIdMapperCacheModel.description = getDescription();

		String description = userIdMapperCacheModel.description;

		if ((description != null) && (description.length() == 0)) {
			userIdMapperCacheModel.description = null;
		}

		userIdMapperCacheModel.externalUserId = getExternalUserId();

		String externalUserId = userIdMapperCacheModel.externalUserId;

		if ((externalUserId != null) && (externalUserId.length() == 0)) {
			userIdMapperCacheModel.externalUserId = null;
		}

		return userIdMapperCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<UserIdMapper, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<UserIdMapper, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<UserIdMapper, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((UserIdMapper)this));
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
		Map<String, Function<UserIdMapper, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<UserIdMapper, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<UserIdMapper, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((UserIdMapper)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, UserIdMapper>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private long _mvccVersion;
	private long _userIdMapperId;
	private long _companyId;
	private long _userId;
	private String _type;
	private String _description;
	private String _externalUserId;

	public static long getColumnBitmask(String columnName) {
		return _columnBitmasks.get(columnName);
	}

	public <T> T getColumnValue(String columnName) {
		if (_attributeNames.containsKey(columnName)) {
			columnName = _attributeNames.get(columnName);
		}

		Function<UserIdMapper, Object> function = _attributeGetterFunctions.get(
			columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((UserIdMapper)this);
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
		_columnOriginalValues.put("userIdMapperId", _userIdMapperId);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("userId", _userId);
		_columnOriginalValues.put("type_", _type);
		_columnOriginalValues.put("description", _description);
		_columnOriginalValues.put("externalUserId", _externalUserId);
	}

	private static final Map<String, Long> _columnBitmasks;
	private static final Map<String, String> _attributeNames;

	static {
		Map<String, Long> columnBitmasks = new LinkedHashMap<>();

		columnBitmasks.put("mvccVersion", 1L);

		columnBitmasks.put("userIdMapperId", 2L);

		columnBitmasks.put("companyId", 4L);

		columnBitmasks.put("userId", 8L);

		columnBitmasks.put("type_", 16L);

		columnBitmasks.put("description", 32L);

		columnBitmasks.put("externalUserId", 64L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);

		Map<String, String> attributeNames = new LinkedHashMap<>();

		attributeNames.put("type_", "type");

		_attributeNames = Collections.unmodifiableMap(attributeNames);
	}

	private transient Map<String, Object> _columnOriginalValues;
	private long _columnBitmask;
	private UserIdMapper _escapedModel;

}