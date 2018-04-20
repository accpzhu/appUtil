package com.sys.core.swing.component.comboBox;

/**
 * 用于初始化JComboBox对象
 * @author Administrator
 *
 */
public class ComboBoxItem {
	
	public ComboBoxItem(String id,String showText) {
	
		this.id=id;
		this.showText=showText;
	}

	private  String id;
	private String showText;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getShowText() {
		return showText;
	}

	public void setShowText(String showText) {
		this.showText = showText;
	}
	
	public String toString()
    {
        return showText;
    }
}
