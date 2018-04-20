package com.sys.core.util;

import com.sys.core.util.bean.PropertyUtil;
import java.io.File;
/**
 * url过滤
 * @author zzs
 *
 */
public class UrlOrStrFilter {

	/**
	 * @param 过滤字符串
	 * @param 间隔字符
	 * @param 返回数组下标
	 * @return
	 */
	public static String getFilterStr(String source,String filterStr,Integer index){
		
		String str[]=source.split(filterStr);
		
		return str[index.intValue()];
	}
	/**
	 * @param source 过滤字符串
	 * @param filterStr 间隔字符
	 * @return
	 */
	public static String[] getFilterStr(String source,String filterStr){
		
		String str[]=source.split(filterStr);
		
		return str;
	}

	/**
	 * @param source 需要处理的字符串数组
	 * @param index 获取下标
	 * @return
	 */
	public static String getFilterStr(String source[],Integer index){
	
		return source[index.intValue()];
	}
	
	/**
	 * 返回最后截取字符串(获取url文件名)
	 * @return
	 */
	public static String getLastFilterStr(String source,String filterStr){
		
		if (PropertyUtil.getString(source)!=""){
			
			String str[]=source.split(filterStr);
		
			return str[str.length-1];
		}
		
        return "";
	}
	
	/**
	 * 注文件名目录一定要用'/'如下面注释
	 * E:/zzs/javaWorkSpace/workSpace1/exportExcel/image/image/dhlbags/source_img/
	 * 返回最后截取字符串(获取url文件名)
	 * @return
	 */
	public static String getFilePathFilterStr(String source,String filterStr){
		
		if (PropertyUtil.getString(source)!=""){
			
			String str[]=source.split(filterStr);
		
			return File.separator+str[str.length-2]+File.separator+str[str.length-1];
		}
		
        return "";
	}
	
	
	public static void main(String args[]){
		
		String temp="E:\\zzs\\javaWorkSpace\\workSpace1\\exportExcel\\image\\image\\dhlbags\\source_img\\x_20101102034317602.jpg";
		
		System.out.println(UrlOrStrFilter.getFilePathFilterStr(temp.replace("\\", "/"), "/"));
		
	}
	
}
