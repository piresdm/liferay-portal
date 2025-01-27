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

package com.liferay.digital.signature.manager.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.digital.signature.manager.DSEnvelopeManager;
import com.liferay.digital.signature.model.DSDocument;
import com.liferay.digital.signature.model.DSEnvelope;
import com.liferay.digital.signature.model.DSRecipient;
import com.liferay.petra.function.UnsafeConsumer;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.util.Base64;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;

import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Brian Wing Shun Chan
 */
@RunWith(Arquillian.class)
public class DSEnvelopeManagerTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Test
	public void testAddDSEnvelope() throws Exception {
		Class<?> clazz = getClass();

		DSEnvelope dsEnvelope = _dsEnvelopeManager.addDSEnvelope(
			TestPropsValues.getCompanyId(), TestPropsValues.getGroupId(),
			new DSEnvelope() {
				{
					dsDocuments = Collections.singletonList(
						new DSDocument() {
							{
								data = Base64.encode(
									FileUtil.getBytes(
										clazz.getResourceAsStream(
											"dependencies/Document.pdf")));
								dsDocumentId = "1";
								name = RandomTestUtil.randomString();
							}
						});
					dsRecipients = Collections.singletonList(
						new DSRecipient() {
							{
								dsRecipientId = "1";
								emailAddress =
									RandomTestUtil.randomString() +
										"@liferay.com";
								name = RandomTestUtil.randomString();
							}
						});
					emailBlurb = RandomTestUtil.randomString();
					emailSubject = RandomTestUtil.randomString();
					name = RandomTestUtil.randomString();
					senderEmailAddress =
						RandomTestUtil.randomString() + "@liferay.com";
					status = "sent";
				}
			});

		Assert.assertTrue(Validator.isNotNull(dsEnvelope.getDSEnvelopeId()));

		_dsEnvelopeManager.deleteDSEnvelopes(
			TestPropsValues.getCompanyId(), TestPropsValues.getGroupId(),
			dsEnvelope.getDSEnvelopeId());
	}

	@Test
	public void testGetDSEnvelope() throws Exception {
		String expectedEmailSubject = RandomTestUtil.randomString();

		DSEnvelope dsEnvelope = _dsEnvelopeManager.addDSEnvelope(
			TestPropsValues.getCompanyId(), TestPropsValues.getGroupId(),
			new DSEnvelope() {
				{
					emailSubject = expectedEmailSubject;
					status = "created";
				}
			});

		dsEnvelope = _dsEnvelopeManager.getDSEnvelope(
			TestPropsValues.getCompanyId(), TestPropsValues.getGroupId(),
			dsEnvelope.getDSEnvelopeId());

		Assert.assertEquals(expectedEmailSubject, dsEnvelope.getEmailSubject());

		_dsEnvelopeManager.deleteDSEnvelopes(
			TestPropsValues.getCompanyId(), TestPropsValues.getGroupId(),
			dsEnvelope.getDSEnvelopeId());
	}

	@Test
	public void testGetDSEnvelopesPage() throws Exception {
		DSEnvelope dsEnvelope1 = _dsEnvelopeManager.addDSEnvelope(
			TestPropsValues.getCompanyId(), TestPropsValues.getGroupId(),
			new DSEnvelope() {
				{
					dsDocuments = Collections.singletonList(
						new DSDocument() {
							{
								data = Base64.encode(
									FileUtil.getBytes(
										getClass(),
										"dependencies/Document.pdf"));
								dsDocumentId = "1";
								name = RandomTestUtil.randomString();
							}
						});
					dsRecipients = Collections.singletonList(
						new DSRecipient() {
							{
								dsRecipientId = "1";
								emailAddress =
									RandomTestUtil.randomString() +
										"@liferay.com";
								name = RandomTestUtil.randomString();
							}
						});
					emailBlurb = RandomTestUtil.randomString();
					emailSubject = RandomTestUtil.randomString();
					name = RandomTestUtil.randomString();
					senderEmailAddress =
						RandomTestUtil.randomString() + "@liferay.com";
					status = "sent";
				}
			});

		DSEnvelope dsEnvelope2 = _dsEnvelopeManager.addDSEnvelope(
			TestPropsValues.getCompanyId(), TestPropsValues.getGroupId(),
			new DSEnvelope() {
				{
					dsDocuments = Collections.singletonList(
						new DSDocument() {
							{
								data = Base64.encode(
									FileUtil.getBytes(
										getClass(),
										"dependencies/Document.pdf"));
								dsDocumentId = "1";
								name = RandomTestUtil.randomString();
							}
						});
					dsRecipients = Collections.singletonList(
						new DSRecipient() {
							{
								dsRecipientId = "1";
								emailAddress =
									RandomTestUtil.randomString() +
										"@liferay.com";
								name = RandomTestUtil.randomString();
							}
						});
					emailBlurb = RandomTestUtil.randomString();
					emailSubject = RandomTestUtil.randomString();
					name = dsEnvelope1.getName() + RandomTestUtil.randomInt();
					senderEmailAddress =
						RandomTestUtil.randomString() + "@liferay.com";
					status = "sent";
				}
			});

		if (false) {
			_assertPage(
				dsEnvelope1.getName(), "asc", 2,
				dsEnvelopes -> {
					_assertEquals(dsEnvelope2, dsEnvelopes.get(0));
					_assertEquals(dsEnvelope1, dsEnvelopes.get(1));
				});
			_assertPage(
				dsEnvelope1.getName(), "desc", 2,
				dsEnvelopes -> {
					_assertEquals(dsEnvelope1, dsEnvelopes.get(0));
					_assertEquals(dsEnvelope2, dsEnvelopes.get(1));
				});
			_assertPage(
				dsEnvelope2.getName(), "desc", 1,
				dsEnvelopes -> _assertEquals(dsEnvelope2, dsEnvelopes.get(0)));
		}

		_dsEnvelopeManager.deleteDSEnvelopes(
			TestPropsValues.getCompanyId(), TestPropsValues.getGroupId(),
			new String[] {
				dsEnvelope1.getDSEnvelopeId(), dsEnvelope2.getDSEnvelopeId()
			});
	}

	private void _assertEquals(
		DSEnvelope expectedDSEnvelope, DSEnvelope actualDSEnvelope) {

		Assert.assertEquals(
			expectedDSEnvelope.getDSEnvelopeId(),
			actualDSEnvelope.getDSEnvelopeId());
		Assert.assertEquals(
			expectedDSEnvelope.getName(), actualDSEnvelope.getName());
	}

	private void _assertPage(
			String keywords, String order, int expectedPageSize,
			UnsafeConsumer<List<DSEnvelope>, Exception> unsafeConsumer)
		throws Exception {

		Page<DSEnvelope> page = _dsEnvelopeManager.getDSEnvelopesPage(
			TestPropsValues.getCompanyId(), TestPropsValues.getGroupId(),
			"2021-01-01", keywords, order, Pagination.of(1, 2), "");

		Assert.assertEquals(expectedPageSize, page.getPageSize());

		List<DSEnvelope> dsEnvelopes = (List<DSEnvelope>)page.getItems();

		unsafeConsumer.accept(dsEnvelopes);
	}

	@Inject
	private DSEnvelopeManager _dsEnvelopeManager;

}