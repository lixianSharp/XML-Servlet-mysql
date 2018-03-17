package cn.itcast.c_dbcp;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;
import org.junit.Test;

public class App_DBCP {

	/**
	 * 1、硬编码方式实现连接池
	 * @throws Exception
	 */
	//@Test
	public void testDbcp() throws Exception {
		//DBCP连接池核心类
		BasicDataSource dataSource=new BasicDataSource();
		//连接池参数配置，初始化连接数，最大连接数/连接字符串、驱动、用户、密码
		dataSource.setUrl("jdbc:mysql:///day19");//数据库连接字符串
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");//数据库驱动
		dataSource.setUsername("root");//数据库连接用户
		dataSource.setPassword("root");//数据库连接密码
		dataSource.setInitialSize(3);//初始化连接
		dataSource.setMaxActive(6);//最大连接数
		dataSource.setMaxIdle(3000);//最大空闲时间 单位是秒
		
		//获取连接
		Connection con=dataSource.getConnection();
		//预编译的sql
		String sql = "update admin set name='陈六' where id=1";
		//执行预编译的sql
		PreparedStatement pstmt = con.prepareStatement(sql);
		//执行sql
		int count = pstmt.executeUpdate();
		System.out.println("影响了:"+count+"行");
		//关闭
		con.close();
		
	}
	
	
	
	/**
	 * 【推荐使用这种】便于项目的维护
	 * 2、配置方式实现连接池，将连接池参数放在一个配置文件中存放
	 * @throws Exception
	 *注意：如果配置文件中的url读取不出来，是里面的转义符的问题， \: 才代表 :   要对冒号进行转义
	 */
	@Test
	public void testProp() throws Exception {
		//加载prop配置文件
		Properties prop=new Properties();
		//获取文件流
		InputStream inStream=App_DBCP.class.getResourceAsStream("db.properties");
		//加载属性配置文件
		prop.load(inStream);
		
		String url = prop.getProperty("url");
		//System.out.println(url);//jdbc:mysql://localhost:3306/day19
		//根据prop配置，直接创建数据源对象。通过基本的数据源先加载prop配置并且创建数据源
		DataSource dataSource=BasicDataSourceFactory.createDataSource(prop);
	
		//获取连接
		Connection  con=dataSource.getConnection();//根据数据源对象获取连接
		//System.out.println(con);//jdbc:mysql://localhost:3306/day19, UserName=root@localhost, MySQL-AB JDBC Driver
		con.prepareStatement("delete from admin where id=2").executeUpdate();
		//关闭
		con.close();
		
		//System.out.println("ok");
	
	}
	
	public static void main(String[] args) throws Exception {
		new App_DBCP().testProp();
	}
}
