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

package com.liferay.sharing.model.impl;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
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
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.sharing.model.SharingEntry;
import com.liferay.sharing.model.SharingEntryModel;
import com.liferay.sharing.model.SharingEntrySoap;

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
 * The base model implementation for the SharingEntry service. Represents a row in the &quot;SharingEntry&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>SharingEntryModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link SharingEntryImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SharingEntryImpl
 * @generated
 */
@JSON(strict = true)
public class SharingEntryModelImpl
	extends BaseModelImpl<SharingEntry> implements SharingEntryModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a sharing entry model instance should use the <code>SharingEntry</code> interface instead.
	 */
	public static final String TABLE_NAME = "SharingEntry";

	public static final Object[][] TABLE_COLUMNS = {
		{"uuid_", Types.VARCHAR}, {"sharingEntryId", Types.BIGINT},
		{"groupId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"userId", Types.BIGINT}, {"userName", Types.VARCHAR},
		{"createDate", Types.TIMESTAMP}, {"modifiedDate", Types.TIMESTAMP},
		{"toUserId", Types.BIGINT}, {"classNameId", Types.BIGINT},
		{"classPK", Types.BIGINT}, {"shareable", Types.BOOLEAN},
		{"actionIds", Types.BIGINT}, {"expirationDate", Types.TIMESTAMP}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("sharingEntryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("toUserId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("classNameId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("classPK", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("shareable", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("actionIds", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("expirationDate", Types.TIMESTAMP);
	}

	public static final String TABLE_SQL_CREATE =
		"create table SharingEntry (uuid_ VARCHAR(75) null,sharingEntryId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,toUserId LONG,classNameId LONG,classPK LONG,shareable BOOLEAN,actionIds LONG,expirationDate DATE null)";

	public static final String TABLE_SQL_DROP = "drop table SharingEntry";

	public static final String ORDER_BY_JPQL =
		" ORDER BY sharingEntry.sharingEntryId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY SharingEntry.sharingEntryId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

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
	public static final long COMPANYID_COLUMN_BITMASK = 4L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)
	 */
	@Deprecated
	public static final long EXPIRATIONDATE_COLUMN_BITMASK = 8L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)
	 */
	@Deprecated
	public static final long GROUPID_COLUMN_BITMASK = 16L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)
	 */
	@Deprecated
	public static final long TOUSERID_COLUMN_BITMASK = 32L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)
	 */
	@Deprecated
	public static final long USERID_COLUMN_BITMASK = 64L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)
	 */
	@Deprecated
	public static final long UUID_COLUMN_BITMASK = 128L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)
	 */
	@Deprecated
	public static final long SHARINGENTRYID_COLUMN_BITMASK = 256L;

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
	public static SharingEntry toModel(SharingEntrySoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		SharingEntry model = new SharingEntryImpl();

		model.setUuid(soapModel.getUuid());
		model.setSharingEntryId(soapModel.getSharingEntryId());
		model.setGroupId(soapModel.getGroupId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setToUserId(soapModel.getToUserId());
		model.setClassNameId(soapModel.getClassNameId());
		model.setClassPK(soapModel.getClassPK());
		model.setShareable(soapModel.isShareable());
		model.setActionIds(soapModel.getActionIds());
		model.setExpirationDate(soapModel.getExpirationDate());

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
	public static List<SharingEntry> toModels(SharingEntrySoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<SharingEntry> models = new ArrayList<SharingEntry>(
			soapModels.length);

		for (SharingEntrySoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public SharingEntryModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _sharingEntryId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setSharingEntryId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _sharingEntryId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return SharingEntry.class;
	}

	@Override
	public String getModelClassName() {
		return SharingEntry.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<SharingEntry, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<SharingEntry, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<SharingEntry, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((SharingEntry)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<SharingEntry, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<SharingEntry, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(SharingEntry)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<SharingEntry, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<SharingEntry, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, SharingEntry>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			SharingEntry.class.getClassLoader(), SharingEntry.class,
			ModelWrapper.class);

		try {
			Constructor<SharingEntry> constructor =
				(Constructor<SharingEntry>)proxyClass.getConstructor(
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

	private static final Map<String, Function<SharingEntry, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<SharingEntry, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<SharingEntry, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<SharingEntry, Object>>();
		Map<String, BiConsumer<SharingEntry, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<SharingEntry, ?>>();

		attributeGetterFunctions.put("uuid", SharingEntry::getUuid);
		attributeSetterBiConsumers.put(
			"uuid", (BiConsumer<SharingEntry, String>)SharingEntry::setUuid);
		attributeGetterFunctions.put(
			"sharingEntryId", SharingEntry::getSharingEntryId);
		attributeSetterBiConsumers.put(
			"sharingEntryId",
			(BiConsumer<SharingEntry, Long>)SharingEntry::setSharingEntryId);
		attributeGetterFunctions.put("groupId", SharingEntry::getGroupId);
		attributeSetterBiConsumers.put(
			"groupId",
			(BiConsumer<SharingEntry, Long>)SharingEntry::setGroupId);
		attributeGetterFunctions.put("companyId", SharingEntry::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId",
			(BiConsumer<SharingEntry, Long>)SharingEntry::setCompanyId);
		attributeGetterFunctions.put("userId", SharingEntry::getUserId);
		attributeSetterBiConsumers.put(
			"userId", (BiConsumer<SharingEntry, Long>)SharingEntry::setUserId);
		attributeGetterFunctions.put("userName", SharingEntry::getUserName);
		attributeSetterBiConsumers.put(
			"userName",
			(BiConsumer<SharingEntry, String>)SharingEntry::setUserName);
		attributeGetterFunctions.put("createDate", SharingEntry::getCreateDate);
		attributeSetterBiConsumers.put(
			"createDate",
			(BiConsumer<SharingEntry, Date>)SharingEntry::setCreateDate);
		attributeGetterFunctions.put(
			"modifiedDate", SharingEntry::getModifiedDate);
		attributeSetterBiConsumers.put(
			"modifiedDate",
			(BiConsumer<SharingEntry, Date>)SharingEntry::setModifiedDate);
		attributeGetterFunctions.put("toUserId", SharingEntry::getToUserId);
		attributeSetterBiConsumers.put(
			"toUserId",
			(BiConsumer<SharingEntry, Long>)SharingEntry::setToUserId);
		attributeGetterFunctions.put(
			"classNameId", SharingEntry::getClassNameId);
		attributeSetterBiConsumers.put(
			"classNameId",
			(BiConsumer<SharingEntry, Long>)SharingEntry::setClassNameId);
		attributeGetterFunctions.put("classPK", SharingEntry::getClassPK);
		attributeSetterBiConsumers.put(
			"classPK",
			(BiConsumer<SharingEntry, Long>)SharingEntry::setClassPK);
		attributeGetterFunctions.put("shareable", SharingEntry::getShareable);
		attributeSetterBiConsumers.put(
			"shareable",
			(BiConsumer<SharingEntry, Boolean>)SharingEntry::setShareable);
		attributeGetterFunctions.put("actionIds", SharingEntry::getActionIds);
		attributeSetterBiConsumers.put(
			"actionIds",
			(BiConsumer<SharingEntry, Long>)SharingEntry::setActionIds);
		attributeGetterFunctions.put(
			"expirationDate", SharingEntry::getExpirationDate);
		attributeSetterBiConsumers.put(
			"expirationDate",
			(BiConsumer<SharingEntry, Date>)SharingEntry::setExpirationDate);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@JSON
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

	@JSON
	@Override
	public long getSharingEntryId() {
		return _sharingEntryId;
	}

	@Override
	public void setSharingEntryId(long sharingEntryId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_sharingEntryId = sharingEntryId;
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

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalCompanyId() {
		return GetterUtil.getLong(getColumnOriginalValue("companyId"));
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

	@JSON
	@Override
	public long getToUserId() {
		return _toUserId;
	}

	@Override
	public void setToUserId(long toUserId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_toUserId = toUserId;
	}

	@Override
	public String getToUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getToUserId());

			return user.getUuid();
		}
		catch (PortalException portalException) {
			return "";
		}
	}

	@Override
	public void setToUserUuid(String toUserUuid) {
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalToUserId() {
		return GetterUtil.getLong(getColumnOriginalValue("toUserId"));
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

	@JSON
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

	@JSON
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

	@JSON
	@Override
	public boolean getShareable() {
		return _shareable;
	}

	@JSON
	@Override
	public boolean isShareable() {
		return _shareable;
	}

	@Override
	public void setShareable(boolean shareable) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_shareable = shareable;
	}

	@JSON
	@Override
	public long getActionIds() {
		return _actionIds;
	}

	@Override
	public void setActionIds(long actionIds) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_actionIds = actionIds;
	}

	@JSON
	@Override
	public Date getExpirationDate() {
		return _expirationDate;
	}

	@Override
	public void setExpirationDate(Date expirationDate) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_expirationDate = expirationDate;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public Date getOriginalExpirationDate() {
		return getColumnOriginalValue("expirationDate");
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(
			PortalUtil.getClassNameId(SharingEntry.class.getName()),
			getClassNameId());
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
			getCompanyId(), SharingEntry.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public SharingEntry toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, SharingEntry>
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
		SharingEntryImpl sharingEntryImpl = new SharingEntryImpl();

		sharingEntryImpl.setUuid(getUuid());
		sharingEntryImpl.setSharingEntryId(getSharingEntryId());
		sharingEntryImpl.setGroupId(getGroupId());
		sharingEntryImpl.setCompanyId(getCompanyId());
		sharingEntryImpl.setUserId(getUserId());
		sharingEntryImpl.setUserName(getUserName());
		sharingEntryImpl.setCreateDate(getCreateDate());
		sharingEntryImpl.setModifiedDate(getModifiedDate());
		sharingEntryImpl.setToUserId(getToUserId());
		sharingEntryImpl.setClassNameId(getClassNameId());
		sharingEntryImpl.setClassPK(getClassPK());
		sharingEntryImpl.setShareable(isShareable());
		sharingEntryImpl.setActionIds(getActionIds());
		sharingEntryImpl.setExpirationDate(getExpirationDate());

		sharingEntryImpl.resetOriginalValues();

		return sharingEntryImpl;
	}

	@Override
	public int compareTo(SharingEntry sharingEntry) {
		long primaryKey = sharingEntry.getPrimaryKey();

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

		if (!(object instanceof SharingEntry)) {
			return false;
		}

		SharingEntry sharingEntry = (SharingEntry)object;

		long primaryKey = sharingEntry.getPrimaryKey();

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

		_setModifiedDate = false;

		_columnBitmask = 0;
	}

	@Override
	public CacheModel<SharingEntry> toCacheModel() {
		SharingEntryCacheModel sharingEntryCacheModel =
			new SharingEntryCacheModel();

		sharingEntryCacheModel.uuid = getUuid();

		String uuid = sharingEntryCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			sharingEntryCacheModel.uuid = null;
		}

		sharingEntryCacheModel.sharingEntryId = getSharingEntryId();

		sharingEntryCacheModel.groupId = getGroupId();

		sharingEntryCacheModel.companyId = getCompanyId();

		sharingEntryCacheModel.userId = getUserId();

		sharingEntryCacheModel.userName = getUserName();

		String userName = sharingEntryCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			sharingEntryCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			sharingEntryCacheModel.createDate = createDate.getTime();
		}
		else {
			sharingEntryCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			sharingEntryCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			sharingEntryCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		sharingEntryCacheModel.toUserId = getToUserId();

		sharingEntryCacheModel.classNameId = getClassNameId();

		sharingEntryCacheModel.classPK = getClassPK();

		sharingEntryCacheModel.shareable = isShareable();

		sharingEntryCacheModel.actionIds = getActionIds();

		Date expirationDate = getExpirationDate();

		if (expirationDate != null) {
			sharingEntryCacheModel.expirationDate = expirationDate.getTime();
		}
		else {
			sharingEntryCacheModel.expirationDate = Long.MIN_VALUE;
		}

		return sharingEntryCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<SharingEntry, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<SharingEntry, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<SharingEntry, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((SharingEntry)this));
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
		Map<String, Function<SharingEntry, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<SharingEntry, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<SharingEntry, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((SharingEntry)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, SharingEntry>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private String _uuid;
	private long _sharingEntryId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _toUserId;
	private long _classNameId;
	private long _classPK;
	private boolean _shareable;
	private long _actionIds;
	private Date _expirationDate;

	public static long getColumnBitmask(String columnName) {
		return _columnBitmasks.get(columnName);
	}

	public <T> T getColumnValue(String columnName) {
		if (_attributeNames.containsKey(columnName)) {
			columnName = _attributeNames.get(columnName);
		}

		Function<SharingEntry, Object> function = _attributeGetterFunctions.get(
			columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((SharingEntry)this);
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

		_columnOriginalValues.put("uuid_", _uuid);
		_columnOriginalValues.put("sharingEntryId", _sharingEntryId);
		_columnOriginalValues.put("groupId", _groupId);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("userId", _userId);
		_columnOriginalValues.put("userName", _userName);
		_columnOriginalValues.put("createDate", _createDate);
		_columnOriginalValues.put("modifiedDate", _modifiedDate);
		_columnOriginalValues.put("toUserId", _toUserId);
		_columnOriginalValues.put("classNameId", _classNameId);
		_columnOriginalValues.put("classPK", _classPK);
		_columnOriginalValues.put("shareable", _shareable);
		_columnOriginalValues.put("actionIds", _actionIds);
		_columnOriginalValues.put("expirationDate", _expirationDate);
	}

	private static final Map<String, Long> _columnBitmasks;
	private static final Map<String, String> _attributeNames;

	static {
		Map<String, Long> columnBitmasks = new LinkedHashMap<>();

		columnBitmasks.put("uuid_", 1L);

		columnBitmasks.put("sharingEntryId", 2L);

		columnBitmasks.put("groupId", 4L);

		columnBitmasks.put("companyId", 8L);

		columnBitmasks.put("userId", 16L);

		columnBitmasks.put("userName", 32L);

		columnBitmasks.put("createDate", 64L);

		columnBitmasks.put("modifiedDate", 128L);

		columnBitmasks.put("toUserId", 256L);

		columnBitmasks.put("classNameId", 512L);

		columnBitmasks.put("classPK", 1024L);

		columnBitmasks.put("shareable", 2048L);

		columnBitmasks.put("actionIds", 4096L);

		columnBitmasks.put("expirationDate", 8192L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);

		Map<String, String> attributeNames = new LinkedHashMap<>();

		attributeNames.put("uuid_", "uuid");

		_attributeNames = Collections.unmodifiableMap(attributeNames);
	}

	private transient Map<String, Object> _columnOriginalValues;
	private long _columnBitmask;
	private SharingEntry _escapedModel;

}