package com.sys.core.develop.util;

public class ProgramUtil {
	
	
	/**
	 * 根据模板template替换character,循环生成结果
	 * @param str
	 * @param character
	 * @param limit
	 */
	public static void printTemplatePro(String template,String character,Integer limit){
		
		
		for(int i=0;i<=limit;i++){
			
			System.out.println(template.replace(character, String.valueOf(i)));
			
		}
		
	}
	
	
	public static void main(String args[]){
		
		ProgramUtil util=new ProgramUtil();
		
		util.printTemplatePro("System.out.print(rs.getCell(#,i).getContents());", "#", 22);
		
		
	}
	

}
