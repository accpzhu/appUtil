package com.sys.core.swing.component.page;

import org.jdesktop.swingx.JXPanel;
import com.sys.core.swing.component.comboBox.JSelecteComboBox;
import com.sys.core.swing.component.page.bean.DataGridForPageBean;
import com.sys.core.util.DataUtil;
import java.awt.Dimension;
import javax.swing.Box;
import org.jdesktop.swingx.JXButton;
import org.jdesktop.swingx.JXLabel;
import java.awt.Component;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JProgressBar;
import javax.swing.JRootPane;

/**
 * 
 * @author Administrator
 *
 */
public class PageStatusBar extends JXPanel {
	
	private JTextField currentPageNumText;
	private JSelecteComboBox pageSizeComboBox;
	private JXButton firstPageButt;
	private JXButton previousPageButt;
	private JXButton nextPageButt;
	private JXButton endPageButt;
	private JXLabel summaryRowShowLab;
	private JXButton refreshButt;
	private JXLabel summaryPageShowLab;
	private String[] comboBoxModel=new String[] {"27","15", "30", "60", "120"};
	private DataGridForPageBean pageStatusBarBean=new DataGridForPageBean();
    private JProgressBar dataGridLoadingIndeterminate;
    
	public PageStatusBar(){
		initComponents();
	}
	/**
	 * 填充工具栏对象数据
	 * @param pageStatusBarBean
	 */
	public void setPageStatusBarData(DataGridForPageBean pageStatusBarBean){
		if(pageStatusBarBean!=null){
			currentPageNumText.setText(pageStatusBarBean.getCurrentPageNum().toString());
			summaryRowShowLab.setText(pageStatusBarBean.getSummaryRow().toString());
			summaryPageShowLab.setText(pageStatusBarBean.getSummaryPage().toString());
		}
	}
	
	/**
	 * 
	 */
	public void clear(){
		
		currentPageNumText.setText("1");
		summaryRowShowLab.setText("0");
		summaryPageShowLab.setText("0");
	}
	
	/**
	 * 返回工具栏内对象数据
	 * @return
	 */
	public DataGridForPageBean getPageStatusBarData( ){
		pageStatusBarBean.setCurrentPageNum(DataUtil.getIntForDefaultVal(currentPageNumText.getText(), 0));
		pageStatusBarBean.setPageSize(DataUtil.getIntForDefaultVal(pageSizeComboBox.getSelectedItem().toString(),0));
		pageStatusBarBean.setSummaryPage(DataUtil.getIntForDefaultVal(summaryPageShowLab.getText(),0));
		pageStatusBarBean.setSummaryRow(DataUtil.getIntForDefaultVal(summaryRowShowLab.getText(),15));
		return pageStatusBarBean;
	}
	
	
	public JTextField getCurrentPageNumText() {
		return currentPageNumText;
	}

	public JSelecteComboBox getPageSizeComboBox() {
		return pageSizeComboBox;
	}

	public JXButton getFirstPageButt() {
		return firstPageButt;
	}

	public JXButton getPreviousPageButt() {
		return previousPageButt;
	}

	public JXButton getNextPageButt() {
		return nextPageButt;
	}

	public JXButton getEndPageButt() {
		return endPageButt;
	}

	public JXLabel getSummaryRowShowLab() {
		return summaryRowShowLab;
	}

	public JXButton getRefreshButt() {
		return refreshButt;
	}
	
	/**
	 * 显示滚动条
	 */
	public void showProgressBar(){
		
		dataGridLoadingIndeterminate.setVisible(true);
	}
	
	/**
	 * 隐藏滚动条
	 */
	public void hideProgressBar(){
		
		dataGridLoadingIndeterminate.setVisible(false);
	}

	public void initComponents(){
		setLayout(new BorderLayout(0, 0));
		Box box=Box.createHorizontalBox();

		pageSizeComboBox = new JSelecteComboBox();
		pageSizeComboBox.setModel(new DefaultComboBoxModel(comboBoxModel));
		pageSizeComboBox.setMaximumSize(new Dimension(50, 25)); 
		
		box.add(Box.createHorizontalStrut(10));
		box.add(pageSizeComboBox);
		box.add(Box.createHorizontalStrut(20));
		
		firstPageButt = new JXButton();
		firstPageButt.setText("\u9996\u9875");
		box.add(firstPageButt);
		
		previousPageButt = new JXButton();
		previousPageButt.setText("\u4E0A\u4E00\u9875");
		box.add(previousPageButt);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		box.add(horizontalStrut);
		
		JXLabel label = new JXLabel();
		label.setText("\u7B2C");
		box.add(label);
		
		currentPageNumText = new JTextField();
		currentPageNumText.setText("1");
		box.add(currentPageNumText);
		currentPageNumText.setColumns(10);
		currentPageNumText.setMaximumSize(new Dimension(30, 25)); 
		
		JXLabel label_1 = new JXLabel();
		label_1.setText("\u9875");
		box.add(label_1);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		box.add(horizontalStrut_1);
		
		nextPageButt = new JXButton();
		nextPageButt.setText("\u4E0B\u4E00\u9875");
		box.add(nextPageButt);
		
		endPageButt = new JXButton();
		endPageButt.setText("\u5C3E\u9875");
		box.add(endPageButt);
		
		Component horizontalStrut_2 = Box.createHorizontalStrut(10);
		box.add(horizontalStrut_2);
		
		refreshButt = new JXButton();
		refreshButt.setText("\u5237\u65B0");
		box.add(refreshButt);
		add(box, BorderLayout.WEST);
		
		Box box2=Box.createHorizontalBox();
		box2.setAlignmentX(Component.RIGHT_ALIGNMENT);
		
		dataGridLoadingIndeterminate = new JProgressBar(); 
		dataGridLoadingIndeterminate.setIndeterminate(true); //设置进度条为不确定模式,默认为确定模式  
		dataGridLoadingIndeterminate.setStringPainted(true);
		dataGridLoadingIndeterminate.setForeground(Color.green); 
		dataGridLoadingIndeterminate.setString("数据加载中......"); 
		dataGridLoadingIndeterminate.setSize(70, 20);
		dataGridLoadingIndeterminate.setVisible(false);
        box2.add(dataGridLoadingIndeterminate);
       
		Component horizontalStrut_11 = Box.createHorizontalStrut(30);
		box2.add(horizontalStrut_11);
		
		JXLabel lab = new JXLabel();
		lab.setAlignmentX(Component.RIGHT_ALIGNMENT);
		lab.setText("\u5171");
		box2.add(lab);
		
		summaryRowShowLab = new JXLabel();
		summaryRowShowLab.setAlignmentX(Component.RIGHT_ALIGNMENT);
		summaryRowShowLab.setText("0");
		box2.add(summaryRowShowLab);
		
		JXLabel rowShowLab = new JXLabel();
		rowShowLab.setAlignmentX(Component.RIGHT_ALIGNMENT);
		rowShowLab.setText("\u884C\uFF0C");
		box2.add(rowShowLab);
		
		summaryPageShowLab = new JXLabel();
		summaryPageShowLab.setAlignmentX(Component.RIGHT_ALIGNMENT);
		summaryPageShowLab.setText("0");
		box2.add(summaryPageShowLab);
		
		JXLabel pageShowLab = new JXLabel();
		pageShowLab.setAlignmentX(Component.RIGHT_ALIGNMENT);
		pageShowLab.setText("\u9875");
		box2.add(pageShowLab);
		
		JXLabel rowDataShowLab = new JXLabel();
		rowDataShowLab.setAlignmentX(Component.RIGHT_ALIGNMENT);
		rowDataShowLab.setText("\u6570\u636E");
		box2.add(rowDataShowLab);
		
		box2.add(Box.createHorizontalStrut(30));
		add(box2, BorderLayout.EAST);
	}
}
