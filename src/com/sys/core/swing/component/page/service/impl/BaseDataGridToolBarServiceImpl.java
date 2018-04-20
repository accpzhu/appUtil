package com.sys.core.swing.component.page.service.impl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.sys.core.swing.component.page.service.DataGridToolBarService;

/**
 * dataGrid工具栏
 * @author Administrator
 */
public abstract class BaseDataGridToolBarServiceImpl implements DataGridToolBarService{

	@Override
	public ActionListener getAddButtListener() {
		
		ActionListener actionListener=new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				addButtAction();
			}
			
		};
		return actionListener;
	}

	@Override
	public ActionListener getUpdateButtListener() {
		ActionListener actionListener=new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				updateButtAction();
			}
		};
		return actionListener;
	}

	@Override
	public ActionListener getDelButtListener() {		
		ActionListener actionListener=new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				delButtAction();
			}
		};
		return actionListener;
	}

	@Override
	public ActionListener getQueryButtListener() {
		ActionListener actionListener=new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				queryButtAction();
			}
		};
		return actionListener;
	}
	
	/**
	 * 添加按钮事件实现
	 */
	public void addButtAction() {
		
	}
	/**
	 * 更新按钮事件实现
	 */
	public void updateButtAction(){
		
	}
	/**
	 * 删除按钮事件实现
	 */
	public void delButtAction(){
		
	}
	/**
	 * 查询按钮事件实现
	 */
	public void queryButtAction(){
		
	}
}
