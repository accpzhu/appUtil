package com.sys.core.swing.component.page.service;

import java.awt.event.MouseListener;

import javax.swing.JPopupMenu;

public interface DataGridMouseService {

	/**
	 * 左键双击表格事件
	 * @return
	 */
	public MouseListener getDoubleSelectButtListener();
	
	/**
	 * 右键点击事件
	 * @return
	 */
	public MouseListener getMouseRightClickedButtListener();
	
}
