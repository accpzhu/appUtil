package com.sys.core.util.jdbc.bean;

import com.sys.core.model.BaseModel;

/**
 * 数据库字段model
 * @author Administrator
 *
 */
public class DbColumn extends BaseModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2277248477788296333L;
	private Integer columnId;
	private String columnName;
	private String columnShowName;
	private String columnTypeName;
	private String columnTypeCode;
	private Integer columnLength;
	private Integer tableId;
	private Integer widgetCode;
	 
	
	

	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public Integer getColumnId() {
		return columnId;
	}
	public void setColumnId(Integer columnId) {
		this.columnId = columnId;
	}
	public String getColumnShowName() {
		return columnShowName;
	}
	public void setColumnShowName(String columnShowName) {
		this.columnShowName = columnShowName;
	}
	public String getColumnTypeName() {
		return columnTypeName;
	}
	public void setColumnTypeName(String columnTypeName) {
		this.columnTypeName = columnTypeName;
	}
	public String getColumnTypeCode() {
		return columnTypeCode;
	}
	public void setColumnTypeCode(String columnTypeCode) {
		this.columnTypeCode = columnTypeCode;
	}
	public Integer getColumnLength() {
		return columnLength;
	}
	public void setColumnLength(Integer columnLength) {
		this.columnLength = columnLength;
	}
	public Integer getTableId() {
		return tableId;
	}
	public void setTableId(Integer tableId) {
		this.tableId = tableId;
	}
	public Integer getWidgetCode() {
		return widgetCode;
	}
	public void setWidgetCode(Integer widgetCode) {
		this.widgetCode = widgetCode;
	}

}
