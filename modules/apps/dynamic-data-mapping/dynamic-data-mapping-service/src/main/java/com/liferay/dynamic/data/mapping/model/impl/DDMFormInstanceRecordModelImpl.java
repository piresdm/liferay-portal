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

package com.liferay.dynamic.data.mapping.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.dynamic.data.mapping.model.DDMFormInstanceRecord;
import com.liferay.dynamic.data.mapping.model.DDMFormInstanceRecordModel;
import com.liferay.dynamic.data.mapping.model.DDMFormInstanceRecordSoap;
import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
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
import com.liferay.portal.kernel.util.StringBundler;

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
 * The base model implementation for the DDMFormInstanceRecord service. Represents a row in the &quot;DDMFormInstanceRecord&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface </code>DDMFormInstanceRecordModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link DDMFormInstanceRecordImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DDMFormInstanceRecordImpl
 * @generated
 */
@JSON(strict = true)
@ProviderType
public class DDMFormInstanceRecordModelImpl
	extends BaseModelImpl<DDMFormInstanceRecord>
	implements DDMFormInstanceRecordModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a ddm form instance record model instance should use the <code>DDMFormInstanceRecord</code> interface instead.
	 */
	public static final String TABLE_NAME = "DDMFormInstanceRecord";

	public static final Object[][] TABLE_COLUMNS = {
		{"uuid_", Types.VARCHAR}, {"formInstanceRecordId", Types.BIGINT},
		{"groupId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"userId", Types.BIGINT}, {"userName", Types.VARCHAR},
		{"versionUserId", Types.BIGINT}, {"versionUserName", Types.VARCHAR},
		{"createDate", Types.TIMESTAMP}, {"modifiedDate", Types.TIMESTAMP},
		{"formInstanceId", Types.BIGINT},
		{"formInstanceVersion", Types.VARCHAR}, {"storageId", Types.BIGINT},
		{"version", Types.VARCHAR}, {"lastPublishDate", Types.TIMESTAMP}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("formInstanceRecordId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("versionUserId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("versionUserName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("formInstanceId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("formInstanceVersion", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("storageId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("version", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("lastPublishDate", Types.TIMESTAMP);
	}

	public static final String TABLE_SQL_CREATE =
		"create table DDMFormInstanceRecord (uuid_ VARCHAR(75) null,formInstanceRecordId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,versionUserId LONG,versionUserName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,formInstanceId LONG,formInstanceVersion VARCHAR(75) null,storageId LONG,version VARCHAR(75) null,lastPublishDate DATE null)";

	public static final String TABLE_SQL_DROP =
		"drop table DDMFormInstanceRecord";

	public static final String ORDER_BY_JPQL =
		" ORDER BY ddmFormInstanceRecord.formInstanceRecordId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY DDMFormInstanceRecord.formInstanceRecordId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(
		com.liferay.dynamic.data.mapping.service.util.ServiceProps.get(
			"value.object.entity.cache.enabled.com.liferay.dynamic.data.mapping.model.DDMFormInstanceRecord"),
		true);

	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(
		com.liferay.dynamic.data.mapping.service.util.ServiceProps.get(
			"value.object.finder.cache.enabled.com.liferay.dynamic.data.mapping.model.DDMFormInstanceRecord"),
		true);

	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(
		com.liferay.dynamic.data.mapping.service.util.ServiceProps.get(
			"value.object.column.bitmask.enabled.com.liferay.dynamic.data.mapping.model.DDMFormInstanceRecord"),
		true);

	public static final long COMPANYID_COLUMN_BITMASK = 1L;

	public static final long FORMINSTANCEID_COLUMN_BITMASK = 2L;

	public static final long FORMINSTANCEVERSION_COLUMN_BITMASK = 4L;

	public static final long GROUPID_COLUMN_BITMASK = 8L;

	public static final long USERID_COLUMN_BITMASK = 16L;

	public static final long UUID_COLUMN_BITMASK = 32L;

	public static final long FORMINSTANCERECORDID_COLUMN_BITMASK = 64L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static DDMFormInstanceRecord toModel(
		DDMFormInstanceRecordSoap soapModel) {

		if (soapModel == null) {
			return null;
		}

		DDMFormInstanceRecord model = new DDMFormInstanceRecordImpl();

		model.setUuid(soapModel.getUuid());
		model.setFormInstanceRecordId(soapModel.getFormInstanceRecordId());
		model.setGroupId(soapModel.getGroupId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setVersionUserId(soapModel.getVersionUserId());
		model.setVersionUserName(soapModel.getVersionUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setFormInstanceId(soapModel.getFormInstanceId());
		model.setFormInstanceVersion(soapModel.getFormInstanceVersion());
		model.setStorageId(soapModel.getStorageId());
		model.setVersion(soapModel.getVersion());
		model.setLastPublishDate(soapModel.getLastPublishDate());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<DDMFormInstanceRecord> toModels(
		DDMFormInstanceRecordSoap[] soapModels) {

		if (soapModels == null) {
			return null;
		}

		List<DDMFormInstanceRecord> models =
			new ArrayList<DDMFormInstanceRecord>(soapModels.length);

		for (DDMFormInstanceRecordSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.dynamic.data.mapping.service.util.ServiceProps.get(
			"lock.expiration.time.com.liferay.dynamic.data.mapping.model.DDMFormInstanceRecord"));

	public DDMFormInstanceRecordModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _formInstanceRecordId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setFormInstanceRecordId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _formInstanceRecordId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return DDMFormInstanceRecord.class;
	}

	@Override
	public String getModelClassName() {
		return DDMFormInstanceRecord.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<DDMFormInstanceRecord, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<DDMFormInstanceRecord, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<DDMFormInstanceRecord, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((DDMFormInstanceRecord)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<DDMFormInstanceRecord, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<DDMFormInstanceRecord, Object>
				attributeSetterBiConsumer = attributeSetterBiConsumers.get(
					attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(DDMFormInstanceRecord)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<DDMFormInstanceRecord, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<DDMFormInstanceRecord, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, DDMFormInstanceRecord>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			DDMFormInstanceRecord.class.getClassLoader(),
			DDMFormInstanceRecord.class, ModelWrapper.class);

		try {
			Constructor<DDMFormInstanceRecord> constructor =
				(Constructor<DDMFormInstanceRecord>)proxyClass.getConstructor(
					InvocationHandler.class);

			return invocationHandler -> {
				try {
					return constructor.newInstance(invocationHandler);
				}
				catch (ReflectiveOperationException roe) {
					throw new InternalError(roe);
				}
			};
		}
		catch (NoSuchMethodException nsme) {
			throw new InternalError(nsme);
		}
	}

	private static final Map<String, Function<DDMFormInstanceRecord, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<DDMFormInstanceRecord, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<DDMFormInstanceRecord, Object>>
			attributeGetterFunctions =
				new LinkedHashMap
					<String, Function<DDMFormInstanceRecord, Object>>();
		Map<String, BiConsumer<DDMFormInstanceRecord, ?>>
			attributeSetterBiConsumers =
				new LinkedHashMap
					<String, BiConsumer<DDMFormInstanceRecord, ?>>();

		attributeGetterFunctions.put(
			"uuid",
			new Function<DDMFormInstanceRecord, Object>() {

				@Override
				public Object apply(
					DDMFormInstanceRecord ddmFormInstanceRecord) {

					return ddmFormInstanceRecord.getUuid();
				}

			});
		attributeSetterBiConsumers.put(
			"uuid",
			new BiConsumer<DDMFormInstanceRecord, Object>() {

				@Override
				public void accept(
					DDMFormInstanceRecord ddmFormInstanceRecord, Object uuid) {

					ddmFormInstanceRecord.setUuid((String)uuid);
				}

			});
		attributeGetterFunctions.put(
			"formInstanceRecordId",
			new Function<DDMFormInstanceRecord, Object>() {

				@Override
				public Object apply(
					DDMFormInstanceRecord ddmFormInstanceRecord) {

					return ddmFormInstanceRecord.getFormInstanceRecordId();
				}

			});
		attributeSetterBiConsumers.put(
			"formInstanceRecordId",
			new BiConsumer<DDMFormInstanceRecord, Object>() {

				@Override
				public void accept(
					DDMFormInstanceRecord ddmFormInstanceRecord,
					Object formInstanceRecordId) {

					ddmFormInstanceRecord.setFormInstanceRecordId(
						(Long)formInstanceRecordId);
				}

			});
		attributeGetterFunctions.put(
			"groupId",
			new Function<DDMFormInstanceRecord, Object>() {

				@Override
				public Object apply(
					DDMFormInstanceRecord ddmFormInstanceRecord) {

					return ddmFormInstanceRecord.getGroupId();
				}

			});
		attributeSetterBiConsumers.put(
			"groupId",
			new BiConsumer<DDMFormInstanceRecord, Object>() {

				@Override
				public void accept(
					DDMFormInstanceRecord ddmFormInstanceRecord,
					Object groupId) {

					ddmFormInstanceRecord.setGroupId((Long)groupId);
				}

			});
		attributeGetterFunctions.put(
			"companyId",
			new Function<DDMFormInstanceRecord, Object>() {

				@Override
				public Object apply(
					DDMFormInstanceRecord ddmFormInstanceRecord) {

					return ddmFormInstanceRecord.getCompanyId();
				}

			});
		attributeSetterBiConsumers.put(
			"companyId",
			new BiConsumer<DDMFormInstanceRecord, Object>() {

				@Override
				public void accept(
					DDMFormInstanceRecord ddmFormInstanceRecord,
					Object companyId) {

					ddmFormInstanceRecord.setCompanyId((Long)companyId);
				}

			});
		attributeGetterFunctions.put(
			"userId",
			new Function<DDMFormInstanceRecord, Object>() {

				@Override
				public Object apply(
					DDMFormInstanceRecord ddmFormInstanceRecord) {

					return ddmFormInstanceRecord.getUserId();
				}

			});
		attributeSetterBiConsumers.put(
			"userId",
			new BiConsumer<DDMFormInstanceRecord, Object>() {

				@Override
				public void accept(
					DDMFormInstanceRecord ddmFormInstanceRecord,
					Object userId) {

					ddmFormInstanceRecord.setUserId((Long)userId);
				}

			});
		attributeGetterFunctions.put(
			"userName",
			new Function<DDMFormInstanceRecord, Object>() {

				@Override
				public Object apply(
					DDMFormInstanceRecord ddmFormInstanceRecord) {

					return ddmFormInstanceRecord.getUserName();
				}

			});
		attributeSetterBiConsumers.put(
			"userName",
			new BiConsumer<DDMFormInstanceRecord, Object>() {

				@Override
				public void accept(
					DDMFormInstanceRecord ddmFormInstanceRecord,
					Object userName) {

					ddmFormInstanceRecord.setUserName((String)userName);
				}

			});
		attributeGetterFunctions.put(
			"versionUserId",
			new Function<DDMFormInstanceRecord, Object>() {

				@Override
				public Object apply(
					DDMFormInstanceRecord ddmFormInstanceRecord) {

					return ddmFormInstanceRecord.getVersionUserId();
				}

			});
		attributeSetterBiConsumers.put(
			"versionUserId",
			new BiConsumer<DDMFormInstanceRecord, Object>() {

				@Override
				public void accept(
					DDMFormInstanceRecord ddmFormInstanceRecord,
					Object versionUserId) {

					ddmFormInstanceRecord.setVersionUserId((Long)versionUserId);
				}

			});
		attributeGetterFunctions.put(
			"versionUserName",
			new Function<DDMFormInstanceRecord, Object>() {

				@Override
				public Object apply(
					DDMFormInstanceRecord ddmFormInstanceRecord) {

					return ddmFormInstanceRecord.getVersionUserName();
				}

			});
		attributeSetterBiConsumers.put(
			"versionUserName",
			new BiConsumer<DDMFormInstanceRecord, Object>() {

				@Override
				public void accept(
					DDMFormInstanceRecord ddmFormInstanceRecord,
					Object versionUserName) {

					ddmFormInstanceRecord.setVersionUserName(
						(String)versionUserName);
				}

			});
		attributeGetterFunctions.put(
			"createDate",
			new Function<DDMFormInstanceRecord, Object>() {

				@Override
				public Object apply(
					DDMFormInstanceRecord ddmFormInstanceRecord) {

					return ddmFormInstanceRecord.getCreateDate();
				}

			});
		attributeSetterBiConsumers.put(
			"createDate",
			new BiConsumer<DDMFormInstanceRecord, Object>() {

				@Override
				public void accept(
					DDMFormInstanceRecord ddmFormInstanceRecord,
					Object createDate) {

					ddmFormInstanceRecord.setCreateDate((Date)createDate);
				}

			});
		attributeGetterFunctions.put(
			"modifiedDate",
			new Function<DDMFormInstanceRecord, Object>() {

				@Override
				public Object apply(
					DDMFormInstanceRecord ddmFormInstanceRecord) {

					return ddmFormInstanceRecord.getModifiedDate();
				}

			});
		attributeSetterBiConsumers.put(
			"modifiedDate",
			new BiConsumer<DDMFormInstanceRecord, Object>() {

				@Override
				public void accept(
					DDMFormInstanceRecord ddmFormInstanceRecord,
					Object modifiedDate) {

					ddmFormInstanceRecord.setModifiedDate((Date)modifiedDate);
				}

			});
		attributeGetterFunctions.put(
			"formInstanceId",
			new Function<DDMFormInstanceRecord, Object>() {

				@Override
				public Object apply(
					DDMFormInstanceRecord ddmFormInstanceRecord) {

					return ddmFormInstanceRecord.getFormInstanceId();
				}

			});
		attributeSetterBiConsumers.put(
			"formInstanceId",
			new BiConsumer<DDMFormInstanceRecord, Object>() {

				@Override
				public void accept(
					DDMFormInstanceRecord ddmFormInstanceRecord,
					Object formInstanceId) {

					ddmFormInstanceRecord.setFormInstanceId(
						(Long)formInstanceId);
				}

			});
		attributeGetterFunctions.put(
			"formInstanceVersion",
			new Function<DDMFormInstanceRecord, Object>() {

				@Override
				public Object apply(
					DDMFormInstanceRecord ddmFormInstanceRecord) {

					return ddmFormInstanceRecord.getFormInstanceVersion();
				}

			});
		attributeSetterBiConsumers.put(
			"formInstanceVersion",
			new BiConsumer<DDMFormInstanceRecord, Object>() {

				@Override
				public void accept(
					DDMFormInstanceRecord ddmFormInstanceRecord,
					Object formInstanceVersion) {

					ddmFormInstanceRecord.setFormInstanceVersion(
						(String)formInstanceVersion);
				}

			});
		attributeGetterFunctions.put(
			"storageId",
			new Function<DDMFormInstanceRecord, Object>() {

				@Override
				public Object apply(
					DDMFormInstanceRecord ddmFormInstanceRecord) {

					return ddmFormInstanceRecord.getStorageId();
				}

			});
		attributeSetterBiConsumers.put(
			"storageId",
			new BiConsumer<DDMFormInstanceRecord, Object>() {

				@Override
				public void accept(
					DDMFormInstanceRecord ddmFormInstanceRecord,
					Object storageId) {

					ddmFormInstanceRecord.setStorageId((Long)storageId);
				}

			});
		attributeGetterFunctions.put(
			"version",
			new Function<DDMFormInstanceRecord, Object>() {

				@Override
				public Object apply(
					DDMFormInstanceRecord ddmFormInstanceRecord) {

					return ddmFormInstanceRecord.getVersion();
				}

			});
		attributeSetterBiConsumers.put(
			"version",
			new BiConsumer<DDMFormInstanceRecord, Object>() {

				@Override
				public void accept(
					DDMFormInstanceRecord ddmFormInstanceRecord,
					Object version) {

					ddmFormInstanceRecord.setVersion((String)version);
				}

			});
		attributeGetterFunctions.put(
			"lastPublishDate",
			new Function<DDMFormInstanceRecord, Object>() {

				@Override
				public Object apply(
					DDMFormInstanceRecord ddmFormInstanceRecord) {

					return ddmFormInstanceRecord.getLastPublishDate();
				}

			});
		attributeSetterBiConsumers.put(
			"lastPublishDate",
			new BiConsumer<DDMFormInstanceRecord, Object>() {

				@Override
				public void accept(
					DDMFormInstanceRecord ddmFormInstanceRecord,
					Object lastPublishDate) {

					ddmFormInstanceRecord.setLastPublishDate(
						(Date)lastPublishDate);
				}

			});

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
		_columnBitmask |= UUID_COLUMN_BITMASK;

		if (_originalUuid == null) {
			_originalUuid = _uuid;
		}

		_uuid = uuid;
	}

	public String getOriginalUuid() {
		return GetterUtil.getString(_originalUuid);
	}

	@JSON
	@Override
	public long getFormInstanceRecordId() {
		return _formInstanceRecordId;
	}

	@Override
	public void setFormInstanceRecordId(long formInstanceRecordId) {
		_formInstanceRecordId = formInstanceRecordId;
	}

	@JSON
	@Override
	public long getGroupId() {
		return _groupId;
	}

	@Override
	public void setGroupId(long groupId) {
		_columnBitmask |= GROUPID_COLUMN_BITMASK;

		if (!_setOriginalGroupId) {
			_setOriginalGroupId = true;

			_originalGroupId = _groupId;
		}

		_groupId = groupId;
	}

	public long getOriginalGroupId() {
		return _originalGroupId;
	}

	@JSON
	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_columnBitmask |= COMPANYID_COLUMN_BITMASK;

		if (!_setOriginalCompanyId) {
			_setOriginalCompanyId = true;

			_originalCompanyId = _companyId;
		}

		_companyId = companyId;
	}

	public long getOriginalCompanyId() {
		return _originalCompanyId;
	}

	@JSON
	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
		_columnBitmask |= USERID_COLUMN_BITMASK;

		if (!_setOriginalUserId) {
			_setOriginalUserId = true;

			_originalUserId = _userId;
		}

		_userId = userId;
	}

	@Override
	public String getUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getUserId());

			return user.getUuid();
		}
		catch (PortalException pe) {
			return "";
		}
	}

	@Override
	public void setUserUuid(String userUuid) {
	}

	public long getOriginalUserId() {
		return _originalUserId;
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
		_userName = userName;
	}

	@JSON
	@Override
	public long getVersionUserId() {
		return _versionUserId;
	}

	@Override
	public void setVersionUserId(long versionUserId) {
		_versionUserId = versionUserId;
	}

	@Override
	public String getVersionUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getVersionUserId());

			return user.getUuid();
		}
		catch (PortalException pe) {
			return "";
		}
	}

	@Override
	public void setVersionUserUuid(String versionUserUuid) {
	}

	@JSON
	@Override
	public String getVersionUserName() {
		if (_versionUserName == null) {
			return "";
		}
		else {
			return _versionUserName;
		}
	}

	@Override
	public void setVersionUserName(String versionUserName) {
		_versionUserName = versionUserName;
	}

	@JSON
	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
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

		_modifiedDate = modifiedDate;
	}

	@JSON
	@Override
	public long getFormInstanceId() {
		return _formInstanceId;
	}

	@Override
	public void setFormInstanceId(long formInstanceId) {
		_columnBitmask |= FORMINSTANCEID_COLUMN_BITMASK;

		if (!_setOriginalFormInstanceId) {
			_setOriginalFormInstanceId = true;

			_originalFormInstanceId = _formInstanceId;
		}

		_formInstanceId = formInstanceId;
	}

	public long getOriginalFormInstanceId() {
		return _originalFormInstanceId;
	}

	@JSON
	@Override
	public String getFormInstanceVersion() {
		if (_formInstanceVersion == null) {
			return "";
		}
		else {
			return _formInstanceVersion;
		}
	}

	@Override
	public void setFormInstanceVersion(String formInstanceVersion) {
		_columnBitmask |= FORMINSTANCEVERSION_COLUMN_BITMASK;

		if (_originalFormInstanceVersion == null) {
			_originalFormInstanceVersion = _formInstanceVersion;
		}

		_formInstanceVersion = formInstanceVersion;
	}

	public String getOriginalFormInstanceVersion() {
		return GetterUtil.getString(_originalFormInstanceVersion);
	}

	@JSON
	@Override
	public long getStorageId() {
		return _storageId;
	}

	@Override
	public void setStorageId(long storageId) {
		_storageId = storageId;
	}

	@JSON
	@Override
	public String getVersion() {
		if (_version == null) {
			return "";
		}
		else {
			return _version;
		}
	}

	@Override
	public void setVersion(String version) {
		_version = version;
	}

	@JSON
	@Override
	public Date getLastPublishDate() {
		return _lastPublishDate;
	}

	@Override
	public void setLastPublishDate(Date lastPublishDate) {
		_lastPublishDate = lastPublishDate;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(
			PortalUtil.getClassNameId(DDMFormInstanceRecord.class.getName()));
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), DDMFormInstanceRecord.class.getName(),
			getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public DDMFormInstanceRecord toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = _escapedModelProxyProviderFunction.apply(
				new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		DDMFormInstanceRecordImpl ddmFormInstanceRecordImpl =
			new DDMFormInstanceRecordImpl();

		ddmFormInstanceRecordImpl.setUuid(getUuid());
		ddmFormInstanceRecordImpl.setFormInstanceRecordId(
			getFormInstanceRecordId());
		ddmFormInstanceRecordImpl.setGroupId(getGroupId());
		ddmFormInstanceRecordImpl.setCompanyId(getCompanyId());
		ddmFormInstanceRecordImpl.setUserId(getUserId());
		ddmFormInstanceRecordImpl.setUserName(getUserName());
		ddmFormInstanceRecordImpl.setVersionUserId(getVersionUserId());
		ddmFormInstanceRecordImpl.setVersionUserName(getVersionUserName());
		ddmFormInstanceRecordImpl.setCreateDate(getCreateDate());
		ddmFormInstanceRecordImpl.setModifiedDate(getModifiedDate());
		ddmFormInstanceRecordImpl.setFormInstanceId(getFormInstanceId());
		ddmFormInstanceRecordImpl.setFormInstanceVersion(
			getFormInstanceVersion());
		ddmFormInstanceRecordImpl.setStorageId(getStorageId());
		ddmFormInstanceRecordImpl.setVersion(getVersion());
		ddmFormInstanceRecordImpl.setLastPublishDate(getLastPublishDate());

		ddmFormInstanceRecordImpl.resetOriginalValues();

		return ddmFormInstanceRecordImpl;
	}

	@Override
	public int compareTo(DDMFormInstanceRecord ddmFormInstanceRecord) {
		long primaryKey = ddmFormInstanceRecord.getPrimaryKey();

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
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DDMFormInstanceRecord)) {
			return false;
		}

		DDMFormInstanceRecord ddmFormInstanceRecord =
			(DDMFormInstanceRecord)obj;

		long primaryKey = ddmFormInstanceRecord.getPrimaryKey();

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

	@Override
	public boolean isEntityCacheEnabled() {
		return ENTITY_CACHE_ENABLED;
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return FINDER_CACHE_ENABLED;
	}

	@Override
	public void resetOriginalValues() {
		DDMFormInstanceRecordModelImpl ddmFormInstanceRecordModelImpl = this;

		ddmFormInstanceRecordModelImpl._originalUuid =
			ddmFormInstanceRecordModelImpl._uuid;

		ddmFormInstanceRecordModelImpl._originalGroupId =
			ddmFormInstanceRecordModelImpl._groupId;

		ddmFormInstanceRecordModelImpl._setOriginalGroupId = false;

		ddmFormInstanceRecordModelImpl._originalCompanyId =
			ddmFormInstanceRecordModelImpl._companyId;

		ddmFormInstanceRecordModelImpl._setOriginalCompanyId = false;

		ddmFormInstanceRecordModelImpl._originalUserId =
			ddmFormInstanceRecordModelImpl._userId;

		ddmFormInstanceRecordModelImpl._setOriginalUserId = false;

		ddmFormInstanceRecordModelImpl._setModifiedDate = false;

		ddmFormInstanceRecordModelImpl._originalFormInstanceId =
			ddmFormInstanceRecordModelImpl._formInstanceId;

		ddmFormInstanceRecordModelImpl._setOriginalFormInstanceId = false;

		ddmFormInstanceRecordModelImpl._originalFormInstanceVersion =
			ddmFormInstanceRecordModelImpl._formInstanceVersion;

		ddmFormInstanceRecordModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<DDMFormInstanceRecord> toCacheModel() {
		DDMFormInstanceRecordCacheModel ddmFormInstanceRecordCacheModel =
			new DDMFormInstanceRecordCacheModel();

		ddmFormInstanceRecordCacheModel.uuid = getUuid();

		String uuid = ddmFormInstanceRecordCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			ddmFormInstanceRecordCacheModel.uuid = null;
		}

		ddmFormInstanceRecordCacheModel.formInstanceRecordId =
			getFormInstanceRecordId();

		ddmFormInstanceRecordCacheModel.groupId = getGroupId();

		ddmFormInstanceRecordCacheModel.companyId = getCompanyId();

		ddmFormInstanceRecordCacheModel.userId = getUserId();

		ddmFormInstanceRecordCacheModel.userName = getUserName();

		String userName = ddmFormInstanceRecordCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			ddmFormInstanceRecordCacheModel.userName = null;
		}

		ddmFormInstanceRecordCacheModel.versionUserId = getVersionUserId();

		ddmFormInstanceRecordCacheModel.versionUserName = getVersionUserName();

		String versionUserName =
			ddmFormInstanceRecordCacheModel.versionUserName;

		if ((versionUserName != null) && (versionUserName.length() == 0)) {
			ddmFormInstanceRecordCacheModel.versionUserName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			ddmFormInstanceRecordCacheModel.createDate = createDate.getTime();
		}
		else {
			ddmFormInstanceRecordCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			ddmFormInstanceRecordCacheModel.modifiedDate =
				modifiedDate.getTime();
		}
		else {
			ddmFormInstanceRecordCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		ddmFormInstanceRecordCacheModel.formInstanceId = getFormInstanceId();

		ddmFormInstanceRecordCacheModel.formInstanceVersion =
			getFormInstanceVersion();

		String formInstanceVersion =
			ddmFormInstanceRecordCacheModel.formInstanceVersion;

		if ((formInstanceVersion != null) &&
			(formInstanceVersion.length() == 0)) {

			ddmFormInstanceRecordCacheModel.formInstanceVersion = null;
		}

		ddmFormInstanceRecordCacheModel.storageId = getStorageId();

		ddmFormInstanceRecordCacheModel.version = getVersion();

		String version = ddmFormInstanceRecordCacheModel.version;

		if ((version != null) && (version.length() == 0)) {
			ddmFormInstanceRecordCacheModel.version = null;
		}

		Date lastPublishDate = getLastPublishDate();

		if (lastPublishDate != null) {
			ddmFormInstanceRecordCacheModel.lastPublishDate =
				lastPublishDate.getTime();
		}
		else {
			ddmFormInstanceRecordCacheModel.lastPublishDate = Long.MIN_VALUE;
		}

		return ddmFormInstanceRecordCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<DDMFormInstanceRecord, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<DDMFormInstanceRecord, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<DDMFormInstanceRecord, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(
				attributeGetterFunction.apply((DDMFormInstanceRecord)this));
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
		Map<String, Function<DDMFormInstanceRecord, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<DDMFormInstanceRecord, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<DDMFormInstanceRecord, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(
				attributeGetterFunction.apply((DDMFormInstanceRecord)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static final Function<InvocationHandler, DDMFormInstanceRecord>
		_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	private String _uuid;
	private String _originalUuid;
	private long _formInstanceRecordId;
	private long _groupId;
	private long _originalGroupId;
	private boolean _setOriginalGroupId;
	private long _companyId;
	private long _originalCompanyId;
	private boolean _setOriginalCompanyId;
	private long _userId;
	private long _originalUserId;
	private boolean _setOriginalUserId;
	private String _userName;
	private long _versionUserId;
	private String _versionUserName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _formInstanceId;
	private long _originalFormInstanceId;
	private boolean _setOriginalFormInstanceId;
	private String _formInstanceVersion;
	private String _originalFormInstanceVersion;
	private long _storageId;
	private String _version;
	private Date _lastPublishDate;
	private long _columnBitmask;
	private DDMFormInstanceRecord _escapedModel;

}