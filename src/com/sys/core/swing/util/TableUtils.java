package com.sys.core.swing.util;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 * JTable工具类
 * @author Administrator
 *
 */
public class TableUtils {
	
	/** 
	 * 隐藏表格中的某一列 
	 * @param table  表格 
	 * @param index  要隐藏的列 的索引
	 */ 
	public static void hideColumn(JTable table,int index){ 
		//隐藏列
		if(table.getColumnModel().getColumn(index)!=null){
		    TableColumn tc= table.getColumnModel().getColumn(index); 
		    tc.setMaxWidth(0); 
		    tc.setPreferredWidth(0); 
		    tc.setMinWidth(0); 
		    tc.setWidth(0); 
		}
        //隐藏标题
		if(table.getTableHeader()!=null){
		    table.getTableHeader().getColumnModel().getColumn(index).setMaxWidth(0); 
		    table.getTableHeader().getColumnModel().getColumn(index).setMinWidth(0); 
		}
	}
	
	/** 
	 * 隐藏表格中的某一列 
	 * @param table  表格 
	 * @param index  要隐藏的列 的索引
	 */ 
	public static void setColumnWidth(JTable table,int index,Integer width){ 
		//隐藏列
		if(table.getColumnModel().getColumn(index)!=null){
		    TableColumn tc= table.getColumnModel().getColumn(index); 
//		    tc.setMaxWidth(0); 
//		    tc.setMinWidth(0); 
		    tc.setWidth(width); 
		    tc.setPreferredWidth(width); 
		}
  
	}
	
	/**
	 * 放回
	 * @return
	 */
	public static DefaultTableModel getPropertiesEditModelFor1RowNotEdit(){
		
		DefaultTableModel tableModel=new DefaultTableModel() {  
	        public boolean isCellEditable(int rowIndex, int columnIndex) {  
	            boolean f = (0 <= rowIndex && rowIndex < getRowCount() && columnIndex == 1) ? false : true;  
	            return f;  
	        }  
	    };  
	    return tableModel;
	}
	


}
