package com.sys.core.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class PropertyUtil {

	/**
	 * 获取bean内属性名称
	 * 构建一个属性描述器 把对应属性 propertyName 的 get 和 set 方法保存到属性描述器中
	 * @param clazz
	 * @param propertyName
	 * @return
	 */
	public  static PropertyDescriptor getPropertyDescriptor(Class clazz, String propertyName){
		StringBuffer sb = new StringBuffer();//构建一个可变字符串用来构建方法名称
		Method setMethod = null;
		Method getMethod = null;
		PropertyDescriptor  pd =null;
		
		 try
		 {
			Field f = clazz.getDeclaredField(propertyName);//根据字段名来获取字段
			if(f!=null){
			//构建方法的后缀
		    String methodEnd = propertyName.substring(0,1).toUpperCase()+propertyName.substring(1);
		    sb.append("set"+methodEnd);//构建set方法
		    //构建set 方法
		    setMethod = clazz.getDeclaredMethod(sb.toString(),new Class[]{f.getType()} );
		    sb.delete(0, sb.length());//清空整个可变字符串
			sb.append("get"+methodEnd);//构建get方法
			//构建get 方法
			getMethod = clazz.getDeclaredMethod(sb.toString(), new Class[]{});
			
			//构建一个属性描述器 把对应属性 propertyName 的 get 和 set 方法保存到属性描述器中
			pd = new PropertyDescriptor(propertyName,getMethod,setMethod);
		  }
		}catch(Exception ex){
			ex.printStackTrace();    
		}
		return pd;
	}
	
	
	/**
	 * 
	 * 根据bean属性获取set方法名称
	 * @param propertyName
	 * @return
	 */
	public static String getSetMethodName(String propertyName){
		
		String sb = new String();
	    String methodEnd = propertyName.substring(0,1).toUpperCase()+propertyName.substring(1);
	    sb="set"+methodEnd;
	    return sb ;
	}
	
	
	
	/**
	 * 根据bean属性获取get方法名称
	 * @param propertyName
	 * @return
	 */
	public static String getgetMethodName(String propertyName){
		
		String sb = new String();
	    String methodEnd = propertyName.substring(0,1).toUpperCase()+propertyName.substring(1);
	    sb="get"+methodEnd;
	    return sb ;
	}
	
	
	/**
	 * 根据方法名判断实例化类中是否有该方法
	 * @param clazz
	 * @param methodNam
	 * @return
	 */
	public static Boolean isSetMethodExists(Class clazz, String propertyName){
		
	    //构建set 方法
	    try {
	    	Field f = clazz.getDeclaredField(propertyName);//根据字段名来获取字段
			clazz.getDeclaredMethod(getSetMethodName(propertyName)  ,new Class[]{f.getType()} );
		} catch (Exception e) {
			return false;
		} 
		return true;
	}
	
	/**
	 * 
	 * 调用bean内set*方法
	 * @param obj
	 * @param propertyName
	 * @param value
	 */
	public static void setProperty(Object obj,String propertyName,Object value){
		Class clazz = obj.getClass();//获取对象的类型
		PropertyDescriptor pd = getPropertyDescriptor(clazz,propertyName);//获取 clazz 类型中的 propertyName 的属性描述器

		if(pd.getWriteMethod()!=null){
			Method setMethod = pd.getWriteMethod();//从属性描述器中获取 set 方法
			Class clTemp=pd.getPropertyType();
			try {
				if( "java.lang.String".equals(clTemp.getName()) ) {
					
					setMethod.invoke(obj, new Object[]{String.valueOf(value)});

			} else if( "java.lang.Long".equals(clTemp.getName()) || (clTemp==Long.TYPE) ) {
				setMethod.invoke(obj, new Object[]{(Long)value});
			} 
			else if( "java.lang.Integer".equals(clTemp.getName()) || (clTemp == Integer.TYPE) ) {
				setMethod.invoke(obj, new Object[]{(Integer)value});
			} 
			else if("java.lang.Float".equals(clTemp.getName()) || (clTemp == Float.TYPE)){
				setMethod.invoke(obj, new Object[]{(Float)value});
			}else if ("java.lang.Double".equals(clTemp.getName()) ||(clTemp == Double.TYPE)){
				setMethod.invoke(obj, new Object[]{(Double)value});
			}else
					 setMethod.invoke(obj, new Object[]{value}); 
				//调用 set 方法将传入的value值保存属性中去
			} catch (Exception e){

				e.printStackTrace();
			}
		}
		

	}
	
	/**
	 * 获取bean内get*方法值
	 * @param obj
	 * @param propertyName
	 * @return
	 */
	public static Object getProperty(Object obj, String propertyName){
		Class clazz = obj.getClass();//获取对象的类型
		PropertyDescriptor  pd = getPropertyDescriptor(clazz,propertyName);//获取 clazz 类型中的 propertyName 的属性描述器
		Method getMethod = pd.getReadMethod();//从属性描述器中获取 get 方法
		Object value =null ;
		 try {
			value = getMethod.invoke(obj, new Object[]{});//调用方法获取方法的返回值
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;//返回值
	}
	
	 /**
	 *  判断对象是否实例化
	 * @param obj
	 * @param cls
	 * @return
	 */
	public boolean isInstance(Object obj, Class cls) { 
	        return cls.isInstance(obj); 
	   } 
	
	
	/**
	 * 判断是否为基础数据类型
	 * @param clazz
	 * @return
	 */
	public static Boolean checkPropertyName(Class clazz) {
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
	
	/**
	 * 数组转化成List
	 * @param obj
	 * @return
	 */
	public static List getList(Object[] obj){
		
		List list =new ArrayList();
		for (int i=0;i<obj.length;i++){
			list.add(obj[i]);
		}
		
		return list;
	}
		
	/**
	 * 初始化init.properties文件
	 * @param key
	 * @return String
	 */
	public static String getPropertyParam(String propertyName,String key){
		ResourceBundle rb = ResourceBundle.getBundle(propertyName);
		return rb.getString(key);
	}
	
	/**
	 * 修改.properties文件
	 * @param propertyFile
	 * @param key
	 * @param value
	 */
	public static void setPropertyParam(String propertyFile,String key,String value){
		
		Properties prop = new Properties();
		  try {
		  File file = new File(propertyFile);
		  if (!file.exists())
		  file.createNewFile();
		  InputStream fis = new FileInputStream(file);
		  prop.load(fis);
		  fis.close();
		  OutputStream fos = new FileOutputStream(propertyFile);
		  prop.setProperty(key, value);
		  //prop.store(fos, "Update '" + key + "' value");
		  fos.close();
		  } catch (IOException e) {
		   e.printStackTrace();
		  
		  }
	}
	
	public static String getString(Object value) {
		if (value == null) {
			return "";
		} else {
			return String.valueOf(value).trim();
		}
	}
	
	public static int getInteger(Object value) {
		if (value == null) {
			return 0;
		} else {
			return Integer.parseInt(String.valueOf(value).trim());
		}
	}
	
	/**
	 * 获取bean内string
	 * @param value
	 * @param property
	 * @return
	 */
	public static String getBeanValueForStr(Object obj,String property) {
		
		return getString(getProperty(obj,property)) ;
	   
	}
	
	/**
	 * 获取bean内Integer
	 * @param value
	 * @param property
	 * @return
	 */
	public static Integer getBeanValueForInt(Object obj,String property) {
		
		return getInteger(getProperty(obj,property)) ;
	   
	}
	
	
	/**
	 * @param obj
	 * @param index
	 * @return
	 */
	public static String getBeanValueForStr(Object obj,Integer index){
		
		Field [] fields=obj.getClass().getDeclaredFields();
		
		return getBeanValueForStr(obj,fields[index].getName());
	}
	
	/**
	 * @param obj
	 * @param index
	 * @return
	 */
	public static Integer getBeanValueForInt(Object obj,Integer index){
		
		Field [] fields=obj.getClass().getDeclaredFields();
		
		return getBeanValueForInt(obj,fields[index].getName());
	}
	
	
	/**
	 * 显示出该javaBean内属性
	 * @param obj
	 */
	public static void printObjectField(Object obj){
		
		Field [] fields=obj.getClass().getDeclaredFields();
		
		System.out.println("obj.size="+fields.length);
		System.out.println("Object属性:");
		
		for(int i=0;i<fields.length;i++){
			
			System.out.println("index: "+i+"  "+fields[i].getName()+"   类型:"+fields[i].getGenericType().toString());
		}
	}
	
	/**
	 * 显示出该javaBean内属性
	 * @param obj
	 */
	public static void printObjectFieldFormatStr(Object obj){
		
		Field [] fields=obj.getClass().getDeclaredFields();
		
		for(int i=0;i<fields.length;i++){
			
			System.out.println("private String "+fields[i].getName()+";");
		}
	}
	
	/**
	 * 根据index给obj填值
	 * @param obj
	 * @param index
	 * @param value
	 */
	public static void setBeanValue(Object obj,Integer index,Object value){
		
		if(value!=null){
		
			Field [] fields=obj.getClass().getDeclaredFields();
			setProperty(obj,fields[index].getName(),value);
		
		}
	}
	
	
	public static void main(String args[]){
		
//		PropertyUtil util=new PropertyUtil();
//		
//		util.printObjectFieldFormatStr(new com.guohualife.contimport.common.generated.model.ImportLachargeextrainfo());
	    
	    String str=",111111,222,22222222,111,333";
	    
	    System.out.println(str.subSequence(2, str.length()));
	}

}

