package com.sys.core.swing.component.page.service.impl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPopupMenu;

import com.sys.core.swing.component.page.DataGridForPage;
import com.sys.core.swing.component.page.service.DataGridMouseService;
import com.sys.core.swing.component.page.service.DataGridToolBarService;
import com.sys.core.swing.component.page.service.PageStatusBarService;

/**
 * @author Administrator
 *
 */
public abstract class SampleDataGridServiceImpl implements DataGridMouseService, PageStatusBarService,DataGridToolBarService{

    private DataGridForPage dataGridForPage;
	private JPopupMenu jPopupMenu;
	
	/**
	 * @param jPopupMenu
	 */
	public void setjPopupMenu(JPopupMenu jPopupMenu) {
		this.jPopupMenu = jPopupMenu;
	}

	public DataGridForPage getDataGridForPage() {
		return dataGridForPage;
	}

	public void setDataGridForPage(DataGridForPage dataGridForPage) {
		this.dataGridForPage = dataGridForPage;
	}
	
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
	
	/**
	 * 页面跳转
	 * @param pageNum
	 * @param pageSize
	 */
	public void gotoPage(Integer pageNum,Integer pageSize){
		

	}
	
	@Override
	public MouseListener getDoubleSelectButtListener() {
		
		MouseListener mouseListener=new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {   
				
				 if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
					 doubleClick();
                 }
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			
				
			}
		};
		
		return mouseListener;
	}
	
	
	/**
	 * 返回右键菜单时间
	 * @return
	 */
	@Override
	public MouseListener getMouseRightClickedButtListener() {
		MouseListener mouseListener=new MouseListener(){
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton()==MouseEvent.BUTTON3) {//弹出右键菜单
					if(jPopupMenu!=null){
						jPopupMenu.show(e.getComponent(), e.getX(), e.getY());
					}
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				
				
			}
			@Override
			public void mousePressed(MouseEvent e) {
				
				
			}
			@Override
			public void mouseReleased(MouseEvent e) {
			
				
			}
		};
		return mouseListener;
	}
	
	public void doubleClick(){
		
	}
}
