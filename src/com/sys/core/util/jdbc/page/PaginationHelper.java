package com.sys.core.util.jdbc.page;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.jdbc.core.RowMapper;

import com.sys.core.swing.component.page.bean.DataGridForPageBean;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
/**
 * @author Administrator
 *
 * @param <E>
 */
public class PaginationHelper {
	
	public DataGridForPageBean fetchPage(final JdbcTemplate jt,
			                        final String sqlCountRows, 
			                        final String sqlFetchRows,
			                        final int pageNo, 
			                        final int pageSize,
			                        final RowMapper rowMapper) {
		
		// determine how many rows are available
		
		final int rowCount = jt.queryForInt(sqlCountRows);
		
		// calculate the number of pages
		
		int pageCount = rowCount / pageSize;
		if (rowCount > pageSize * pageCount) {
			pageCount++;
		}
		// create the page object
		final DataGridForPageBean page = new DataGridForPageBean();
		page.setCurrentPageNum(pageNo);
		page.setPageSize(pageCount);
		page.setSummaryRow(rowCount);
		page.setSummaryPage(pageCount);
		// fetch a single page of results
		final int startRow = (pageNo - 1) * pageSize;
		
		jt.query(sqlFetchRows,  new ResultSetExtractor() {
			
			public Object extractData(ResultSet rs) throws SQLException,DataAccessException {
				
				List pageItems = new ArrayList();
				int currentRow = 0;
				while (rs.next() && currentRow < startRow + pageSize) {
					if (currentRow >= startRow) {
						pageItems.add(rowMapper.mapRow(rs, currentRow));
					}
					currentRow++;
				}
				page.setPageItems(pageItems);
				return page;
			}
		});
		
		return page;
	}
	
	
	/**
	 * 例子
	 * @param pageSize
	 * @return
	 * @throws DataAccessException
	 */
//	@Override
//	public List<Client> getAllCompanyTest(int pageSize) throws DataAccessException {
//		PaginationHelper<Client> ph = new PaginationHelper<Client>();
//		List<Client> c=new ArrayList<Client>();
//        CurrentPage<Client> p=ph.fetchPage(
//                jdbcTemplate,  
//                "SELECT count(*) FROM angle_company WHERE state=?",
//                "SELECT acid,corpname,contact,legal,tel,postcode,mail,address,summary,employee_eeid FROM angle_company WHERE state=?",
//                new Object[]{JdbcSqlCollection.NORMALRECORD},                
//                pageSize,
//                JdbcSqlCollection.PAGERECORDS,
//                new TestClientRowMap()
//        );		
//        c=p.getPageItems();
//		return c;
//	}
	
}
