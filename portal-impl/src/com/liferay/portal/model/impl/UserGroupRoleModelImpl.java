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
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.UserGroupRole;
import com.liferay.portal.kernel.model.UserGroupRoleModel;
import com.liferay.portal.kernel.model.UserGroupRoleSoap;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;

import java.io.Serializable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * The base model implementation for the UserGroupRole service. Represents a row in the &quot;UserGroupRole&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>UserGroupRoleModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link UserGroupRoleImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see UserGroupRoleImpl
 * @generated
 */
@JSON(strict = true)
public class UserGroupRoleModelImpl
	extends BaseModelImpl<UserGroupRole> implements UserGroupRoleModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a user group role model instance should use the <code>UserGroupRole</code> interface instead.
	 */
	public static final String TABLE_NAME = "UserGroupRole";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"ctCollectionId", Types.BIGINT},
		{"userGroupRoleId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"userId", Types.BIGINT}, {"groupId", Types.BIGINT},
		{"roleId", Types.BIGINT}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("ctCollectionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userGroupRoleId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("roleId", Types.BIGINT);
	}

	public static final String TABLE_SQL_CREATE =
		"create table UserGroupRole (mvccVersion LONG default 0 not null,ctCollectionId LONG default 0 not null,userGroupRoleId LONG not null,companyId LONG,userId LONG,groupId LONG,roleId LONG,primary key (userGroupRoleId, ctCollectionId))";

	public static final String TABLE_SQL_DROP = "drop table UserGroupRole";

	public static final String ORDER_BY_JPQL =
		" ORDER BY userGroupRole.userGroupRoleId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY UserGroupRole.userGroupRoleId ASC";

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
	public static final long GROUPID_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)
	 */
	@Deprecated
	public static final long ROLEID_COLUMN_BITMASK = 2L;

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
	public static final long USERGROUPROLEID_COLUMN_BITMASK = 8L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static UserGroupRole toModel(UserGroupRoleSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		UserGroupRole model = new UserGroupRoleImpl();

		model.setMvccVersion(soapModel.getMvccVersion());
		model.setCtCollectionId(soapModel.getCtCollectionId());
		model.setUserGroupRoleId(soapModel.getUserGroupRoleId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setGroupId(soapModel.getGroupId());
		model.setRoleId(soapModel.getRoleId());

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
	public static List<UserGroupRole> toModels(UserGroupRoleSoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<UserGroupRole> models = new ArrayList<UserGroupRole>(
			soapModels.length);

		for (UserGroupRoleSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.portal.util.PropsUtil.get(
			"lock.expiration.time.com.liferay.portal.kernel.model.UserGroupRole"));

	public UserGroupRoleModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _userGroupRoleId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setUserGroupRoleId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _userGroupRoleId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return UserGroupRole.class;
	}

	@Override
	public String getModelClassName() {
		return UserGroupRole.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<UserGroupRole, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<UserGroupRole, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<UserGroupRole, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((UserGroupRole)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<UserGroupRole, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<UserGroupRole, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(UserGroupRole)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<UserGroupRole, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<UserGroupRole, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, UserGroupRole>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			UserGroupRole.class.getClassLoader(), UserGroupRole.class,
			ModelWrapper.class);

		try {
			Constructor<UserGroupRole> constructor =
				(Constructor<UserGroupRole>)proxyClass.getConstructor(
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

	private static final Map<String, Function<UserGroupRole, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<UserGroupRole, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<UserGroupRole, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<UserGroupRole, Object>>();
		Map<String, BiConsumer<UserGroupRole, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<UserGroupRole, ?>>();

		attributeGetterFunctions.put(
			"mvccVersion", UserGroupRole::getMvccVersion);
		attributeSetterBiConsumers.put(
			"mvccVersion",
			(BiConsumer<UserGroupRole, Long>)UserGroupRole::setMvccVersion);
		attributeGetterFunctions.put(
			"ctCollectionId", UserGroupRole::getCtCollectionId);
		attributeSetterBiConsumers.put(
			"ctCollectionId",
			(BiConsumer<UserGroupRole, Long>)UserGroupRole::setCtCollectionId);
		attributeGetterFunctions.put(
			"userGroupRoleId", UserGroupRole::getUserGroupRoleId);
		attributeSetterBiConsumers.put(
			"userGroupRoleId",
			(BiConsumer<UserGroupRole, Long>)UserGroupRole::setUserGroupRoleId);
		attributeGetterFunctions.put("companyId", UserGroupRole::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId",
			(BiConsumer<UserGroupRole, Long>)UserGroupRole::setCompanyId);
		attributeGetterFunctions.put("userId", UserGroupRole::getUserId);
		attributeSetterBiConsumers.put(
			"userId",
			(BiConsumer<UserGroupRole, Long>)UserGroupRole::setUserId);
		attributeGetterFunctions.put("groupId", UserGroupRole::getGroupId);
		attributeSetterBiConsumers.put(
			"groupId",
			(BiConsumer<UserGroupRole, Long>)UserGroupRole::setGroupId);
		attributeGetterFunctions.put("roleId", UserGroupRole::getRoleId);
		attributeSetterBiConsumers.put(
			"roleId",
			(BiConsumer<UserGroupRole, Long>)UserGroupRole::setRoleId);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
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

	@JSON
	@Override
	public long getUserGroupRoleId() {
		return _userGroupRoleId;
	}

	@Override
	public void setUserGroupRoleId(long userGroupRoleId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_userGroupRoleId = userGroupRoleId;
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

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalUserId() {
		return GetterUtil.getLong(getColumnOriginalValue("userId"));
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
	public long getRoleId() {
		return _roleId;
	}

	@Override
	public void setRoleId(long roleId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_roleId = roleId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalRoleId() {
		return GetterUtil.getLong(getColumnOriginalValue("roleId"));
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
			getCompanyId(), UserGroupRole.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public UserGroupRole toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, UserGroupRole>
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
		UserGroupRoleImpl userGroupRoleImpl = new UserGroupRoleImpl();

		userGroupRoleImpl.setMvccVersion(getMvccVersion());
		userGroupRoleImpl.setCtCollectionId(getCtCollectionId());
		userGroupRoleImpl.setUserGroupRoleId(getUserGroupRoleId());
		userGroupRoleImpl.setCompanyId(getCompanyId());
		userGroupRoleImpl.setUserId(getUserId());
		userGroupRoleImpl.setGroupId(getGroupId());
		userGroupRoleImpl.setRoleId(getRoleId());

		userGroupRoleImpl.resetOriginalValues();

		return userGroupRoleImpl;
	}

	@Override
	public int compareTo(UserGroupRole userGroupRole) {
		long primaryKey = userGroupRole.getPrimaryKey();

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

		if (!(object instanceof UserGroupRole)) {
			return false;
		}

		UserGroupRole userGroupRole = (UserGroupRole)object;

		long primaryKey = userGroupRole.getPrimaryKey();

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
	public CacheModel<UserGroupRole> toCacheModel() {
		UserGroupRoleCacheModel userGroupRoleCacheModel =
			new UserGroupRoleCacheModel();

		userGroupRoleCacheModel.mvccVersion = getMvccVersion();

		userGroupRoleCacheModel.ctCollectionId = getCtCollectionId();

		userGroupRoleCacheModel.userGroupRoleId = getUserGroupRoleId();

		userGroupRoleCacheModel.companyId = getCompanyId();

		userGroupRoleCacheModel.userId = getUserId();

		userGroupRoleCacheModel.groupId = getGroupId();

		userGroupRoleCacheModel.roleId = getRoleId();

		return userGroupRoleCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<UserGroupRole, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<UserGroupRole, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<UserGroupRole, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((UserGroupRole)this));
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
		Map<String, Function<UserGroupRole, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<UserGroupRole, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<UserGroupRole, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((UserGroupRole)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, UserGroupRole>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private long _mvccVersion;
	private long _ctCollectionId;
	private long _userGroupRoleId;
	private long _companyId;
	private long _userId;
	private long _groupId;
	private long _roleId;

	public static long getColumnBitmask(String columnName) {
		return _columnBitmasks.get(columnName);
	}

	public <T> T getColumnValue(String columnName) {
		Function<UserGroupRole, Object> function =
			_attributeGetterFunctions.get(columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((UserGroupRole)this);
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
		_columnOriginalValues.put("userGroupRoleId", _userGroupRoleId);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("userId", _userId);
		_columnOriginalValues.put("groupId", _groupId);
		_columnOriginalValues.put("roleId", _roleId);
	}

	private static final Map<String, Long> _columnBitmasks;

	static {
		Map<String, Long> columnBitmasks = new LinkedHashMap<>();

		columnBitmasks.put("mvccVersion", 1L);

		columnBitmasks.put("ctCollectionId", 2L);

		columnBitmasks.put("userGroupRoleId", 4L);

		columnBitmasks.put("companyId", 8L);

		columnBitmasks.put("userId", 16L);

		columnBitmasks.put("groupId", 32L);

		columnBitmasks.put("roleId", 64L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private transient Map<String, Object> _columnOriginalValues;
	private long _columnBitmask;
	private UserGroupRole _escapedModel;

}