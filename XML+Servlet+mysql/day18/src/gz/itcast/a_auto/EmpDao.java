package gz.itcast.a_auto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.Test;

import util.JdbcUtil;

public class EmpDao {

	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	//保存员工，同时保存关联的部门
	@Test
	public void save(Employee emp){
		
		//保存部门
		String sql_dept="insert into dept(deptName) values(?)";
		//保存员工
		String sql_emp="insert into employee (empName,dept_id) values(?,?)";
		//部门id
		int deptId=0;
		
		try {
			//1.注册驱动程序,获取连接对象
			con=JdbcUtil.getConnection();
			/** 保存部门，获取自增长标记 **/
			//【1、需要指定返回自增长标记】  (执行预编译的sql)
			pstmt = con.prepareStatement(sql_dept, Statement.RETURN_GENERATED_KEYS);
			//设置参数
			pstmt.setString(1, emp.getDept().getDeptName());
			//执行
			pstmt.executeUpdate();
			
			//【获取上边保存的部门自增长的主键】
			rs=pstmt.getGeneratedKeys();
			//得到返回的自增长字段
			if(rs.next()){
				deptId=rs.getInt(1);
			}
			/**保存员工**/
			pstmt=con.prepareStatement(sql_emp);
			//设置参数
			pstmt.setString(1, emp.getEmpName());
			pstmt.setInt(2, deptId);
			pstmt.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally{
			//关闭资源
			JdbcUtil.close(con, pstmt, rs);
			
		}
	}
	
}