package com.sys.core.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ReadExcelUtil {
	
	private Workbook book;
	
	/**
	 * 初始化Workbook
	 * @param path
	 * @throws Exception
	 */
	public void initWorkbook (String path) throws Exception{
		
		InputStream is = new FileInputStream(path);   
		
		book = Workbook.getWorkbook(is);  
		
	}
	
	/**
	 * 关闭Workbook
	 */
	public void closeWorkbook(){
		
		if(book!=null){
			
			book.close();
		}
		
	}

}
