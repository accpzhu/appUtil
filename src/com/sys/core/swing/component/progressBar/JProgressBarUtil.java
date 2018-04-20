package com.sys.core.swing.component.progressBar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.JRootPane;


/**
 * 滚动条工具类
 * @author Administrator
 *
 */
public class JProgressBarUtil { 
    private static JDialog dialog = new JDialog(); 
    private static JProgressBar jpbFileLoadingIndeterminate;

    /**
     * 创建一个不确定模式的进度条
     * @param com 该参数是进度条弹出在com组件前
     */
    public static void showIndeterminateProgress(Component com){

        /**
         * 创建一个不确定模式的进度条
         */ 
        jpbFileLoadingIndeterminate = new JProgressBar(); 
        jpbFileLoadingIndeterminate.setIndeterminate(true); //设置进度条为不确定模式,默认为确定模式  
        jpbFileLoadingIndeterminate.setStringPainted(true);
        jpbFileLoadingIndeterminate.setForeground(Color.green); 
        jpbFileLoadingIndeterminate.setString("数据加载中......"); 
        dialog.add(jpbFileLoadingIndeterminate, BorderLayout.SOUTH); 
        dialog.setSize(500, 20); 
        dialog.setLocationRelativeTo(null); //居中显示  
        dialog.setUndecorated(true);        //禁用此窗体的装饰  
        dialog.getRootPane().setWindowDecorationStyle(JRootPane.NONE); //采用指定的窗体装饰风格  
        dialog.setVisible(true); 
        dialog.setLocationRelativeTo(com);
    }
    
    /**
     * 关闭滚动条
     */
    public static void closeIndeterminateProgress(){
      jpbFileLoadingIndeterminate.setIndeterminate(false); //设置进度条为确定模式,即常规模式,否则那个条还会走来走去  
      jpbFileLoadingIndeterminate.setString("文件加载完毕.."); 
      dialog.setVisible(false); //隐藏窗体  
      dialog.dispose();         //释放资源,关闭窗体  
      dialog = null;            //若不再使用了就这样  
    }
      
    public static void main(String[] args) { 
    	JProgressBarUtil aa= new JProgressBarUtil();
    	//aa.showIndeterminateProgress(); 

    	//aa.closeIndeterminateProgress();
    } 
} 