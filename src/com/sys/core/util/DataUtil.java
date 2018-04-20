package com.sys.core.util;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.sys.core.model.MyDynaBean;
public class DataUtil {

	/**
	 * 确认数据是否是数字
	 * @param s
	 * @return
	 */
	public static boolean isNum(String s) {
		
        Pattern pattern = Pattern.compile("\\d*");
        Matcher isNum = pattern.matcher(s);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }
	
	/**
	 * 判断数据内是否有中文
	 * @param s
	 * @return
	 */
	public static boolean isChinaStr(String s) {
		
		 int count = 0; 
		  String regEx = "[\\u4e00-\\u9fa5]"; 
		  Pattern p = Pattern.compile(regEx); 
		  Matcher m = p.matcher(s); 
		  while (m.find()) { 
		   for (int i = 0; i <= m.groupCount(); i++) { 
		    count = count + 1; 
		   } 
		  } 

		  if(count>0){
			  
			  return true;  
		  }
        return false;
    }


	
	/**
	 * 确认数据是否是float类型
	 * @param s
	 * @return
	 */
	public static boolean isFloat(String s) {
		
      try{
    	  Float.parseFloat(s);
    	  
      }catch(Exception e){
    	  
    	  return false;
      }

        return true;
    }
	
    /**
     * 获取文章行数
     * @param str
     * @return
     * @throws IOException 
     */
    public static Integer getStringLineSize(String str) throws IOException{
    	
    	int line = 0;  
    	
    	String tempString="";
    	
    	BufferedReader reader = new BufferedReader(new StringReader(str));
    	
    	while (((tempString = reader.readLine()) != null)){  
    		//显示行号   
    		++line;  
        }  

    	return line;
    }
	

    /**
     * 获取文章str下num行信息 (从0开始)
     * @param str
     * @param num
     * @return
     */
    public static String getStringLine(String str,Integer num) throws IOException{
    	
    	int line = 0;  
    	
    	String tempString="";
    	
    	BufferedReader reader = new BufferedReader(new StringReader(str));
    	
    	while (((tempString = reader.readLine()) != null)){  
    		//显示行号   
    		line++;  
    		
    		if(line==num){
    			
    			return tempString;
    		}
    		
        }  
    	
    	return "";
    }
    
    public static Vector<Vector> getVectorInVectorsForList(List list ){
    	
    	Vector v = new Vector();
    	
    	if(list!=null){
        	for(int i=0;i<list.size();i++)
        	{
        		if(list.get(i).getClass().toString().contains("MyDynaBean")){	
        			v.add(((MyDynaBean)list.get(i)).getVectors());
        		}else
        	    v.add(list.get(i));
        	} 
    	}
    	return  v;
    }
    
    /**
     * String转换成integer,如果异常则返回defaultValue
     * @param str要转换的值,defaultValue默认值
     * @return Integer
     */
    public static Integer getIntForDefaultVal(String str,Integer defaultValue){
    	try{
    		return Integer.parseInt(str);
    	}catch(java.lang.NumberFormatException e){
    		return defaultValue;
    	}
	 
    }
    
    
    public static Vector<Vector> getVectorForList(List list ){
    	
    	Vector v = new Vector();
    	
    	if(list!=null){
        	for(int i=0;i<list.size();i++)
        	{
        	    v.add(list.get(i));
        	} 
    	}
    	return  v;
    }
    
    public static void main(String args[]){
    	
    	System.out.println(DataUtil.getIntForDefaultVal("",0));
    	
    }

}
