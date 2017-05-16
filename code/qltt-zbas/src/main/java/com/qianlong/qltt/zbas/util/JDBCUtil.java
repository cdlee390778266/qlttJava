package com.qianlong.qltt.zbas.util;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.springframework.util.ResourceUtils;

import com.qianlong.qltt.zbas.entity.ConnectionParam;

public class JDBCUtil {
	/** 数据库配置文件路径 */
	public static final String DB_PROPERTIES = "classpath:conf/sqlmap.properties";

	public static ConnectionParam getSourceDBConnectionParam() {
		FileReader fileReader = null;
		try {
			File cfgFile = ResourceUtils.getFile("classpath:conf/sqlmap.properties");
			fileReader = new FileReader(cfgFile);
			Properties properties = new Properties();
			properties.load(fileReader);
			ConnectionParam param = new ConnectionParam();
			param.setDriverClass(properties.getProperty("jdbc.driverClassName"));
			param.setUrl(properties.getProperty("jdbc.url"));
			param.setUsername(properties.getProperty("jdbc.username"));
			param.setPassword(properties.getProperty("jdbc.password"));
			return param;
		} catch (IOException e) {
			throw new RuntimeException("获取数据库配置文件失败");
		} finally {
			if (fileReader != null) {
				try {
					fileReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static Connection getConnection(String diverClass, String url, String username, String password) {
		try {
			Connection connection = null; // 定义一个MYSQL链接对象
			Class.forName(diverClass).newInstance(); // MYSQL驱动
			connection = DriverManager.getConnection(url, username, password); // 链接本地MYSQL
			return connection;
		} catch (Exception e) {
			throw new RuntimeException("连接数据库失败");
		}
	}

	public static Connection getConnection(ConnectionParam param) {
		return getConnection(param.getDriverClass(), param.getUrl(), param.getUsername(), param.getPassword()); // 链接本地MYSQL
	}

	public static void closeConnection(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new RuntimeException("关闭数据库连接失败");
			}
		}
	}

	public static void closeStatement(Statement statement) {
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				throw new RuntimeException("关闭数据库连接失败");
			}
		}
	}
	
	public static void closeResultSet(ResultSet resultSet) {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				throw new RuntimeException("关闭数据库连接失败");
			}
		}
	}
	
	public static void checkConnectionParam(ConnectionParam param) {
		Connection connection = null;
		Statement statement = null;
		try {
			connection = getConnection(param);
			statement = connection.createStatement();
			statement.executeQuery("select 1 from dual;");
		} catch (SQLException e) {
			throw new RuntimeException("连接数据库失败,请检查目标数据库状态或填写的数据库参数是否正确");
		}finally {
			closeStatement(statement);
			closeConnection(connection);
		}	
	}
	
	
	public static List getAllTableName(Connection cnn) throws SQLException {
		List tables = new ArrayList();

		DatabaseMetaData dbMetaData = cnn.getMetaData();

		// 可为:"TABLE", "VIEW", "SYSTEM TABLE",
		// "GLOBAL TEMPORARY", "LOCAL TEMPORARY", "ALIAS", "SYNONYM"
		String[] types = { "TABLE" };

		ResultSet tabs = dbMetaData.getTables(null, null, null, types/* 只要表就好了 */);
		/*
		 * 记录集的结构如下: TABLE_CAT String => table catalog (may be null) TABLE_SCHEM
		 * String => table schema (may be null) TABLE_NAME String => table name
		 * TABLE_TYPE String => table type. REMARKS String => explanatory
		 * comment on the table TYPE_CAT String => the types catalog (may be
		 * null) TYPE_SCHEM String => the types schema (may be null) TYPE_NAME
		 * String => type name (may be null) SELF_REFERENCING_COL_NAME String =>
		 * name of the designated "identifier" column of a typed table (may be
		 * null) REF_GENERATION String => specifies how values in
		 * SELF_REFERENCING_COL_NAME are created. Values are "SYSTEM", "USER",
		 * "DERIVED". (may be null)
		 */
		while (tabs.next()) {
			// 只要表名这一列
			tables.add(tabs.getObject("TABLE_NAME"));

		}
		System.out.println(tables);
		return tables;
	}

	public static void main(String[] args) {
		ConnectionParam param = new ConnectionParam();
		param.setDriverClass("com.mysql.jdbc.Driver");
		//jdbc:mysql://127.0.0.1:3306/test?useUnicode=true&amp;characterEncoding=UTF-8
		param.setUrl("jdbc:mysql://127.0.0.1:3306/test?useUnicode=true&characterEncoding=UTF-8");
		param.setUsername("root");
		param.setPassword("cdyf2008");
		try (Connection connection = getConnection(param);
			 Statement statement = connection.createStatement();
			 
			){
			String sql = "INSERT INTO `ttadprealtime` VALUES ('100000000', '0', '1', '东方中科', '002819', '334', '涨停股', '2017-05-03', '2017-05-03', '2017-05-03', '55', '0');";
			statement.addBatch(sql);
			statement.executeBatch();
			statement.clearBatch();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
