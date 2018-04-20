package com.sys.core.swing.component.dialog.bean;

/**
 * PropertiesEdit(属性编辑界面)内对象一行数据
 * @author Administrator
 *
 */
public class PropertiesEditBean implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8469861713976322816L;
	private String colName;
	private String showLable;
	private Object value;
	private String valueType;

	
	public String getColName() {
		return colName;
	}
	public void setColName(String colName) {
		this.colName = colName;
	}
	public String getShowLable() {
		return showLable;
	}
	public void setShowLable(String showLable) {
		this.showLable = showLable;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	public String getValueType() {
		return valueType;
	}
	public void setValueType(String valueType) {
		this.valueType = valueType;
	}
}
