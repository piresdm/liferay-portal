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

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.LayoutSetBranch;
import com.liferay.portal.kernel.model.LayoutSetBranchModel;
import com.liferay.portal.kernel.model.LayoutSetBranchSoap;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
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
 * The base model implementation for the LayoutSetBranch service. Represents a row in the &quot;LayoutSetBranch&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface </code>LayoutSetBranchModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link LayoutSetBranchImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LayoutSetBranchImpl
 * @generated
 */
@JSON(strict = true)
@ProviderType
public class LayoutSetBranchModelImpl
	extends BaseModelImpl<LayoutSetBranch> implements LayoutSetBranchModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a layout set branch model instance should use the <code>LayoutSetBranch</code> interface instead.
	 */
	public static final String TABLE_NAME = "LayoutSetBranch";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"layoutSetBranchId", Types.BIGINT},
		{"groupId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"userId", Types.BIGINT}, {"userName", Types.VARCHAR},
		{"createDate", Types.TIMESTAMP}, {"modifiedDate", Types.TIMESTAMP},
		{"privateLayout", Types.BOOLEAN}, {"name", Types.VARCHAR},
		{"description", Types.VARCHAR}, {"master", Types.BOOLEAN},
		{"logoId", Types.BIGINT}, {"themeId", Types.VARCHAR},
		{"colorSchemeId", Types.VARCHAR}, {"css", Types.CLOB},
		{"settings_", Types.CLOB}, {"layoutSetPrototypeUuid", Types.VARCHAR},
		{"layoutSetPrototypeLinkEnabled", Types.BOOLEAN}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("layoutSetBranchId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("privateLayout", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("name", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("description", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("master", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("logoId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("themeId", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("colorSchemeId", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("css", Types.CLOB);
		TABLE_COLUMNS_MAP.put("settings_", Types.CLOB);
		TABLE_COLUMNS_MAP.put("layoutSetPrototypeUuid", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("layoutSetPrototypeLinkEnabled", Types.BOOLEAN);
	}

	public static final String TABLE_SQL_CREATE =
		"create table LayoutSetBranch (mvccVersion LONG default 0 not null,layoutSetBranchId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,privateLayout BOOLEAN,name VARCHAR(75) null,description STRING null,master BOOLEAN,logoId LONG,themeId VARCHAR(75) null,colorSchemeId VARCHAR(75) null,css TEXT null,settings_ TEXT null,layoutSetPrototypeUuid VARCHAR(75) null,layoutSetPrototypeLinkEnabled BOOLEAN)";

	public static final String TABLE_SQL_DROP = "drop table LayoutSetBranch";

	public static final String ORDER_BY_JPQL =
		" ORDER BY layoutSetBranch.name ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY LayoutSetBranch.name ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(
		com.liferay.portal.util.PropsUtil.get(
			"value.object.entity.cache.enabled.com.liferay.portal.kernel.model.LayoutSetBranch"),
		true);

	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(
		com.liferay.portal.util.PropsUtil.get(
			"value.object.finder.cache.enabled.com.liferay.portal.kernel.model.LayoutSetBranch"),
		true);

	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(
		com.liferay.portal.util.PropsUtil.get(
			"value.object.column.bitmask.enabled.com.liferay.portal.kernel.model.LayoutSetBranch"),
		true);

	public static final long GROUPID_COLUMN_BITMASK = 1L;

	public static final long MASTER_COLUMN_BITMASK = 2L;

	public static final long NAME_COLUMN_BITMASK = 4L;

	public static final long PRIVATELAYOUT_COLUMN_BITMASK = 8L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static LayoutSetBranch toModel(LayoutSetBranchSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		LayoutSetBranch model = new LayoutSetBranchImpl();

		model.setMvccVersion(soapModel.getMvccVersion());
		model.setLayoutSetBranchId(soapModel.getLayoutSetBranchId());
		model.setGroupId(soapModel.getGroupId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setPrivateLayout(soapModel.isPrivateLayout());
		model.setName(soapModel.getName());
		model.setDescription(soapModel.getDescription());
		model.setMaster(soapModel.isMaster());
		model.setLogoId(soapModel.getLogoId());
		model.setThemeId(soapModel.getThemeId());
		model.setColorSchemeId(soapModel.getColorSchemeId());
		model.setCss(soapModel.getCss());
		model.setSettings(soapModel.getSettings());
		model.setLayoutSetPrototypeUuid(soapModel.getLayoutSetPrototypeUuid());
		model.setLayoutSetPrototypeLinkEnabled(
			soapModel.isLayoutSetPrototypeLinkEnabled());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<LayoutSetBranch> toModels(
		LayoutSetBranchSoap[] soapModels) {

		if (soapModels == null) {
			return null;
		}

		List<LayoutSetBranch> models = new ArrayList<LayoutSetBranch>(
			soapModels.length);

		for (LayoutSetBranchSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.portal.util.PropsUtil.get(
			"lock.expiration.time.com.liferay.portal.kernel.model.LayoutSetBranch"));

	public LayoutSetBranchModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _layoutSetBranchId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setLayoutSetBranchId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _layoutSetBranchId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return LayoutSetBranch.class;
	}

	@Override
	public String getModelClassName() {
		return LayoutSetBranch.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<LayoutSetBranch, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<LayoutSetBranch, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<LayoutSetBranch, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((LayoutSetBranch)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<LayoutSetBranch, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<LayoutSetBranch, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(LayoutSetBranch)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<LayoutSetBranch, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<LayoutSetBranch, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, LayoutSetBranch>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			LayoutSetBranch.class.getClassLoader(), LayoutSetBranch.class,
			ModelWrapper.class);

		try {
			Constructor<LayoutSetBranch> constructor =
				(Constructor<LayoutSetBranch>)proxyClass.getConstructor(
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

	private static final Map<String, Function<LayoutSetBranch, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<LayoutSetBranch, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<LayoutSetBranch, Object>>
			attributeGetterFunctions =
				new LinkedHashMap<String, Function<LayoutSetBranch, Object>>();
		Map<String, BiConsumer<LayoutSetBranch, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<LayoutSetBranch, ?>>();

		attributeGetterFunctions.put(
			"mvccVersion",
			new Function<LayoutSetBranch, Object>() {

				@Override
				public Object apply(LayoutSetBranch layoutSetBranch) {
					return layoutSetBranch.getMvccVersion();
				}

			});
		attributeSetterBiConsumers.put(
			"mvccVersion",
			new BiConsumer<LayoutSetBranch, Object>() {

				@Override
				public void accept(
					LayoutSetBranch layoutSetBranch, Object mvccVersion) {

					layoutSetBranch.setMvccVersion((Long)mvccVersion);
				}

			});
		attributeGetterFunctions.put(
			"layoutSetBranchId",
			new Function<LayoutSetBranch, Object>() {

				@Override
				public Object apply(LayoutSetBranch layoutSetBranch) {
					return layoutSetBranch.getLayoutSetBranchId();
				}

			});
		attributeSetterBiConsumers.put(
			"layoutSetBranchId",
			new BiConsumer<LayoutSetBranch, Object>() {

				@Override
				public void accept(
					LayoutSetBranch layoutSetBranch, Object layoutSetBranchId) {

					layoutSetBranch.setLayoutSetBranchId(
						(Long)layoutSetBranchId);
				}

			});
		attributeGetterFunctions.put(
			"groupId",
			new Function<LayoutSetBranch, Object>() {

				@Override
				public Object apply(LayoutSetBranch layoutSetBranch) {
					return layoutSetBranch.getGroupId();
				}

			});
		attributeSetterBiConsumers.put(
			"groupId",
			new BiConsumer<LayoutSetBranch, Object>() {

				@Override
				public void accept(
					LayoutSetBranch layoutSetBranch, Object groupId) {

					layoutSetBranch.setGroupId((Long)groupId);
				}

			});
		attributeGetterFunctions.put(
			"companyId",
			new Function<LayoutSetBranch, Object>() {

				@Override
				public Object apply(LayoutSetBranch layoutSetBranch) {
					return layoutSetBranch.getCompanyId();
				}

			});
		attributeSetterBiConsumers.put(
			"companyId",
			new BiConsumer<LayoutSetBranch, Object>() {

				@Override
				public void accept(
					LayoutSetBranch layoutSetBranch, Object companyId) {

					layoutSetBranch.setCompanyId((Long)companyId);
				}

			});
		attributeGetterFunctions.put(
			"userId",
			new Function<LayoutSetBranch, Object>() {

				@Override
				public Object apply(LayoutSetBranch layoutSetBranch) {
					return layoutSetBranch.getUserId();
				}

			});
		attributeSetterBiConsumers.put(
			"userId",
			new BiConsumer<LayoutSetBranch, Object>() {

				@Override
				public void accept(
					LayoutSetBranch layoutSetBranch, Object userId) {

					layoutSetBranch.setUserId((Long)userId);
				}

			});
		attributeGetterFunctions.put(
			"userName",
			new Function<LayoutSetBranch, Object>() {

				@Override
				public Object apply(LayoutSetBranch layoutSetBranch) {
					return layoutSetBranch.getUserName();
				}

			});
		attributeSetterBiConsumers.put(
			"userName",
			new BiConsumer<LayoutSetBranch, Object>() {

				@Override
				public void accept(
					LayoutSetBranch layoutSetBranch, Object userName) {

					layoutSetBranch.setUserName((String)userName);
				}

			});
		attributeGetterFunctions.put(
			"createDate",
			new Function<LayoutSetBranch, Object>() {

				@Override
				public Object apply(LayoutSetBranch layoutSetBranch) {
					return layoutSetBranch.getCreateDate();
				}

			});
		attributeSetterBiConsumers.put(
			"createDate",
			new BiConsumer<LayoutSetBranch, Object>() {

				@Override
				public void accept(
					LayoutSetBranch layoutSetBranch, Object createDate) {

					layoutSetBranch.setCreateDate((Date)createDate);
				}

			});
		attributeGetterFunctions.put(
			"modifiedDate",
			new Function<LayoutSetBranch, Object>() {

				@Override
				public Object apply(LayoutSetBranch layoutSetBranch) {
					return layoutSetBranch.getModifiedDate();
				}

			});
		attributeSetterBiConsumers.put(
			"modifiedDate",
			new BiConsumer<LayoutSetBranch, Object>() {

				@Override
				public void accept(
					LayoutSetBranch layoutSetBranch, Object modifiedDate) {

					layoutSetBranch.setModifiedDate((Date)modifiedDate);
				}

			});
		attributeGetterFunctions.put(
			"privateLayout",
			new Function<LayoutSetBranch, Object>() {

				@Override
				public Object apply(LayoutSetBranch layoutSetBranch) {
					return layoutSetBranch.getPrivateLayout();
				}

			});
		attributeSetterBiConsumers.put(
			"privateLayout",
			new BiConsumer<LayoutSetBranch, Object>() {

				@Override
				public void accept(
					LayoutSetBranch layoutSetBranch, Object privateLayout) {

					layoutSetBranch.setPrivateLayout((Boolean)privateLayout);
				}

			});
		attributeGetterFunctions.put(
			"name",
			new Function<LayoutSetBranch, Object>() {

				@Override
				public Object apply(LayoutSetBranch layoutSetBranch) {
					return layoutSetBranch.getName();
				}

			});
		attributeSetterBiConsumers.put(
			"name",
			new BiConsumer<LayoutSetBranch, Object>() {

				@Override
				public void accept(
					LayoutSetBranch layoutSetBranch, Object name) {

					layoutSetBranch.setName((String)name);
				}

			});
		attributeGetterFunctions.put(
			"description",
			new Function<LayoutSetBranch, Object>() {

				@Override
				public Object apply(LayoutSetBranch layoutSetBranch) {
					return layoutSetBranch.getDescription();
				}

			});
		attributeSetterBiConsumers.put(
			"description",
			new BiConsumer<LayoutSetBranch, Object>() {

				@Override
				public void accept(
					LayoutSetBranch layoutSetBranch, Object description) {

					layoutSetBranch.setDescription((String)description);
				}

			});
		attributeGetterFunctions.put(
			"master",
			new Function<LayoutSetBranch, Object>() {

				@Override
				public Object apply(LayoutSetBranch layoutSetBranch) {
					return layoutSetBranch.getMaster();
				}

			});
		attributeSetterBiConsumers.put(
			"master",
			new BiConsumer<LayoutSetBranch, Object>() {

				@Override
				public void accept(
					LayoutSetBranch layoutSetBranch, Object master) {

					layoutSetBranch.setMaster((Boolean)master);
				}

			});
		attributeGetterFunctions.put(
			"logoId",
			new Function<LayoutSetBranch, Object>() {

				@Override
				public Object apply(LayoutSetBranch layoutSetBranch) {
					return layoutSetBranch.getLogoId();
				}

			});
		attributeSetterBiConsumers.put(
			"logoId",
			new BiConsumer<LayoutSetBranch, Object>() {

				@Override
				public void accept(
					LayoutSetBranch layoutSetBranch, Object logoId) {

					layoutSetBranch.setLogoId((Long)logoId);
				}

			});
		attributeGetterFunctions.put(
			"themeId",
			new Function<LayoutSetBranch, Object>() {

				@Override
				public Object apply(LayoutSetBranch layoutSetBranch) {
					return layoutSetBranch.getThemeId();
				}

			});
		attributeSetterBiConsumers.put(
			"themeId",
			new BiConsumer<LayoutSetBranch, Object>() {

				@Override
				public void accept(
					LayoutSetBranch layoutSetBranch, Object themeId) {

					layoutSetBranch.setThemeId((String)themeId);
				}

			});
		attributeGetterFunctions.put(
			"colorSchemeId",
			new Function<LayoutSetBranch, Object>() {

				@Override
				public Object apply(LayoutSetBranch layoutSetBranch) {
					return layoutSetBranch.getColorSchemeId();
				}

			});
		attributeSetterBiConsumers.put(
			"colorSchemeId",
			new BiConsumer<LayoutSetBranch, Object>() {

				@Override
				public void accept(
					LayoutSetBranch layoutSetBranch, Object colorSchemeId) {

					layoutSetBranch.setColorSchemeId((String)colorSchemeId);
				}

			});
		attributeGetterFunctions.put(
			"css",
			new Function<LayoutSetBranch, Object>() {

				@Override
				public Object apply(LayoutSetBranch layoutSetBranch) {
					return layoutSetBranch.getCss();
				}

			});
		attributeSetterBiConsumers.put(
			"css",
			new BiConsumer<LayoutSetBranch, Object>() {

				@Override
				public void accept(
					LayoutSetBranch layoutSetBranch, Object css) {

					layoutSetBranch.setCss((String)css);
				}

			});
		attributeGetterFunctions.put(
			"settings",
			new Function<LayoutSetBranch, Object>() {

				@Override
				public Object apply(LayoutSetBranch layoutSetBranch) {
					return layoutSetBranch.getSettings();
				}

			});
		attributeSetterBiConsumers.put(
			"settings",
			new BiConsumer<LayoutSetBranch, Object>() {

				@Override
				public void accept(
					LayoutSetBranch layoutSetBranch, Object settings) {

					layoutSetBranch.setSettings((String)settings);
				}

			});
		attributeGetterFunctions.put(
			"layoutSetPrototypeUuid",
			new Function<LayoutSetBranch, Object>() {

				@Override
				public Object apply(LayoutSetBranch layoutSetBranch) {
					return layoutSetBranch.getLayoutSetPrototypeUuid();
				}

			});
		attributeSetterBiConsumers.put(
			"layoutSetPrototypeUuid",
			new BiConsumer<LayoutSetBranch, Object>() {

				@Override
				public void accept(
					LayoutSetBranch layoutSetBranch,
					Object layoutSetPrototypeUuid) {

					layoutSetBranch.setLayoutSetPrototypeUuid(
						(String)layoutSetPrototypeUuid);
				}

			});
		attributeGetterFunctions.put(
			"layoutSetPrototypeLinkEnabled",
			new Function<LayoutSetBranch, Object>() {

				@Override
				public Object apply(LayoutSetBranch layoutSetBranch) {
					return layoutSetBranch.getLayoutSetPrototypeLinkEnabled();
				}

			});
		attributeSetterBiConsumers.put(
			"layoutSetPrototypeLinkEnabled",
			new BiConsumer<LayoutSetBranch, Object>() {

				@Override
				public void accept(
					LayoutSetBranch layoutSetBranch,
					Object layoutSetPrototypeLinkEnabled) {

					layoutSetBranch.setLayoutSetPrototypeLinkEnabled(
						(Boolean)layoutSetPrototypeLinkEnabled);
				}

			});

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
		_mvccVersion = mvccVersion;
	}

	@JSON
	@Override
	public long getLayoutSetBranchId() {
		return _layoutSetBranchId;
	}

	@Override
	public void setLayoutSetBranchId(long layoutSetBranchId) {
		_layoutSetBranchId = layoutSetBranchId;
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
		_companyId = companyId;
	}

	@JSON
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
	public boolean getPrivateLayout() {
		return _privateLayout;
	}

	@JSON
	@Override
	public boolean isPrivateLayout() {
		return _privateLayout;
	}

	@Override
	public void setPrivateLayout(boolean privateLayout) {
		_columnBitmask |= PRIVATELAYOUT_COLUMN_BITMASK;

		if (!_setOriginalPrivateLayout) {
			_setOriginalPrivateLayout = true;

			_originalPrivateLayout = _privateLayout;
		}

		_privateLayout = privateLayout;
	}

	public boolean getOriginalPrivateLayout() {
		return _originalPrivateLayout;
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
		_columnBitmask = -1L;

		if (_originalName == null) {
			_originalName = _name;
		}

		_name = name;
	}

	public String getOriginalName() {
		return GetterUtil.getString(_originalName);
	}

	@JSON
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
		_description = description;
	}

	@JSON
	@Override
	public boolean getMaster() {
		return _master;
	}

	@JSON
	@Override
	public boolean isMaster() {
		return _master;
	}

	@Override
	public void setMaster(boolean master) {
		_columnBitmask |= MASTER_COLUMN_BITMASK;

		if (!_setOriginalMaster) {
			_setOriginalMaster = true;

			_originalMaster = _master;
		}

		_master = master;
	}

	public boolean getOriginalMaster() {
		return _originalMaster;
	}

	@JSON
	@Override
	public long getLogoId() {
		return _logoId;
	}

	@Override
	public void setLogoId(long logoId) {
		_logoId = logoId;
	}

	@JSON
	@Override
	public String getThemeId() {
		if (_themeId == null) {
			return "";
		}
		else {
			return _themeId;
		}
	}

	@Override
	public void setThemeId(String themeId) {
		_themeId = themeId;
	}

	@JSON
	@Override
	public String getColorSchemeId() {
		if (_colorSchemeId == null) {
			return "";
		}
		else {
			return _colorSchemeId;
		}
	}

	@Override
	public void setColorSchemeId(String colorSchemeId) {
		_colorSchemeId = colorSchemeId;
	}

	@JSON
	@Override
	public String getCss() {
		if (_css == null) {
			return "";
		}
		else {
			return _css;
		}
	}

	@Override
	public void setCss(String css) {
		_css = css;
	}

	@JSON
	@Override
	public String getSettings() {
		if (_settings == null) {
			return "";
		}
		else {
			return _settings;
		}
	}

	@Override
	public void setSettings(String settings) {
		_settings = settings;
	}

	@JSON
	@Override
	public String getLayoutSetPrototypeUuid() {
		if (_layoutSetPrototypeUuid == null) {
			return "";
		}
		else {
			return _layoutSetPrototypeUuid;
		}
	}

	@Override
	public void setLayoutSetPrototypeUuid(String layoutSetPrototypeUuid) {
		_layoutSetPrototypeUuid = layoutSetPrototypeUuid;
	}

	@JSON
	@Override
	public boolean getLayoutSetPrototypeLinkEnabled() {
		return _layoutSetPrototypeLinkEnabled;
	}

	@JSON
	@Override
	public boolean isLayoutSetPrototypeLinkEnabled() {
		return _layoutSetPrototypeLinkEnabled;
	}

	@Override
	public void setLayoutSetPrototypeLinkEnabled(
		boolean layoutSetPrototypeLinkEnabled) {

		_layoutSetPrototypeLinkEnabled = layoutSetPrototypeLinkEnabled;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), LayoutSetBranch.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public LayoutSetBranch toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = _escapedModelProxyProviderFunction.apply(
				new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		LayoutSetBranchImpl layoutSetBranchImpl = new LayoutSetBranchImpl();

		layoutSetBranchImpl.setMvccVersion(getMvccVersion());
		layoutSetBranchImpl.setLayoutSetBranchId(getLayoutSetBranchId());
		layoutSetBranchImpl.setGroupId(getGroupId());
		layoutSetBranchImpl.setCompanyId(getCompanyId());
		layoutSetBranchImpl.setUserId(getUserId());
		layoutSetBranchImpl.setUserName(getUserName());
		layoutSetBranchImpl.setCreateDate(getCreateDate());
		layoutSetBranchImpl.setModifiedDate(getModifiedDate());
		layoutSetBranchImpl.setPrivateLayout(isPrivateLayout());
		layoutSetBranchImpl.setName(getName());
		layoutSetBranchImpl.setDescription(getDescription());
		layoutSetBranchImpl.setMaster(isMaster());
		layoutSetBranchImpl.setLogoId(getLogoId());
		layoutSetBranchImpl.setThemeId(getThemeId());
		layoutSetBranchImpl.setColorSchemeId(getColorSchemeId());
		layoutSetBranchImpl.setCss(getCss());
		layoutSetBranchImpl.setSettings(getSettings());
		layoutSetBranchImpl.setLayoutSetPrototypeUuid(
			getLayoutSetPrototypeUuid());
		layoutSetBranchImpl.setLayoutSetPrototypeLinkEnabled(
			isLayoutSetPrototypeLinkEnabled());

		layoutSetBranchImpl.resetOriginalValues();

		return layoutSetBranchImpl;
	}

	@Override
	public int compareTo(LayoutSetBranch layoutSetBranch) {
		int value = 0;

		value = getName().compareTo(layoutSetBranch.getName());

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

		if (!(obj instanceof LayoutSetBranch)) {
			return false;
		}

		LayoutSetBranch layoutSetBranch = (LayoutSetBranch)obj;

		long primaryKey = layoutSetBranch.getPrimaryKey();

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
		LayoutSetBranchModelImpl layoutSetBranchModelImpl = this;

		layoutSetBranchModelImpl._originalGroupId =
			layoutSetBranchModelImpl._groupId;

		layoutSetBranchModelImpl._setOriginalGroupId = false;

		layoutSetBranchModelImpl._setModifiedDate = false;

		layoutSetBranchModelImpl._originalPrivateLayout =
			layoutSetBranchModelImpl._privateLayout;

		layoutSetBranchModelImpl._setOriginalPrivateLayout = false;

		layoutSetBranchModelImpl._originalName = layoutSetBranchModelImpl._name;

		layoutSetBranchModelImpl._originalMaster =
			layoutSetBranchModelImpl._master;

		layoutSetBranchModelImpl._setOriginalMaster = false;

		layoutSetBranchModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<LayoutSetBranch> toCacheModel() {
		LayoutSetBranchCacheModel layoutSetBranchCacheModel =
			new LayoutSetBranchCacheModel();

		layoutSetBranchCacheModel.mvccVersion = getMvccVersion();

		layoutSetBranchCacheModel.layoutSetBranchId = getLayoutSetBranchId();

		layoutSetBranchCacheModel.groupId = getGroupId();

		layoutSetBranchCacheModel.companyId = getCompanyId();

		layoutSetBranchCacheModel.userId = getUserId();

		layoutSetBranchCacheModel.userName = getUserName();

		String userName = layoutSetBranchCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			layoutSetBranchCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			layoutSetBranchCacheModel.createDate = createDate.getTime();
		}
		else {
			layoutSetBranchCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			layoutSetBranchCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			layoutSetBranchCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		layoutSetBranchCacheModel.privateLayout = isPrivateLayout();

		layoutSetBranchCacheModel.name = getName();

		String name = layoutSetBranchCacheModel.name;

		if ((name != null) && (name.length() == 0)) {
			layoutSetBranchCacheModel.name = null;
		}

		layoutSetBranchCacheModel.description = getDescription();

		String description = layoutSetBranchCacheModel.description;

		if ((description != null) && (description.length() == 0)) {
			layoutSetBranchCacheModel.description = null;
		}

		layoutSetBranchCacheModel.master = isMaster();

		layoutSetBranchCacheModel.logoId = getLogoId();

		layoutSetBranchCacheModel.themeId = getThemeId();

		String themeId = layoutSetBranchCacheModel.themeId;

		if ((themeId != null) && (themeId.length() == 0)) {
			layoutSetBranchCacheModel.themeId = null;
		}

		layoutSetBranchCacheModel.colorSchemeId = getColorSchemeId();

		String colorSchemeId = layoutSetBranchCacheModel.colorSchemeId;

		if ((colorSchemeId != null) && (colorSchemeId.length() == 0)) {
			layoutSetBranchCacheModel.colorSchemeId = null;
		}

		layoutSetBranchCacheModel.css = getCss();

		String css = layoutSetBranchCacheModel.css;

		if ((css != null) && (css.length() == 0)) {
			layoutSetBranchCacheModel.css = null;
		}

		layoutSetBranchCacheModel.settings = getSettings();

		String settings = layoutSetBranchCacheModel.settings;

		if ((settings != null) && (settings.length() == 0)) {
			layoutSetBranchCacheModel.settings = null;
		}

		layoutSetBranchCacheModel.layoutSetPrototypeUuid =
			getLayoutSetPrototypeUuid();

		String layoutSetPrototypeUuid =
			layoutSetBranchCacheModel.layoutSetPrototypeUuid;

		if ((layoutSetPrototypeUuid != null) &&
			(layoutSetPrototypeUuid.length() == 0)) {

			layoutSetBranchCacheModel.layoutSetPrototypeUuid = null;
		}

		layoutSetBranchCacheModel.layoutSetPrototypeLinkEnabled =
			isLayoutSetPrototypeLinkEnabled();

		return layoutSetBranchCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<LayoutSetBranch, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<LayoutSetBranch, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<LayoutSetBranch, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((LayoutSetBranch)this));
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
		Map<String, Function<LayoutSetBranch, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<LayoutSetBranch, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<LayoutSetBranch, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((LayoutSetBranch)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static final Function<InvocationHandler, LayoutSetBranch>
		_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	private long _mvccVersion;
	private long _layoutSetBranchId;
	private long _groupId;
	private long _originalGroupId;
	private boolean _setOriginalGroupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private boolean _privateLayout;
	private boolean _originalPrivateLayout;
	private boolean _setOriginalPrivateLayout;
	private String _name;
	private String _originalName;
	private String _description;
	private boolean _master;
	private boolean _originalMaster;
	private boolean _setOriginalMaster;
	private long _logoId;
	private String _themeId;
	private String _colorSchemeId;
	private String _css;
	private String _settings;
	private String _layoutSetPrototypeUuid;
	private boolean _layoutSetPrototypeLinkEnabled;
	private long _columnBitmask;
	private LayoutSetBranch _escapedModel;

}