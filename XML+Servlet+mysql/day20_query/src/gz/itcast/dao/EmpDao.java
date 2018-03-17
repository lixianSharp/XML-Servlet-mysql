package gz.itcast.dao;

import gz.itcast.entity.Employee;
import gz.itcast.util.JdbcUtil;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

/**
 * 员工的dao
 * 
 * @author 贤元
 * 
 */
public class EmpDao {

	/**
	 * 提供一个查询当前页员工数据的方法
	 */
	public List<Employee> queryData(int currentPage, int pageSize) {
		try {
			// 1)创建QueryRunner
			QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
			// 查询当前页的sql
			// SELECT * FROM employee LIMIT (当前页码-1)*每页显示记录数,每页显示记录数;
			String sql = "SELECT * FROM employee LIMIT ?,?";
			// 起始行
			int startNo = (currentPage - 1) * pageSize;
			List<Employee> list = (List<Employee>) qr.query(sql,
					new BeanListHandler(Employee.class), new Object[] {
							startNo, pageSize });
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * 提供一个查询总记录数的方法
	 * 
	 * @param args
	 */
	public int queryCount() {
		try {
			QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
			String sql = "SELECT COUNT(*) FROM employee";
			Long count = (Long) qr.query(sql, new ScalarHandler());
			return count.intValue();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	public static void main(String[] args) {
		EmpDao dao = new EmpDao();
		/*
		 * List<Employee> list = dao.queryData(4, 2); for (Employee employee :
		 * list) { System.out.println(employee); }
		 */

		int count = dao.queryCount();
		System.out.println(count);
	}

}
