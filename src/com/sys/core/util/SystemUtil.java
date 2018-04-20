package com.sys.core.util;


/**
 * 
 * @author Administrator
 *
 */
public class SystemUtil {
	
	
	public static void classPathpt(){
		
		System.out.println(System.getProperty("java.class.path"));
	}
	
	public static void libraryPathpt(){
		
		System.out.println(System.getProperty("java.library.path"));
	}
	
	public static void userDirPt(){
		
		System.out.println(System.getProperty("user.dir"));
	}
	
	public static void println(String str){
		
		System.out.println(str);
	}
	
	public static void main(String [] str){
		
		userDirPt();
	}
	
	public static String  getHello(){
		
		return "hello word";
	}
	

}
