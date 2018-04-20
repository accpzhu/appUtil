package com.sys.core.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

/**
 * 自定义动态bean
 * @author zzs
 *
 */
public class MyDynaBean implements Serializable{

	private static final long serialVersionUID = 3511566305355498043L;
	
	protected Map<String, Object> valuesMap = new HashMap();
	
	protected List <String>listKey  = new ArrayList();

	@Override
	public String toString() {
		return "MyDynaBean [valuesMap=" + valuesMap + ", listKey=" + listKey + "]";
	}

	public List <String> getListKey() {
		
		return listKey;
	}

	public Object[] getKeyArray() {
		
		return valuesMap.keySet().toArray();
	}
	
	public void setValue(String name,Object obj){
		
		valuesMap.put(name, obj);
		listKey.add(name);
	}
	
	public Vector getVectors(){
		
		Vector v=new Vector();
		for(String str : getListKey()) {
			v.add(valuesMap.get(str));
		}
        return v;
	}
	
	public Object getValue(String name){
		return valuesMap.get(name);
	}
	
    public Object [] getArrayObjects(){
    	return valuesMap.values().toArray();
    }
    
    public Object getValueForIndex(Integer index){
    	
    	 Iterator<Map.Entry<String, Object>> it = valuesMap.entrySet().iterator();
    	 int i=0;
    	  while (it.hasNext()) {
    		  i++;
    	    Map.Entry<String, Object> entry = it.next();
	    	   if(i==index){
	    		  return entry.getValue();
	    	   }
    	  }

    	return null;
    }
    
    /**
     * 检测是否有该属性值
     * @param property
     * @return
     */
    public Boolean propertyIsExists(String property){
    	
    	if(valuesMap.get(property)!=null && !valuesMap.get(property).toString().equals("")){
    		return true;
    	}else
    	return false;
    }

	public Object clone() {  
		
	  MyDynaBean o = null;  
	   try {  
		    o = (MyDynaBean) super.clone();  
		} catch (CloneNotSupportedException e) {  
		          e.printStackTrace();  
		}  
		   return o;  
    }  
	
	public static void main(String args[]){
        
		MyDynaBean bean=new MyDynaBean();

		bean.setValue("aaa", "aaa");
		bean.setValue("bbb", "bbb");
		bean.setValue("ccc", "ccc");
		bean.setValue("ddd", "ddd");
		
		System.out.println(bean.getValueForIndex(5));
	}

}
