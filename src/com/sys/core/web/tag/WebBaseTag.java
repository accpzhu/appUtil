package com.sys.core.web.tag;


/**
 * 控件抽象类
 * @author Administrator
 *
 */
public abstract class WebBaseTag{
	
	
	protected  StringBuffer htmlStr;
	
	
	protected StringBuffer getHtmlStr() {
		return htmlStr;
	}

	protected void setHtmlStr(StringBuffer htmlStr) {
		this.htmlStr = htmlStr;
	}
	
	
	protected  abstract void doStart();
	 
	protected abstract void doHandle();
	 
	protected abstract void doEnd();
	  
	 public StringBuffer toHtml(){
	 doStart();
	 doHandle();
	 doEnd();
	 
	 return this.htmlStr;
	 }
	
	
}