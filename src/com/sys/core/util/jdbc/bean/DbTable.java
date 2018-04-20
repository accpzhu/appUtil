package com.sys.core.util.jdbc.bean;

import com.sys.core.model.BaseModel;
/**
 * 数据库表model
 * @author Administrator
 *
 */
public class DbTable extends BaseModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3877985045118540448L;
	
	private Integer tableId;
	private String tableName;
	private String type;
	//tableName,type,projectId,dbName,memo
	private Integer projectId;
	private String dbName;
	private String memo;
	
	public Integer getTableId() {
		return tableId;
	}
	public void setTableId(Integer tableId) {
		this.tableId = tableId;
	}
	public Integer getProjectId() {
		return projectId;
	}
	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}
	public String getDbName() {
		return dbName;
	}
	public void setDbName(String dbName) {
		this.dbName = dbName;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	/**
	 * @return
	 */
	public String getTableName() {
		return tableName;
	}
	/**
	 * @param tableName
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	/**
	 * @return
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type
	 */
	public void setType(String type) {
		this.type = type;
	}


}
