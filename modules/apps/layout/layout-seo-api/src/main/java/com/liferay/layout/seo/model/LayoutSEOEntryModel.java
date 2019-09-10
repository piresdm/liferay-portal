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

package com.liferay.layout.seo.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.exception.LocaleException;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.LocalizedModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.model.StagedGroupedModel;

import java.util.Date;
import java.util.Locale;
import java.util.Map;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the LayoutSEOEntry service. Represents a row in the &quot;LayoutSEOEntry&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.layout.seo.model.impl.LayoutSEOEntryModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.layout.seo.model.impl.LayoutSEOEntryImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LayoutSEOEntry
 * @generated
 */
@ProviderType
public interface LayoutSEOEntryModel
	extends BaseModel<LayoutSEOEntry>, LocalizedModel, ShardedModel,
			StagedGroupedModel {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a layout seo entry model instance should use the {@link LayoutSEOEntry} interface instead.
	 */

	/**
	 * Returns the primary key of this layout seo entry.
	 *
	 * @return the primary key of this layout seo entry
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this layout seo entry.
	 *
	 * @param primaryKey the primary key of this layout seo entry
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the uuid of this layout seo entry.
	 *
	 * @return the uuid of this layout seo entry
	 */
	@AutoEscape
	@Override
	public String getUuid();

	/**
	 * Sets the uuid of this layout seo entry.
	 *
	 * @param uuid the uuid of this layout seo entry
	 */
	@Override
	public void setUuid(String uuid);

	/**
	 * Returns the layout seo entry ID of this layout seo entry.
	 *
	 * @return the layout seo entry ID of this layout seo entry
	 */
	public long getLayoutSEOEntryId();

	/**
	 * Sets the layout seo entry ID of this layout seo entry.
	 *
	 * @param layoutSEOEntryId the layout seo entry ID of this layout seo entry
	 */
	public void setLayoutSEOEntryId(long layoutSEOEntryId);

	/**
	 * Returns the group ID of this layout seo entry.
	 *
	 * @return the group ID of this layout seo entry
	 */
	@Override
	public long getGroupId();

	/**
	 * Sets the group ID of this layout seo entry.
	 *
	 * @param groupId the group ID of this layout seo entry
	 */
	@Override
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this layout seo entry.
	 *
	 * @return the company ID of this layout seo entry
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this layout seo entry.
	 *
	 * @param companyId the company ID of this layout seo entry
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this layout seo entry.
	 *
	 * @return the user ID of this layout seo entry
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this layout seo entry.
	 *
	 * @param userId the user ID of this layout seo entry
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this layout seo entry.
	 *
	 * @return the user uuid of this layout seo entry
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this layout seo entry.
	 *
	 * @param userUuid the user uuid of this layout seo entry
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this layout seo entry.
	 *
	 * @return the user name of this layout seo entry
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this layout seo entry.
	 *
	 * @param userName the user name of this layout seo entry
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this layout seo entry.
	 *
	 * @return the create date of this layout seo entry
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this layout seo entry.
	 *
	 * @param createDate the create date of this layout seo entry
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this layout seo entry.
	 *
	 * @return the modified date of this layout seo entry
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this layout seo entry.
	 *
	 * @param modifiedDate the modified date of this layout seo entry
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the private layout of this layout seo entry.
	 *
	 * @return the private layout of this layout seo entry
	 */
	public boolean getPrivateLayout();

	/**
	 * Returns <code>true</code> if this layout seo entry is private layout.
	 *
	 * @return <code>true</code> if this layout seo entry is private layout; <code>false</code> otherwise
	 */
	public boolean isPrivateLayout();

	/**
	 * Sets whether this layout seo entry is private layout.
	 *
	 * @param privateLayout the private layout of this layout seo entry
	 */
	public void setPrivateLayout(boolean privateLayout);

	/**
	 * Returns the layout ID of this layout seo entry.
	 *
	 * @return the layout ID of this layout seo entry
	 */
	public long getLayoutId();

	/**
	 * Sets the layout ID of this layout seo entry.
	 *
	 * @param layoutId the layout ID of this layout seo entry
	 */
	public void setLayoutId(long layoutId);

	/**
	 * Returns the enabled of this layout seo entry.
	 *
	 * @return the enabled of this layout seo entry
	 */
	public boolean getEnabled();

	/**
	 * Returns <code>true</code> if this layout seo entry is enabled.
	 *
	 * @return <code>true</code> if this layout seo entry is enabled; <code>false</code> otherwise
	 */
	public boolean isEnabled();

	/**
	 * Sets whether this layout seo entry is enabled.
	 *
	 * @param enabled the enabled of this layout seo entry
	 */
	public void setEnabled(boolean enabled);

	/**
	 * Returns the canonical url of this layout seo entry.
	 *
	 * @return the canonical url of this layout seo entry
	 */
	public String getCanonicalURL();

	/**
	 * Returns the localized canonical url of this layout seo entry in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized canonical url of this layout seo entry
	 */
	@AutoEscape
	public String getCanonicalURL(Locale locale);

	/**
	 * Returns the localized canonical url of this layout seo entry in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized canonical url of this layout seo entry. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@AutoEscape
	public String getCanonicalURL(Locale locale, boolean useDefault);

	/**
	 * Returns the localized canonical url of this layout seo entry in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized canonical url of this layout seo entry
	 */
	@AutoEscape
	public String getCanonicalURL(String languageId);

	/**
	 * Returns the localized canonical url of this layout seo entry in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized canonical url of this layout seo entry
	 */
	@AutoEscape
	public String getCanonicalURL(String languageId, boolean useDefault);

	@AutoEscape
	public String getCanonicalURLCurrentLanguageId();

	@AutoEscape
	public String getCanonicalURLCurrentValue();

	/**
	 * Returns a map of the locales and localized canonical urls of this layout seo entry.
	 *
	 * @return the locales and localized canonical urls of this layout seo entry
	 */
	public Map<Locale, String> getCanonicalURLMap();

	/**
	 * Sets the canonical url of this layout seo entry.
	 *
	 * @param canonicalURL the canonical url of this layout seo entry
	 */
	public void setCanonicalURL(String canonicalURL);

	/**
	 * Sets the localized canonical url of this layout seo entry in the language.
	 *
	 * @param canonicalURL the localized canonical url of this layout seo entry
	 * @param locale the locale of the language
	 */
	public void setCanonicalURL(String canonicalURL, Locale locale);

	/**
	 * Sets the localized canonical url of this layout seo entry in the language, and sets the default locale.
	 *
	 * @param canonicalURL the localized canonical url of this layout seo entry
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	public void setCanonicalURL(
		String canonicalURL, Locale locale, Locale defaultLocale);

	public void setCanonicalURLCurrentLanguageId(String languageId);

	/**
	 * Sets the localized canonical urls of this layout seo entry from the map of locales and localized canonical urls.
	 *
	 * @param canonicalURLMap the locales and localized canonical urls of this layout seo entry
	 */
	public void setCanonicalURLMap(Map<Locale, String> canonicalURLMap);

	/**
	 * Sets the localized canonical urls of this layout seo entry from the map of locales and localized canonical urls, and sets the default locale.
	 *
	 * @param canonicalURLMap the locales and localized canonical urls of this layout seo entry
	 * @param defaultLocale the default locale
	 */
	public void setCanonicalURLMap(
		Map<Locale, String> canonicalURLMap, Locale defaultLocale);

	/**
	 * Returns the last publish date of this layout seo entry.
	 *
	 * @return the last publish date of this layout seo entry
	 */
	@Override
	public Date getLastPublishDate();

	/**
	 * Sets the last publish date of this layout seo entry.
	 *
	 * @param lastPublishDate the last publish date of this layout seo entry
	 */
	@Override
	public void setLastPublishDate(Date lastPublishDate);

	@Override
	public String[] getAvailableLanguageIds();

	@Override
	public String getDefaultLanguageId();

	@Override
	public void prepareLocalizedFieldsForImport() throws LocaleException;

	@Override
	public void prepareLocalizedFieldsForImport(Locale defaultImportLocale)
		throws LocaleException;

}