package com.sys.core.util.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import com.sys.core.model.MyDynaBean;
import com.sys.core.util.StringUtil;
import com.sys.core.util.jdbc.util.ResultSetUtil;

/**
 * 数据库链接
 * @author zzs
 */
public class DbConnect {
	
	private String driverClass;
	private String jdbcUrl;
	private String user;
	private String password;
	private Connection connect;
	private String ip;
	private String port;
	public static String mysqlDriverClassName="com.mysql.jdbc.Driver";
	public static String oracleDriverClassName="oracle.jdbc.driver.OracleDriver";
	
		
	public void init(){
	
	}
	
	public static String getMysqlDriverClassName() {
		return mysqlDriverClassName;
	}
	public static String getOracleDriverClassName() {
		return oracleDriverClassName;
	}
	
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}
	public String getDriverClass() {
		return driverClass;
	}
	public void setDriverClass(String driverClass) {
		this.driverClass = driverClass;
	}
	public String getJdbcUrl() {
		return jdbcUrl;
	}
	
	/**
	 * @return
	 */
	public String getOracleJdbcUrl(String ip,String port, String sid){
		this.ip=ip;
		this.port=port;
		String url="jdbc:oracle:thin:@"+this.getIp()+":"+port+":"+sid+"";
		return url;
	}
	
	public void setJdbcUrl(String jdbcUrl) {
		this.jdbcUrl = jdbcUrl;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}


	/* (non-Javadoc)
	 * @see com.sys.core.util.jdbc.service.DbConnectService#closeConn(java.sql.Connection)
	 */
	public void closeConn(Connection conn) throws SQLException {
		if(connect!=null){

				conn.close();
		}
	}
	
	/**
	 * @throws SQLException
	 */
	public void closeConn() throws SQLException {
		if(connect!=null){
			connect.close();
		}
	}


	/* (non-Javadoc)
	 * @see com.sys.core.util.jdbc.service.DbConnectService#getFreeConnect()
	 */
	public Connection getConnect() throws SQLException, ClassNotFoundException {
		
		if(this.connect!=null){
			return this.connect;
		}else
			this.connect=this.getNewConnect();
			return this.connect;

	}
	


	/* (non-Javadoc)
	 * @see com.sys.core.util.jdbc.service.DbConnectService#getNewConnect()
	 */
	public Connection getNewConnect() throws SQLException, ClassNotFoundException {
		
		Connection con=null;
		Class.forName(driverClass);
		con=DriverManager.getConnection(jdbcUrl, user, password);
		return con;
	}

	/* (non-Javadoc)
	 * @see com.sys.core.util.jdbc.service.DbConnectService#releaseConn(java.sql.Connection)
	 */
	public void releaseConn(Connection conn) throws SQLException {
		
		if(conn==this.connect){
		  this.connect=conn;
		}else{
			this.closeConn(conn);
		}
	}
	
	/**
	 * @return
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public String getStringForSql(String sqlStr) throws SQLException, ClassNotFoundException{
		
		Connection conn1=this.getConnect();
		
		ResultSet result=conn1.createStatement().executeQuery(sqlStr);
		
        String tempStr="";
        result.next();
		if(result.getObject(1)!=null){
			
			tempStr=StringUtil.getString(result.getObject(1));
			
		}
		
		this.releaseConn(conn1);
		
		return tempStr;
	}

	/* (non-Javadoc)
	 * @see com.sys.core.util.jdbc.service.DbConnectService#queryForStr(java.lang.String)
	 */
	public ResultSet queryForStr(String sql) throws SQLException, ClassNotFoundException {

		Connection conn1=this.getConnect();
		if(conn1==null){
			throw new SQLException("数据库链接为空!!!");
		}
		ResultSet result=conn1.createStatement().executeQuery(sql);

		this.releaseConn(conn1);
		
		return result;
	}

	/* (non-Javadoc)
	 * @see com.sys.core.util.jdbc.service.DbConnectService#execute(java.lang.String)
	 */
	public Boolean execute(String sql) throws SQLException, ClassNotFoundException {
		
		Connection conn1=this.getConnect();

		Statement  statement= conn1.createStatement();
		
		Boolean bool=statement.execute(sql);
		
		statement.close();
		
		this.releaseConn(conn1);
		
		return bool;
	}
	
	/**
	 * 查询语句只有两列的情况下，自动封装并返回数据集合
	 * @param sqlStr
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException 
	 */
	public Map getMapForSql(String sqlStr) throws SQLException, ClassNotFoundException{
		
		Connection conn1=this.getConnect();
		
		ResultSet result=conn1.createStatement().executeQuery(sqlStr);
		
        String tempStr="";
        Map map=new HashMap();
		if(result.next()){
			
			map.put(result.getObject(1), result.getObject(2));
			
		}
		
		this.releaseConn(conn1);
		
		return map;
	}

	/**
	 * 查询语句只有两列的情况下，自动封装并返回数据集合
	 * @param sqlStr
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException 
	 */
	public List<MyDynaBean> getListForSql(String sqlStr) throws SQLException, ClassNotFoundException{
		
		Connection conn1=this.getConnect();
		
		ResultSet result=conn1.createStatement().executeQuery(sqlStr);
		
        String tempStr="";
      
        List list=ResultSetUtil.getMyDynaBeanForList(result);
		this.releaseConn(conn1);
		return list;
	}
	/* (non-Javadoc)
	 * @see com.sys.core.util.jdbc.service.DbConnectService#commit()
	 */
	public void commit() throws SQLException {
		
		this.connect.commit();
	}
	
	
	public static void main(String args[]) throws ClassNotFoundException{

		DbConnect dbConnect=new DbConnect();
		
		String url="jdbc:mysql://192.168.1.204:3337/information_schema?useUnicode=true&characterEncoding=utf-8&autoReconnect=true&useOldAliasMetadataBehavior=true";
		dbConnect.setJdbcUrl(url);
		dbConnect.setDriverClass("com.mysql.jdbc.Driver");
		dbConnect.setUser("root");
		dbConnect.setPassword("berheley_mysql");
		
		String str=
				"SELECT '192.168.1.204' ip,\n" +
						"       aa.SCHEMA_NAME 数据库名称,\n" + 
						"       '3337' 端口,\n" + 
						"       'mysql' 数据库类型,\n" + 
						"       '5.6' 数据库版本,\n" + 
						"       '' 负责人,\n" + 
						"       '' 项目名称,\n" + 
						"       '0' 备份状态\n" + 
						"  FROM SCHEMATA aa\n" + 
						" WHERE aa.SCHEMA_NAME NOT IN\n" + 
						"       ('information_schema', 'test', 'mysql', 'performance_schema')";
		
		try {

			ResultSet rs=dbConnect.queryForStr(str);
			
			ResultSetMetaData resultSetMetaData=rs.getMetaData();
			
//			while(rs.next()){
//				System.out.println(rs.getObject(1).toString());
//				System.out.println(rs.getObject(2).toString());
//			}
			
			for(int i=1;i<=resultSetMetaData.getColumnCount();i++){
				
				System.out.println(resultSetMetaData.getColumnName(i));
				System.out.println(resultSetMetaData.getColumnLabel(i));
				System.out.println(resultSetMetaData.getSchemaName(i));
				System.out.println(resultSetMetaData.getCatalogName(i));
				System.out.println(resultSetMetaData.getColumnClassName(i));
			}

			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}

}
