package gz.itcast.dao;

import gz.itcast.entity.Department;
import gz.itcast.util.JdbcUtil;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

public class DeptDao {

	/**
	 * 查询所有部门
	 */
	public List<Department> findAll(){
		try{
		QueryRunner qr=new QueryRunner(JdbcUtil.getDataSource());
		String sql="select * from department";
		List<Department> list=(List<Department>) qr.query(sql, new BeanListHandler(Department.class));
		return list;
		}catch(SQLException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	
	public static void main(String[] args) {
		DeptDao dao=new DeptDao();
		List<Department>list=dao.findAll();
		for(Department department:list){
			System.out.println(department);
		}
	}
	
}
