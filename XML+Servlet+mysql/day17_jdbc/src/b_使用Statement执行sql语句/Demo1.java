package b_使用Statement执行sql语句;

import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.Test;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

/**
 * 使用Statement对象执行静态sql语句
 * @author 贤元
 *
 */
public class Demo1 {

	//连接数据库的URL
	private String url="jdbc:mysql://localhost:3306/day17";//jdbc协议:mysql子协议://主机:端口/要连接的数据库
	private String user="root";//用户名
	private String password="root";//密码
	
	/**
	 * 执行DDL语句(创建表)
	 */
	@Test
	public void test1(){
		java.sql.Statement stmt=null;
		java.sql.Connection conn=null;
		
		try {
			//1.注册驱动程序  通过得到字节码对象的方式加载静态代码块，从而注册驱动程序
			Class.forName("com.mysql.jdbc.Driver");
			
			//2.获取连接对象   通过驱动管理器类来获取连接对象
			conn= DriverManager.getConnection(url, user, password);
			
			//3.创建Statement  用于发送sql语句
			stmt= conn.createStatement();
			
			//4.准备sql
			String sql="CREATE TABLE student(id INT PRIMARY KEY AUTO_INCREMENT,NAME VARCHAR(20),gender VARCHAR(2))";
			
			//5.发送sql语句，执行sql语句 返回值为影响的行数
			int count=stmt.executeUpdate(sql);
			
			//6.输出
			System.out.println("影响了"+count+"行");
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally{
			//7.关闭连接(顺序：后打开的先关闭)
			if(stmt!=null){
				try {
					stmt.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					throw new RuntimeException();
				}
			}
			if(conn!=null){
				try {
					conn.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					throw new RuntimeException();
				}
			}
		}
		
		
	}
	
	
}
