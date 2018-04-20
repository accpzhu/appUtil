package com.sys.core.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import java.io.File;
import jxl.write.Number;
import jxl.write.biff.RowsExceededException;

import java.util.List;
import java.util.ArrayList;
import java.io.OutputStream;
import jxl.Sheet;

public class WriteExcelUtil {
	
	private WritableWorkbook book;
	
	/**
	 * 创建新的excel并返回page页
	 * @param os
	 * @param sheetTitle
	 * @param page
	 * @return
	 * @throws IOException
	 */
	public  WritableSheet getNewBookSheet(OutputStream os,String sheetTitle,Integer page) throws IOException{
		
		this.book=   Workbook.createWorkbook(os);   
		
		return book.createSheet(sheetTitle,page);
	}
	
	/**
	 * 创建新的excel并返回page页
	 * @param os
	 * @param sheetTitle
	 * @param page
	 * @return
	 * @throws IOException
	 */
	public  WritableSheet getNewBookSheet(File file,String sheetTitle,Integer page) throws IOException{
		
		 this.book= Workbook.createWorkbook(file);   
			return book.createSheet(sheetTitle,page);
			
	}
	
	/**
	 * 根据sheet生成标题
	 * @param list
	 * @throws WriteException 
	 * @throws RowsExceededException 
	 */
	public void writeSheetTitle(List <String>list,WritableSheet sheet,WritableCellFormat wcfF) throws Exception{
		
		for(int i=0;i<list.size();i++){
			
			sheet.addCell(new Label(i, 0, list.get(i), wcfF));
		}	
		
	}
	/**
	 * @throws Exception 
	 * 
	 */
	public void writeClose(WritableWorkbook workBook) throws Exception{
		
		if(workBook != null){
			book.write();
			book.close();
		}

	}

	/**
	 * @throws Exception 
	 * 
	 */
	public void writeClose() throws Exception{
		
		if(this.book != null){
			book.write();
			book.close();
		}

	}
	
	/**
	 * @return
	 */
	public WritableWorkbook getBook() {
		return book;
	}
	

}