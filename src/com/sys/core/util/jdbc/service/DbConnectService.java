package com.sys.core.util.jdbc.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface DbConnectService {
	
	/**
	 * 获取一个数据库链接
	 * @return
	 */
	public Connection getConnect();
	

	/**
	 * 获取数据库新链接
	 * @return
	 */
	public Connection getNewConnect();
	
	/**
	 * 释放数据库链接
	 * 
	 */
	public void releaseConn(Connection conn);
	
	/**
	 * 关闭数据库链接
	 */
	public void closeConn(Connection conn);
	
	
	/**
	 * 根据查询语句返回结果集
	 * @param sql
	 * @return
	 * @throws SQLException
	 */
	public ResultSet queryForStr(String sql)throws SQLException;
	

	/**
	 * 根据sql语句执行返回处理结果
	 * @param sql
	 * @return
	 * @throws SQLException
	 */
	public Boolean execute(String sql)throws SQLException;
	
	
	/**
	 * 获取链接驱动
	 * @return
	 */
	public String getDriverClass();

	/**
	 * 获取数据库链接url
	 * @return
	 */
	public String getJdbcUrl();

	/**
	 * 获取数据库链接用户名
	 * @return
	 */
	public String getUser();

	/**
	 * 获取数据链接密码
	 * @return
	 */
	public String getPassword();
	
    /**
     * 数据提交
     * @throws SQLException 
     */
    public void commit() throws SQLException;		

	
	
}
