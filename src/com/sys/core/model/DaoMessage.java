package com.sys.core.model;

/**
 * 系统提示信息
 * @author Administrator
 *
 */
public class DaoMessage extends BaseModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4800017464817212823L;
	
  private Integer messageId;
  private String messageCode;
  private String messageStr;
  
  public Integer getMessageId() {
	return messageId;
}
public void setMessageId(Integer messageId) {
	this.messageId = messageId;
}
public String getMessageCode() {
	return messageCode;
}
public void setMessageCode(String messageCode) {
	this.messageCode = messageCode;
}
public String getMessageStr() {
	return messageStr;
}
public void setMessageStr(String messageStr) {
	this.messageStr = messageStr;
}


}
