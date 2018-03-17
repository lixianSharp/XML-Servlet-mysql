package gz.itcast.a_auto2;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import util.JdbcUtil;

/**
  一些知识点：JDBC
   JDBC技术：Java数据库连接技术，
   接口：
   Connection：连接对象
   PreparedStatement：执行命令对象:把SQL语句发送到数据库执行
   ResultSet： (在现实)结果集接口，必须要保持与数据库的连接！
   
   JDBC开发步骤：
    1、建项目，引入数据库驱动包。
    2、加载驱动
       Class.forName()
    3、获取连接对象
    4、创建执行sql语句的pstmt对象;写sql
    5、执行sql：
      a）更新：  delete/insert/update
          |  executeUpdate（）；
	  b)查询： select
	      | executeQuery();
	6、关闭/异常      
	

 * @author 贤元
 *
 */
//插入数据获取插入数据的自增长值
public class Demo1 {

	public Connection conn=null;
	public PreparedStatement pstmt=null;
	public ResultSet rs=null;
	
	
	//创建要执行的URL
	String url="jdbc:mysql://localhost:3306/day18";//jdbc协议：mysql数据库自协议://主机:端口/要连接的数据库
	String user="root";//用户名
	String password="root";//密码
	
	
	
	/**
	 * 先演示普通插入，没有获取自增长
	 * @throws Exception
	 */
	@Test
	public void testGetAutoIncrement1() {
		
		try {
			//1.注册驱动  通过加载字节码对象来加载静态代码块，从而注册驱动程序
			Class.forName("com.mysql.jdbc.Driver");
			//2.获取连接对象   通过驱动管理器类来获取连接对象
			conn=DriverManager.getConnection(url, user, password);
			
			//3.准备预编译的sql
			String sql="INSERT INTO users(userName,pwd)VALUES(?,?)";
			//4创建pstmt对象   执行预编译的sql
			pstmt=conn.prepareStatement(sql);
			
			//5.设置参数
			/**
			 * 参数一：参数位置 从1开始
			 */
			pstmt.setString(1, "李贤元");
			pstmt.setString(2, "lixianyuan");
			//发送参数 执行sql  返回影响的行数
			int count=pstmt.executeUpdate();
			System.out.println("影响了"+count+"行");
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally{
			//关闭资源
			JdbcUtil.close(conn, pstmt, rs);
		}
		
		
	}
	
	
	
	/**
	 * 2.插入同时获取自增长
	 * @throws Exception
	 */
	@Test
	public void testGetAutoIncrement2(){
		
		try {
			//1、注册驱动
			Class.forName("com.mysql.jdbc.Driver");
			//2. 获取连接对象  通过驱动管理器类来获取连接对象
			conn=DriverManager.getConnection(url, user, password);
			
			//3.准备预编译的sql
			String sql="INSERT INTO users(userName,pwd)VALUES(?,?)";
			//4.执行预编译的sql 返回pstmt对象  【在参数中，指定返回自增长列】
			pstmt=conn.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
			
			//5.设置参数
			/**
			 * 参数一： 参数位置  从1开始
			 */
			pstmt.setString(1, "lixianyuan.org");
			pstmt.setString(2, "李贤");
			
			//6、发送参数 执行sql  返回影响的行数
			int count=pstmt.executeUpdate();
			System.out.println("影响了"+count+"行");
			
			//【在执行更新后，获取自增长列】
			rs = pstmt.getGeneratedKeys();//获取子增长列，也就是部门表中当前的(上面部门表中插入的那行数据)的主键id的值
			if (rs.next()) {
				System.out.println("刚才插入数据的自增长编号是：" + rs.getInt(1));//也就是刚才插入的数据的编号
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally{
			//释放资源
			JdbcUtil.close(conn, pstmt, rs);
		}
	}
	
}
