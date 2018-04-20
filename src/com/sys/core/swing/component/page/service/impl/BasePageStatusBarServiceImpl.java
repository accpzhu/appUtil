package com.sys.core.swing.component.page.service.impl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Map;

import com.sys.core.swing.component.page.DataGridForPage;
import com.sys.core.swing.component.page.service.PageStatusBarService;
import com.sys.core.swing.component.progressBar.JProgressBarUtil;

/**
 * 跳转页面基础实现
 * @author Administrator
 *
 */
public abstract  class BasePageStatusBarServiceImpl implements PageStatusBarService{

    private DataGridForPage dataGridForPage;
	
	public DataGridForPage getDataGridForPage() {
		return dataGridForPage;
	}

	public void setDataGridForPage(DataGridForPage dataGridForPage) {
		this.dataGridForPage = dataGridForPage;
	}
	
	@Override
	public ItemListener getPageSizeComboBoxListener() {
		
		ItemListener itemListener=new ItemListener() { 
        	@Override
	       	public void itemStateChanged(ItemEvent e){ 
        		dataGridForPage.showProgressBar();
        		gotoPage(dataGridForPage.getPageStatusBarData().getCurrentPageNum(),
        				 dataGridForPage.getPageStatusBarData().getPageSize());
        		dataGridForPage.hideProgressBar();
	       	}
	     };
		
		return itemListener;
	}

	@Override
	public ActionListener getFirstPageButtListener() {
		
		ActionListener actionListener=new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				dataGridForPage.showProgressBar();
        		gotoPage(1,
        				dataGridForPage.getPageStatusBarData().getPageSize());
        		dataGridForPage.hideProgressBar();
			}
			
		};
		
		return actionListener;
	}

	@Override
	public ActionListener getPreviousPageButtListener() {
		ActionListener actionListener=new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				dataGridForPage.showProgressBar();
        		gotoPage(getPageNum(dataGridForPage.getPageStatusBarData().getCurrentPageNum()-1),
        				 dataGridForPage.getPageStatusBarData().getPageSize());
        		dataGridForPage.hideProgressBar();
			}
			
		};
		return actionListener;
	}

	@Override
	public ActionListener getNextPageButtListener() {
		ActionListener actionListener=new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				dataGridForPage.showProgressBar();
        		gotoPage(getPageNum(dataGridForPage.getPageStatusBarData().getCurrentPageNum()+1),
        				 dataGridForPage.getPageStatusBarData().getPageSize());
        		dataGridForPage.hideProgressBar();
			}
			
		};
		return actionListener;
	}

	@Override
	public ActionListener getEndPageButtListener() {
		
		ActionListener actionListener=new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				dataGridForPage.showProgressBar();
        		gotoPage(dataGridForPage.getPageStatusBarData().getSummaryPage(),
        				 dataGridForPage.getPageStatusBarData().getPageSize());
        		dataGridForPage.hideProgressBar();
			}
			
		};
		return actionListener;
	}

	@Override
	public ActionListener getRefreshButtListener() {
		ActionListener actionListener=new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				dataGridForPage.showProgressBar();
        		gotoPage(dataGridForPage.getPageStatusBarData().getCurrentPageNum(),
        				 dataGridForPage.getPageStatusBarData().getPageSize());
        		dataGridForPage.hideProgressBar();
			}
			
		};
		return actionListener;
	}
	
	/**
	 * 刷新数据
	 */
	public void refreshData() {
		dataGridForPage.showProgressBar();
		gotoPage(dataGridForPage.getPageStatusBarData().getCurrentPageNum(),
				 dataGridForPage.getPageStatusBarData().getPageSize());
		dataGridForPage.hideProgressBar();
	}
	
	/**
	 * 页面跳转
	 * @param pageNum
	 * @param pageSize
	 */
	public void gotoPage(Integer pageNum,Integer pageSize){
		

	}
	
	public Integer getPageNum(Integer pageNum){
		if(pageNum<1){
			return 1;
		}
		if(pageNum>dataGridForPage.getPageStatusBarData().getSummaryPage()){
			return dataGridForPage.getPageStatusBarData().getSummaryPage();
		}
		return pageNum;
	}

	/**
	 * 页面跳转
	 * @param pageNum          页数
	 * @param pageSize         页面大小
	 * @param selectCountStr   数据统计查询语句
	 * @param selectStr        查询语句
	 */
//	public void gotoPage(Integer pageNum,Integer pageSize, String selectCountStr,String selectStr){
//		
//
//	}
	
	
}
