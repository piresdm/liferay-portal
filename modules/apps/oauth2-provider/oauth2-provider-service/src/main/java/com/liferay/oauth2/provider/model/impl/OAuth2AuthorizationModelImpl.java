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

package com.liferay.oauth2.provider.model.impl;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.oauth2.provider.model.OAuth2Authorization;
import com.liferay.oauth2.provider.model.OAuth2AuthorizationModel;
import com.liferay.oauth2.provider.model.OAuth2AuthorizationSoap;
import com.liferay.petra.string.StringBundler;
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
 * The base model implementation for the OAuth2Authorization service. Represents a row in the &quot;OAuth2Authorization&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>OAuth2AuthorizationModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link OAuth2AuthorizationImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see OAuth2AuthorizationImpl
 * @generated
 */
public class OAuth2AuthorizationModelImpl
	extends BaseModelImpl<OAuth2Authorization>
	implements OAuth2AuthorizationModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a o auth2 authorization model instance should use the <code>OAuth2Authorization</code> interface instead.
	 */
	public static final String TABLE_NAME = "OAuth2Authorization";

	public static final Object[][] TABLE_COLUMNS = {
		{"oAuth2AuthorizationId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"userId", Types.BIGINT}, {"userName", Types.VARCHAR},
		{"createDate", Types.TIMESTAMP}, {"oAuth2ApplicationId", Types.BIGINT},
		{"oA2AScopeAliasesId", Types.BIGINT},
		{"accessTokenContent", Types.CLOB},
		{"accessTokenContentHash", Types.BIGINT},
		{"accessTokenCreateDate", Types.TIMESTAMP},
		{"accessTokenExpirationDate", Types.TIMESTAMP},
		{"remoteHostInfo", Types.VARCHAR}, {"remoteIPInfo", Types.VARCHAR},
		{"refreshTokenContent", Types.CLOB},
		{"refreshTokenContentHash", Types.BIGINT},
		{"refreshTokenCreateDate", Types.TIMESTAMP},
		{"refreshTokenExpirationDate", Types.TIMESTAMP}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("oAuth2AuthorizationId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("oAuth2ApplicationId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("oA2AScopeAliasesId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("accessTokenContent", Types.CLOB);
		TABLE_COLUMNS_MAP.put("accessTokenContentHash", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("accessTokenCreateDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("accessTokenExpirationDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("remoteHostInfo", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("remoteIPInfo", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("refreshTokenContent", Types.CLOB);
		TABLE_COLUMNS_MAP.put("refreshTokenContentHash", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("refreshTokenCreateDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("refreshTokenExpirationDate", Types.TIMESTAMP);
	}

	public static final String TABLE_SQL_CREATE =
		"create table OAuth2Authorization (oAuth2AuthorizationId LONG not null primary key,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,oAuth2ApplicationId LONG,oA2AScopeAliasesId LONG,accessTokenContent TEXT null,accessTokenContentHash LONG,accessTokenCreateDate DATE null,accessTokenExpirationDate DATE null,remoteHostInfo VARCHAR(255) null,remoteIPInfo VARCHAR(75) null,refreshTokenContent TEXT null,refreshTokenContentHash LONG,refreshTokenCreateDate DATE null,refreshTokenExpirationDate DATE null)";

	public static final String TABLE_SQL_DROP =
		"drop table OAuth2Authorization";

	public static final String ORDER_BY_JPQL =
		" ORDER BY oAuth2Authorization.oAuth2AuthorizationId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY OAuth2Authorization.oAuth2AuthorizationId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)
	 */
	@Deprecated
	public static final long ACCESSTOKENCONTENTHASH_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)
	 */
	@Deprecated
	public static final long COMPANYID_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)
	 */
	@Deprecated
	public static final long OAUTH2APPLICATIONID_COLUMN_BITMASK = 4L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)
	 */
	@Deprecated
	public static final long REFRESHTOKENCONTENTHASH_COLUMN_BITMASK = 8L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)
	 */
	@Deprecated
	public static final long USERID_COLUMN_BITMASK = 16L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)
	 */
	@Deprecated
	public static final long OAUTH2AUTHORIZATIONID_COLUMN_BITMASK = 32L;

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
	public static OAuth2Authorization toModel(
		OAuth2AuthorizationSoap soapModel) {

		if (soapModel == null) {
			return null;
		}

		OAuth2Authorization model = new OAuth2AuthorizationImpl();

		model.setOAuth2AuthorizationId(soapModel.getOAuth2AuthorizationId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setOAuth2ApplicationId(soapModel.getOAuth2ApplicationId());
		model.setOAuth2ApplicationScopeAliasesId(
			soapModel.getOAuth2ApplicationScopeAliasesId());
		model.setAccessTokenContent(soapModel.getAccessTokenContent());
		model.setAccessTokenContentHash(soapModel.getAccessTokenContentHash());
		model.setAccessTokenCreateDate(soapModel.getAccessTokenCreateDate());
		model.setAccessTokenExpirationDate(
			soapModel.getAccessTokenExpirationDate());
		model.setRemoteHostInfo(soapModel.getRemoteHostInfo());
		model.setRemoteIPInfo(soapModel.getRemoteIPInfo());
		model.setRefreshTokenContent(soapModel.getRefreshTokenContent());
		model.setRefreshTokenContentHash(
			soapModel.getRefreshTokenContentHash());
		model.setRefreshTokenCreateDate(soapModel.getRefreshTokenCreateDate());
		model.setRefreshTokenExpirationDate(
			soapModel.getRefreshTokenExpirationDate());

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
	public static List<OAuth2Authorization> toModels(
		OAuth2AuthorizationSoap[] soapModels) {

		if (soapModels == null) {
			return null;
		}

		List<OAuth2Authorization> models = new ArrayList<OAuth2Authorization>(
			soapModels.length);

		for (OAuth2AuthorizationSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final String MAPPING_TABLE_OA2AUTHS_OA2SCOPEGRANTS_NAME =
		"OA2Auths_OA2ScopeGrants";

	public static final Object[][]
		MAPPING_TABLE_OA2AUTHS_OA2SCOPEGRANTS_COLUMNS = {
			{"companyId", Types.BIGINT},
			{"oAuth2AuthorizationId", Types.BIGINT},
			{"oAuth2ScopeGrantId", Types.BIGINT}
		};

	public static final String
		MAPPING_TABLE_OA2AUTHS_OA2SCOPEGRANTS_SQL_CREATE =
			"create table OA2Auths_OA2ScopeGrants (companyId LONG not null,oAuth2AuthorizationId LONG not null,oAuth2ScopeGrantId LONG not null,primary key (oAuth2AuthorizationId, oAuth2ScopeGrantId))";

	public OAuth2AuthorizationModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _oAuth2AuthorizationId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setOAuth2AuthorizationId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _oAuth2AuthorizationId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return OAuth2Authorization.class;
	}

	@Override
	public String getModelClassName() {
		return OAuth2Authorization.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<OAuth2Authorization, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<OAuth2Authorization, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<OAuth2Authorization, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((OAuth2Authorization)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<OAuth2Authorization, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<OAuth2Authorization, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(OAuth2Authorization)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<OAuth2Authorization, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<OAuth2Authorization, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, OAuth2Authorization>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			OAuth2Authorization.class.getClassLoader(),
			OAuth2Authorization.class, ModelWrapper.class);

		try {
			Constructor<OAuth2Authorization> constructor =
				(Constructor<OAuth2Authorization>)proxyClass.getConstructor(
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

	private static final Map<String, Function<OAuth2Authorization, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<OAuth2Authorization, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<OAuth2Authorization, Object>>
			attributeGetterFunctions =
				new LinkedHashMap
					<String, Function<OAuth2Authorization, Object>>();
		Map<String, BiConsumer<OAuth2Authorization, ?>>
			attributeSetterBiConsumers =
				new LinkedHashMap<String, BiConsumer<OAuth2Authorization, ?>>();

		attributeGetterFunctions.put(
			"oAuth2AuthorizationId",
			OAuth2Authorization::getOAuth2AuthorizationId);
		attributeSetterBiConsumers.put(
			"oAuth2AuthorizationId",
			(BiConsumer<OAuth2Authorization, Long>)
				OAuth2Authorization::setOAuth2AuthorizationId);
		attributeGetterFunctions.put(
			"companyId", OAuth2Authorization::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId",
			(BiConsumer<OAuth2Authorization, Long>)
				OAuth2Authorization::setCompanyId);
		attributeGetterFunctions.put("userId", OAuth2Authorization::getUserId);
		attributeSetterBiConsumers.put(
			"userId",
			(BiConsumer<OAuth2Authorization, Long>)
				OAuth2Authorization::setUserId);
		attributeGetterFunctions.put(
			"userName", OAuth2Authorization::getUserName);
		attributeSetterBiConsumers.put(
			"userName",
			(BiConsumer<OAuth2Authorization, String>)
				OAuth2Authorization::setUserName);
		attributeGetterFunctions.put(
			"createDate", OAuth2Authorization::getCreateDate);
		attributeSetterBiConsumers.put(
			"createDate",
			(BiConsumer<OAuth2Authorization, Date>)
				OAuth2Authorization::setCreateDate);
		attributeGetterFunctions.put(
			"oAuth2ApplicationId", OAuth2Authorization::getOAuth2ApplicationId);
		attributeSetterBiConsumers.put(
			"oAuth2ApplicationId",
			(BiConsumer<OAuth2Authorization, Long>)
				OAuth2Authorization::setOAuth2ApplicationId);
		attributeGetterFunctions.put(
			"oAuth2ApplicationScopeAliasesId",
			OAuth2Authorization::getOAuth2ApplicationScopeAliasesId);
		attributeSetterBiConsumers.put(
			"oAuth2ApplicationScopeAliasesId",
			(BiConsumer<OAuth2Authorization, Long>)
				OAuth2Authorization::setOAuth2ApplicationScopeAliasesId);
		attributeGetterFunctions.put(
			"accessTokenContent", OAuth2Authorization::getAccessTokenContent);
		attributeSetterBiConsumers.put(
			"accessTokenContent",
			(BiConsumer<OAuth2Authorization, String>)
				OAuth2Authorization::setAccessTokenContent);
		attributeGetterFunctions.put(
			"accessTokenContentHash",
			OAuth2Authorization::getAccessTokenContentHash);
		attributeSetterBiConsumers.put(
			"accessTokenContentHash",
			(BiConsumer<OAuth2Authorization, Long>)
				OAuth2Authorization::setAccessTokenContentHash);
		attributeGetterFunctions.put(
			"accessTokenCreateDate",
			OAuth2Authorization::getAccessTokenCreateDate);
		attributeSetterBiConsumers.put(
			"accessTokenCreateDate",
			(BiConsumer<OAuth2Authorization, Date>)
				OAuth2Authorization::setAccessTokenCreateDate);
		attributeGetterFunctions.put(
			"accessTokenExpirationDate",
			OAuth2Authorization::getAccessTokenExpirationDate);
		attributeSetterBiConsumers.put(
			"accessTokenExpirationDate",
			(BiConsumer<OAuth2Authorization, Date>)
				OAuth2Authorization::setAccessTokenExpirationDate);
		attributeGetterFunctions.put(
			"remoteHostInfo", OAuth2Authorization::getRemoteHostInfo);
		attributeSetterBiConsumers.put(
			"remoteHostInfo",
			(BiConsumer<OAuth2Authorization, String>)
				OAuth2Authorization::setRemoteHostInfo);
		attributeGetterFunctions.put(
			"remoteIPInfo", OAuth2Authorization::getRemoteIPInfo);
		attributeSetterBiConsumers.put(
			"remoteIPInfo",
			(BiConsumer<OAuth2Authorization, String>)
				OAuth2Authorization::setRemoteIPInfo);
		attributeGetterFunctions.put(
			"refreshTokenContent", OAuth2Authorization::getRefreshTokenContent);
		attributeSetterBiConsumers.put(
			"refreshTokenContent",
			(BiConsumer<OAuth2Authorization, String>)
				OAuth2Authorization::setRefreshTokenContent);
		attributeGetterFunctions.put(
			"refreshTokenContentHash",
			OAuth2Authorization::getRefreshTokenContentHash);
		attributeSetterBiConsumers.put(
			"refreshTokenContentHash",
			(BiConsumer<OAuth2Authorization, Long>)
				OAuth2Authorization::setRefreshTokenContentHash);
		attributeGetterFunctions.put(
			"refreshTokenCreateDate",
			OAuth2Authorization::getRefreshTokenCreateDate);
		attributeSetterBiConsumers.put(
			"refreshTokenCreateDate",
			(BiConsumer<OAuth2Authorization, Date>)
				OAuth2Authorization::setRefreshTokenCreateDate);
		attributeGetterFunctions.put(
			"refreshTokenExpirationDate",
			OAuth2Authorization::getRefreshTokenExpirationDate);
		attributeSetterBiConsumers.put(
			"refreshTokenExpirationDate",
			(BiConsumer<OAuth2Authorization, Date>)
				OAuth2Authorization::setRefreshTokenExpirationDate);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@Override
	public long getOAuth2AuthorizationId() {
		return _oAuth2AuthorizationId;
	}

	@Override
	public void setOAuth2AuthorizationId(long oAuth2AuthorizationId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_oAuth2AuthorizationId = oAuth2AuthorizationId;
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

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalUserId() {
		return GetterUtil.getLong(getColumnOriginalValue("userId"));
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
	public long getOAuth2ApplicationId() {
		return _oAuth2ApplicationId;
	}

	@Override
	public void setOAuth2ApplicationId(long oAuth2ApplicationId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_oAuth2ApplicationId = oAuth2ApplicationId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalOAuth2ApplicationId() {
		return GetterUtil.getLong(
			getColumnOriginalValue("oAuth2ApplicationId"));
	}

	@Override
	public long getOAuth2ApplicationScopeAliasesId() {
		return _oAuth2ApplicationScopeAliasesId;
	}

	@Override
	public void setOAuth2ApplicationScopeAliasesId(
		long oAuth2ApplicationScopeAliasesId) {

		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_oAuth2ApplicationScopeAliasesId = oAuth2ApplicationScopeAliasesId;
	}

	@Override
	public String getAccessTokenContent() {
		if (_accessTokenContent == null) {
			return "";
		}
		else {
			return _accessTokenContent;
		}
	}

	@Override
	public void setAccessTokenContent(String accessTokenContent) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_accessTokenContent = accessTokenContent;
	}

	@Override
	public long getAccessTokenContentHash() {
		return _accessTokenContentHash;
	}

	@Override
	public void setAccessTokenContentHash(long accessTokenContentHash) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_accessTokenContentHash = accessTokenContentHash;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalAccessTokenContentHash() {
		return GetterUtil.getLong(
			getColumnOriginalValue("accessTokenContentHash"));
	}

	@Override
	public Date getAccessTokenCreateDate() {
		return _accessTokenCreateDate;
	}

	@Override
	public void setAccessTokenCreateDate(Date accessTokenCreateDate) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_accessTokenCreateDate = accessTokenCreateDate;
	}

	@Override
	public Date getAccessTokenExpirationDate() {
		return _accessTokenExpirationDate;
	}

	@Override
	public void setAccessTokenExpirationDate(Date accessTokenExpirationDate) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_accessTokenExpirationDate = accessTokenExpirationDate;
	}

	@Override
	public String getRemoteHostInfo() {
		if (_remoteHostInfo == null) {
			return "";
		}
		else {
			return _remoteHostInfo;
		}
	}

	@Override
	public void setRemoteHostInfo(String remoteHostInfo) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_remoteHostInfo = remoteHostInfo;
	}

	@Override
	public String getRemoteIPInfo() {
		if (_remoteIPInfo == null) {
			return "";
		}
		else {
			return _remoteIPInfo;
		}
	}

	@Override
	public void setRemoteIPInfo(String remoteIPInfo) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_remoteIPInfo = remoteIPInfo;
	}

	@Override
	public String getRefreshTokenContent() {
		if (_refreshTokenContent == null) {
			return "";
		}
		else {
			return _refreshTokenContent;
		}
	}

	@Override
	public void setRefreshTokenContent(String refreshTokenContent) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_refreshTokenContent = refreshTokenContent;
	}

	@Override
	public long getRefreshTokenContentHash() {
		return _refreshTokenContentHash;
	}

	@Override
	public void setRefreshTokenContentHash(long refreshTokenContentHash) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_refreshTokenContentHash = refreshTokenContentHash;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalRefreshTokenContentHash() {
		return GetterUtil.getLong(
			getColumnOriginalValue("refreshTokenContentHash"));
	}

	@Override
	public Date getRefreshTokenCreateDate() {
		return _refreshTokenCreateDate;
	}

	@Override
	public void setRefreshTokenCreateDate(Date refreshTokenCreateDate) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_refreshTokenCreateDate = refreshTokenCreateDate;
	}

	@Override
	public Date getRefreshTokenExpirationDate() {
		return _refreshTokenExpirationDate;
	}

	@Override
	public void setRefreshTokenExpirationDate(Date refreshTokenExpirationDate) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_refreshTokenExpirationDate = refreshTokenExpirationDate;
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
			getCompanyId(), OAuth2Authorization.class.getName(),
			getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public OAuth2Authorization toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, OAuth2Authorization>
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
		OAuth2AuthorizationImpl oAuth2AuthorizationImpl =
			new OAuth2AuthorizationImpl();

		oAuth2AuthorizationImpl.setOAuth2AuthorizationId(
			getOAuth2AuthorizationId());
		oAuth2AuthorizationImpl.setCompanyId(getCompanyId());
		oAuth2AuthorizationImpl.setUserId(getUserId());
		oAuth2AuthorizationImpl.setUserName(getUserName());
		oAuth2AuthorizationImpl.setCreateDate(getCreateDate());
		oAuth2AuthorizationImpl.setOAuth2ApplicationId(
			getOAuth2ApplicationId());
		oAuth2AuthorizationImpl.setOAuth2ApplicationScopeAliasesId(
			getOAuth2ApplicationScopeAliasesId());
		oAuth2AuthorizationImpl.setAccessTokenContent(getAccessTokenContent());
		oAuth2AuthorizationImpl.setAccessTokenContentHash(
			getAccessTokenContentHash());
		oAuth2AuthorizationImpl.setAccessTokenCreateDate(
			getAccessTokenCreateDate());
		oAuth2AuthorizationImpl.setAccessTokenExpirationDate(
			getAccessTokenExpirationDate());
		oAuth2AuthorizationImpl.setRemoteHostInfo(getRemoteHostInfo());
		oAuth2AuthorizationImpl.setRemoteIPInfo(getRemoteIPInfo());
		oAuth2AuthorizationImpl.setRefreshTokenContent(
			getRefreshTokenContent());
		oAuth2AuthorizationImpl.setRefreshTokenContentHash(
			getRefreshTokenContentHash());
		oAuth2AuthorizationImpl.setRefreshTokenCreateDate(
			getRefreshTokenCreateDate());
		oAuth2AuthorizationImpl.setRefreshTokenExpirationDate(
			getRefreshTokenExpirationDate());

		oAuth2AuthorizationImpl.resetOriginalValues();

		return oAuth2AuthorizationImpl;
	}

	@Override
	public int compareTo(OAuth2Authorization oAuth2Authorization) {
		long primaryKey = oAuth2Authorization.getPrimaryKey();

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

		if (!(object instanceof OAuth2Authorization)) {
			return false;
		}

		OAuth2Authorization oAuth2Authorization = (OAuth2Authorization)object;

		long primaryKey = oAuth2Authorization.getPrimaryKey();

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
	public CacheModel<OAuth2Authorization> toCacheModel() {
		OAuth2AuthorizationCacheModel oAuth2AuthorizationCacheModel =
			new OAuth2AuthorizationCacheModel();

		oAuth2AuthorizationCacheModel.oAuth2AuthorizationId =
			getOAuth2AuthorizationId();

		oAuth2AuthorizationCacheModel.companyId = getCompanyId();

		oAuth2AuthorizationCacheModel.userId = getUserId();

		oAuth2AuthorizationCacheModel.userName = getUserName();

		String userName = oAuth2AuthorizationCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			oAuth2AuthorizationCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			oAuth2AuthorizationCacheModel.createDate = createDate.getTime();
		}
		else {
			oAuth2AuthorizationCacheModel.createDate = Long.MIN_VALUE;
		}

		oAuth2AuthorizationCacheModel.oAuth2ApplicationId =
			getOAuth2ApplicationId();

		oAuth2AuthorizationCacheModel.oAuth2ApplicationScopeAliasesId =
			getOAuth2ApplicationScopeAliasesId();

		oAuth2AuthorizationCacheModel.accessTokenContent =
			getAccessTokenContent();

		String accessTokenContent =
			oAuth2AuthorizationCacheModel.accessTokenContent;

		if ((accessTokenContent != null) &&
			(accessTokenContent.length() == 0)) {

			oAuth2AuthorizationCacheModel.accessTokenContent = null;
		}

		oAuth2AuthorizationCacheModel.accessTokenContentHash =
			getAccessTokenContentHash();

		Date accessTokenCreateDate = getAccessTokenCreateDate();

		if (accessTokenCreateDate != null) {
			oAuth2AuthorizationCacheModel.accessTokenCreateDate =
				accessTokenCreateDate.getTime();
		}
		else {
			oAuth2AuthorizationCacheModel.accessTokenCreateDate =
				Long.MIN_VALUE;
		}

		Date accessTokenExpirationDate = getAccessTokenExpirationDate();

		if (accessTokenExpirationDate != null) {
			oAuth2AuthorizationCacheModel.accessTokenExpirationDate =
				accessTokenExpirationDate.getTime();
		}
		else {
			oAuth2AuthorizationCacheModel.accessTokenExpirationDate =
				Long.MIN_VALUE;
		}

		oAuth2AuthorizationCacheModel.remoteHostInfo = getRemoteHostInfo();

		String remoteHostInfo = oAuth2AuthorizationCacheModel.remoteHostInfo;

		if ((remoteHostInfo != null) && (remoteHostInfo.length() == 0)) {
			oAuth2AuthorizationCacheModel.remoteHostInfo = null;
		}

		oAuth2AuthorizationCacheModel.remoteIPInfo = getRemoteIPInfo();

		String remoteIPInfo = oAuth2AuthorizationCacheModel.remoteIPInfo;

		if ((remoteIPInfo != null) && (remoteIPInfo.length() == 0)) {
			oAuth2AuthorizationCacheModel.remoteIPInfo = null;
		}

		oAuth2AuthorizationCacheModel.refreshTokenContent =
			getRefreshTokenContent();

		String refreshTokenContent =
			oAuth2AuthorizationCacheModel.refreshTokenContent;

		if ((refreshTokenContent != null) &&
			(refreshTokenContent.length() == 0)) {

			oAuth2AuthorizationCacheModel.refreshTokenContent = null;
		}

		oAuth2AuthorizationCacheModel.refreshTokenContentHash =
			getRefreshTokenContentHash();

		Date refreshTokenCreateDate = getRefreshTokenCreateDate();

		if (refreshTokenCreateDate != null) {
			oAuth2AuthorizationCacheModel.refreshTokenCreateDate =
				refreshTokenCreateDate.getTime();
		}
		else {
			oAuth2AuthorizationCacheModel.refreshTokenCreateDate =
				Long.MIN_VALUE;
		}

		Date refreshTokenExpirationDate = getRefreshTokenExpirationDate();

		if (refreshTokenExpirationDate != null) {
			oAuth2AuthorizationCacheModel.refreshTokenExpirationDate =
				refreshTokenExpirationDate.getTime();
		}
		else {
			oAuth2AuthorizationCacheModel.refreshTokenExpirationDate =
				Long.MIN_VALUE;
		}

		return oAuth2AuthorizationCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<OAuth2Authorization, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<OAuth2Authorization, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<OAuth2Authorization, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((OAuth2Authorization)this));
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
		Map<String, Function<OAuth2Authorization, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<OAuth2Authorization, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<OAuth2Authorization, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((OAuth2Authorization)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, OAuth2Authorization>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private long _oAuth2AuthorizationId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private long _oAuth2ApplicationId;
	private long _oAuth2ApplicationScopeAliasesId;
	private String _accessTokenContent;
	private long _accessTokenContentHash;
	private Date _accessTokenCreateDate;
	private Date _accessTokenExpirationDate;
	private String _remoteHostInfo;
	private String _remoteIPInfo;
	private String _refreshTokenContent;
	private long _refreshTokenContentHash;
	private Date _refreshTokenCreateDate;
	private Date _refreshTokenExpirationDate;

	public static long getColumnBitmask(String columnName) {
		return _columnBitmasks.get(columnName);
	}

	public <T> T getColumnValue(String columnName) {
		if (_attributeNames.containsKey(columnName)) {
			columnName = _attributeNames.get(columnName);
		}

		Function<OAuth2Authorization, Object> function =
			_attributeGetterFunctions.get(columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((OAuth2Authorization)this);
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

		_columnOriginalValues.put(
			"oAuth2AuthorizationId", _oAuth2AuthorizationId);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("userId", _userId);
		_columnOriginalValues.put("userName", _userName);
		_columnOriginalValues.put("createDate", _createDate);
		_columnOriginalValues.put("oAuth2ApplicationId", _oAuth2ApplicationId);
		_columnOriginalValues.put(
			"oA2AScopeAliasesId", _oAuth2ApplicationScopeAliasesId);
		_columnOriginalValues.put("accessTokenContent", _accessTokenContent);
		_columnOriginalValues.put(
			"accessTokenContentHash", _accessTokenContentHash);
		_columnOriginalValues.put(
			"accessTokenCreateDate", _accessTokenCreateDate);
		_columnOriginalValues.put(
			"accessTokenExpirationDate", _accessTokenExpirationDate);
		_columnOriginalValues.put("remoteHostInfo", _remoteHostInfo);
		_columnOriginalValues.put("remoteIPInfo", _remoteIPInfo);
		_columnOriginalValues.put("refreshTokenContent", _refreshTokenContent);
		_columnOriginalValues.put(
			"refreshTokenContentHash", _refreshTokenContentHash);
		_columnOriginalValues.put(
			"refreshTokenCreateDate", _refreshTokenCreateDate);
		_columnOriginalValues.put(
			"refreshTokenExpirationDate", _refreshTokenExpirationDate);
	}

	private static final Map<String, Long> _columnBitmasks;
	private static final Map<String, String> _attributeNames;

	static {
		Map<String, Long> columnBitmasks = new LinkedHashMap<>();

		columnBitmasks.put("oAuth2AuthorizationId", 1L);

		columnBitmasks.put("companyId", 2L);

		columnBitmasks.put("userId", 4L);

		columnBitmasks.put("userName", 8L);

		columnBitmasks.put("createDate", 16L);

		columnBitmasks.put("oAuth2ApplicationId", 32L);

		columnBitmasks.put("oA2AScopeAliasesId", 64L);

		columnBitmasks.put("accessTokenContent", 128L);

		columnBitmasks.put("accessTokenContentHash", 256L);

		columnBitmasks.put("accessTokenCreateDate", 512L);

		columnBitmasks.put("accessTokenExpirationDate", 1024L);

		columnBitmasks.put("remoteHostInfo", 2048L);

		columnBitmasks.put("remoteIPInfo", 4096L);

		columnBitmasks.put("refreshTokenContent", 8192L);

		columnBitmasks.put("refreshTokenContentHash", 16384L);

		columnBitmasks.put("refreshTokenCreateDate", 32768L);

		columnBitmasks.put("refreshTokenExpirationDate", 65536L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);

		Map<String, String> attributeNames = new LinkedHashMap<>();

		attributeNames.put(
			"oA2AScopeAliasesId", "oAuth2ApplicationScopeAliasesId");

		_attributeNames = Collections.unmodifiableMap(attributeNames);
	}

	private transient Map<String, Object> _columnOriginalValues;
	private long _columnBitmask;
	private OAuth2Authorization _escapedModel;

}