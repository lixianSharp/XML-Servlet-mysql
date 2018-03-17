package a_连接数据库的三种方式;

import java.sql.DriverManager;
import java.util.Properties;

import org.junit.Test;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Driver;

/**
 * 之前操作数据：
 * 	1）通过mysql的客户端工具，登录数据库服务器(mysql -u root -p 密码)
 *  2）编写sql语句
 *  3）发送sql语句到数据库服务器执行
 * 
 * 
 * 什么是jdbc？就是使用代码(程序)发送sql的技术，就是jdbc技术！！！
 * 
 * 使用jdbc发送sql前提：
 * 		登录数据库服务器(连接数据库服务器)
 * 			数据库的IP地址
 * 			端口
 * 			数据库用户名
 * 			密码
 * 
 *案例：jdbc连接数据库 
 * 
 * @author 贤元
 *
 */
public class ThreeMethod {

	//连接数据库的URL
	//url格式：  jdbc协议:数据库自协议://主机:端口/连接的数据库
	private String url="jdbc:mysql://localhost:3306/day17";
	//连接数据库的用户名
	private String user="root";//用户名
	//连接数据库的密码
	private String password="root";//密码
	
	/**
	 * 第一种方法
	 * @throws Exception 
	 */
	@Test
	public void test1() throws Exception{
		//1、创建驱动程序类对象
		Driver driver=new com.mysql.jdbc.Driver();//新版本
		//Driver driver = new org.gjt.mm.mysql.Driver(); //旧版本
		
		//设置用户名和密码,也就是先将用户名和密码存储在Properties对象中
		Properties props=new Properties();
		props.setProperty("user", user);
		props.setProperty("password", password);
		
		//3、连接数据库，返回连接对象
		Connection conn=(Connection) driver.connect(url, props);
		System.out.println("数据库连接成功！"+conn);
	}
	
	/**
	 * 第二种方法
	 * 使用驱动管理器类连接数据库(注册了两次，没必要)
	 */
	@Test
	public void test2() throws Exception {
		//1、创建驱动程序类对象
		Driver driver=new com.mysql.jdbc.Driver();//新版本
		//Driver driver2 =new com.oracle.jdbc.Driver();//旧版本

		//2.注册驱动程序(可以注册多个驱动程序)
		DriverManager.registerDriver(driver);
		
		//3.连接到具体的数据库
		Connection conn=(Connection) DriverManager.getConnection(url, user, password);
		System.out.println(conn+"  第二种方法连接数据库成功");
	}
	
	/**
	 * (推荐使用这种方式连接数据库)
	 * 推荐使用  加载驱动程序类  来注册驱动程序
	 */
	@Test
	public void test3() throws Exception {
		//1.通过得到字节码对象的方式加载静态代码块，从而注册驱动程序
		Class.forName("com.mysql.jdbc.Driver");
		
		//2.连接到具体的数据库,通过驱动管理器类来实现
		Connection conn=(Connection) DriverManager.getConnection(url, user, password);
		System.out.println(conn+" 使用第三种方法连接数据库成功！");
	}
	
}
