package com.sys.core.util.jdbc.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.sys.core.util.jdbc.bean.DbColumn;

/**
 * 数据库列查询
 * @author Administrator
 *
 */
public interface DbColumnService {

	 
	/**
	 * 获取数据库内字段
	 * 
	 * @param catalog            数据库名
	 * @param schemaPattern
	 * @param tableNamePattern   表名
	 * @param columnNamePattern
	 * @return
	 */
	public ResultSet getColumnsForDb(String catalog, String schemaPattern, String tableNamePattern, String columnNamePattern)throws SQLException;
	
	
	/**
	 * 
	 *  获取数据库内字段
	 * @param catalog
	 * @param tableName
	 * @return
	 * @throws SQLException
	 */
	public List<DbColumn> getColumnsForDb(String catalog,String tableName)throws SQLException;
	
}
