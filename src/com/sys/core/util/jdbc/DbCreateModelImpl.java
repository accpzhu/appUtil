package com.sys.core.util.jdbc;

import com.sys.core.util.jdbc.service.DbColumnService;
import com.sys.core.util.jdbc.service.DbCreateModelService;
import com.sys.core.util.jdbc.service.DbTableService;

/**
 * 创建数据模型
 * @author Administrator
 *
 */
public class DbCreateModelImpl implements DbCreateModelService{
	
	private DbColumnService dbColumnService;
	
	private DbTableService dbTableService;

	public String CreateModelTable() {
		
		//dbTableService.getTables(dbname, types);
		
		//dbColumnService.get
		
		return null;
	}

	
	public String InsertDbColumn() {
		
	
		
		return null;
	}

	public String InsertDbTable() {
		

		
		return null;
	}

}
