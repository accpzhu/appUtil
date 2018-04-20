package com.sys.core.util.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.sys.core.util.bean.PropertyUtil;
import com.sys.core.util.jdbc.bean.DbColumn;
import com.sys.core.util.jdbc.bean.DbTable;
import com.sys.core.util.jdbc.service.DbColumnService;
import com.sys.core.util.jdbc.service.DbConnectService;
import com.sys.core.util.jdbc.service.DbTableService;

/**
 * 
 * 数据库列操作实现
 * @author Administrator
 *
 */
public class DbColumnImpl implements DbColumnService{
	
	private DbConnectService dbConnectService;
	
	private DbTableService  dbTableService;

	/* (non-Javadoc)
	 * @see com.sys.core.util.jdbc.service.DbColumnService#getColumns(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public ResultSet getColumnsForDb(String catalog, String schemaPattern,
			String tableNamePattern, String columnNamePattern)
			throws SQLException {
		
		return dbConnectService.getConnect().getMetaData().getColumns(catalog,schemaPattern , tableNamePattern, columnNamePattern);
	}

	/* (non-Javadoc)
	 * @see com.sys.core.util.jdbc.service.DbColumnService#getColumns(java.lang.String, java.lang.String)
	 */
	public List<DbColumn> getColumnsForDb(String catalog, String tableName)
			throws SQLException {
		List list=new ArrayList();
		
		ResultSet result=this.getColumnsForDb(catalog, null, tableName, null);
		
		while(result.next()){
			
			DbColumn column=new DbColumn();
			
			DbTable table =dbTableService.queryTableForData(PropertyUtil.getString(result.getString(3)));
						
			column.setTableId(table.getTableId());
			column.setColumnName(PropertyUtil.getString(result.getString(4)));
			column.setColumnTypeName(PropertyUtil.getString(result.getString(6)));
			column.setColumnLength(Integer.getInteger(PropertyUtil.getString(result.getString(7))));
			
			list.add(column);
		}
		
		return list;
	}
	
    
	/**
	 * @param tableId
	 */
	public void delColumsForTable(Integer tableId){
		
		
	}
	
	
	/**
	 * @param column
	 * @throws SQLException 
	 */
	public void insertColumsForTable(DbColumn column) throws SQLException{
		String sql="insert into dbColumn (columnName,\n" +
			"columnShowName,columnTypeName,\n" + 
			"columnTypeCode,columnLength,tableId,widgetCode)\n" + 
			"value('"+column.getColumnName()+"'," +
					"'"+column.getColumnShowName()+"'," +
					"'"+column.getColumnTypeName()+"'," +
					"'"+column.getColumnTypeCode()+"'," +
					""+column.getColumnLength()+"," +
					""+column.getTableId()+"," +
					""+column.getWidgetCode()+")";

	    dbConnectService.execute(sql);
		
	}
	
	public void updateColumsForTable(String dbname,String []types,String tableName) throws SQLException{
		
		List <DbTable> list=dbTableService.queryAllTablesForDate();
		
		for(DbTable i : list){
			
			
		}
		
	}
	
	/**
	 * @return
	 */
	public DbConnectService getDbConnectService() {
		return dbConnectService;
	}

	/**
	 * @param dbConnectService
	 */
	public void setDbConnectService(DbConnectService dbConnectService) {
		this.dbConnectService = dbConnectService;
	}
	
	/**
	 * @param dbTableService
	 */
	public void setDbTableService(DbTableService dbTableService) {
		this.dbTableService = dbTableService;
	}

}
