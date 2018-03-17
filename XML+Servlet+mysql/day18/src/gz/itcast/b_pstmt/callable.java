package gz.itcast.b_pstmt;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

import org.junit.Test;

import util.JdbcUtil;
//执行存储过程
public class callable {

	//全局参数
	private Connection conn=null;
	private ResultSet rs=null;
	private CallableStatement cstmt=null;//存储过程
	
	/**
	 * 程序中调用存储过程
	 * @throws Exception
	 */
	@Test
	public void testCall() {
		
		try {
			//1.注册驱动，获取连接对象
			conn=JdbcUtil.getConnection();
			//2.准备预编译的sql
			String sql="CALL proc_login";
			//3.执行预编译的sql
			cstmt=conn.prepareCall(sql);
			//4.执行sql 返回结果集
			rs=cstmt.executeQuery();
			
			
			//遍历结果，测试
			while(rs.next()){
				String name=rs.getString("userName");
				String pwd=rs.getString("pwd");
				System.out.println(name+","+pwd);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}finally{
			//关闭资源
			JdbcUtil.close(conn, cstmt, rs);
			
		}
		
		
	}
	
	
}
