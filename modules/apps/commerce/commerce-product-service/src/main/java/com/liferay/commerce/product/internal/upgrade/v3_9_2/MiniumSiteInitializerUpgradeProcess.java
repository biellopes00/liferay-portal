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

package com.liferay.commerce.product.internal.upgrade.v3_9_2;

import com.liferay.commerce.product.internal.upgrade.base.BaseCommerceProductServiceUpgradeProcess;
import com.liferay.counter.kernel.service.CounterLocalService;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.jdbc.AutoBatchPreparedStatementUtil;
import com.liferay.portal.kernel.model.Layout;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Crescenzo Rega
 * @author Andrea Sbarra
 */
public class MiniumSiteInitializerUpgradeProcess
	extends BaseCommerceProductServiceUpgradeProcess {

	public MiniumSiteInitializerUpgradeProcess(
		CounterLocalService counterLocalService) {

		_counterLocalService = counterLocalService;
	}

	@Override
	public void doUpgrade() throws Exception {
		try (PreparedStatement preparedStatement1 = connection.prepareStatement(
			"select siteGroupId from CommerceChannel where siteGroupId " +
			"in (select groupId from LayoutSet where privateLayout = ? and " +
			"themeId = 'minium_WAR_miniumtheme')");

			 PreparedStatement preparedStatement2 =
				 AutoBatchPreparedStatementUtil.autoBatch(
					 connection,
					 "select plid from Layout where groupId = ? " +
					 "and privateLayout = ? ORDER by " +
					 "layoutId");

			 PreparedStatement preparedStatement3 =
				 AutoBatchPreparedStatementUtil.autoBatch(
					 connection,
					 "update Layout set layoutId = ?, privateLayout = ? " +
					 "where groupId = ? and plid = ? and privateLayout = ?");

			 PreparedStatement preparedStatement4 =
				 AutoBatchPreparedStatementUtil.autoBatch(
					 connection,
					 "select max(layoutId) from Layout where groupId = ? " +
					 "and privateLayout = ?");

			 PreparedStatement preparedStatement5 =
				 AutoBatchPreparedStatementUtil.autoBatch(
					 connection,
					 "select layoutId, parentLayoutId from Layout where " +
					 "groupId = ? and layoutId > ? and parentLayoutId > 0");

			 PreparedStatement preparedStatement6 =
				 AutoBatchPreparedStatementUtil.autoBatch(
					 connection,
					 "update Layout set parentLayoutId = ? where " +
					 "groupId = ? and layoutId = ? and parentLayoutId > 0");

			 PreparedStatement preparedStatement7 =
				 AutoBatchPreparedStatementUtil.autoBatch(
					 connection,
					 "select plid from Layout where friendlyURL != '/login' " +
					 "and groupId = ? and parentPlid = 0 and type_ = " +
					 "'portlet' order by priority");

			 PreparedStatement preparedStatement8 =
				 AutoBatchPreparedStatementUtil.autoBatch(
					 connection,
					 "update Layout set priority = ? where groupId = ? and " +
					 "plid = ?")) {

			preparedStatement1.setBoolean(1, true);

			try (ResultSet resultSet1 = preparedStatement1.executeQuery()) {
				while (resultSet1.next()) {
					long siteGroupId = resultSet1.getLong("siteGroupId");

					long maxLayoutId = _getMaxLayoutId(
						preparedStatement4, siteGroupId);

					_updateLayoutId(
						preparedStatement2, preparedStatement3, siteGroupId);

					_updateParentLayoutId(
						maxLayoutId, preparedStatement5, preparedStatement6,
						siteGroupId);

					_updateLayoutPriorities(
						preparedStatement7, preparedStatement8, siteGroupId);
				}
			}
		}
	}

	private long _getMaxLayoutId(
			PreparedStatement preparedStatement, long siteGroupId)
		throws SQLException {

		preparedStatement.setLong(1, siteGroupId);
		preparedStatement.setBoolean(2, false);

		try (ResultSet resultSet = preparedStatement.executeQuery()) {
			if (resultSet.next()) {
				return resultSet.getLong(1);
			}
		}

		return 0;
	}

	private void _updateLayoutId(
			PreparedStatement preparedStatement1,
			PreparedStatement preparedStatement2, long siteGroupId)
		throws SQLException {

		preparedStatement1.setLong(1, siteGroupId);

		preparedStatement1.setBoolean(2, true);

		try (ResultSet resultSet = preparedStatement1.executeQuery()) {
			while (resultSet.next()) {
				long plid = resultSet.getLong("plid");

				long newLayoutId = _counterLocalService.increment(
					StringBundler.concat(
						Layout.class.getName(), StringPool.POUND, siteGroupId,
						StringPool.POUND, false));

				preparedStatement2.setLong(1, newLayoutId);

				preparedStatement2.setBoolean(2, false);

				preparedStatement2.setLong(3, siteGroupId);

				preparedStatement2.setLong(4, plid);

				preparedStatement2.setBoolean(5, true);

				preparedStatement2.addBatch();
			}
		}

		preparedStatement2.executeBatch();
	}

	private void _updateLayoutPriorities(
			PreparedStatement preparedStatement1,
			PreparedStatement preparedStatement2, long siteGroupId)
		throws SQLException {

		preparedStatement1.setLong(1, siteGroupId);

		try (ResultSet resultSet = preparedStatement1.executeQuery()) {
			long priority = 0;

			while (resultSet.next()) {
				long plid = resultSet.getLong("plid");

				priority++;

				preparedStatement2.setLong(1, priority);

				preparedStatement2.setLong(2, siteGroupId);

				preparedStatement2.setLong(3, plid);

				preparedStatement2.addBatch();
			}

			preparedStatement2.executeBatch();
		}
	}

	private void _updateParentLayoutId(
			long maxLayoutId, PreparedStatement preparedStatement1,
			PreparedStatement preparedStatement2, long siteGroupId)
		throws SQLException {

		preparedStatement1.setLong(1, siteGroupId);
		preparedStatement1.setLong(2, maxLayoutId);

		try (ResultSet resultSet8 = preparedStatement1.executeQuery()) {
			while (resultSet8.next()) {
				long layoutId = resultSet8.getLong("layoutId");

				long parentLayoutId = resultSet8.getLong("parentLayoutId");

				preparedStatement2.setLong(1, parentLayoutId + maxLayoutId);

				preparedStatement2.setLong(2, siteGroupId);

				preparedStatement2.setLong(3, layoutId);

				preparedStatement2.addBatch();
			}
		}

		preparedStatement2.executeBatch();
	}

	private final CounterLocalService _counterLocalService;

}