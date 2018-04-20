package com.sys.core.swing.component.page.service;

import java.awt.event.ActionListener;
import java.awt.event.ItemListener;

import com.sys.core.swing.component.page.DataGridForPage;

/**
 * 
 * 分页组件事件处理
 * @author Administrator
 *
 */
public interface PageStatusBarService {
	
	public void setDataGridForPage(DataGridForPage dataGridForPage);
	
	/**
	 * @return  PageSizeComboBox实现的选择事件
	 */
	public ItemListener getPageSizeComboBoxListener();
	
	/**
	 * @return 首页按钮事件
	 */
	public ActionListener getFirstPageButtListener();
	
	/**
	 * @return 上一页按钮事件
	 */
	public ActionListener getPreviousPageButtListener();
	
    /**
     * @return 下一页按钮事件
     */
    public ActionListener getNextPageButtListener();
    
    /**
     * @return 尾页按钮事件
     */
    public ActionListener getEndPageButtListener();
    
    /**
     * @return 刷新按钮事件
     */
    public ActionListener getRefreshButtListener();
}
