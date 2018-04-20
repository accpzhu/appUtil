package com.sys.core.util.jdbc.util;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sys.core.model.MyDynaBean;

/**
 * 查询结果集工具类
 * @author Administrator
 *
 */
public class ResultSetUtil {
	
	/**
	 * 返回MyDynaBean封装的list
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	public static List<MyDynaBean> getMyDynaBeanForList(ResultSet rs) throws SQLException{
		
		List <MyDynaBean> list=new ArrayList();
		
		while(rs.next()) { 
			MyDynaBean myDynaBean=new MyDynaBean();
			ResultSetMetaData resultSetMetaData=rs.getMetaData();
			for(int i=1;i<=resultSetMetaData.getColumnCount();i++){
				myDynaBean.setValue(resultSetMetaData.getColumnName(i), rs.getObject(resultSetMetaData.getColumnName(i)));
			}
			list.add(myDynaBean);
		} 
		
		return list;
	}

}
