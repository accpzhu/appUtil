package com.sys.core.util.jdbc.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import com.sys.core.util.jdbc.bean.DbTable;

/**
 * 数据库表查询
 * @author Administrator
 *
 */
public interface DbTableService {
	
	/**
	 * 获取数据库表名
	 * @param dbname         数据库名称
	 * @param schemaPattern
	 * @param tableNamePattern "%"
	 * @param types          String types[]={"TABLE","VIEW"};
	 * @return
	 */
	public ResultSet getTablesForDb(String dbname,      
								String schemaPattern,
								String tableNamePattern, 
								String types []
								)throws SQLException;
	

	/**
	 * @param dbname
	 * @param types
	 * @param arg    0:所述项目编码,1:数据库名称,2:备注
	 * @return
	 * @throws SQLException
	 */
	public List<DbTable> getTablesForDb(String dbname, String[] types,Object [] arg) throws SQLException;
    
 
	/**
	 * 添加新表数据
	 * @param dbname
	 * @param types
	 * @param arg
	 * @throws SQLException
	 */
	public void updateForTables(String dbname, String[] types,Object [] arg) throws SQLException;
	
	
	/**
	 * insert Tables数据
	 * @param table
	 * @throws SQLException
	 */
	public void insertTableForDate(DbTable table) throws SQLException;

	
    /**
     * @param table
     * @throws SQLException
     */
    public void delTableForDate(DbTable table) throws SQLException;
    
    
    /**
     * @param tableName
     * @return
     * @throws SQLException
     */
    public DbTable queryTableForData(String tableName) throws SQLException;
    
    /**
     * @param dbname
     * @param types
     * @param arg
     * @param tableName
     * @return
     * @throws SQLException
     */
    public DbTable getTableForDb(String dbname, String[] types,Object [] arg,String tableName) throws SQLException;
    
    /**
     * @return
     * @throws SQLException
     */
    public List <DbTable> queryAllTablesForDate() throws SQLException;
}
