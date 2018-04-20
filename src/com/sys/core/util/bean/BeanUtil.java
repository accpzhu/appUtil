package com.sys.core.util.bean;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.commons.beanutils.BasicDynaBean;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.beans.factory.config.PropertiesFactoryBean;

import com.sys.core.model.MyDynaBean;

public class BeanUtil {
	
	private static Map<String, Object> beansMap;
	
	private DataSource dataSource;
	
	private String dbType;
	
	public String getDbType() {
		return dbType;
	}

	public void setDbType(String dbType) {
		this.dbType = dbType;
	}
	@Resource
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}


	
	public BeanUtil(String dbType){
		
		beansMap=new HashMap<String, Object>();
		
		this.dbType=dbType;
		
	}
	
	public BeanUtil(){
		
		beansMap=new HashMap<String, Object>();
		
	}
	
	/**
	 * @param tableName
	 * @return
	 * @throws SQLException
	 */
	public void  initBeanForMysqlTableNames(String []tableNames) throws SQLException{

		    for(int i=0;i<tableNames.length;i++){
		    	
		    	String sqlStr="SELECT * FROM INFORMATION_SCHEMA.COLUMNS WHERE table_name='"+tableNames[i]+"'";
				MyDynaBean tempBean=this.getBeanForSqlStr(sqlStr);
				beansMap.put(tableNames[i], tempBean);
		    }
		
	}
	
	/**
	 * @param tableName
	 * @return
	 * @throws SQLException
	 */
	public MyDynaBean getBeanForMysqlTableName(String tableName) throws SQLException{
		
			String sqlStr="SELECT * FROM INFORMATION_SCHEMA.COLUMNS WHERE table_name='"+tableName+"'";

			if(beansMap.get(tableName)==null){
				MyDynaBean tempBean=this.getBeanForSqlStr(sqlStr);
				beansMap.put(tableName, tempBean);
				return tempBean;
			}else
				
			return (MyDynaBean) ((MyDynaBean) beansMap.get(tableName)).clone();
	}
	
	/**
	 * @param tableName
	 * @return 为弯沉
	 * @throws SQLException
	 */
	public MyDynaBean getBeanForOracleTableName(String tableName) throws SQLException{
		
     return null;      
    }
	
	/**
	 * @param sqlStr  
	 *        mysql: SELECT * FROM INFORMATION_SCHEMA.COLUMNS WHERE table_name='db_detailed'
	 * @return
	 * @throws SQLException
	 */
	private MyDynaBean getBeanForSqlStr(String sqlStr) throws SQLException{
		
				MyDynaBean myDynaBean=new MyDynaBean();
				Connection connection=DataSourceUtils.getConnection(dataSource);
				ResultSet result=connection.createStatement().executeQuery(sqlStr);
				ResultSetMetaData resultSetMetaData=result.getMetaData();
				for(int i=1;i<=resultSetMetaData.getColumnCount();i++){
					//System.out.println("ColumnName:"+resultSetMetaData.getColumnName(i));
					myDynaBean.setValue(resultSetMetaData.getColumnName(i), null);
				}
				DataSourceUtils.releaseConnection(connection, dataSource);
				return myDynaBean;
	} 

}
