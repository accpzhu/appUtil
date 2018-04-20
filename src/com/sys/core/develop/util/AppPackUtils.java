package com.sys.core.develop.util;

import java.io.File;
import java.io.FileFilter;

import com.sys.core.util.filestream.FileFilterUtil;

public class AppPackUtils {
	
	public static void main(String args[]){
		
		new AppPackUtils().createAppRunBat("com.ui.run.RunApp", "D:/temp/lib/");
	}
	
	public void createAppRunBat(String mainMethodName,String classpathFileName){
		File file=new File(classpathFileName);
		File files[]=file.listFiles(FileFilterUtil.getFileFilterForEndsWith(".jar"));
		
		String classpaths="";
		for(int i=0;i<files.length;i++){
			classpaths+="./lib/"+files[i].getName()+";";
		}
		String fileContext="set classpath="+classpaths+"\r\n";
		fileContext+="java "+mainMethodName;
		
		System.out.println(fileContext);
	}

}
