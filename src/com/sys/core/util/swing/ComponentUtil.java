package com.sys.core.util.swing;

import java.awt.Component;
import java.awt.Container;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 *
 */
public class ComponentUtil {
	
	/**
	 * 当前Container是否包含className组件
	 * @param c
	 * @param className
	 * @return
	 */
	public static Boolean componentIsExists(Container c,String className){
		if(c!=null){
			if(c.getComponentCount()>0){
				
				for(int i=0;i<c.getComponentCount();i++){			
					return c.getComponents()[i].getClass().toGenericString().toLowerCase().contains(className.toLowerCase());
				}
			}
		}
		return false;
	}
	
	/**
	 * @param c
	 * @param className
	 * @return
	 */
	public static List <Component> getComponentIsExists(Container c,String className){
		List list=new ArrayList<Component>();
		if(c!=null){
			if(c.getComponentCount()>0){
				for(int i=0;i<c.getComponentCount();i++){	
					if( c.getComponents()[i].getClass().toGenericString().toLowerCase()
							.contains(className.toLowerCase())){
						list.add(c.getComponents()[i]);
					}
				}
			}
		}
		return list;
	}
	
	/**
	 * 返回c容器中相应className的组件中的组件名为componentName的object
	 * @param c                容器
	 * @param className        容器中包含的组件的className
	 * @param componentName    要寻找的组件的名称
	 * @return
	 */
	public static Component getComponentIsExistsForName(Container c,String className,String componentName){
		List <Component> list=getComponentIsExists(c,className);
		for(Component com:list){
			if(com.getName().equals(componentName)){
				return com;
			}
		}
		return null;
	}
	
	/**
	 * 
	 * @param c
	 * @param className
	 */
	public static void componentIsExistsAndRemove(Container c,String className){
		
		if(c!=null){
			if(c.getComponentCount()>0){
				for(int i=0;i<c.getComponentCount();i++){			
					 if(c.getComponents()[i].getClass().toGenericString().toLowerCase().contains(className.toLowerCase())){
						 c.remove(i);
					 }
				}
			}
		}
	}
}
