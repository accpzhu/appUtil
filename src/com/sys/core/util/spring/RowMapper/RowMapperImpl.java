package com.sys.core.util.spring.RowMapper;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.sys.core.util.bean.PropertyUtil;

/**
 * spring 查询结果后自动封装数据(spring Templant)
 * @author Administrator
 *
 */
public class RowMapperImpl implements RowMapper{
	
	public String className;
	
	public RowMapperImpl(){
		
		
	}
	
	public RowMapperImpl(String className){
		
		this.className=className;
		
	}

	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		Object obj=null;
		try {
			Class c = Class.forName(className);
			 obj=c.newInstance();
			Field field []=c.getDeclaredFields();
			for (int i=0;i<field.length;i++){
				String propertyName=field[i].getName();
				if (checkPropertyName(field[i].getType()) 
						&& PropertyUtil.isSetMethodExists(obj.getClass(), propertyName)
				){
				PropertyUtil.setProperty(obj, propertyName, rs.getObject(propertyName));
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
	}
	
	/**
	 * @param clazz
	 * @return
	 */
	public Boolean checkPropertyName(Class clazz) {
		if( "java.lang.String".equals(clazz.getName()) ) {
				return true;
		} else if( "java.lang.Long".equals(clazz.getName()) || (clazz ==Long.TYPE) ) {
		return true;
		} else
		if( "java.lang.Integer".equals(clazz.getName()) || (clazz == Integer.TYPE) ) {
			return true;
		} else if("java.lang.Float".equals(clazz.getName()) || (clazz == Float.TYPE)){
			return true;
		}else if ("java.lang.Double".equals(clazz.getName()) ||(clazz == Double.TYPE)){
			return true;
		}else
		return false;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}
}