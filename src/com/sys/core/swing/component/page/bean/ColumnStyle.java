package com.sys.core.swing.component.page.bean;

import java.io.Serializable;

public class ColumnStyle implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 3724673513203635924L;
	
	private int columnInd;
	private int width;
	public ColumnStyle(){
		
	}
	
	/**
	 * @param columnInd  列index
	 * @param width      列宽
	 */
	public ColumnStyle(int columnInd,int width){
		this.columnInd=columnInd;
		this.width=width;
	}
	
	public int getColumnInd() {
		return columnInd;
	}
	public void setColumnInd(int columnInd) {
		this.columnInd = columnInd;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
}
