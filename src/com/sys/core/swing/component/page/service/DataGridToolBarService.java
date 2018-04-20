package com.sys.core.swing.component.page.service;

import java.awt.event.ActionListener;


/**
 * dataGrid工具栏事件
 * @author Administrator
 */
public interface DataGridToolBarService {

	/**
	 * 添加数据按钮事件
	 * @return
	 */
	public ActionListener getAddButtListener();
	/**
	 * 更新数据按钮事件
	 * @return
	 */
	public ActionListener getUpdateButtListener();
	/**
	 * 删除按钮事件
	 * @return
	 */
	public ActionListener getDelButtListener();
	/**
	 * 查询按钮事件
	 * @return
	 */
	public ActionListener getQueryButtListener();
}
