package c_PreparedStatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

/**
 * 使用PreparedStatement执行sql语句
 * 
 * 注意：  查询的应该用executeQuery();
 * 		增、删、改 用executeUpdate();
 * 
 *步骤：
 *	1、注册驱动程序 
 *	2.获取连接对象(通过驱动管理器类来获取连接对象)  
 *	3.准备预编译的sql  
 *	4.执行预编译的sql  
 *	5.设置参数(参数一： 参数位置  从1开始) 
 *	6.发送参数，执行sql  
 *	7.释放资源(关闭资源)
 * 
 * 
 * @author 贤元
 * 
 */
public class Demo1 {

	Connection conn = null;
	PreparedStatement pstmt = null;

	// 创建执行的URL
	// jdbc协议:mysql数据库自协议://主机:端口/要连接的数据库
	String url = "jdbc:mysql://localhost:3306/day17";
	String user = "root";// 数据库名
	String password = "root";// 密码

	/**
	 * 增加
	 * 
	 * @throws Exception
	 */
	@Test
	public void testInsert() {

		try {
			// 1.注册驱动 ， 通过得到字节码对象的的方式加载静态代码块，从而注册驱动程序
			Class.forName("com.mysql.jdbc.Driver");
			// 2.获取连接对象  通过驱动管理器类来获取连接对象 
			conn = DriverManager.getConnection(url, user, password);

			// 3.准备预编译的sql语句(检查语法)
			String sql = "INSERT INTO student(id,NAME,gender)VALUES(?,?,?)";// ？号表示一个参数的占位符
			// 4.执行预编译的sql
			pstmt = conn.prepareStatement(sql);

			// 5.设置参数的值
			/**
			 * 参数一：参数位置 从1开始 参数二：用户要写入的值
			 */
			pstmt.setInt(1, 3);
			pstmt.setString(2, "刘志强");
			pstmt.setString(3, "男");
			// 6.发送参数，执行sql 返回影响的行数
			int count = pstmt.executeUpdate();

			System.out.println("影响了" + count + "行");
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			// 释放资源
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					throw new RuntimeException(e);
				}
			}
			if (conn != null) {
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

	/**
	 * 修改
	 * 
	 * @throws Exception
	 */
	@Test
	public void testUpdate() {
		try {
			// 1.注册驱动程序 通过加载字节码对象的方式加载静态代码块，从而注册驱动程序
			Class.forName("com.mysql.jdbc.Driver");
			// 2.获取连接对象 通过驱动管理器类来获取连接对象
			conn = DriverManager.getConnection(url, user, password);

			// 3.准备预编译的sql
			String sql = "UPDATE student SET NAME=? WHERE id=?";
			// 4.执行预编译的sql
			pstmt = conn.prepareStatement(sql);

			// 5.设置参数的值
			/**
			 * 参数一： 参数位置 从1开始 参数二： 各个参数位置设置的对应的值
			 */
			pstmt.setString(1, "狗娃");
			pstmt.setInt(2, 3);

			// 6.发送参数，执行sql 返回值为影响的行数
			int count = pstmt.executeUpdate();

			System.out.println("影响了" + count + "行");
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			// 关闭资源
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException(e);
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					throw new RuntimeException();
				}
			}
		}

	}

	/**
	 * 删除
	 * @throws Exception
	 */
	@Test
	public void testDelete() throws Exception {
		//1.注册驱动程序
		Class.forName("com.mysql.jdbc.Driver");
		//2.获取连接对象    通过驱动管理器类来获取连接对象
		conn=DriverManager.getConnection(url, user, password);
		
		//3.创建预编译的sql
		String sql="DELETE FROM student WHERE id=?";
		//4.执行预编译
		pstmt=conn.prepareStatement(sql);
		
		//5.设置参数
		/**
		 * 参数一：参数位置  从1开始
		 * 参数二： 各个参数位置对应的你要输入的值
		 */
		pstmt.setInt(1, 3);
		
		
		
		//6.发送参数  执行sql   返回影响的行数
 		int count=pstmt.executeUpdate();
		
 		
 		
 		
		
	}
	
	/**
	 * 查询
	 * @throws Exception
	 */
	@Test
	public void testQuery(){
		ResultSet rs=null;
		
		try {
			//1.注册驱动程序
			Class.forName("com.mysql.jdbc.Driver");
			//2.获取连接对象  通过驱动管理器类获取连接对象
			conn=DriverManager.getConnection(url, user, password);
			
			//3.准备预编译的sql
			String sql="select * from student";
			//4.执行预编译的sql
			pstmt=conn.prepareStatement(sql);
			
			//5.发送参数，执行sql
			rs=pstmt.executeQuery();
			
			//6.遍历
			while(rs.next()){//他的遍历是一行数据一行数据进行遍历的
				int id=rs.getInt("id");
				String name=rs.getString("name");
				String gender=rs.getString("gender");
				System.out.println(id+","+name+","+gender);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally{
			//7.释放资源
			if(pstmt!=null){
				try {
					pstmt.close();
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException();
				}
			}
			if(conn!=null){
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException();
				}
			}
			
		}
		
		
		
	}
	
	
}
