package com.sys.core.rcp.model;

import com.sys.core.model.BaseModel;

public class TableColumnStyle extends BaseModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6707479955648199042L;
	private String colText;
	private Integer width=100;
	private Boolean moveable=false;
	private Integer alignment=16777216;
	private Boolean resizable=true;
	private String  toolTipText;
	private String  resultStr;
	


	/**
	 * @param colText
	 * @param width
	 * @param moveable
	 * @param alignment
	 * @param resizable
	 * @param toolTipText
	 */
	public TableColumnStyle(String colText,
							Integer width,
							Boolean moveable,
							Integer alignment,
							Boolean resizable,
							String  toolTipText,
							String  resultStr){
		this.colText=colText;
		this.width=width;
		this.moveable=moveable;
		this.alignment=alignment;
		this.resizable=resizable;
		this.toolTipText=toolTipText;
		this.resultStr=resultStr;
	}
	
	/**
	 * @param colText
	 * @param width
	 */
	public TableColumnStyle(String colText,Integer width,String  resultStr){
		this.colText=colText;
		this.width=width;
		this.resultStr=resultStr;
	}
	
	public TableColumnStyle(){
		
		
	}
	
	public String getResultStr() {
		return resultStr;
	}

	public void setResultStr(String resultStr) {
		this.resultStr = resultStr;
	}
	
	public String getColText() {
		return colText;
	}
	public void setColText(String colText) {
		this.colText = colText;
	}
	public Integer getWidth() {
		return width;
	}
	public void setWidth(Integer width) {
		this.width = width;
	}
	public Boolean getMoveable() {
		return moveable;
	}
	public void setMoveable(Boolean moveable) {
		this.moveable = moveable;
	}
	public Integer getAlignment() {
		return alignment;
	}
	public void setAlignment(Integer alignment) {
		this.alignment = alignment;
	}
	public Boolean getResizable() {
		return resizable;
	}
	public void setResizable(Boolean resizable) {
		this.resizable = resizable;
	}
	public String getToolTipText() {
		return toolTipText;
	}
	public void setToolTipText(String toolTipText) {
		this.toolTipText = toolTipText;
	}
	
}
