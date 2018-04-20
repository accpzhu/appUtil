package com.sys.core.util.spring.jdbc.dao;

import java.util.List;
import java.util.Map;

//import org.springframework.jdbc.core.RowMapper;

public interface BaseDao {

	
	/**
	 * 执行update 语句
	 * @param sql
	 * @param objs
	 */
	public Integer update(String sql,Object[] objs);
	
	/**
	 * 执行insert 语句
	 * @param sql
	 * @param objs
	 */
	public Integer add(String sql,Object[] objs);
	
	/**
	 * 返回map list 数据
	 * @param sql
	 * @param objs
	 * @return
	 */
	public List queryMapList(String sql,Object[] objs);
	
	/**
	 * 执行update 语句
	 * @param sql
	 * @param objs
	 */
	public Integer delete (String sql,Object[] objs);
	
	/**
	 * 返回object list
	 * @param sql
	 * @param objs
	 * @param rowMapper
	 * @return
	 */
	//public List queryForObjectList(String sql,Object[] objs,RowMapper rowMapper);
	
	
	/**
	 * 返回单个Object对象
	 * @param sql
	 * @param objs
	 * @param rowMapper
	 * @return
	 */
	//public Object getForObject(String sql,Object[] objs,RowMapper rowMapper);
	
	
	/**
	 * 返回单个Map Object对象
	 * @param sql
	 * @param objs
	 * @param rowMapper
	 * @return
	 */
	//public Map getForMap(String sql,Object[] objs,RowMapper rowMapper);
	
    /**
     * 返回记录数
     * @param sql
     * @param objs
     * @return
     */
    public Integer getForCount(String sql,Object[] objs);
	
}