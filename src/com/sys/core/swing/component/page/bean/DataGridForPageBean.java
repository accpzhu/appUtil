package com.sys.core.swing.component.page.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import com.sys.core.model.MyDynaBean;
import com.sys.core.util.DataUtil;

/**
 * dataGrid 页面数据内容bean
 * @author Administrator
 */
public class DataGridForPageBean implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8685702139837158066L;
	private Integer pageSize;
	private Integer currentPageNum;
	private Integer summaryRow;
	private Integer summaryPage;
    private List<MyDynaBean> pageItems ;
    private DefaultTableModel jtableDataModel;
    

	/**
     * 返回swing中JTable组件的数据,确保pageItems中有值,pageItems为空则返回空指针,
     * @return DefaultTableModel
     */
    public DefaultTableModel getJtableModel(){
    	if(pageItems!=null){
    		if(pageItems.size()>0){
    			jtableDataModel=new DefaultTableModel();
               	MyDynaBean myDynaBean=(MyDynaBean)pageItems.get(0);
            	Vector v=DataUtil.getVectorForList(pageItems);
            	jtableDataModel.setDataVector(DataUtil.getVectorInVectorsForList(pageItems), DataUtil.getVectorForList(myDynaBean.getListKey()));
    	    	return jtableDataModel;
    		}
    	}
    	return jtableDataModel;
    }
    
    public List <MyDynaBean> getPageItems(){
    	pageItems=new ArrayList<MyDynaBean>();
    	return pageItems;
    }
    public void setPageItems(List<MyDynaBean> pageItems){
    	this.pageItems=pageItems;
    }
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getCurrentPageNum() {
		return currentPageNum;
	}
	public void setCurrentPageNum(Integer currentPageNum) {
		this.currentPageNum = currentPageNum;
	}
	public Integer getSummaryRow() {
		return summaryRow;
	}
	public void setSummaryRow(Integer summaryRow) {
		this.summaryRow = summaryRow;
	}
	public Integer getSummaryPage() {
		return summaryPage;
	}
	public void setSummaryPage(Integer summaryPage) {
		this.summaryPage = summaryPage;
	}
	/**
	 * 判断分页数据是否为空
	 * @return Boolean
	 */
	public Boolean pageItemsIsNull(){
		
		if(pageItems==null){
			return true;
		}
        return false;
	}
	
    @Override
	public String toString() {
		return "DataGridForPageBean [pageSize=" + pageSize + ", currentPageNum=" + currentPageNum + ", summaryRow="
				+ summaryRow + ", summaryPage=" + summaryPage + ", pageItems=" + pageItems + ", jtableDataModel="
				+ jtableDataModel + "]";
	}

}
