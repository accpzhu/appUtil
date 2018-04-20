package com.sys.core.swing.component.dialog;

import javax.swing.JOptionPane;

public class ShowMesageDialog {
	
	public static void showError(String ErrorMsg){
		
		JOptionPane.showMessageDialog(null, ErrorMsg, "提示",JOptionPane.ERROR_MESSAGE); 
	}

}
