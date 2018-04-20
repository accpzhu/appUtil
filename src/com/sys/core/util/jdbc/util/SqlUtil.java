package com.sys.core.util.jdbc.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.sys.core.model.MyDynaBean;
import com.sys.core.util.StringUtil;

/**
 * 生成sql工具
 * @author Administrator
 */
public class SqlUtil {
	
	private Map<String, Object> propertyMap; 
	private List<String> conditionList; 
	private List<String> relationList; 
	private Map<String, String> orderByMap; 
	private String selectConditionStr;       //例子selectCondition="select ......"
	private String fromStr;                  //例子                    fromStr="from ........"

	public SqlUtil() { 
		init(); 
	} 
	
	void init(){ 
		propertyMap = new LinkedHashMap<String, Object>(); 
		conditionList = new LinkedList<String>(); 
		relationList = new LinkedList<String>(); 
		orderByMap = new LinkedHashMap<String, String>(); 
	}
	
	/** * 添加查询条件 * * 
	 * @param relation * 关联 "and","or"等 * 
	 * @param property * 查询的对象属性 * 
	 * @param condition * 查询的条件，关系符 * 
	 * @param value * 查询的值 */ 
	public SqlUtil setWhereStr(String relation, String property, String condition, Object value) { 
		if(value != null){ 
			relationList.add(relation); 
			propertyMap.put(property, value); 
			conditionList.add(condition); 
		} return this; 
	}
	
	/**
	 * 如果property存在bean中则添加like条件
	 * @param relation * 关联 "and","or"等 * 
	 * @param property * 查询的对象属性 * 
	 * @param bean
	 */
	public void setLikeRelation(String relation,String property,MyDynaBean bean){
		
		if(bean!=null){
			if(bean.propertyIsExists(property)){
				
				this.setWhereStr(relation, property, "like", "%"+bean.getValue(property)+"%");
			}
		}
	}
	
	/**
	 * 如果property存在bean中则添加like条件
	 * @param relation * 关联 "and","or"等 * 
	 * @param property * 查询的对象属性 * 
	 * @param bean
	 */
	public void setEqualsRelation(String relation,String property,MyDynaBean bean){
		if(bean!=null){
			if(bean.propertyIsExists(property)){	
				this.setWhereStr(relation, property, "=", bean.getValue(property));
			}
		}
	}
	private String buildWhereStr() { 
		StringBuffer buffer = new StringBuffer(); 
		if (!propertyMap.isEmpty() && propertyMap.size() > 0) { 
			buffer.append(" WHERE 1 = 1 "); 
			int index = 0; 
			for (String property : propertyMap.keySet()) { 
				if (property != null && !property.equals("")) { 
					buffer.append(relationList.get(index)); 
					buffer.append(" ").append(property).append(" ").append( conditionList.get(index)).append(" ")
					.append( getValue(propertyMap.get(property))).append(" "); 
				} index++; 
			} 
		 } 
		return buffer.toString(); 
	}
	
	private String getValue(Object object) { 
		if (object.toString().equals("is null") || object.toString().equals("is not null") || 
			object.toString().equals("?")) { 
		      return object.toString(); 
		    } else if (object.getClass().equals(String.class)) { 
		    	return "'" + object.toString() + "'"; 
		    } else if (object.getClass().equals(Date.class) || object.getClass().equals(Timestamp.class)) { 
		    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		    	return "to_date('" + sdf.format(object) + "','yyyy-mm-dd hh24:mi:ss')"; 
		    } return object.toString(); 
    }
	
	/** * 创建SQL * @return String */ 
	public String buildSelectSQL() { 
		StringBuffer buffer = new StringBuffer(); 
		buffer.append(" ").append(this.getSelectConditionStr()).append(" ").append(this.getFromStr())
		.append(buildWhereStr()) .append(this.getOrderByStr()); return buffer.toString().trim(); 
	} 
	
	/** * 创建COUNT（*）的语句 * @return String */ 
	public String buildCountSQL() { 
		StringBuffer buffer = new StringBuffer(); 
		buffer.append("SELECT COUNT(*) ").append(this.getFromStr())
		.append(" ").append( buildWhereStr()); 
		return buffer.toString().trim(); 
	} 

	/**
	 * 
	 * @return
	 */
	private String getOrderByStr() { 
		StringBuffer buffer = new StringBuffer(); 
		if (!orderByMap.isEmpty() && orderByMap.size() > 0) { 
			buffer.append("ORDER BY "); 
			int index = 0; 
			for (String orderBy : orderByMap.keySet()) { 
				if (index != 0) { 
					buffer.append(","); 
			    } 
			buffer.append( orderBy).append(" ").append(orderByMap.get(orderBy)); index++; 
		    } 
		} 
		return buffer.toString(); 
	} 
	
	/**  
	 * 设置ORDER BY的key = value * 
	 * @param orderByStr  
	 * @param order desc/ASC  
	 * @return String */ 
	public SqlUtil setOrderByStr(String orderByStr, String order) { 
		orderByMap.put(orderByStr, order); 
		return this; 
	} 
	
	/**
	 * 创建insertSql语句
	 * @param bean       MyDynaBean对象
	 * @param tableName  表名
	 * @return
	 */
	public  static String genInsertSQL(MyDynaBean bean,String tableName){
		String insertSQL="insert into "+tableName+" (";
		String insertSqlValues=" values (";

		for (int i = 0; i < bean.getListKey().size(); i++) {   
			//if value of this field is null,then ignore this field
			if(!StringUtil.getString(bean.getListKey().get(i)).equals("") ){
				insertSQL+=bean.getListKey().get(i);
				if(bean.getListKey().size()-1>i){//确保最后参数后不带‘,’号
					insertSQL+=",";
				}
			}
			String value="";
			String typeOfThisField=(String) bean.getValue(bean.getListKey().get(i)).getClass().getName();

			if(typeOfThisField.contentEquals("java.lang.String")){   //when String ,add '' around the string ,like:'tempString'
				value="'"+bean.getValue(bean.getListKey().get(i))+"'";
			}else if(typeOfThisField.contentEquals("java.util.Date")){	
				// select to_date('2008-01-01 14:26:38','YYYY-MM-DD HH24:MI:SS') from dual
				value="to_date('"+(String) bean.getValue(bean.getListKey().get(i))+"','YYYY-MM-DD HH24:MI:SS')";
			}else {	//other types like long,double ,add nothing but their value
				value=(String) bean.getValue(bean.getListKey().get(i));
			}
			insertSqlValues +=value;
			if(bean.getListKey().size()-1>i){ //确保最后参数后不带,号
			 insertSqlValues +=",";
			}
		}	
        //insertSQL结束
		insertSQL+=")";
		insertSqlValues +=")";
		insertSQL=insertSQL+insertSqlValues;

		//System.out.println("SQL is:"+insertSQL);
		return insertSQL;

	}
	
	/**
	 * 返回根据id创建更新语句
	 * @param tableName
	 * @param pkColumnName
	 * @param bean
	 * @return
	 */
	public static String genUpdateSQLForId(String tableName,String pkColumnName,MyDynaBean bean){
		
		String updateSQL="update "+ tableName +" set ";
		for (int i = 0; i < bean.getListKey().size(); i++){
			if(bean.getValue(bean.getListKey().get(i))!=null ){
				//System.out.println(bean.toString());
				String setSQL=bean.getListKey().get(i)+"=";
				String typeOfThisField=StringUtil.getString(bean.getValue(bean.getListKey().get(i)).getClass().getName());
				String value="";

				if(typeOfThisField.contentEquals("java.lang.String")){   
					//when String ,add '' around the string ,like:'tempString'
					value="'"+bean.getValue(bean.getListKey().get(i))+"'";
					setSQL+=value;
				}else if(typeOfThisField.contentEquals("java.util.Date")){	
					// select to_date('2008-01-01 14:26:38','YYYY-MM-DD HH24:MI:SS') from dual
					value="to_date('"+(String) bean.getValue(bean.getListKey().get(i))+"','YYYY-MM-DD HH24:MI:SS')";
					setSQL+=value;
				}else {	//other types like long,double ,add nothing but their value
					value=bean.getValue(bean.getListKey().get(i)).toString();
					setSQL+=value;
				}
				if(bean.getListKey().size()-1>i){
					setSQL +=",";
				}	
				updateSQL +=setSQL;
			}

		}
		updateSQL +=" where "+pkColumnName +"="+bean.getValue(pkColumnName)+"";		

		//System.out.println(updateSQL);
		
		return updateSQL;
	}
	

	
	public String getFromStr() {
		return fromStr;
	}

	public void setFromStr(String fromStr) {
		this.fromStr = fromStr;
	}

	/**
	 * @return
	 */
	public String getSelectConditionStr() {
		return selectConditionStr;
	}

	/**
	 * @param selectConditionStr
	 */
	public void setSelectConditionStr(String selectConditionStr) {
		this.selectConditionStr = selectConditionStr;
	}
}
