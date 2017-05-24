package com.qianlong.qltt.zbas.service.impl;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.qianlong.qltt.zbas.entity.ConnectionParam;
import com.qianlong.qltt.zbas.entity.TableInfo;
import com.qianlong.qltt.zbas.service.ITableManageService;
import com.qianlong.qltt.zbas.util.JDBCUtil;
import com.qianlong.qltt.zbas.util.JSONUtil;
import com.qianlong.qltt.zbas.util.StringUtil;

@Service
public class TableManageServiceImpl implements ITableManageService {

	private static final Logger logger = LoggerFactory.getLogger(TableManageServiceImpl.class);

	public static final String MYSQL_JDBC_URL = "jdbc:mysql://HOSTNAME:PORT/DATABASE?useUnicode=true&characterEncoding=UTF-8";

	@Override
	public ConnectionParam getSourceDBConnectionParam() {
		return JDBCUtil.getSourceDBConnectionParam();
	}

	@Override
	public List<TableInfo> getTables(ConnectionParam param) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet tabs = null;
		ResultSet resultSet = null;
		DatabaseMetaData dbMetaData = null;
		try {
			connection = JDBCUtil.getConnection(param);
			dbMetaData = connection.getMetaData();
			TableInfo tableInfo =null;
			String[] types = { "TABLE" };
			List<TableInfo> tables = new ArrayList<TableInfo>();
			tabs = dbMetaData.getTables(null, null, null, types);
			while (tabs.next()) {
				tableInfo = new TableInfo();
				String tablename = tabs.getString("TABLE_NAME");
				tableInfo.setTableName(tablename);
				tableInfo.setRemarks(tabs.getString("REMARKS"));
				statement = connection.prepareStatement("select count(1) from "+tablename+";");
				resultSet = statement.executeQuery();
				resultSet.next();
				tableInfo.setRecordNum(resultSet.getInt(1));
				JDBCUtil.closeStatement(statement);
				JDBCUtil.closeResultSet(resultSet);
				tables.add(tableInfo);
			}
			logger.debug(JSONUtil.arrayToJson(tables));
			return tables;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JDBCUtil.closeResultSet(resultSet);
			JDBCUtil.closeResultSet(tabs);
			JDBCUtil.closeStatement(statement);
			JDBCUtil.closeConnection(connection);
		}
	}

	@Override
	public ConnectionParam getDBConnectionParam(String host, String port, String database, String username,
			String password) {
		ConnectionParam param = JDBCUtil.getSourceDBConnectionParam();
		String url = MYSQL_JDBC_URL.replace("HOSTNAME", host.trim()).replace("PORT", port.trim()).replace("DATABASE",
				database.trim());
		param.setUrl(url);
		param.setUsername(username);
		param.setPassword(password);
		return param;
	}

	// TODO 该方法在效率上急需优化
	@Override
	public void copyTables(ConnectionParam sourceConnectionParam, ConnectionParam targetConnectionParam,
			String[] tablenames) {
		long starttime = Calendar.getInstance().getTimeInMillis();
		for (String tablename : tablenames) {
			copyTable(sourceConnectionParam, targetConnectionParam, tablename);
		}
		long end = Calendar.getInstance().getTimeInMillis();
		logger.debug("执行数据表copy, 一共耗时" + (end - starttime) + "ms");
	}

	private void copyTable(ConnectionParam sourceConnectionParam, ConnectionParam targetConnectionParam,
			String tablename) {
		long starttime = Calendar.getInstance().getTimeInMillis();
		Connection sourceConnection = null;
		Connection targetConnection = null;
		Statement sourceStatement = null;
		Statement targetStatement = null;
		PreparedStatement targetPstmt = null;
		try {
			sourceConnection = JDBCUtil.getConnection(sourceConnectionParam);
			targetConnection = JDBCUtil.getConnection(targetConnectionParam);
			sourceStatement = sourceConnection.createStatement();
			targetStatement = targetConnection.createStatement();

			ResultSet ctResultSet = sourceStatement.executeQuery("show create table " + tablename + ";");
			// 在目标数据库中建表
			StringBuilder delsql = new StringBuilder();
			delsql.append("DROP TABLE IF EXISTS `").append(tablename).append("`;");// 如果表存在将干掉它
			targetStatement.execute(delsql.toString());
			String createTableSql = null;
			ctResultSet.next();
			createTableSql = ctResultSet.getString(2);
			JDBCUtil.closeResultSet(ctResultSet);
			logger.debug("建表语句：" + createTableSql.toString());
			if (!StringUtil.nullOrBlank(createTableSql)) {
				targetStatement.execute(createTableSql.toString());
				ResultSet resultSet = sourceStatement.executeQuery("select * from " + tablename + ";");
				ResultSetMetaData resultsetmetaDate = resultSet.getMetaData();
				int columnCount = resultsetmetaDate.getColumnCount();
				targetConnection.setAutoCommit(false);// 设置手动提交
				StringBuilder pstsql = new StringBuilder("INSERT INTO ").append(tablename).append(" VALUES (");
				for (int i = 0; i < columnCount; i++) {
					pstsql.append("?,");
				}
				String pstsqlstring = pstsql.substring(0, pstsql.length() - 1) + ");";
				logger.debug("插入语句：" + pstsqlstring);
				targetPstmt = targetConnection.prepareStatement(pstsqlstring);
				int batchCounter = 0; // 累加的批处理数量
				while (resultSet.next()) {
					for (int i = 1; i <= columnCount; i++) {
						targetPstmt.setObject(i, resultSet.getObject(i));
					}
					targetPstmt.addBatch();
					batchCounter++;
					if (batchCounter % 1000 == 0) { // 1000条数据一提交
						targetPstmt.executeBatch();
						targetPstmt.clearBatch();
						targetConnection.commit();
					}
				}
				// 提交剩余的批处理
				targetPstmt.executeBatch();
				targetPstmt.clearBatch();
				targetConnection.commit();
				// 释放连接
				JDBCUtil.closeResultSet(resultSet);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			JDBCUtil.closeStatement(sourceStatement);
			JDBCUtil.closeStatement(targetPstmt);
			JDBCUtil.closeStatement(targetStatement);
			JDBCUtil.closeConnection(sourceConnection);
			JDBCUtil.closeConnection(targetConnection);
			long end = Calendar.getInstance().getTimeInMillis();
			logger.debug("copy " + tablename + " 一共耗时" + (end - starttime) + "ms");
		}
	}
}
