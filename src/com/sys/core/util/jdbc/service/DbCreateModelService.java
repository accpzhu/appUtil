package com.sys.core.util.jdbc.service;

/**
 * 创建数据库模型service
 * @author Administrator
 *
 */
public interface DbCreateModelService {
	
	
	/**
	 * 创建dbTable,dbColumn
	 * @return
	 */
	public String  CreateModelTable();
	
	public String InsertDbTable();
	
	public String InsertDbColumn();
	
	

}
