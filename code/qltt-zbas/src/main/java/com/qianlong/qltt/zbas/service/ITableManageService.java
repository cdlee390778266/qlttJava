package com.qianlong.qltt.zbas.service;

import java.util.List;

import com.qianlong.qltt.zbas.entity.ConnectionParam;
import com.qianlong.qltt.zbas.entity.TableInfo;

public interface ITableManageService {

	/**获取源数据库的连接参数*/
	ConnectionParam getSourceDBConnectionParam();

	/**根据JDBC参数获取数据库全部表名*/
	List<TableInfo> getTables(ConnectionParam param);

	/**拼装Connection 参数*/
	ConnectionParam getDBConnectionParam(String host, String port, String database, String username, String password);

	/**
	 * 将源数据库中的表拷贝到目标数据库中
	 */
	void copyTables(ConnectionParam sourceConnectionParam, ConnectionParam targetConnectionParam, String[] tablenames);
	
}
