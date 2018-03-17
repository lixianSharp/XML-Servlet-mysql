package cn.itcast.d_c3p0;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.junit.Test;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class App {

	//1、硬编码方式，使用C3P0连接池管理连接，
	@Test
	public void testCode() throws Exception {
		//创建连接池核心工具类
		ComboPooledDataSource dataSource=new ComboPooledDataSource();
		//设置连接参数：url、驱动、用户密码、初始连接数、最大连连接数
		dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/day19");
		dataSource.setDriverClass("com.mysql.jdbc.Driver");
		dataSource.setUser("root");
		dataSource.setPassword("root");
		dataSource.setInitialPoolSize(3);
		dataSource.setMaxPoolSize(6);
		dataSource.setMaxIdleTime(1000);
		
		//---->从连接池对象中获取连接对象
		Connection con=dataSource.getConnection();
		//执行更新
		con.prepareStatement("delete from admin where id=3").executeUpdate();
		//关闭
		con.close();
		System.out.println("ok,操作成功");
		
		
	}
	
	
	
	
	
	
	/**
	 * 2、XML配置方式，使用C3P0连接池管理连接
	 * 		注意：文件名写成c3p0-config.xml,并且放在src目录下，它会自动加载src下的c3p0配置文件，如果文件名不是这个，则需要在new ComboPooledDataSource();中的括号中写上你的那个c3p0配置文件名
	 * @throws Exception
	 */
	@Test
	public void testXML() throws Exception {
		//创建c3p0连接池核心工具类
		//自动加载src下c3p0配置文件【c3p0-config.xml】
		ComboPooledDataSource dataSource=new ComboPooledDataSource();
		
		//获取连接
		Connection con =dataSource.getConnection();
		//执行更新
		//执行预编译的sql
		PreparedStatement pstmt = con.prepareStatement("delete from admin where id=2");
		//执行sql
		int count = pstmt.executeUpdate();
		System.out.println("影响了:"+count+"行");
		//关闭
		con.close();
	}
	
	
	
}
