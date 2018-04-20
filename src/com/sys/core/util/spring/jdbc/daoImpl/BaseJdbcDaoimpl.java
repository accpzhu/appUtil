package com.sys.core.util.spring.jdbc.daoImpl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import com.sys.core.util.spring.jdbc.dao.BaseDao;

/**
 * 基础数据库操作类
 * @author Administrator
 *
 */
public class BaseJdbcDaoimpl extends JdbcDaoSupport implements BaseDao{
	
	@Resource
	public void setJb(JdbcTemplate jb) {
	 super.setJdbcTemplate(jb);
	}
	
	/**
	 * 执行update 语句
	 * @param sql
	 * @param objs
	 */
	/* (non-Javadoc)
	 * @see com.sys.core.util.spring.jdbc.dao.BaseDao#update(java.lang.String, java.lang.Object[])
	 */
	public Integer update(String sql,Object[] objs){
		
		return this.getJdbcTemplate().update(sql, objs);
		
	} 
	
	/**
	 * 执行insert 语句
	 * @param sql
	 * @param objs
	 */
	public Integer add(String sql,Object[] objs){
		
		return this.getJdbcTemplate().update(sql, objs);
	}
	
	/**
	 * 返回map list 数据
	 * @param sql
	 * @param objs
	 * @return
	 */
	public List queryMapList(String sql,Object[] objs){
		
		
		return this.getJdbcTemplate().queryForList(sql, objs);
	}
	/**
	 * 执行update 语句
	 * @param sql
	 * @param objs
	 */
	public Integer delete (String sql,Object[] objs){
		
		return this.getJdbcTemplate().update(sql, objs);
	}
	/**
	 * 返回object list
	 * @param sql
	 * @param objs
	 * @param rowMapper
	 * @return
	 */
	public List queryForObjectList(String sql,Object[] objs,RowMapper rowMapper){

		return  this.getJdbcTemplate().query(sql, objs, rowMapper);
	}
	/**
	 * 返回单个Object对象
	 * @param sql
	 * @param objs
	 * @param rowMapper
	 * @return
	 */
	public Object getForObject(String sql,Object[] objs,RowMapper rowMapper){
		
		return this.getJdbcTemplate().queryForObject( sql, objs, rowMapper);
	}
	/**
	 * 返回单个Map Object对象
	 * @param sql
	 * @param objs
	 * @param rowMapper
	 * @return
	 */
	public Map getForMap(String sql,Object[] objs,RowMapper rowMapper){
		
		return this.getJdbcTemplate().queryForMap(sql, objs);
	}
    /**
     * 返回记录数
     * @param sql
     * @param objs
     * @return
     */
    public Integer getForCount(String sql,Object[] objs){
    	
    	return this.getJdbcTemplate().queryForInt( sql,objs);  
    	
    }
}



