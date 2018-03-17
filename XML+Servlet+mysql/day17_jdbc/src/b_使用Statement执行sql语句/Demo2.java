package b_使用Statement执行sql语句;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

//执行DML语句
/**
 * 步骤：    1.通过加载字节码对象的方式加载静态代码块，从而注册驱动程序 
 * 		 2.获取连接对象 
 * 		 3.创建Statement对象 
 * 		 4.准备sql  
 * 		 5.执行sql
 * 		 6.打印结果
 * 		 7、关闭流(关闭资源)
 */

/**
 * 使用Statement执行DML语句
 * @author 贤元
 *
 */
public class Demo2 {

	//执行数据库的URL
	//jdbc协议：mysql数据库自协议://主机：端口/要连接的数据库
	private String url="jdbc:mysql://localhost:3306/day17";
	
	private String user="root";//用户名
	private String password="root";//密码
	
	/**
	 * 增加
	 * @throws Exception
	 */
	@Test
	public void testInsert() {
		Statement stmt=null;
		Connection conn=null;
		
		try {
			//1.注册驱动程序  通过加载静态的字节码对象获取连接对象，从而注册驱动程序
			Class.forName("com.mysql.jdbc.Driver");
			//2.获取连接对象
			conn=DriverManager.getConnection(url, user, password);
			
			//3. 创建Statement  用于发送sql
			stmt=conn.createStatement();
			
			//4.准备sql
			String sql="insert into student(id,name,gender) values(1,'lixian','男')";
			
			//5.发送sql语句，执行sql语句  返回值为影响的行数
			int count=stmt.executeUpdate(sql);
			
			//6.输出
			System.out.println("影响了" + count+"行");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally{
			//7.释放资源
			if(stmt!=null){
				try {
					stmt.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					throw new RuntimeException(e);
				}
			  if(conn!=null){
				  try {
					conn.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					throw new RuntimeException(e);
				}
			  }
			}
			
			
		}
		
		
		
	}
	
	
	/*
	 * 修改
	 */
	@Test
	public void testUpdate() {
		Connection conn = null;
		Statement stmt = null;
		// 模拟用户输入
		String name = "陈六";
		int id = 1;
		try {
			// 1、注册驱动
			Class.forName("com.mysql.jdbc.Driver");
			// 2、获取连接对象
			conn = DriverManager.getConnection(url, user, password);
			// 3、创建Statement对象
			stmt = conn.createStatement();
			// 4、准备sql
			String sql = "UPDATE student SET NAME='"+name+"' WHERE id="+id+"";
			System.out.println(sql);
			// 5、发送sql 执行sql 返回结果，返回的结果为影响的行数
			int count = stmt.executeUpdate(sql);
			// 6、输出
			System.out.println("影响了：" + count + "行");
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		} finally {
			// 7、释放资源
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException(e);
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException();
				}
			}
		}

	}
	
	
	
	/*
	 * 删除
	 */
	public void testDelete(){
		Connection conn = null;
		Statement stmt = null;
	
		try {
			// 1、注册驱动
			Class.forName("com.mysql.jdbc.Driver");
			// 2、获取连接对象
			conn = DriverManager.getConnection(url, user, password);
			// 3、创建Statement对象
			stmt = conn.createStatement();
			// 4、准备sql
			String sql = "DELETE FROM student ";//删除表中的所有数据
			System.out.println(sql);
			// 5、发送sql 执行sql 返回结果，返回的结果为影响的行数
			int count = stmt.executeUpdate(sql);
			// 6、输出
			System.out.println("影响了：" + count + "行");
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		} finally {
			// 7、释放资源
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException(e);
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException(e);
				}
			}
		}
	}
	
}
