package com.sys.core.util.swing;

import java.awt.Component;
import java.awt.Container;

/**
 * @author Administrator
 *
 */
public class JFrameUtil {
	
	/**
	 * 删除JFrame内组件
	 * @param container
	 * @param removeComponent
	 */
	public  static void removeContentPanel(Container container,Component removeComponent){

		container.remove(removeComponent);
		container.validate();
	}
	


}
