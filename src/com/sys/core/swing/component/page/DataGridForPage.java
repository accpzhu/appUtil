/**
 * 
 */
package com.sys.core.swing.component.page;

import org.jdesktop.swingx.JXPanel;
import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JToolBar;
import org.jdesktop.swingx.JXButton;
import org.jdesktop.swingx.JXTable;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.sys.core.swing.component.page.bean.ColumnStyle;
import com.sys.core.swing.component.page.bean.DataGridForPageBean;
import com.sys.core.swing.component.page.service.DataGridMouseService;
import com.sys.core.swing.component.page.service.DataGridToolBarService;
import com.sys.core.swing.component.page.service.PageStatusBarService;

/**
 * 
 * @author Administrator
 */
public class DataGridForPage extends JXPanel {
	
	private PageStatusBar pageStatusBar;
	private JToolBar toolBar;
	private JXButton addButt;
	private JXButton updataButt;
	private JXButton delButt;
	private JXButton queryButt;
	private JXTable table;
	private PageStatusBarService pageStatusBarService;
	private DataGridToolBarService dataGridToolBarService;
	private List<ColumnStyle> columnStyleList;
    private DataGridMouseService dataGridMouseService;





	public DataGridForPage() {
		initComponents();
	}
	
	/**
	 * 显示滚动条
	 */
	public void showProgressBar(){
		pageStatusBar.showProgressBar();
	}
	
	/**
	 * 隐藏滚动条
	 */
	public void hideProgressBar(){
		
		pageStatusBar.hideProgressBar();
	}
	
	/**
	 * 注册分页工具栏事件
	 * @param pageStatusBarService
	 */
	public void setPageStatusBarListener(PageStatusBarService pageStatusBarService){
		
		pageStatusBarService.setDataGridForPage(this);
		if(this.getPageStatusBarService()==null){//如果pageStatusBarService为空才添加事件，避免事件重复添加
			if(pageStatusBarService!=null){
				this.getPageStatusBar().getPageSizeComboBox().addItemListener(pageStatusBarService.getPageSizeComboBoxListener());
				this.getPageStatusBar().getFirstPageButt().addActionListener(pageStatusBarService.getFirstPageButtListener());
				this.getPageStatusBar().getPreviousPageButt().addActionListener(pageStatusBarService.getPreviousPageButtListener());
				this.getPageStatusBar().getNextPageButt().addActionListener(pageStatusBarService.getNextPageButtListener());
				this.getPageStatusBar().getEndPageButt().addActionListener(pageStatusBarService.getEndPageButtListener());
				this.getPageStatusBar().getRefreshButt().addActionListener(pageStatusBarService.getRefreshButtListener());
			}
			this.pageStatusBarService=pageStatusBarService;
		}
	}
	
	/**
	 * 添加双击编辑事件
	 * @param dataGridMouseService
	 */
	public void setDataGridMouseClickListenter(DataGridMouseService dataGridMouseService){
		if(this.getDataGridMouseService()==null){
			if(dataGridMouseService!=null){
				this.getTable().addMouseListener(dataGridMouseService.getDoubleSelectButtListener());
			}
			this.dataGridMouseService=dataGridMouseService;
		}
		
	}
	
	/**
	 * 右键单击事件
	 * @param dataGridMouseService
	 */
	public void setDataGridRightClickListenter(DataGridMouseService dataGridMouseService){
		if(this.getDataGridMouseService()==null){
			if(dataGridMouseService!=null){
				this.getTable().addMouseListener(dataGridMouseService.getMouseRightClickedButtListener());
			}
			this.dataGridMouseService=dataGridMouseService;
		}
		
	}
	
	/**
	 * 注册dataGrid工具栏事件
	 * @param DataGridToolBarService
	 */
	public void setDataGridToolBarServiceListener(DataGridToolBarService dataGridToolBarService){
		
		if(this.getDataGridToolBarService()==null){
			if(dataGridToolBarService!=null){
				this.addButt.addActionListener(dataGridToolBarService.getAddButtListener());
				this.updataButt.addActionListener(dataGridToolBarService.getUpdateButtListener());
				this.delButt.addActionListener(dataGridToolBarService.getDelButtListener());
				this.queryButt.addActionListener(dataGridToolBarService.getQueryButtListener());
			}
			this.dataGridToolBarService=dataGridToolBarService;
			
			//this.table.addMouseListener(l);
			
		}
	}
	
	
	
	/**
	 * 初始化组件
	 */
	public void initComponents() {
		
		setLayout(new BorderLayout(0, 0));
		
		//this.setPreferredSize(new Dimension(700, 600));
		
		toolBar = new JToolBar();
		add(toolBar, BorderLayout.NORTH);
		
		addButt = new JXButton();
		addButt.setText("\u6DFB\u52A0");
		toolBar.add(addButt);
		
		updataButt = new JXButton();
		updataButt.setText("\u7F16\u8F91");
		toolBar.add(updataButt);
		
		delButt = new JXButton();
		delButt.setText("\u5220\u9664");
		toolBar.add(delButt);
		
		queryButt = new JXButton();
		queryButt.setText("\u67E5\u8BE2");
		toolBar.add(queryButt);
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		table = new JXTable();
		table.setEditable(false);
		
		//使table内数据居中显示
		DefaultTableCellRenderer   r   =   new   DefaultTableCellRenderer();   
		r.setHorizontalAlignment(JLabel.CENTER);   
		table.setDefaultRenderer(Object.class,   r);
		
		scrollPane.setViewportView(table);
		
		pageStatusBar=new PageStatusBar();
		add(pageStatusBar, BorderLayout.SOUTH);
		
	} 
	
	/**
	 * 为table填充数据,并刷新组件
	 * @param defaultTableModel
	 */
	public void setTableDate(DefaultTableModel defaultTableModel){
		table.setModel(defaultTableModel);
		table.repaint();
	}
	
	/**
	 * 填充dataGrid表格及工具栏数据
	 * @param dataGridForPageBean
	 */
	public void setDataGridForPageDate(DataGridForPageBean dataGridForPageBean){
		
		if(dataGridForPageBean!=null){
			
			if(dataGridForPageBean.getJtableModel()!=null){
				table.setModel(dataGridForPageBean.getJtableModel());
				pageStatusBar.setPageStatusBarData(dataGridForPageBean);
				table.repaint();
			}else{
				((DefaultTableModel) table.getModel()).getDataVector().clear();   //清除表格数据
				((DefaultTableModel) table.getModel()).fireTableDataChanged();//通知模型更新
				table.updateUI();//刷新表格 
				this.getPageStatusBar().clear();
			}
			
			setColumnStyle();
		}
		
	}
	
	public void setColumnStyle(){
		if(columnStyleList!=null){
			for(ColumnStyle temStyle:columnStyleList){
				
				this.table.getColumn(temStyle.getColumnInd()).setWidth(temStyle.getWidth());
				this.table.getColumn(temStyle.getColumnInd()).setPreferredWidth(temStyle.getWidth());
			}

		}
	}
	
	/**
	 * 获取分页工具栏内组件数据
	 * @return
	 */
	public DataGridForPageBean getPageStatusBarData(){
		return pageStatusBar.getPageStatusBarData();
	}
	
	public PageStatusBar getPageStatusBar() {
		return pageStatusBar;
	}

	public JToolBar getToolBar() {
		return toolBar;
	}

	public JXButton getAddButt() {
		return addButt;
	}

	public JXButton getUpdataButt() {
		return updataButt;
	}

	public JXButton getDelButt() {
		return delButt;
	}

	public JXButton getQueryButt() {
		return queryButt;
	}

	public JXTable getTable() {
		return table;
	}
	
	/**
	 * 添加按钮
	 * @param button
	 */
	public void addButton(JXButton button){
		
		toolBar.add(button);
	}
	
	/**
	 * 不显示工具栏
	 */
	public void hideToolBar(){
		
		this.toolBar.setVisible(false);
	}
	
	/**
	 * 不显示分页组件
	 */
	public void hidePageStatusBar(){
		
		this.pageStatusBar.setVisible(false);
	}
	
    /**
     * 
     * @param name
     * @return
     */
    public Boolean isToolBarButExists(String name){
    	if(toolBar!=null){
    	 	for(int i=0;i<toolBar.getComponentCount();i++){
    	 		if(toolBar.getComponent(i).getName()!=null){
            		if(toolBar.getComponent(i).getName().equals(name)){
             		   return true;	
             		}
    	 		}
        	}
    	}
    	return false;
    }
	/**
	 * 移除工具栏所有组件
	 */
	public void removeToolBarBut(){
		
		this.toolBar.removeAll();
	}
	
	public PageStatusBarService getPageStatusBarService() {
		return pageStatusBarService;
	}

	public void setPageStatusBarService(PageStatusBarService pageStatusBarService) {
		this.pageStatusBarService = pageStatusBarService;
	}
	
	public DataGridToolBarService getDataGridToolBarService() {
		return dataGridToolBarService;
	}

	public void setDataGridToolBarService(DataGridToolBarService dataGridToolBarService) {
		this.dataGridToolBarService = dataGridToolBarService;
	}
	
	public void setColumnStyleList(List<ColumnStyle> columnStyleList) {
		this.columnStyleList = columnStyleList;
	}
	public DataGridMouseService getDataGridMouseService() {
		return dataGridMouseService;
	}
}
