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

package com.liferay.portal.tools.service.builder.test.model.impl;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.tools.service.builder.test.model.LVEntry;
import com.liferay.portal.tools.service.builder.test.model.LVEntryLocalization;
import com.liferay.portal.tools.service.builder.test.model.LVEntryModel;
import com.liferay.portal.tools.service.builder.test.model.LVEntryVersion;
import com.liferay.portal.tools.service.builder.test.service.LVEntryLocalServiceUtil;

import java.io.Serializable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;

import java.sql.Types;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * The base model implementation for the LVEntry service. Represents a row in the &quot;LVEntry&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>LVEntryModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link LVEntryImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LVEntryImpl
 * @generated
 */
public class LVEntryModelImpl
	extends BaseModelImpl<LVEntry> implements LVEntryModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a lv entry model instance should use the <code>LVEntry</code> interface instead.
	 */
	public static final String TABLE_NAME = "LVEntry";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"uuid_", Types.VARCHAR},
		{"headId", Types.BIGINT}, {"head", Types.BOOLEAN},
		{"defaultLanguageId", Types.VARCHAR}, {"lvEntryId", Types.BIGINT},
		{"companyId", Types.BIGINT}, {"groupId", Types.BIGINT},
		{"uniqueGroupKey", Types.VARCHAR}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("headId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("head", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("defaultLanguageId", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("lvEntryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("uniqueGroupKey", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE =
		"create table LVEntry (mvccVersion LONG default 0 not null,uuid_ VARCHAR(75) null,headId LONG,head BOOLEAN,defaultLanguageId VARCHAR(75) null,lvEntryId LONG not null primary key,companyId LONG,groupId LONG,uniqueGroupKey VARCHAR(75) null)";

	public static final String TABLE_SQL_DROP = "drop table LVEntry";

	public static final String ORDER_BY_JPQL =
		" ORDER BY lvEntry.lvEntryId ASC";

	public static final String ORDER_BY_SQL = " ORDER BY LVEntry.lvEntryId ASC";

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
	public static final long UNIQUEGROUPKEY_COLUMN_BITMASK = 16L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)
	 */
	@Deprecated
	public static final long UUID_COLUMN_BITMASK = 32L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)
	 */
	@Deprecated
	public static final long LVENTRYID_COLUMN_BITMASK = 64L;

	public static final String MAPPING_TABLE_BIGDECIMALENTRIES_LVENTRIES_NAME =
		"BigDecimalEntries_LVEntries";

	public static final Object[][]
		MAPPING_TABLE_BIGDECIMALENTRIES_LVENTRIES_COLUMNS = {
			{"companyId", Types.BIGINT}, {"bigDecimalEntryId", Types.BIGINT},
			{"lvEntryId", Types.BIGINT}
		};

	public static final String
		MAPPING_TABLE_BIGDECIMALENTRIES_LVENTRIES_SQL_CREATE =
			"create table BigDecimalEntries_LVEntries (companyId LONG not null,bigDecimalEntryId LONG not null,lvEntryId LONG not null,primary key (bigDecimalEntryId, lvEntryId))";

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static final boolean
		FINDER_CACHE_ENABLED_BIGDECIMALENTRIES_LVENTRIES = true;

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.portal.tools.service.builder.test.service.util.ServiceProps.
			get(
				"lock.expiration.time.com.liferay.portal.tools.service.builder.test.model.LVEntry"));

	public LVEntryModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _lvEntryId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setLvEntryId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _lvEntryId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return LVEntry.class;
	}

	@Override
	public String getModelClassName() {
		return LVEntry.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<LVEntry, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<LVEntry, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<LVEntry, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName, attributeGetterFunction.apply((LVEntry)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<LVEntry, Object>> attributeSetterBiConsumers =
			getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<LVEntry, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(LVEntry)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<LVEntry, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<LVEntry, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, LVEntry>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			LVEntry.class.getClassLoader(), LVEntry.class, ModelWrapper.class);

		try {
			Constructor<LVEntry> constructor =
				(Constructor<LVEntry>)proxyClass.getConstructor(
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

	private static final Map<String, Function<LVEntry, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<LVEntry, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<LVEntry, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<LVEntry, Object>>();
		Map<String, BiConsumer<LVEntry, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<LVEntry, ?>>();

		attributeGetterFunctions.put("mvccVersion", LVEntry::getMvccVersion);
		attributeSetterBiConsumers.put(
			"mvccVersion", (BiConsumer<LVEntry, Long>)LVEntry::setMvccVersion);
		attributeGetterFunctions.put("uuid", LVEntry::getUuid);
		attributeSetterBiConsumers.put(
			"uuid", (BiConsumer<LVEntry, String>)LVEntry::setUuid);
		attributeGetterFunctions.put("headId", LVEntry::getHeadId);
		attributeSetterBiConsumers.put(
			"headId", (BiConsumer<LVEntry, Long>)LVEntry::setHeadId);
		attributeGetterFunctions.put(
			"defaultLanguageId", LVEntry::getDefaultLanguageId);
		attributeSetterBiConsumers.put(
			"defaultLanguageId",
			(BiConsumer<LVEntry, String>)LVEntry::setDefaultLanguageId);
		attributeGetterFunctions.put("lvEntryId", LVEntry::getLvEntryId);
		attributeSetterBiConsumers.put(
			"lvEntryId", (BiConsumer<LVEntry, Long>)LVEntry::setLvEntryId);
		attributeGetterFunctions.put("companyId", LVEntry::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId", (BiConsumer<LVEntry, Long>)LVEntry::setCompanyId);
		attributeGetterFunctions.put("groupId", LVEntry::getGroupId);
		attributeSetterBiConsumers.put(
			"groupId", (BiConsumer<LVEntry, Long>)LVEntry::setGroupId);
		attributeGetterFunctions.put(
			"uniqueGroupKey", LVEntry::getUniqueGroupKey);
		attributeSetterBiConsumers.put(
			"uniqueGroupKey",
			(BiConsumer<LVEntry, String>)LVEntry::setUniqueGroupKey);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@Override
	public String[] getAvailableLanguageIds() {
		List<LVEntryLocalization> lvEntryLocalizations =
			LVEntryLocalServiceUtil.getLVEntryLocalizations(getPrimaryKey());

		String[] availableLanguageIds = new String[lvEntryLocalizations.size()];

		for (int i = 0; i < availableLanguageIds.length; i++) {
			LVEntryLocalization lvEntryLocalization = lvEntryLocalizations.get(
				i);

			availableLanguageIds[i] = lvEntryLocalization.getLanguageId();
		}

		return availableLanguageIds;
	}

	@Override
	public String getTitle() {
		return getTitle(getDefaultLanguageId(), false);
	}

	@Override
	public String getTitle(String languageId) {
		return getTitle(languageId, true);
	}

	@Override
	public String getTitle(String languageId, boolean useDefault) {
		if (useDefault) {
			return LocalizationUtil.getLocalization(
				new Function<String, String>() {

					@Override
					public String apply(String languageId) {
						return _getTitle(languageId);
					}

				},
				languageId, getDefaultLanguageId());
		}

		return _getTitle(languageId);
	}

	@Override
	public String getTitleMapAsXML() {
		return LocalizationUtil.getXml(
			getLanguageIdToTitleMap(), getDefaultLanguageId(), "Title");
	}

	@Override
	public Map<String, String> getLanguageIdToTitleMap() {
		Map<String, String> languageIdToTitleMap =
			new HashMap<String, String>();

		List<LVEntryLocalization> lvEntryLocalizations =
			LVEntryLocalServiceUtil.getLVEntryLocalizations(getPrimaryKey());

		for (LVEntryLocalization lvEntryLocalization : lvEntryLocalizations) {
			languageIdToTitleMap.put(
				lvEntryLocalization.getLanguageId(),
				lvEntryLocalization.getTitle());
		}

		return languageIdToTitleMap;
	}

	private String _getTitle(String languageId) {
		LVEntryLocalization lvEntryLocalization =
			LVEntryLocalServiceUtil.fetchLVEntryLocalization(
				getPrimaryKey(), languageId);

		if (lvEntryLocalization == null) {
			return "";
		}

		return lvEntryLocalization.getTitle();
	}

	@Override
	public String getContent() {
		return getContent(getDefaultLanguageId(), false);
	}

	@Override
	public String getContent(String languageId) {
		return getContent(languageId, true);
	}

	@Override
	public String getContent(String languageId, boolean useDefault) {
		if (useDefault) {
			return LocalizationUtil.getLocalization(
				new Function<String, String>() {

					@Override
					public String apply(String languageId) {
						return _getContent(languageId);
					}

				},
				languageId, getDefaultLanguageId());
		}

		return _getContent(languageId);
	}

	@Override
	public String getContentMapAsXML() {
		return LocalizationUtil.getXml(
			getLanguageIdToContentMap(), getDefaultLanguageId(), "Content");
	}

	@Override
	public Map<String, String> getLanguageIdToContentMap() {
		Map<String, String> languageIdToContentMap =
			new HashMap<String, String>();

		List<LVEntryLocalization> lvEntryLocalizations =
			LVEntryLocalServiceUtil.getLVEntryLocalizations(getPrimaryKey());

		for (LVEntryLocalization lvEntryLocalization : lvEntryLocalizations) {
			languageIdToContentMap.put(
				lvEntryLocalization.getLanguageId(),
				lvEntryLocalization.getContent());
		}

		return languageIdToContentMap;
	}

	private String _getContent(String languageId) {
		LVEntryLocalization lvEntryLocalization =
			LVEntryLocalServiceUtil.fetchLVEntryLocalization(
				getPrimaryKey(), languageId);

		if (lvEntryLocalization == null) {
			return "";
		}

		return lvEntryLocalization.getContent();
	}

	@Override
	public void populateVersionModel(LVEntryVersion lvEntryVersion) {
		lvEntryVersion.setUuid(getUuid());
		lvEntryVersion.setDefaultLanguageId(getDefaultLanguageId());
		lvEntryVersion.setCompanyId(getCompanyId());
		lvEntryVersion.setGroupId(getGroupId());
		lvEntryVersion.setUniqueGroupKey(getUniqueGroupKey());
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

	@Override
	public String getDefaultLanguageId() {
		if (_defaultLanguageId == null) {
			return "";
		}
		else {
			return _defaultLanguageId;
		}
	}

	@Override
	public void setDefaultLanguageId(String defaultLanguageId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_defaultLanguageId = defaultLanguageId;
	}

	@Override
	public long getLvEntryId() {
		return _lvEntryId;
	}

	@Override
	public void setLvEntryId(long lvEntryId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_lvEntryId = lvEntryId;
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
	public String getUniqueGroupKey() {
		if (_uniqueGroupKey == null) {
			return "";
		}
		else {
			return _uniqueGroupKey;
		}
	}

	@Override
	public void setUniqueGroupKey(String uniqueGroupKey) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_uniqueGroupKey = uniqueGroupKey;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public String getOriginalUniqueGroupKey() {
		return getColumnOriginalValue("uniqueGroupKey");
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
			getCompanyId(), LVEntry.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public LVEntry toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, LVEntry>
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
		LVEntryImpl lvEntryImpl = new LVEntryImpl();

		lvEntryImpl.setMvccVersion(getMvccVersion());
		lvEntryImpl.setUuid(getUuid());
		lvEntryImpl.setHeadId(getHeadId());
		lvEntryImpl.setDefaultLanguageId(getDefaultLanguageId());
		lvEntryImpl.setLvEntryId(getLvEntryId());
		lvEntryImpl.setCompanyId(getCompanyId());
		lvEntryImpl.setGroupId(getGroupId());
		lvEntryImpl.setUniqueGroupKey(getUniqueGroupKey());

		lvEntryImpl.resetOriginalValues();

		return lvEntryImpl;
	}

	@Override
	public int compareTo(LVEntry lvEntry) {
		long primaryKey = lvEntry.getPrimaryKey();

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

		if (!(object instanceof LVEntry)) {
			return false;
		}

		LVEntry lvEntry = (LVEntry)object;

		long primaryKey = lvEntry.getPrimaryKey();

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
	public CacheModel<LVEntry> toCacheModel() {
		LVEntryCacheModel lvEntryCacheModel = new LVEntryCacheModel();

		lvEntryCacheModel.mvccVersion = getMvccVersion();

		lvEntryCacheModel.uuid = getUuid();

		String uuid = lvEntryCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			lvEntryCacheModel.uuid = null;
		}

		lvEntryCacheModel.headId = getHeadId();

		lvEntryCacheModel.head = isHead();

		lvEntryCacheModel.defaultLanguageId = getDefaultLanguageId();

		String defaultLanguageId = lvEntryCacheModel.defaultLanguageId;

		if ((defaultLanguageId != null) && (defaultLanguageId.length() == 0)) {
			lvEntryCacheModel.defaultLanguageId = null;
		}

		lvEntryCacheModel.lvEntryId = getLvEntryId();

		lvEntryCacheModel.companyId = getCompanyId();

		lvEntryCacheModel.groupId = getGroupId();

		lvEntryCacheModel.uniqueGroupKey = getUniqueGroupKey();

		String uniqueGroupKey = lvEntryCacheModel.uniqueGroupKey;

		if ((uniqueGroupKey != null) && (uniqueGroupKey.length() == 0)) {
			lvEntryCacheModel.uniqueGroupKey = null;
		}

		return lvEntryCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<LVEntry, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<LVEntry, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<LVEntry, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((LVEntry)this));
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
		Map<String, Function<LVEntry, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<LVEntry, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<LVEntry, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((LVEntry)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, LVEntry>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private long _mvccVersion;
	private String _uuid;
	private long _headId;
	private boolean _head;
	private String _defaultLanguageId;
	private long _lvEntryId;
	private long _companyId;
	private long _groupId;
	private String _uniqueGroupKey;

	public static long getColumnBitmask(String columnName) {
		return _columnBitmasks.get(columnName);
	}

	public <T> T getColumnValue(String columnName) {
		if (columnName.equals("head")) {
			return (T)(Object)getHead();
		}

		if (_attributeNames.containsKey(columnName)) {
			columnName = _attributeNames.get(columnName);
		}

		Function<LVEntry, Object> function = _attributeGetterFunctions.get(
			columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((LVEntry)this);
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
		_columnOriginalValues.put("headId", _headId);
		_columnOriginalValues.put("head", _head);
		_columnOriginalValues.put("defaultLanguageId", _defaultLanguageId);
		_columnOriginalValues.put("lvEntryId", _lvEntryId);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("groupId", _groupId);
		_columnOriginalValues.put("uniqueGroupKey", _uniqueGroupKey);
	}

	private static final Map<String, Long> _columnBitmasks;
	private static final Map<String, String> _attributeNames;

	static {
		Map<String, Long> columnBitmasks = new LinkedHashMap<>();

		columnBitmasks.put("mvccVersion", 1L);

		columnBitmasks.put("uuid_", 2L);

		columnBitmasks.put("headId", 4L);

		columnBitmasks.put("head", 8L);

		columnBitmasks.put("defaultLanguageId", 16L);

		columnBitmasks.put("lvEntryId", 32L);

		columnBitmasks.put("companyId", 64L);

		columnBitmasks.put("groupId", 128L);

		columnBitmasks.put("uniqueGroupKey", 256L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);

		Map<String, String> attributeNames = new LinkedHashMap<>();

		attributeNames.put("uuid_", "uuid");

		_attributeNames = Collections.unmodifiableMap(attributeNames);
	}

	private transient Map<String, Object> _columnOriginalValues;
	private long _columnBitmask;
	private LVEntry _escapedModel;

}