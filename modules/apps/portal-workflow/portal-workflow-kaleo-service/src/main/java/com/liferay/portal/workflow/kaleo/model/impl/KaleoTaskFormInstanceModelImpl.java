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

package com.liferay.portal.workflow.kaleo.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskFormInstance;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskFormInstanceModel;

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
 * The base model implementation for the KaleoTaskFormInstance service. Represents a row in the &quot;KaleoTaskFormInstance&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface </code>KaleoTaskFormInstanceModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link KaleoTaskFormInstanceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KaleoTaskFormInstanceImpl
 * @generated
 */
@ProviderType
public class KaleoTaskFormInstanceModelImpl
	extends BaseModelImpl<KaleoTaskFormInstance>
	implements KaleoTaskFormInstanceModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a kaleo task form instance model instance should use the <code>KaleoTaskFormInstance</code> interface instead.
	 */
	public static final String TABLE_NAME = "KaleoTaskFormInstance";

	public static final Object[][] TABLE_COLUMNS = {
		{"kaleoTaskFormInstanceId", Types.BIGINT}, {"groupId", Types.BIGINT},
		{"companyId", Types.BIGINT}, {"userId", Types.BIGINT},
		{"userName", Types.VARCHAR}, {"createDate", Types.TIMESTAMP},
		{"modifiedDate", Types.TIMESTAMP},
		{"kaleoDefinitionVersionId", Types.BIGINT},
		{"kaleoInstanceId", Types.BIGINT}, {"kaleoTaskId", Types.BIGINT},
		{"kaleoTaskInstanceTokenId", Types.BIGINT},
		{"kaleoTaskFormId", Types.BIGINT}, {"formValues", Types.VARCHAR},
		{"formValueEntryGroupId", Types.BIGINT},
		{"formValueEntryId", Types.BIGINT},
		{"formValueEntryUuid", Types.VARCHAR}, {"metadata", Types.VARCHAR}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("kaleoTaskFormInstanceId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("kaleoDefinitionVersionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("kaleoInstanceId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("kaleoTaskId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("kaleoTaskInstanceTokenId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("kaleoTaskFormId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("formValues", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("formValueEntryGroupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("formValueEntryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("formValueEntryUuid", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("metadata", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE =
		"create table KaleoTaskFormInstance (kaleoTaskFormInstanceId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,kaleoDefinitionVersionId LONG,kaleoInstanceId LONG,kaleoTaskId LONG,kaleoTaskInstanceTokenId LONG,kaleoTaskFormId LONG,formValues STRING null,formValueEntryGroupId LONG,formValueEntryId LONG,formValueEntryUuid VARCHAR(75) null,metadata STRING null)";

	public static final String TABLE_SQL_DROP =
		"drop table KaleoTaskFormInstance";

	public static final String ORDER_BY_JPQL =
		" ORDER BY kaleoTaskFormInstance.kaleoTaskFormInstanceId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY KaleoTaskFormInstance.kaleoTaskFormInstanceId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(
		com.liferay.portal.workflow.kaleo.service.util.ServiceProps.get(
			"value.object.entity.cache.enabled.com.liferay.portal.workflow.kaleo.model.KaleoTaskFormInstance"),
		true);

	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(
		com.liferay.portal.workflow.kaleo.service.util.ServiceProps.get(
			"value.object.finder.cache.enabled.com.liferay.portal.workflow.kaleo.model.KaleoTaskFormInstance"),
		true);

	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(
		com.liferay.portal.workflow.kaleo.service.util.ServiceProps.get(
			"value.object.column.bitmask.enabled.com.liferay.portal.workflow.kaleo.model.KaleoTaskFormInstance"),
		true);

	public static final long COMPANYID_COLUMN_BITMASK = 1L;

	public static final long KALEODEFINITIONVERSIONID_COLUMN_BITMASK = 2L;

	public static final long KALEOINSTANCEID_COLUMN_BITMASK = 4L;

	public static final long KALEOTASKFORMID_COLUMN_BITMASK = 8L;

	public static final long KALEOTASKID_COLUMN_BITMASK = 16L;

	public static final long KALEOTASKINSTANCETOKENID_COLUMN_BITMASK = 32L;

	public static final long KALEOTASKFORMINSTANCEID_COLUMN_BITMASK = 64L;

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.portal.workflow.kaleo.service.util.ServiceProps.get(
			"lock.expiration.time.com.liferay.portal.workflow.kaleo.model.KaleoTaskFormInstance"));

	public KaleoTaskFormInstanceModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _kaleoTaskFormInstanceId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setKaleoTaskFormInstanceId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _kaleoTaskFormInstanceId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return KaleoTaskFormInstance.class;
	}

	@Override
	public String getModelClassName() {
		return KaleoTaskFormInstance.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<KaleoTaskFormInstance, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<KaleoTaskFormInstance, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<KaleoTaskFormInstance, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((KaleoTaskFormInstance)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<KaleoTaskFormInstance, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<KaleoTaskFormInstance, Object>
				attributeSetterBiConsumer = attributeSetterBiConsumers.get(
					attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(KaleoTaskFormInstance)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<KaleoTaskFormInstance, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<KaleoTaskFormInstance, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, KaleoTaskFormInstance>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			KaleoTaskFormInstance.class.getClassLoader(),
			KaleoTaskFormInstance.class, ModelWrapper.class);

		try {
			Constructor<KaleoTaskFormInstance> constructor =
				(Constructor<KaleoTaskFormInstance>)proxyClass.getConstructor(
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

	private static final Map<String, Function<KaleoTaskFormInstance, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<KaleoTaskFormInstance, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<KaleoTaskFormInstance, Object>>
			attributeGetterFunctions =
				new LinkedHashMap
					<String, Function<KaleoTaskFormInstance, Object>>();
		Map<String, BiConsumer<KaleoTaskFormInstance, ?>>
			attributeSetterBiConsumers =
				new LinkedHashMap
					<String, BiConsumer<KaleoTaskFormInstance, ?>>();

		attributeGetterFunctions.put(
			"kaleoTaskFormInstanceId",
			new Function<KaleoTaskFormInstance, Object>() {

				@Override
				public Object apply(
					KaleoTaskFormInstance kaleoTaskFormInstance) {

					return kaleoTaskFormInstance.getKaleoTaskFormInstanceId();
				}

			});
		attributeSetterBiConsumers.put(
			"kaleoTaskFormInstanceId",
			new BiConsumer<KaleoTaskFormInstance, Object>() {

				@Override
				public void accept(
					KaleoTaskFormInstance kaleoTaskFormInstance,
					Object kaleoTaskFormInstanceId) {

					kaleoTaskFormInstance.setKaleoTaskFormInstanceId(
						(Long)kaleoTaskFormInstanceId);
				}

			});
		attributeGetterFunctions.put(
			"groupId",
			new Function<KaleoTaskFormInstance, Object>() {

				@Override
				public Object apply(
					KaleoTaskFormInstance kaleoTaskFormInstance) {

					return kaleoTaskFormInstance.getGroupId();
				}

			});
		attributeSetterBiConsumers.put(
			"groupId",
			new BiConsumer<KaleoTaskFormInstance, Object>() {

				@Override
				public void accept(
					KaleoTaskFormInstance kaleoTaskFormInstance,
					Object groupId) {

					kaleoTaskFormInstance.setGroupId((Long)groupId);
				}

			});
		attributeGetterFunctions.put(
			"companyId",
			new Function<KaleoTaskFormInstance, Object>() {

				@Override
				public Object apply(
					KaleoTaskFormInstance kaleoTaskFormInstance) {

					return kaleoTaskFormInstance.getCompanyId();
				}

			});
		attributeSetterBiConsumers.put(
			"companyId",
			new BiConsumer<KaleoTaskFormInstance, Object>() {

				@Override
				public void accept(
					KaleoTaskFormInstance kaleoTaskFormInstance,
					Object companyId) {

					kaleoTaskFormInstance.setCompanyId((Long)companyId);
				}

			});
		attributeGetterFunctions.put(
			"userId",
			new Function<KaleoTaskFormInstance, Object>() {

				@Override
				public Object apply(
					KaleoTaskFormInstance kaleoTaskFormInstance) {

					return kaleoTaskFormInstance.getUserId();
				}

			});
		attributeSetterBiConsumers.put(
			"userId",
			new BiConsumer<KaleoTaskFormInstance, Object>() {

				@Override
				public void accept(
					KaleoTaskFormInstance kaleoTaskFormInstance,
					Object userId) {

					kaleoTaskFormInstance.setUserId((Long)userId);
				}

			});
		attributeGetterFunctions.put(
			"userName",
			new Function<KaleoTaskFormInstance, Object>() {

				@Override
				public Object apply(
					KaleoTaskFormInstance kaleoTaskFormInstance) {

					return kaleoTaskFormInstance.getUserName();
				}

			});
		attributeSetterBiConsumers.put(
			"userName",
			new BiConsumer<KaleoTaskFormInstance, Object>() {

				@Override
				public void accept(
					KaleoTaskFormInstance kaleoTaskFormInstance,
					Object userName) {

					kaleoTaskFormInstance.setUserName((String)userName);
				}

			});
		attributeGetterFunctions.put(
			"createDate",
			new Function<KaleoTaskFormInstance, Object>() {

				@Override
				public Object apply(
					KaleoTaskFormInstance kaleoTaskFormInstance) {

					return kaleoTaskFormInstance.getCreateDate();
				}

			});
		attributeSetterBiConsumers.put(
			"createDate",
			new BiConsumer<KaleoTaskFormInstance, Object>() {

				@Override
				public void accept(
					KaleoTaskFormInstance kaleoTaskFormInstance,
					Object createDate) {

					kaleoTaskFormInstance.setCreateDate((Date)createDate);
				}

			});
		attributeGetterFunctions.put(
			"modifiedDate",
			new Function<KaleoTaskFormInstance, Object>() {

				@Override
				public Object apply(
					KaleoTaskFormInstance kaleoTaskFormInstance) {

					return kaleoTaskFormInstance.getModifiedDate();
				}

			});
		attributeSetterBiConsumers.put(
			"modifiedDate",
			new BiConsumer<KaleoTaskFormInstance, Object>() {

				@Override
				public void accept(
					KaleoTaskFormInstance kaleoTaskFormInstance,
					Object modifiedDate) {

					kaleoTaskFormInstance.setModifiedDate((Date)modifiedDate);
				}

			});
		attributeGetterFunctions.put(
			"kaleoDefinitionVersionId",
			new Function<KaleoTaskFormInstance, Object>() {

				@Override
				public Object apply(
					KaleoTaskFormInstance kaleoTaskFormInstance) {

					return kaleoTaskFormInstance.getKaleoDefinitionVersionId();
				}

			});
		attributeSetterBiConsumers.put(
			"kaleoDefinitionVersionId",
			new BiConsumer<KaleoTaskFormInstance, Object>() {

				@Override
				public void accept(
					KaleoTaskFormInstance kaleoTaskFormInstance,
					Object kaleoDefinitionVersionId) {

					kaleoTaskFormInstance.setKaleoDefinitionVersionId(
						(Long)kaleoDefinitionVersionId);
				}

			});
		attributeGetterFunctions.put(
			"kaleoInstanceId",
			new Function<KaleoTaskFormInstance, Object>() {

				@Override
				public Object apply(
					KaleoTaskFormInstance kaleoTaskFormInstance) {

					return kaleoTaskFormInstance.getKaleoInstanceId();
				}

			});
		attributeSetterBiConsumers.put(
			"kaleoInstanceId",
			new BiConsumer<KaleoTaskFormInstance, Object>() {

				@Override
				public void accept(
					KaleoTaskFormInstance kaleoTaskFormInstance,
					Object kaleoInstanceId) {

					kaleoTaskFormInstance.setKaleoInstanceId(
						(Long)kaleoInstanceId);
				}

			});
		attributeGetterFunctions.put(
			"kaleoTaskId",
			new Function<KaleoTaskFormInstance, Object>() {

				@Override
				public Object apply(
					KaleoTaskFormInstance kaleoTaskFormInstance) {

					return kaleoTaskFormInstance.getKaleoTaskId();
				}

			});
		attributeSetterBiConsumers.put(
			"kaleoTaskId",
			new BiConsumer<KaleoTaskFormInstance, Object>() {

				@Override
				public void accept(
					KaleoTaskFormInstance kaleoTaskFormInstance,
					Object kaleoTaskId) {

					kaleoTaskFormInstance.setKaleoTaskId((Long)kaleoTaskId);
				}

			});
		attributeGetterFunctions.put(
			"kaleoTaskInstanceTokenId",
			new Function<KaleoTaskFormInstance, Object>() {

				@Override
				public Object apply(
					KaleoTaskFormInstance kaleoTaskFormInstance) {

					return kaleoTaskFormInstance.getKaleoTaskInstanceTokenId();
				}

			});
		attributeSetterBiConsumers.put(
			"kaleoTaskInstanceTokenId",
			new BiConsumer<KaleoTaskFormInstance, Object>() {

				@Override
				public void accept(
					KaleoTaskFormInstance kaleoTaskFormInstance,
					Object kaleoTaskInstanceTokenId) {

					kaleoTaskFormInstance.setKaleoTaskInstanceTokenId(
						(Long)kaleoTaskInstanceTokenId);
				}

			});
		attributeGetterFunctions.put(
			"kaleoTaskFormId",
			new Function<KaleoTaskFormInstance, Object>() {

				@Override
				public Object apply(
					KaleoTaskFormInstance kaleoTaskFormInstance) {

					return kaleoTaskFormInstance.getKaleoTaskFormId();
				}

			});
		attributeSetterBiConsumers.put(
			"kaleoTaskFormId",
			new BiConsumer<KaleoTaskFormInstance, Object>() {

				@Override
				public void accept(
					KaleoTaskFormInstance kaleoTaskFormInstance,
					Object kaleoTaskFormId) {

					kaleoTaskFormInstance.setKaleoTaskFormId(
						(Long)kaleoTaskFormId);
				}

			});
		attributeGetterFunctions.put(
			"formValues",
			new Function<KaleoTaskFormInstance, Object>() {

				@Override
				public Object apply(
					KaleoTaskFormInstance kaleoTaskFormInstance) {

					return kaleoTaskFormInstance.getFormValues();
				}

			});
		attributeSetterBiConsumers.put(
			"formValues",
			new BiConsumer<KaleoTaskFormInstance, Object>() {

				@Override
				public void accept(
					KaleoTaskFormInstance kaleoTaskFormInstance,
					Object formValues) {

					kaleoTaskFormInstance.setFormValues((String)formValues);
				}

			});
		attributeGetterFunctions.put(
			"formValueEntryGroupId",
			new Function<KaleoTaskFormInstance, Object>() {

				@Override
				public Object apply(
					KaleoTaskFormInstance kaleoTaskFormInstance) {

					return kaleoTaskFormInstance.getFormValueEntryGroupId();
				}

			});
		attributeSetterBiConsumers.put(
			"formValueEntryGroupId",
			new BiConsumer<KaleoTaskFormInstance, Object>() {

				@Override
				public void accept(
					KaleoTaskFormInstance kaleoTaskFormInstance,
					Object formValueEntryGroupId) {

					kaleoTaskFormInstance.setFormValueEntryGroupId(
						(Long)formValueEntryGroupId);
				}

			});
		attributeGetterFunctions.put(
			"formValueEntryId",
			new Function<KaleoTaskFormInstance, Object>() {

				@Override
				public Object apply(
					KaleoTaskFormInstance kaleoTaskFormInstance) {

					return kaleoTaskFormInstance.getFormValueEntryId();
				}

			});
		attributeSetterBiConsumers.put(
			"formValueEntryId",
			new BiConsumer<KaleoTaskFormInstance, Object>() {

				@Override
				public void accept(
					KaleoTaskFormInstance kaleoTaskFormInstance,
					Object formValueEntryId) {

					kaleoTaskFormInstance.setFormValueEntryId(
						(Long)formValueEntryId);
				}

			});
		attributeGetterFunctions.put(
			"formValueEntryUuid",
			new Function<KaleoTaskFormInstance, Object>() {

				@Override
				public Object apply(
					KaleoTaskFormInstance kaleoTaskFormInstance) {

					return kaleoTaskFormInstance.getFormValueEntryUuid();
				}

			});
		attributeSetterBiConsumers.put(
			"formValueEntryUuid",
			new BiConsumer<KaleoTaskFormInstance, Object>() {

				@Override
				public void accept(
					KaleoTaskFormInstance kaleoTaskFormInstance,
					Object formValueEntryUuid) {

					kaleoTaskFormInstance.setFormValueEntryUuid(
						(String)formValueEntryUuid);
				}

			});
		attributeGetterFunctions.put(
			"metadata",
			new Function<KaleoTaskFormInstance, Object>() {

				@Override
				public Object apply(
					KaleoTaskFormInstance kaleoTaskFormInstance) {

					return kaleoTaskFormInstance.getMetadata();
				}

			});
		attributeSetterBiConsumers.put(
			"metadata",
			new BiConsumer<KaleoTaskFormInstance, Object>() {

				@Override
				public void accept(
					KaleoTaskFormInstance kaleoTaskFormInstance,
					Object metadata) {

					kaleoTaskFormInstance.setMetadata((String)metadata);
				}

			});

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@Override
	public long getKaleoTaskFormInstanceId() {
		return _kaleoTaskFormInstanceId;
	}

	@Override
	public void setKaleoTaskFormInstanceId(long kaleoTaskFormInstanceId) {
		_columnBitmask = -1L;

		_kaleoTaskFormInstanceId = kaleoTaskFormInstanceId;
	}

	@Override
	public long getGroupId() {
		return _groupId;
	}

	@Override
	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

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

	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
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

	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
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

		_modifiedDate = modifiedDate;
	}

	@Override
	public long getKaleoDefinitionVersionId() {
		return _kaleoDefinitionVersionId;
	}

	@Override
	public void setKaleoDefinitionVersionId(long kaleoDefinitionVersionId) {
		_columnBitmask |= KALEODEFINITIONVERSIONID_COLUMN_BITMASK;

		if (!_setOriginalKaleoDefinitionVersionId) {
			_setOriginalKaleoDefinitionVersionId = true;

			_originalKaleoDefinitionVersionId = _kaleoDefinitionVersionId;
		}

		_kaleoDefinitionVersionId = kaleoDefinitionVersionId;
	}

	public long getOriginalKaleoDefinitionVersionId() {
		return _originalKaleoDefinitionVersionId;
	}

	@Override
	public long getKaleoInstanceId() {
		return _kaleoInstanceId;
	}

	@Override
	public void setKaleoInstanceId(long kaleoInstanceId) {
		_columnBitmask |= KALEOINSTANCEID_COLUMN_BITMASK;

		if (!_setOriginalKaleoInstanceId) {
			_setOriginalKaleoInstanceId = true;

			_originalKaleoInstanceId = _kaleoInstanceId;
		}

		_kaleoInstanceId = kaleoInstanceId;
	}

	public long getOriginalKaleoInstanceId() {
		return _originalKaleoInstanceId;
	}

	@Override
	public long getKaleoTaskId() {
		return _kaleoTaskId;
	}

	@Override
	public void setKaleoTaskId(long kaleoTaskId) {
		_columnBitmask |= KALEOTASKID_COLUMN_BITMASK;

		if (!_setOriginalKaleoTaskId) {
			_setOriginalKaleoTaskId = true;

			_originalKaleoTaskId = _kaleoTaskId;
		}

		_kaleoTaskId = kaleoTaskId;
	}

	public long getOriginalKaleoTaskId() {
		return _originalKaleoTaskId;
	}

	@Override
	public long getKaleoTaskInstanceTokenId() {
		return _kaleoTaskInstanceTokenId;
	}

	@Override
	public void setKaleoTaskInstanceTokenId(long kaleoTaskInstanceTokenId) {
		_columnBitmask |= KALEOTASKINSTANCETOKENID_COLUMN_BITMASK;

		if (!_setOriginalKaleoTaskInstanceTokenId) {
			_setOriginalKaleoTaskInstanceTokenId = true;

			_originalKaleoTaskInstanceTokenId = _kaleoTaskInstanceTokenId;
		}

		_kaleoTaskInstanceTokenId = kaleoTaskInstanceTokenId;
	}

	public long getOriginalKaleoTaskInstanceTokenId() {
		return _originalKaleoTaskInstanceTokenId;
	}

	@Override
	public long getKaleoTaskFormId() {
		return _kaleoTaskFormId;
	}

	@Override
	public void setKaleoTaskFormId(long kaleoTaskFormId) {
		_columnBitmask |= KALEOTASKFORMID_COLUMN_BITMASK;

		if (!_setOriginalKaleoTaskFormId) {
			_setOriginalKaleoTaskFormId = true;

			_originalKaleoTaskFormId = _kaleoTaskFormId;
		}

		_kaleoTaskFormId = kaleoTaskFormId;
	}

	public long getOriginalKaleoTaskFormId() {
		return _originalKaleoTaskFormId;
	}

	@Override
	public String getFormValues() {
		if (_formValues == null) {
			return "";
		}
		else {
			return _formValues;
		}
	}

	@Override
	public void setFormValues(String formValues) {
		_formValues = formValues;
	}

	@Override
	public long getFormValueEntryGroupId() {
		return _formValueEntryGroupId;
	}

	@Override
	public void setFormValueEntryGroupId(long formValueEntryGroupId) {
		_formValueEntryGroupId = formValueEntryGroupId;
	}

	@Override
	public long getFormValueEntryId() {
		return _formValueEntryId;
	}

	@Override
	public void setFormValueEntryId(long formValueEntryId) {
		_formValueEntryId = formValueEntryId;
	}

	@Override
	public String getFormValueEntryUuid() {
		if (_formValueEntryUuid == null) {
			return "";
		}
		else {
			return _formValueEntryUuid;
		}
	}

	@Override
	public void setFormValueEntryUuid(String formValueEntryUuid) {
		_formValueEntryUuid = formValueEntryUuid;
	}

	@Override
	public String getMetadata() {
		if (_metadata == null) {
			return "";
		}
		else {
			return _metadata;
		}
	}

	@Override
	public void setMetadata(String metadata) {
		_metadata = metadata;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), KaleoTaskFormInstance.class.getName(),
			getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public KaleoTaskFormInstance toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = _escapedModelProxyProviderFunction.apply(
				new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		KaleoTaskFormInstanceImpl kaleoTaskFormInstanceImpl =
			new KaleoTaskFormInstanceImpl();

		kaleoTaskFormInstanceImpl.setKaleoTaskFormInstanceId(
			getKaleoTaskFormInstanceId());
		kaleoTaskFormInstanceImpl.setGroupId(getGroupId());
		kaleoTaskFormInstanceImpl.setCompanyId(getCompanyId());
		kaleoTaskFormInstanceImpl.setUserId(getUserId());
		kaleoTaskFormInstanceImpl.setUserName(getUserName());
		kaleoTaskFormInstanceImpl.setCreateDate(getCreateDate());
		kaleoTaskFormInstanceImpl.setModifiedDate(getModifiedDate());
		kaleoTaskFormInstanceImpl.setKaleoDefinitionVersionId(
			getKaleoDefinitionVersionId());
		kaleoTaskFormInstanceImpl.setKaleoInstanceId(getKaleoInstanceId());
		kaleoTaskFormInstanceImpl.setKaleoTaskId(getKaleoTaskId());
		kaleoTaskFormInstanceImpl.setKaleoTaskInstanceTokenId(
			getKaleoTaskInstanceTokenId());
		kaleoTaskFormInstanceImpl.setKaleoTaskFormId(getKaleoTaskFormId());
		kaleoTaskFormInstanceImpl.setFormValues(getFormValues());
		kaleoTaskFormInstanceImpl.setFormValueEntryGroupId(
			getFormValueEntryGroupId());
		kaleoTaskFormInstanceImpl.setFormValueEntryId(getFormValueEntryId());
		kaleoTaskFormInstanceImpl.setFormValueEntryUuid(
			getFormValueEntryUuid());
		kaleoTaskFormInstanceImpl.setMetadata(getMetadata());

		kaleoTaskFormInstanceImpl.resetOriginalValues();

		return kaleoTaskFormInstanceImpl;
	}

	@Override
	public int compareTo(KaleoTaskFormInstance kaleoTaskFormInstance) {
		int value = 0;

		if (getKaleoTaskFormInstanceId() <
				kaleoTaskFormInstance.getKaleoTaskFormInstanceId()) {

			value = -1;
		}
		else if (getKaleoTaskFormInstanceId() >
					kaleoTaskFormInstance.getKaleoTaskFormInstanceId()) {

			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof KaleoTaskFormInstance)) {
			return false;
		}

		KaleoTaskFormInstance kaleoTaskFormInstance =
			(KaleoTaskFormInstance)obj;

		long primaryKey = kaleoTaskFormInstance.getPrimaryKey();

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
		KaleoTaskFormInstanceModelImpl kaleoTaskFormInstanceModelImpl = this;

		kaleoTaskFormInstanceModelImpl._originalCompanyId =
			kaleoTaskFormInstanceModelImpl._companyId;

		kaleoTaskFormInstanceModelImpl._setOriginalCompanyId = false;

		kaleoTaskFormInstanceModelImpl._setModifiedDate = false;

		kaleoTaskFormInstanceModelImpl._originalKaleoDefinitionVersionId =
			kaleoTaskFormInstanceModelImpl._kaleoDefinitionVersionId;

		kaleoTaskFormInstanceModelImpl._setOriginalKaleoDefinitionVersionId =
			false;

		kaleoTaskFormInstanceModelImpl._originalKaleoInstanceId =
			kaleoTaskFormInstanceModelImpl._kaleoInstanceId;

		kaleoTaskFormInstanceModelImpl._setOriginalKaleoInstanceId = false;

		kaleoTaskFormInstanceModelImpl._originalKaleoTaskId =
			kaleoTaskFormInstanceModelImpl._kaleoTaskId;

		kaleoTaskFormInstanceModelImpl._setOriginalKaleoTaskId = false;

		kaleoTaskFormInstanceModelImpl._originalKaleoTaskInstanceTokenId =
			kaleoTaskFormInstanceModelImpl._kaleoTaskInstanceTokenId;

		kaleoTaskFormInstanceModelImpl._setOriginalKaleoTaskInstanceTokenId =
			false;

		kaleoTaskFormInstanceModelImpl._originalKaleoTaskFormId =
			kaleoTaskFormInstanceModelImpl._kaleoTaskFormId;

		kaleoTaskFormInstanceModelImpl._setOriginalKaleoTaskFormId = false;

		kaleoTaskFormInstanceModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<KaleoTaskFormInstance> toCacheModel() {
		KaleoTaskFormInstanceCacheModel kaleoTaskFormInstanceCacheModel =
			new KaleoTaskFormInstanceCacheModel();

		kaleoTaskFormInstanceCacheModel.kaleoTaskFormInstanceId =
			getKaleoTaskFormInstanceId();

		kaleoTaskFormInstanceCacheModel.groupId = getGroupId();

		kaleoTaskFormInstanceCacheModel.companyId = getCompanyId();

		kaleoTaskFormInstanceCacheModel.userId = getUserId();

		kaleoTaskFormInstanceCacheModel.userName = getUserName();

		String userName = kaleoTaskFormInstanceCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			kaleoTaskFormInstanceCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			kaleoTaskFormInstanceCacheModel.createDate = createDate.getTime();
		}
		else {
			kaleoTaskFormInstanceCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			kaleoTaskFormInstanceCacheModel.modifiedDate =
				modifiedDate.getTime();
		}
		else {
			kaleoTaskFormInstanceCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		kaleoTaskFormInstanceCacheModel.kaleoDefinitionVersionId =
			getKaleoDefinitionVersionId();

		kaleoTaskFormInstanceCacheModel.kaleoInstanceId = getKaleoInstanceId();

		kaleoTaskFormInstanceCacheModel.kaleoTaskId = getKaleoTaskId();

		kaleoTaskFormInstanceCacheModel.kaleoTaskInstanceTokenId =
			getKaleoTaskInstanceTokenId();

		kaleoTaskFormInstanceCacheModel.kaleoTaskFormId = getKaleoTaskFormId();

		kaleoTaskFormInstanceCacheModel.formValues = getFormValues();

		String formValues = kaleoTaskFormInstanceCacheModel.formValues;

		if ((formValues != null) && (formValues.length() == 0)) {
			kaleoTaskFormInstanceCacheModel.formValues = null;
		}

		kaleoTaskFormInstanceCacheModel.formValueEntryGroupId =
			getFormValueEntryGroupId();

		kaleoTaskFormInstanceCacheModel.formValueEntryId =
			getFormValueEntryId();

		kaleoTaskFormInstanceCacheModel.formValueEntryUuid =
			getFormValueEntryUuid();

		String formValueEntryUuid =
			kaleoTaskFormInstanceCacheModel.formValueEntryUuid;

		if ((formValueEntryUuid != null) &&
			(formValueEntryUuid.length() == 0)) {

			kaleoTaskFormInstanceCacheModel.formValueEntryUuid = null;
		}

		kaleoTaskFormInstanceCacheModel.metadata = getMetadata();

		String metadata = kaleoTaskFormInstanceCacheModel.metadata;

		if ((metadata != null) && (metadata.length() == 0)) {
			kaleoTaskFormInstanceCacheModel.metadata = null;
		}

		return kaleoTaskFormInstanceCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<KaleoTaskFormInstance, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<KaleoTaskFormInstance, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<KaleoTaskFormInstance, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(
				attributeGetterFunction.apply((KaleoTaskFormInstance)this));
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
		Map<String, Function<KaleoTaskFormInstance, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<KaleoTaskFormInstance, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<KaleoTaskFormInstance, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(
				attributeGetterFunction.apply((KaleoTaskFormInstance)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static final Function<InvocationHandler, KaleoTaskFormInstance>
		_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	private long _kaleoTaskFormInstanceId;
	private long _groupId;
	private long _companyId;
	private long _originalCompanyId;
	private boolean _setOriginalCompanyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _kaleoDefinitionVersionId;
	private long _originalKaleoDefinitionVersionId;
	private boolean _setOriginalKaleoDefinitionVersionId;
	private long _kaleoInstanceId;
	private long _originalKaleoInstanceId;
	private boolean _setOriginalKaleoInstanceId;
	private long _kaleoTaskId;
	private long _originalKaleoTaskId;
	private boolean _setOriginalKaleoTaskId;
	private long _kaleoTaskInstanceTokenId;
	private long _originalKaleoTaskInstanceTokenId;
	private boolean _setOriginalKaleoTaskInstanceTokenId;
	private long _kaleoTaskFormId;
	private long _originalKaleoTaskFormId;
	private boolean _setOriginalKaleoTaskFormId;
	private String _formValues;
	private long _formValueEntryGroupId;
	private long _formValueEntryId;
	private String _formValueEntryUuid;
	private String _metadata;
	private long _columnBitmask;
	private KaleoTaskFormInstance _escapedModel;

}