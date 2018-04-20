package com.sys.core.util.jdbc;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import com.sys.core.model.MyDynaBean;
import com.sys.core.util.StringUtil;

public class OracleParameterQuery {

	/**
	 * 	数据库当前连接数
	 *  select count(1) from v$session;
	 * @param dbconn
	 * @param sqlStr
	 * @return
	 * @throws Exception
	 */
	public static String getOracleSessionNum(DbConnect dbconn) throws Exception{
		
		return dbconn.getStringForSql("select count(1) from v$session");
	}
	
	/**
	 * 查询oracle的并发连接数
	 * select count(1) from v$session where status='ACTIVE';
	 * @param dbconn
	 * @param sqlStr
	 * @return
	 * @throws Exception
	 */
	public static String getOracleSessionActive(DbConnect dbconn) throws Exception{
		
		return dbconn.getStringForSql("select count(1) from v$session where status='ACTIVE'");
	}
	
	/**
	 * 查询oracle的休眠连接数
	 * select count(1) from v$session where status='INACTIVE';
	 * @param dbconn
	 * @param sqlStr
	 * @return
	 * @throws Exception
	 */
	public static String getOracleSessionInActive(DbConnect dbconn) throws Exception{
		
		return dbconn.getStringForSql("select count(1) from v$session where status='INACTIVE'");
	}
	
	/**
	 * 数据库允许的最大连接数
	 *  
	 * @param dbconn
	 * @param sqlStr
	 * @return
	 * @throws Exception
	 */
	public static String getOracleMaxConn(DbConnect dbconn) throws Exception{
		
		return dbconn.getStringForSql("select value from v$parameter where name = 'processes'");
	}

	/**
	 * 数据库版本
	 * select banner from v$version where rownum=1;  
	 * @param dbconn
	 * @param sqlStr
	 * @return
	 * @throws Exception
	 */
	public static String getOracleVersion(DbConnect dbconn)throws Exception{
		
		return dbconn.getStringForSql("select banner from v$version where rownum=1");
	}

	/**
	 * oracle运行时间
	 * @param dbconn
	 * @return
	 * @throws Exception
	 */
	public static String getOracleUptime(DbConnect dbconn)throws Exception{
		
		return dbconn.getStringForSql("select\n" +
										"TRUNC(sysdate - (startup_time))||'day(s),'||TRUNC(24*((sysdate-startup_time) "
										+ "-TRUNC(sysdate-startup_time)))\n" + 
										"||'hour(s),'||MOD(TRUNC(1440*((SYSDATE-startup_time)-\n" + 
										"TRUNC(sysdate-startup_time))),60)\n" + 
										"||'minutes(s),'||MOD(TRUNC(86400*((SYSDATE-STARTUP_TIME)-\n" + 
										"TRUNC(SYSDATE-startup_time))),60)\n" + 
										"\t||'seconds' uptime\n" + 
										"\tfrom v$instance");
	}

	/**
	 * 查看数据库是否处于归档模式
	 * @param dbconn
	 * @return
	 * @throws Exception
	 */
	public static String getOracleLogMode(DbConnect dbconn)throws Exception{
		
		return dbconn.getStringForSql("SELECT decode(log_mode,'ARCHIVELOG','true','false')  FROM v$database");
	}

	/**
	 * 闪回使用情况
	 * @param dbconn
	 * @return
	 * @throws Exception
	 */
	public static String getOracleFlashState(DbConnect dbconn)throws Exception{
		
		return dbconn.getStringForSql(
				"select round(((SPACE_USED/SPACE_LIMIT)*100),1)||'%' \"usage\" from v$recovery_file_dest");
	}
	
	/**
	 * 空表是否允许导出
	 * @param dbconn
	 * @return
	 * @throws Exception
	 */
	public static String getOracleEmptyTabExp(DbConnect dbconn)throws Exception{
		
		return dbconn.getStringForSql(
				"select decode(value,'FALSE','true','false') "
				+ "from v$parameter where name = 'deferred_segment_creation'");
	}

	/**
	 * 允许打开游标数
	 * @param dbconn
	 * @return
	 * @throws Exception
	 */
	public static String getOracleOpen_cursors(DbConnect dbconn)throws Exception{
		
		return dbconn.getStringForSql("select value from v$parameter where name like '%open_cursors%'");
	}

	/**
	 * 11g密码过期问题是否解决
	 * @param dbconn
	 * @return
	 * @throws Exception
	 */
	public static String getOraclePasswordUnlimmited(DbConnect dbconn)throws Exception{
		
		return dbconn.getStringForSql(
										"select decode(count(1), 1, 'true', 'fasle')\n" +
										"  from dba_profiles\n" + 
										" where profile = 'DEFAULT'\n" + 
										"   and resource_name = 'PASSWORD_LIFE_TIME'\n" + 
										"   and limit = 'UNLIMITED'");
	}
	
	/**
	 * 查询用户默认表空间语句
	 * @param dbconn
	 * @param username
	 * @return
	 * @throws Exception
	 */
	public static String getOracleUsedTableSpaceForUser(DbConnect dbconn,String username)throws Exception{
		
		return dbconn.getStringForSql(getOracleUsedTableSpaceForUser(username));
	}
	
	/**
	 * 返回oracle数据库username用户所使用表空间查询语句
	 * @param username
	 * @return
	 */
	public static String getOracleUsedTableSpaceForUser(String username){
		
		return 
				"select DEFAULT_TABLESPACE from dba_users where username=upper('bi_gxqdc_dev')";

	}
	
	/**
	 * 返回oracle数据库username用户默认表空间查询语句
	 * @param username
	 * @return
	 */
	public static String getOracleDefTableSpaceForUser(String username){
		
		return "SELECT tablespace_name\n" +
				"  FROM dba_segments\n" + 
				" where lower(OWNER) = '"+username+"'\n" + 
				" GROUP BY owner, tablespace_name";
	}
	
	/**
	 * 返回创建用户表空间语句
	 * 表空间初始大小:50M,自动扩展,下次扩展10M,文件扩展最大不限制(有oracle版本及操作系统决定)
	 * @param username
	 * @return
	 */
	public static String getOracleSampleCreateTableSpace(String tableSpaceName, String pathAndFileName){
		
		return "-----创建表空间tableSpaceName----\n"
				+ "create tablespace "+tableSpaceName+"\n" +
				"datafile '"+pathAndFileName+"' size 50M\n" + 
				"autoextend on\n" + 
				"next 10M\n" + 
				"maxsize unlimited;\n";
	}
	
	/**
	 * 创建多的数据文件的表空间语句
	 * 表空间初始大小:50M,自动扩展,下次扩展10M,文件扩展最大不限制(有oracle版本及操作系统决定)
	 * @param username
	 * @return
	 */
	public static String getOracleAddDataFileCreateTableSpace(String tableSpaceName,String dataFiles){
		
		String headStr="-----创建表空间tableSpaceName----\n" +
				"create tablespace "+tableSpaceName+"\n";
				       //"datafile '"+pathAndFileName+"' size 50M\n";
		String addDataFileStr="";
		String [] dataFilesA=dataFiles.split(";");
		for(int i=0;i<dataFilesA.length;i++){
			
			addDataFileStr+="datafile '"+dataFilesA[i]+"' size 50M\n";
		}
		
		String endStr="autoextend on\n" + 
						"next 10M\n" + 
						"maxsize unlimited;\n\n";

		return   headStr+addDataFileStr+endStr;
	}
	
	
	/**
	 * 根据数据库链接及用户名返回该用户数据使用的创建表空间语句
	 * @param dbConnect
	 * @param username
	 * @return
	 * @throws Exception 
	 */
	public static String getOracleCreateTableSpaceForUser(DbConnect dbConnect,String username) throws Exception{
		
		List <MyDynaBean>list=dbConnect.getListForSql(getTableSpaceForUsernameUsed(username));
		String createTableSpaceStr="";
		List <String>dataFilesRepetitionList=new ArrayList();//多数据文件表空间名

		for(int i=0;i<list.size();i++){//将多数据文件表空间名放到dataFilesRepetitionList中
			
			MyDynaBean myDynaBean=list.get(i);
			String tablespaceName=StringUtil.getString(myDynaBean.getValue("TABLESPACE_NAME"));
			
			for(int j=i+1;j<list.size();j++){
				MyDynaBean myDynaBeanj=list.get(j);
				String tablespaceNamej=StringUtil.getString(myDynaBeanj.getValue("TABLESPACE_NAME"));
				
				if(tablespaceNamej.equals(tablespaceName)){
					if(!dataFilesRepetitionList.contains(tablespaceName)){
						dataFilesRepetitionList.add(tablespaceName);
					}
				}
			}
		}

		for(String str:dataFilesRepetitionList){//生成多数据库文件创建表空间语句
			String dataFiles="";
			String tablespaceName="";
			for(MyDynaBean myDynaBeanTemp:list){
				 tablespaceName=StringUtil.getString(myDynaBeanTemp.getValue("TABLESPACE_NAME"));
				if(str.equals(tablespaceName)){
					String fileName=StringUtil.getString(myDynaBeanTemp.getValue("FILE_NAME"));
					dataFiles=dataFiles+fileName+";";
				}
			}
			createTableSpaceStr+=getOracleAddDataFileCreateTableSpace(str,dataFiles);
		}
		
		//生成单个数据文件的创建表空间语句
			for(String delName:dataFilesRepetitionList){//剔除多数据文件表空间数据，用于下一步便利生成单数据文件表空间语句

				for(Iterator<MyDynaBean> it = list.iterator(); it.hasNext();){
					
					MyDynaBean myDynaBeanii = (MyDynaBean)it.next(); 
					 String tablespaceNameii=StringUtil.getString(myDynaBeanii.getValue("TABLESPACE_NAME"));
					 String fileNameii=StringUtil.getString(myDynaBeanii.getValue("FILE_NAME"));
				if(delName.equals(tablespaceNameii)){	
					it.remove();
				}
			}
		}

		for(MyDynaBean myDynaBeanTemp:list){//生成单数据文件创建表空间语句
			
			createTableSpaceStr+=getOracleSampleCreateTableSpace
					(StringUtil.getString(myDynaBeanTemp.getValue("TABLESPACE_NAME")),
					 StringUtil.getString(myDynaBeanTemp.getValue("FILE_NAME")));
		}

		return createTableSpaceStr;
	}
	
	/**
	 * 查询用户下使用的表空间语句
	 * @param username
	 * @return
	 */
	public static String getTableSpaceForUsernameUsed(String username){
		
		return 
				"SELECT distinct a.tablespace_name, b.FILE_NAME\n" +
				"  FROM dba_segments a\n" + 
				" inner join dba_data_files b\n" + 
				"    on a.tablespace_name = b.TABLESPACE_NAME\n" + 
				"   and lower(a.OWNER) = '"+username+"'";

	}

	/**
	 * 创建用户
	 * @param username  用户名
	 * @param password  密码
	 * @param defTablespace 默认表空间
	 * @return
	 */
	public static String getOracleSampleCreateUser(String username,String password,String defTablespace){
		
		return 
				"create user "+username+"  identified by \""+password+"\"\n" +
				"default tablespace "+defTablespace+"\n" + 
				"  temporary tablespace TEMP\n" + 
				"  profile DEFAULT";
	}
	
	/**
	 * 赋予dba权限至用户
	 * @param username
	 * @return
	 */
	public static String getOracleSampleGrantUserForDba(String username){
		
		return "grant dba to "+username+"";
	}
	
	/**
	 * 删除用户及数据
	 * @param username
	 * @return
	 */
	public static String getOracleSampleDelUserForData(String username){
		
		return "drop user "+username+" cascade";
	}
	
	/**
	 * 获取oracle数据库备份命令
	 * @param username
	 * @param password
	 * @param ip
	 * @param port
	 * @param sid
	 * @param dmpPathAndFileName
	 * @param logPathAndFileName
	 * @return
	 */
	public static String getOracleSampleExp(String username,
			                                String password,
			                                String ip,
			                                String port,
			                                String sid,
			                                String dmpPathAndFileName,
			                                String logPathAndFileName){
		
		return    "exp "+username+"/"+password+"@"+ip+":"+port+"/"+sid+" "
				+ "direct=y  recordlength=65535 buffer=40960000 file="+dmpPathAndFileName+" "
				+ "log="+logPathAndFileName+"";
	}
	
	/**
	 * 获取oracle数据库备份恢复命令
	 * @param username
	 * @param password
	 * @param ip
	 * @param port
	 * @param sid
	 * @param dmpPathAndFileName
	 * @param fromuser
	 * @param touser
	 * @param logPathAndFileName
	 * @return
	 */
	public static String getOracleSampleImp(String username,
								            String password,
								            String ip,
								            String port,
								            String sid,
								            String dmpPathAndFileName,
								            String fromuser,
								            String touser,
								            String logPathAndFileName){
		
		return    "imp "+username+"/"+password+"@"+ip+":"+port+"/"+sid+"  "
				+ "file="+dmpPathAndFileName+"  fromuser="+fromuser+" "
				+ "touser="+touser+"  "+ "log="+logPathAndFileName+" ignore=y";
	}
	
	public static void main(String args[]){
		
		OracleParameterQuery OracleParameterQuery=new OracleParameterQuery();
		DbConnect dbConnect=new DbConnect();
		//String url=dbConnect.getOracleJdbcUrl("192.168.1.202", "1521","orcl");
		dbConnect.setJdbcUrl("jdbc:oracle:thin:@192.168.1.202:1521:orcl");
		dbConnect.setDriverClass(dbConnect.getOracleDriverClassName());
		dbConnect.setUser("drp_yqoc_test");
		dbConnect.setPassword("111111");

		try {
			System.out.println(OracleParameterQuery.getOracleCreateTableSpaceForUser(dbConnect,"drp_yqoc_test"));
		} catch (Exception e) {
			
			e.printStackTrace();
		}

	}

}
