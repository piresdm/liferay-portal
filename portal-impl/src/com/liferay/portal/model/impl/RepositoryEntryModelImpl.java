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
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.RepositoryEntry;
import com.liferay.portal.kernel.model.RepositoryEntryModel;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ProxyUtil;

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
 * The base model implementation for the RepositoryEntry service. Represents a row in the &quot;RepositoryEntry&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>RepositoryEntryModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link RepositoryEntryImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see RepositoryEntryImpl
 * @generated
 */
public class RepositoryEntryModelImpl
	extends BaseModelImpl<RepositoryEntry> implements RepositoryEntryModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a repository entry model instance should use the <code>RepositoryEntry</code> interface instead.
	 */
	public static final String TABLE_NAME = "RepositoryEntry";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"uuid_", Types.VARCHAR},
		{"repositoryEntryId", Types.BIGINT}, {"groupId", Types.BIGINT},
		{"companyId", Types.BIGINT}, {"userId", Types.BIGINT},
		{"userName", Types.VARCHAR}, {"createDate", Types.TIMESTAMP},
		{"modifiedDate", Types.TIMESTAMP}, {"repositoryId", Types.BIGINT},
		{"mappedId", Types.VARCHAR}, {"manualCheckInRequired", Types.BOOLEAN},
		{"lastPublishDate", Types.TIMESTAMP}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("repositoryEntryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("repositoryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("mappedId", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("manualCheckInRequired", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("lastPublishDate", Types.TIMESTAMP);
	}

	public static final String TABLE_SQL_CREATE =
		"create table RepositoryEntry (mvccVersion LONG default 0 not null,uuid_ VARCHAR(75) null,repositoryEntryId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,repositoryId LONG,mappedId VARCHAR(255) null,manualCheckInRequired BOOLEAN,lastPublishDate DATE null)";

	public static final String TABLE_SQL_DROP = "drop table RepositoryEntry";

	public static final String ORDER_BY_JPQL =
		" ORDER BY repositoryEntry.repositoryEntryId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY RepositoryEntry.repositoryEntryId ASC";

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
	public static final long COMPANYID_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)
	 */
	@Deprecated
	public static final long GROUPID_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)
	 */
	@Deprecated
	public static final long MAPPEDID_COLUMN_BITMASK = 4L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)
	 */
	@Deprecated
	public static final long REPOSITORYID_COLUMN_BITMASK = 8L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)
	 */
	@Deprecated
	public static final long UUID_COLUMN_BITMASK = 16L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)
	 */
	@Deprecated
	public static final long REPOSITORYENTRYID_COLUMN_BITMASK = 32L;

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.portal.util.PropsUtil.get(
			"lock.expiration.time.com.liferay.portal.kernel.model.RepositoryEntry"));

	public RepositoryEntryModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _repositoryEntryId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setRepositoryEntryId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _repositoryEntryId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return RepositoryEntry.class;
	}

	@Override
	public String getModelClassName() {
		return RepositoryEntry.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<RepositoryEntry, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<RepositoryEntry, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<RepositoryEntry, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((RepositoryEntry)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<RepositoryEntry, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<RepositoryEntry, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(RepositoryEntry)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<RepositoryEntry, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<RepositoryEntry, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, RepositoryEntry>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			RepositoryEntry.class.getClassLoader(), RepositoryEntry.class,
			ModelWrapper.class);

		try {
			Constructor<RepositoryEntry> constructor =
				(Constructor<RepositoryEntry>)proxyClass.getConstructor(
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

	private static final Map<String, Function<RepositoryEntry, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<RepositoryEntry, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<RepositoryEntry, Object>>
			attributeGetterFunctions =
				new LinkedHashMap<String, Function<RepositoryEntry, Object>>();
		Map<String, BiConsumer<RepositoryEntry, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<RepositoryEntry, ?>>();

		attributeGetterFunctions.put(
			"mvccVersion", RepositoryEntry::getMvccVersion);
		attributeSetterBiConsumers.put(
			"mvccVersion",
			(BiConsumer<RepositoryEntry, Long>)RepositoryEntry::setMvccVersion);
		attributeGetterFunctions.put("uuid", RepositoryEntry::getUuid);
		attributeSetterBiConsumers.put(
			"uuid",
			(BiConsumer<RepositoryEntry, String>)RepositoryEntry::setUuid);
		attributeGetterFunctions.put(
			"repositoryEntryId", RepositoryEntry::getRepositoryEntryId);
		attributeSetterBiConsumers.put(
			"repositoryEntryId",
			(BiConsumer<RepositoryEntry, Long>)
				RepositoryEntry::setRepositoryEntryId);
		attributeGetterFunctions.put("groupId", RepositoryEntry::getGroupId);
		attributeSetterBiConsumers.put(
			"groupId",
			(BiConsumer<RepositoryEntry, Long>)RepositoryEntry::setGroupId);
		attributeGetterFunctions.put(
			"companyId", RepositoryEntry::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId",
			(BiConsumer<RepositoryEntry, Long>)RepositoryEntry::setCompanyId);
		attributeGetterFunctions.put("userId", RepositoryEntry::getUserId);
		attributeSetterBiConsumers.put(
			"userId",
			(BiConsumer<RepositoryEntry, Long>)RepositoryEntry::setUserId);
		attributeGetterFunctions.put("userName", RepositoryEntry::getUserName);
		attributeSetterBiConsumers.put(
			"userName",
			(BiConsumer<RepositoryEntry, String>)RepositoryEntry::setUserName);
		attributeGetterFunctions.put(
			"createDate", RepositoryEntry::getCreateDate);
		attributeSetterBiConsumers.put(
			"createDate",
			(BiConsumer<RepositoryEntry, Date>)RepositoryEntry::setCreateDate);
		attributeGetterFunctions.put(
			"modifiedDate", RepositoryEntry::getModifiedDate);
		attributeSetterBiConsumers.put(
			"modifiedDate",
			(BiConsumer<RepositoryEntry, Date>)
				RepositoryEntry::setModifiedDate);
		attributeGetterFunctions.put(
			"repositoryId", RepositoryEntry::getRepositoryId);
		attributeSetterBiConsumers.put(
			"repositoryId",
			(BiConsumer<RepositoryEntry, Long>)
				RepositoryEntry::setRepositoryId);
		attributeGetterFunctions.put("mappedId", RepositoryEntry::getMappedId);
		attributeSetterBiConsumers.put(
			"mappedId",
			(BiConsumer<RepositoryEntry, String>)RepositoryEntry::setMappedId);
		attributeGetterFunctions.put(
			"manualCheckInRequired", RepositoryEntry::getManualCheckInRequired);
		attributeSetterBiConsumers.put(
			"manualCheckInRequired",
			(BiConsumer<RepositoryEntry, Boolean>)
				RepositoryEntry::setManualCheckInRequired);
		attributeGetterFunctions.put(
			"lastPublishDate", RepositoryEntry::getLastPublishDate);
		attributeSetterBiConsumers.put(
			"lastPublishDate",
			(BiConsumer<RepositoryEntry, Date>)
				RepositoryEntry::setLastPublishDate);

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
	public String getUuid() {
		if (_uuid == null) {
			return "";
		}
		else {
			return _uuid;
		}
	}

	@Override
	public void setUuid(String uuid) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_uuid = uuid;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public String getOriginalUuid() {
		return getColumnOriginalValue("uuid_");
	}

	@Override
	public long getRepositoryEntryId() {
		return _repositoryEntryId;
	}

	@Override
	public void setRepositoryEntryId(long repositoryEntryId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_repositoryEntryId = repositoryEntryId;
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

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalCompanyId() {
		return GetterUtil.getLong(getColumnOriginalValue("companyId"));
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
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public boolean hasSetModifiedDate() {
		return _setModifiedDate;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_setModifiedDate = true;

		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_modifiedDate = modifiedDate;
	}

	@Override
	public long getRepositoryId() {
		return _repositoryId;
	}

	@Override
	public void setRepositoryId(long repositoryId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_repositoryId = repositoryId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalRepositoryId() {
		return GetterUtil.getLong(getColumnOriginalValue("repositoryId"));
	}

	@Override
	public String getMappedId() {
		if (_mappedId == null) {
			return "";
		}
		else {
			return _mappedId;
		}
	}

	@Override
	public void setMappedId(String mappedId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_mappedId = mappedId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public String getOriginalMappedId() {
		return getColumnOriginalValue("mappedId");
	}

	@Override
	public boolean getManualCheckInRequired() {
		return _manualCheckInRequired;
	}

	@Override
	public boolean isManualCheckInRequired() {
		return _manualCheckInRequired;
	}

	@Override
	public void setManualCheckInRequired(boolean manualCheckInRequired) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_manualCheckInRequired = manualCheckInRequired;
	}

	@Override
	public Date getLastPublishDate() {
		return _lastPublishDate;
	}

	@Override
	public void setLastPublishDate(Date lastPublishDate) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_lastPublishDate = lastPublishDate;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(
			PortalUtil.getClassNameId(RepositoryEntry.class.getName()));
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
			getCompanyId(), RepositoryEntry.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public RepositoryEntry toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, RepositoryEntry>
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
		RepositoryEntryImpl repositoryEntryImpl = new RepositoryEntryImpl();

		repositoryEntryImpl.setMvccVersion(getMvccVersion());
		repositoryEntryImpl.setUuid(getUuid());
		repositoryEntryImpl.setRepositoryEntryId(getRepositoryEntryId());
		repositoryEntryImpl.setGroupId(getGroupId());
		repositoryEntryImpl.setCompanyId(getCompanyId());
		repositoryEntryImpl.setUserId(getUserId());
		repositoryEntryImpl.setUserName(getUserName());
		repositoryEntryImpl.setCreateDate(getCreateDate());
		repositoryEntryImpl.setModifiedDate(getModifiedDate());
		repositoryEntryImpl.setRepositoryId(getRepositoryId());
		repositoryEntryImpl.setMappedId(getMappedId());
		repositoryEntryImpl.setManualCheckInRequired(isManualCheckInRequired());
		repositoryEntryImpl.setLastPublishDate(getLastPublishDate());

		repositoryEntryImpl.resetOriginalValues();

		return repositoryEntryImpl;
	}

	@Override
	public int compareTo(RepositoryEntry repositoryEntry) {
		long primaryKey = repositoryEntry.getPrimaryKey();

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

		if (!(object instanceof RepositoryEntry)) {
			return false;
		}

		RepositoryEntry repositoryEntry = (RepositoryEntry)object;

		long primaryKey = repositoryEntry.getPrimaryKey();

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

		_setModifiedDate = false;

		_columnBitmask = 0;
	}

	@Override
	public CacheModel<RepositoryEntry> toCacheModel() {
		RepositoryEntryCacheModel repositoryEntryCacheModel =
			new RepositoryEntryCacheModel();

		repositoryEntryCacheModel.mvccVersion = getMvccVersion();

		repositoryEntryCacheModel.uuid = getUuid();

		String uuid = repositoryEntryCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			repositoryEntryCacheModel.uuid = null;
		}

		repositoryEntryCacheModel.repositoryEntryId = getRepositoryEntryId();

		repositoryEntryCacheModel.groupId = getGroupId();

		repositoryEntryCacheModel.companyId = getCompanyId();

		repositoryEntryCacheModel.userId = getUserId();

		repositoryEntryCacheModel.userName = getUserName();

		String userName = repositoryEntryCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			repositoryEntryCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			repositoryEntryCacheModel.createDate = createDate.getTime();
		}
		else {
			repositoryEntryCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			repositoryEntryCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			repositoryEntryCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		repositoryEntryCacheModel.repositoryId = getRepositoryId();

		repositoryEntryCacheModel.mappedId = getMappedId();

		String mappedId = repositoryEntryCacheModel.mappedId;

		if ((mappedId != null) && (mappedId.length() == 0)) {
			repositoryEntryCacheModel.mappedId = null;
		}

		repositoryEntryCacheModel.manualCheckInRequired =
			isManualCheckInRequired();

		Date lastPublishDate = getLastPublishDate();

		if (lastPublishDate != null) {
			repositoryEntryCacheModel.lastPublishDate =
				lastPublishDate.getTime();
		}
		else {
			repositoryEntryCacheModel.lastPublishDate = Long.MIN_VALUE;
		}

		return repositoryEntryCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<RepositoryEntry, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<RepositoryEntry, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<RepositoryEntry, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((RepositoryEntry)this));
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
		Map<String, Function<RepositoryEntry, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<RepositoryEntry, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<RepositoryEntry, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((RepositoryEntry)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, RepositoryEntry>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private long _mvccVersion;
	private String _uuid;
	private long _repositoryEntryId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _repositoryId;
	private String _mappedId;
	private boolean _manualCheckInRequired;
	private Date _lastPublishDate;

	public static long getColumnBitmask(String columnName) {
		return _columnBitmasks.get(columnName);
	}

	public <T> T getColumnValue(String columnName) {
		if (_attributeNames.containsKey(columnName)) {
			columnName = _attributeNames.get(columnName);
		}

		Function<RepositoryEntry, Object> function =
			_attributeGetterFunctions.get(columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((RepositoryEntry)this);
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
		_columnOriginalValues.put("uuid_", _uuid);
		_columnOriginalValues.put("repositoryEntryId", _repositoryEntryId);
		_columnOriginalValues.put("groupId", _groupId);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("userId", _userId);
		_columnOriginalValues.put("userName", _userName);
		_columnOriginalValues.put("createDate", _createDate);
		_columnOriginalValues.put("modifiedDate", _modifiedDate);
		_columnOriginalValues.put("repositoryId", _repositoryId);
		_columnOriginalValues.put("mappedId", _mappedId);
		_columnOriginalValues.put(
			"manualCheckInRequired", _manualCheckInRequired);
		_columnOriginalValues.put("lastPublishDate", _lastPublishDate);
	}

	private static final Map<String, Long> _columnBitmasks;
	private static final Map<String, String> _attributeNames;

	static {
		Map<String, Long> columnBitmasks = new LinkedHashMap<>();

		columnBitmasks.put("mvccVersion", 1L);

		columnBitmasks.put("uuid_", 2L);

		columnBitmasks.put("repositoryEntryId", 4L);

		columnBitmasks.put("groupId", 8L);

		columnBitmasks.put("companyId", 16L);

		columnBitmasks.put("userId", 32L);

		columnBitmasks.put("userName", 64L);

		columnBitmasks.put("createDate", 128L);

		columnBitmasks.put("modifiedDate", 256L);

		columnBitmasks.put("repositoryId", 512L);

		columnBitmasks.put("mappedId", 1024L);

		columnBitmasks.put("manualCheckInRequired", 2048L);

		columnBitmasks.put("lastPublishDate", 4096L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);

		Map<String, String> attributeNames = new LinkedHashMap<>();

		attributeNames.put("uuid_", "uuid");

		_attributeNames = Collections.unmodifiableMap(attributeNames);
	}

	private transient Map<String, Object> _columnOriginalValues;
	private long _columnBitmask;
	private RepositoryEntry _escapedModel;

}