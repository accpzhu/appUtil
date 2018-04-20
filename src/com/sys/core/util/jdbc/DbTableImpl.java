package com.sys.core.util.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sys.core.util.bean.PropertyUtil;
import com.sys.core.util.jdbc.bean.DbTable;
import com.sys.core.util.jdbc.service.DbConnectService;
import com.sys.core.util.jdbc.service.DbTableService;

/**
 * 数据库表操作实现
 * @author Administrator
 *
 */
public class DbTableImpl implements DbTableService{

	private DbConnectService dbConnectService;
	
	public DbConnectService getDbConnectService() {
		return dbConnectService;
	}

	public void setDbConnectService(DbConnectService dbConnectService) {
		this.dbConnectService = dbConnectService;
	}

	/* (non-Javadoc)
	 * @see com.sys.core.util.jdbc.service.DbTableService#getTables(java.lang.String, java.lang.String, java.lang.String, java.lang.String[])
	 */
	public ResultSet getTablesForDb(String dbname, String schemaPattern,
			String tableNamePattern, String[] types) throws SQLException {
		
		return dbConnectService.getConnect().getMetaData().getTables(dbname, schemaPattern, tableNamePattern, types);
	}

	/* (non-Javadoc)
	 * @see com.sys.core.util.jdbc.service.DbTableService#getTables(java.lang.String, java.lang.String[])
	 */
	public List<DbTable> getTablesForDb(String dbname, String[] types,Object [] arg) throws SQLException{
		
		List list=new ArrayList();
		
		ResultSet result=this.getTablesForDb(dbname, null, null, types);
		while(result.next()){
			
			DbTable table=new DbTable();
			table.setTableName(PropertyUtil.getString(result.getString(3)));
			table.setType(PropertyUtil.getString(result.getString(4)));
			table.setProjectId((Integer)arg[0]);
			table.setDbName((String)arg[1]);
			table.setMemo((String)arg[2]);
			list.add(table);
		}
		
		return list;
	}
	
	/* (non-Javadoc)
	 * @see com.sys.core.util.jdbc.service.DbTableService#insertNewTable(java.lang.String, java.lang.String[], java.lang.Object[])
	 */
	public void updateForTables(String dbname, String[] types,Object [] arg) throws SQLException{
		
		List <DbTable> list=this.getTablesForDb(dbname, types,arg);
				 
		DbTable tempTable=null;
		 
		for (DbTable i :list){
			
		 DbTable table=this.queryTableForData(i.getTableName());
		 
		 if (table==null){
			 
			 this.insertTableForDate(this.getTableForDb(dbname, types, arg, i.getTableName()));
			 
			 tempTable=i;
		 }
		 
		 if(table!=null && this.getTableForDb(dbname, types, arg, i.getTableName())==null){
			this.delTableForDate(table);
		 }

		}//for

		//最后一条数据需要insert 两次才能成功
		if (tempTable!=null){
			this.insertTableForDate(this.getTableForDb(dbname, types, arg, tempTable.getTableName()));
		}
		
		
	}
	
	/* (non-Javadoc)
	 * @see com.sys.core.util.jdbc.service.DbTableService#insertTable(com.sys.core.util.jdbc.bean.DbTable)
	 */
	public void insertTableForDate(DbTable table) throws SQLException{
		
		String sql="insert into dbTable (tableName,type,projectId,dbName,memo) " +
		"values ('" +table.getTableName() +
		"','" +table.getType()+
		"','" +table.getProjectId()+
		"','" +table.getDbName()+
		"','" +table.getMemo()+
		"');";
		
		
	    dbConnectService.execute(sql);
		

		
	}
	
    public void delTableForDate(DbTable table) throws SQLException{
    	
    	String sql="delete dbTable where tableId='" +table.getTableId()+"';";
    	
    	dbConnectService.execute(sql);

    }
    
    public DbTable queryTableForData(String tableName) throws SQLException{
    	
    	ResultSet resuleSet=dbConnectService.queryForStr("select * from dbTable where tableName='" +tableName+
    			"';");
    	DbTable table=null;
		while(resuleSet.next()){
			table=new DbTable();
			table.setTableName(PropertyUtil.getString(resuleSet.getString("tableName")));
			table.setType(PropertyUtil.getString(resuleSet.getString("type")));
			table.setProjectId(resuleSet.getInt("projectId"));
			table.setDbName(PropertyUtil.getString(resuleSet.getString("dbName")));
			table.setMemo(PropertyUtil.getString(resuleSet.getString("memo")));
		}
    	
    	return table;
    }
    
    /* (non-Javadoc)
     * @see com.sys.core.util.jdbc.service.DbTableService#getTable(java.lang.String, java.lang.String[], java.lang.Object[], java.lang.String)
     */
    public DbTable getTableForDb(String dbname, String[] types,Object [] arg,String tableName) throws SQLException{
    	
		List <DbTable> list=this.getTablesForDb(dbname,types,arg);
		
        for(DbTable i: list){
        	
        	if (i.getTableName().equals(tableName)){
        		
        		return i;
        		
        	}
        }
        
		return null;
    }
    
    /**
     * @return
     * @throws SQLException
     */
    public List <DbTable> queryAllTablesForDate() throws SQLException{
    	
    	ResultSet resuleSet=dbConnectService.queryForStr("select * from dbTable;");
        List list=new ArrayList();
		while(resuleSet.next()){
			DbTable table=new DbTable();
			table.setTableId(resuleSet.getInt("tableId"));
			table.setTableName(PropertyUtil.getString(resuleSet.getString("tableName")));
			table.setType(PropertyUtil.getString(resuleSet.getString("type")));
			table.setProjectId(resuleSet.getInt("projectId"));
			table.setDbName(PropertyUtil.getString(resuleSet.getString("dbName")));
			table.setMemo(PropertyUtil.getString(resuleSet.getString("memo")));
			list.add(table);
		}

		return list;

    }
	


}
