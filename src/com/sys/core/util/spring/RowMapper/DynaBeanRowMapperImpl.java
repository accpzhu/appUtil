package com.sys.core.util.spring.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.ResultSetMetaData;  

import org.springframework.jdbc.core.RowMapper;

import com.sys.core.model.MyDynaBean;

public class DynaBeanRowMapperImpl implements RowMapper{

	public DynaBeanRowMapperImpl(){
		
		
	}
	

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	public Object mapRow(ResultSet result, int arg1) throws SQLException {
		
		MyDynaBean myDynaBean=new MyDynaBean();
		
		ResultSetMetaData resultSetMetaData=result.getMetaData();
		
		for(int i=1;i<=resultSetMetaData.getColumnCount();i++){
	
			myDynaBean.setValue(resultSetMetaData.getColumnName(i), result.getObject(resultSetMetaData.getColumnName(i)));
		}
		return myDynaBean;
	}

}
