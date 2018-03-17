package d_CallableStatement;

import static org.junit.Assert.*;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import Util.JdbcUtil;

/**
 * 使用CallableStatement 调用存储过程
 * @author 贤元
 *
 */
public class Demo1 {

	//创建执行的URL
	String url="jdbc:mysql://localhost:3306/day17";//jdbc协议:mysql自协议://主机:端口/要连接的数据库
	String user="root";//数据库名
	String password="root";//数据库密码
	
	
	
	/**
	 * 调用带有输入参数的存储过程
	 * CALL pro_findById(3);
	 * @throws Exception
	 */
	@Test
	public void testCstmt() {
		Connection conn=null;
		CallableStatement cstmt=null;
		ResultSet rs=null;
		
		try {
			//1.注册驱动程序 
			Class.forName("com.mysql.jdbc.Driver");
			//2. 获取连接对象
			DriverManager.getConnection(url,user,password);
			
			//3.准备预编译的sql
			/**
			 * CallableStatement是PreparedStatement的子接口，所以也可以执行预编译
			 */
			String sql="CALL pro_findById(?)";
			//4.执行预编译的sql
			cstmt=(CallableStatement) conn.prepareStatement(sql);
			
			//5.设置参数
			/**
			 * 参数一：参数位置 从1开始  参数二：你要设置的那个值
			 */
			cstmt.setInt(1, 2);
			
			//6.发送参数  返回结果集
			rs=cstmt.executeQuery();//注意：所有的调用存储过程的语句都使用executeQuery()方法执行
			
			//7.遍历结果
			while(rs.next()){
				int id=rs.getInt("id");
				String name=rs.getString("name");
				String gender=rs.getString("gender");
				System.out.println(id+","+name+","+gender);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally{
			//关闭资源
			JdbcUtil.close(conn, cstmt,rs);
		}
		
	}
	
	/**
	 * 执行带有输入输出参数的存储过程
	 * CALL pro_findById2(3,@NAME);
	 * @throws Exception
	 */
	@Test
	public void test2()  {
		Connection conn=null;
		CallableStatement cstmt=null;
		ResultSet rs=null;
		try {
			//1、注册驱动
			 Class.forName("com.mysql.jdbc.Driver");
			//2.获取连接对象
			conn=DriverManager.getConnection(url, user, password);				
			//3、准备预编译的sql
			String sql="CALL pro_findById2(?,?)";//第一个?(问号)是输入参数，第二个?(问号)是输出参数
			//4、预编译(检查语法)
			cstmt=conn.prepareCall(sql);
			//5、设置输入输出参数
			/**
			 * 参数一：参数位置 从1开始
			 */
			//设置输入参数
			cstmt.setInt(1, 2);
			
			//设置输出参数(注册输出参数)
			/**
			 * 参数一：参数的位置
			 * 参数二：存错过程中的输出参数的jdbc类型   VARCHAR(20)
			 */
			cstmt.registerOutParameter(2, java.sql.Types.VARCHAR);
			
			//6.发送参数，执行sql 返回结果集
			rs=cstmt.executeQuery();

			//7.得到输出参数的值
			/**
			 * 索引值：预编译sql中的输出参数的位置
			 */
			String result=cstmt.getString(2);//getXXX方法专门用于获取存储过程中的输出参数
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally{
			//关闭资源
			JdbcUtil.close(conn, cstmt, rs);
		}
	}
	
	
}
