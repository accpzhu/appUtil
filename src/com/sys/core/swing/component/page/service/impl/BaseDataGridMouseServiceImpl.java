package com.sys.core.swing.component.page.service.impl;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.sys.core.swing.component.page.service.DataGridMouseService;

public abstract class BaseDataGridMouseServiceImpl implements DataGridMouseService {

	@Override
	public MouseListener getDoubleSelectButtListener() {
		
		MouseListener mouseListener=new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {
				
				 if (e.getClickCount() == 2) {
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
	
	public void doubleClick(){
		
	}

}
