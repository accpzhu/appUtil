package com.sys.core.swing.component.dialog;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.JXButton;
import org.jdesktop.swingx.JXPanel;
import org.jdesktop.swingx.JXTable;

import com.sys.core.model.MyDynaBean;
import com.sys.core.swing.component.page.PageStatusBar;
import com.sys.core.swing.component.page.bean.DataGridForPageBean;
import com.sys.core.swing.component.page.service.DataGridToolBarService;
import com.sys.core.swing.component.page.service.PageStatusBarService;


public class PropertiesEditDialog extends JDialog {
		
		private JXTable table;
        private JXButton subButt;
        private JXButton testConnButt;
        private ActionListener subButtAction;
        private ActionListener testConnButtAction;
		public PropertiesEditDialog() {
			initComponents();
		}

		/**
		 * 初始化组件
		 */
		public void initComponents() {
			
			getContentPane().setLayout(new BorderLayout(0, 0));
			
			JScrollPane scrollPane = new JScrollPane();
			getContentPane().add(scrollPane, BorderLayout.CENTER);
			
			table = new JXTable();
			table.setEditable(true);
			scrollPane.setViewportView(table);
			
			JXPanel toolBar=new JXPanel();
			
			toolBar.setLayout(new BorderLayout(0, 0));
			


			
			Box box=Box.createHorizontalBox();
			box.setAlignmentX(Component.RIGHT_ALIGNMENT);
			toolBar.add(box,BorderLayout.EAST);
			
			subButt = new JXButton();
			subButt.setText("\u4FDD\u5B58");

			box.add(subButt);
			
			box.add(Box.createHorizontalStrut(5));
			
			testConnButt= new JXButton();
	
			testConnButt.setText("\u6D4B\u8BD5\u8FDE\u63A5");
			box.add(testConnButt);
			getContentPane().add(toolBar, BorderLayout.SOUTH);
			
			testConnButt.setVisible(false);
			
			 setVisible(false);
		}
		
		/**
		 * 设置显示测试链接按钮，testConnButt默认为不显示
		 */
		public void setShowTestConnButt(){
			
			testConnButt.setVisible(true);
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
		 * @return
		 */
		public JXTable getTable() {
			return table;
		}
		
		/**
		 * 注册保存按钮事件,并避免重复添加action
		 */
		public void setSubmitButtActionListener(ActionListener actionListener){
			
			if(subButtAction==null){
				subButt.addActionListener(actionListener);
				subButtAction=actionListener;
			}
		}
		
		/**
		 * 注册保存按钮事件,并避免重复添加action
		 */
		public void setTestConnButtActionListener(ActionListener actionListener){
			
			if(testConnButt==null){
				testConnButt.addActionListener(actionListener);
				testConnButtAction=actionListener;
			}
		}
		
		/**
		 * 
		 * @return
		 */
		public MyDynaBean getMyDynaBean(){
			
			MyDynaBean myDynaBean=new MyDynaBean();
			for(int i=0;i<this.table.getRowCount();i++){
				myDynaBean.setValue(this.table.getValueAt(i, 0).toString(), this.table.getValueAt(i, 2));
			}
			return myDynaBean;
		}
		
		/**
		 * 显示查询窗体
		 */
		public void showPropertiesEditDialog(){
			this.setSize(300, 300);
			this.setPreferredSize(new Dimension(300, 300));
			//屏幕中间弹出窗口
			setLocationRelativeTo(null);
	        setVisible(true);
		}

	    public static void main(String[] args) {

	        javax.swing.SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	            	PropertiesEditDialog aa=new PropertiesEditDialog();
	            	aa.showPropertiesEditDialog();
	            }
	        });
	    }

}
