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

package com.liferay.petra.url.pattern.mapper.internal;

import com.liferay.petra.url.pattern.mapper.URLPatternMapper;
import com.liferay.portal.kernel.test.rule.CodeCoverageAssertor;

import java.util.Map;
import java.util.Set;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;

/**
 * @author Arthur Chan
 */
public abstract class BaseURLPatternMapperCorrectnessTestCase
	extends BaseURLPatternMapperTestCase {

	@ClassRule
	public static final CodeCoverageAssertor codeCoverageAssertor =
		CodeCoverageAssertor.INSTANCE;

	@Test
	public void testGetValue() {
		URLPatternMapper<Integer> urlPatternMapper = createURLPatternMapper(
			createValues());

		for (Map.Entry<String, Integer[]> entry :
				expectedURLPatternMatches.entrySet()) {

			String urlPath = entry.getKey();
			Integer[] expectedURLPatternIndexes = entry.getValue();

			if (expectedURLPatternIndexes.length == 0) {
				Assert.assertNull(urlPatternMapper.getValue(urlPath));

				continue;
			}

			Assert.assertEquals(
				expectedURLPatternIndexes[0],
				urlPatternMapper.getValue(urlPath));
		}
	}

	@Test
	public void testGetValues() {
		URLPatternMapper<Integer> urlPatternMapper = createURLPatternMapper(
			createValues());

		for (Map.Entry<String, Integer[]> entry :
				expectedURLPatternMatches.entrySet()) {

			String urlPath = entry.getKey();
			Integer[] expectedURLPatternIndexes = entry.getValue();

			Set<Integer> actualURLPatternIndexes = urlPatternMapper.getValues(
				urlPath);

			if (expectedURLPatternIndexes.length == 0) {
				Assert.assertTrue(actualURLPatternIndexes.isEmpty());

				continue;
			}

			for (int expectedURLPatternIndex : expectedURLPatternIndexes) {
				Assert.assertTrue(
					actualURLPatternIndexes.remove(expectedURLPatternIndex));
			}

			Assert.assertTrue(actualURLPatternIndexes.isEmpty());
		}
	}

}