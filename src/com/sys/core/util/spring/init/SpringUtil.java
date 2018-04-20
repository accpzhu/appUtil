package com.sys.core.util.spring.init;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class SpringUtil {
	
	private static ApplicationContext applicationContext;
	private static FileSystemXmlApplicationContext fileXMLContent;
	
	/**
	 * 加载spring 配置文件
	 * @param fileUrl
	 */
	public static void initLoad(String[] fileUrl){
		
		fileXMLContent=new FileSystemXmlApplicationContext(fileUrl); 
		
		applicationContext=fileXMLContent;
		

		//fileXMLContent=new  FileSystemXmlApplicationContext(fileUrl); 
	}
	
   public void test(){
	   
		System.out.println("sdfsdf");
   }
   
    /**
     * 
     * 销毁spring运行
     * 
     */
    public static void destroy(){
    	
    	fileXMLContent.destroy();
    }

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}
	
	public static void setApplicationContext(ApplicationContext applicationContext) {
		SpringUtil.applicationContext = applicationContext;
	}

    /**
     * 显示spring加载bean名称
     */
    public static void  toPrintBeanNames(){
    	
		String str[]=applicationContext.getBeanDefinitionNames();
		
		for(int i=0;i<str.length;i++){
			
			System.out.println(str[i]);
		}

    	
    }


}
