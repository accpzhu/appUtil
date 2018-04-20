package com.sys.core.util.jdbc;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import com.sys.core.util.StringUtil;

/**
 * 数据库操作工具
 * @author zzs
 *
 */
public class DataBaseUtil {
	
	/**
	  * 从数据库导出项目数据
	  * @param map
	  * @return
	  */
	 public static String expFile(HashMap<String, String> map) {
	     String commandBuf=""; 
		 String dmpFilepath=map.get("dmpFilepath");
		 String compress=""; if(!StringUtil.getString(map.get("compress")).equals("")){ compress="compress=Y";}; //是否压缩备份文件
		 String full="";if(!StringUtil.getString(map.get("full")).equals("")){ full="full=Y";}; //是全备
		 String owner=map.get("owner"); 
		 String sid=map.get("sid");
		 String userName=map.get("userName");
		 String password=map.get("password");
		 String logFilepath="";if(!StringUtil.getString(map.get("logFilepath")).equals("")){ logFilepath="log="+StringUtil.getString(map.get("logFilepath"))+"";}; //是全备
		 //String tables=map.get("tables");
		 //String proj_id=StringUtil.formatDbColumn(map.get("PROJ_ID"));
		 String[] cmds = new String[3];
		 //表导出语句  如果要导出多表则在 tables参数里添加 例如 tables=(KM_DOC,KM_FOLDER,……,……)
		 //String commandBuf = "exp pip_jk/pip@orcl file=E://"+filename+".dmp  tables=(KM_DOC,KM_FOLDER)  query=/"/"/" where PROJ_ID='"+proj_id+"'/"/"/"";
		 
		 //全备 
		 commandBuf = "exp "+userName+"/"+password+"@"+sid+" file="+dmpFilepath+" "+logFilepath+" owner="+owner+" "+compress+" "+full+" ";
		 
		 System.out.println("执行语句:"+commandBuf); 
		 cmds[0] = "cmd";
		 cmds[1] = "/C";
		 cmds[2] = commandBuf.toString();
		 Process process = null;
		 try {
		   process = Runtime.getRuntime().exec(cmds);
		 } catch (IOException e) {
		  
		   e.printStackTrace();
		 //info="导入出错";
		 }
		 boolean shouldClose = false;
		 String line = null;
		 try {
			 InputStreamReader isr = new InputStreamReader(process.getErrorStream());
			 BufferedReader br = new BufferedReader(isr);
			 
				 while ((line = br.readLine()) != null) {
				       //System.out.println(line);
					 if (line.indexOf("????") != -1) {
					     shouldClose = true;
					     break;
					 }
				 }
		 } catch (IOException ioe) {
		   shouldClose = true;
		   //info="导出出错";
		 }
		 if (shouldClose){
			 process.destroy();
		 }
		 int exitVal;
		 try {
			 exitVal = process.waitFor();
			 System.out.print(exitVal);
		 } catch (InterruptedException e) {
		     e.printStackTrace();
		     //info="导出出错";
		 }
		 return line;
	 }
	 
	 /**
	  * 导入数据库文件
	  * @param map
	  */
	 public static String impFile(HashMap<String, String> map) {
		 String info="";
		 String filepath=map.get("dmpFilepath");
		 String sid=map.get("sid");
		 String userName=map.get("userName");
		 String password=map.get("password");
		 String fromuser=map.get("fromuser");
		 String touser=map.get("touser");
		 String ignore="";if(!StringUtil.getString(ignore).equals("")){ ignore="ignore=y";}; //是全备
		 String[] cmds = new String[3];
		 String commandBuf = "imp "+userName+"/"+password+"@"+sid+" fromuser="+fromuser+" touser="+touser+" file="+filepath+" "+ignore+"";
		 cmds[0] = "cmd";
		 cmds[1] = "/C";
		 cmds[2] = commandBuf.toString();
		 Process process = null;
		 try {
		    process = Runtime.getRuntime().exec(cmds);
		 } catch (IOException e) {
		    e.printStackTrace();
		    info="导入出错";
		 }
	     boolean shouldClose = false;
		 String line = null;
		 try {
				 InputStreamReader isr = new InputStreamReader(process.getErrorStream());
				 BufferedReader br = new BufferedReader(isr);

			 while ((line = br.readLine()) != null) {
			     //System.out.println(line);
				 if (line.indexOf("????") != -1) {
				    shouldClose = true;
				    break;
			      }
		     }
		 } catch (IOException ioe) {
		     shouldClose = true;
		     info="导入出错";
		 }
		 if (shouldClose){
			 process.destroy(); 
		 }

	     int exitVal;
		 try {
			 exitVal = process.waitFor();
			 System.out.print(exitVal);
		 } catch (InterruptedException e) {
			 e.printStackTrace();
			 info="导入出错";
		 }
	   return info;
	 }

/**
 * 返回创建表空间语句  (autoextend on;next 10M;maxsize unlimited)
 * @param tablespaceName   表空间名
 * @param tablespacePath   表空间文件路径(D:\ORACLE11\ADMINISTRATOR\ORADATA\ORCL11\)
 * @return 创建表空间扩展无限制SQL语句
 */
public String getCreateTableSpaceForOracle(String tablespaceName,String filePath){

	String createTableSpaceStr="create tablespace "+tablespaceName+"\n" +
	"datafile '"+filePath+File.separator+tablespaceName.toUpperCase()+".DBF' size 50M\n" + 
	"autoextend on\n" + 
	"next 10M\n" + 
	"maxsize unlimited";
	
	return createTableSpaceStr;

}

/**
 * 创建用户
 * @param username         用户名
 * @param password         密码
 * @param defTablespace    默认使用表空间
 * @return  创建用户sql语句
 */
public String getCreateUserForDeftablespace(String username,String password,String defTablespace){
	
	String createUserForDeftablespace="create user "+username+"  identified by \""+password+"\"\n" +
                                      "default tablespace "+defTablespace+"\n" + 
                                      "  temporary tablespace TEMP\n" + 
                                      "  profile DEFAULT";

	return createUserForDeftablespace;
}

/**
 * 返回赋予用户dba权限语句
 * @param  username 用户名
 * @return grant dba to <username>;
 */
public String getGrantDbaForUser(String username){
	
	String grantDbaForUser="grant dba to "+username+"";
	return grantDbaForUser;
}

/**
 * 返回删除用户及相关数据语句
 * @param username
 * @return
 */
public String getDropUsernameAndData(String username){
	String dropUsernameAndDataStr="drop user "+username+" cascade";
	return dropUsernameAndDataStr;
}

}
