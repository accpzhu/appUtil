package com.sys.core.util;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;

public abstract class StringUtil {
	
	/**
	 * 将object 转为 string value并去空格
	 * 若object为null返回空字串
	 * 
	 * @param value
	 * @return
	 */
	public static String getString(Object value){
		if(value == null){
			return "";
		}
		return String.valueOf(value).trim();
	}
	
	/**
	 * 将字符串转为整形值
	 * 
	 * @param value
	 * @return
	 */
	public static int parseStringToInt(String value){
		try{
			if(value == null || value.trim().equals("")){
				return 0;
			}
			return Integer.parseInt(value);
		}catch(Exception ex){
			return 0;
		}
	}
	
	/**
	 * 将字符串转为BigDecimal
	 * @return
	 */
	public static BigDecimal parseStringToBigDecimal(String value){
		try{
			if(value == null || value.trim().equals("")){
				return new BigDecimal(0);
			}
			return new BigDecimal(value.trim());
		}catch(Exception ex){
			return new BigDecimal(0);
		}
	}
	
	/**
	 * 
	 * 转换字符编码
	 * @param sourse
	 * @param character
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String getStringEncoding(String sourse,String encoding) throws UnsupportedEncodingException{
		
		
		return new String(sourse.getBytes(),encoding);
		
	}
	
	/**
	 * 去除英文数字及其他特殊字符
	 * @param sourse
	 * @return
	 */
	public static String getMatchesChinaStr(String sourse){
		 String regex = "^[\u4e00-\u9fa5]*$";//
		 
		 String str=sourse;
		 
		 str.replaceAll("\\w+", "");
		 
		 if(!str.matches(regex)){
		       int length = str.length();
		       for(int i = 0;i < length;i++){
		        String temp = String.valueOf(str.charAt(i));
		        if(!temp.matches(regex)){
		         str = str.replace(temp, "");
		         i = i - 1;
		         length = str.length();
		        }
		      }
		    }
		
		return str;
	} 
	
	/**
	 * 追加字符串
	 * @param source  要追加的字符串
	 * @param str     追加的字符
	 * @param separate 分隔符
	 * @return    source+separate+str
	 */
	public static String appendNotNullString(String source,String str,String separate){
		
		StringBuffer sb=new StringBuffer(source);
		
		if(str!=null && !StringUtil.getString(str).equals("")){
			sb.append(str);
			sb.append(separate);
		}

		return sb.toString();
		
	}
	
	
	public static void main(String args[]){
		
		String tempStr="";
		
		tempStr=StringUtil.appendNotNullString(tempStr,"aaaa", ";");
		tempStr=StringUtil.appendNotNullString(tempStr,"bbbb", ";");
		
		System.out.println(tempStr);
		
	}
	
}
