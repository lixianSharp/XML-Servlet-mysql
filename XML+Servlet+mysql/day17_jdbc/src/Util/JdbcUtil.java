package Util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtil {

	// 创建执行的URL
	private static String url = null;// jdbc协议:mysql自协议://主机:端口/要连接的数据库
	private static String user = null;// 数据库名
	private static String password = null;// 数据库密码
	private static String driverClass=null;
	
	/**
	 * 静态代码块(只加载一次)
	 */
	static{
		
		try {
			//读取db.properties配置文件
			Properties props=new Properties();
			
			/**
			 * . 代表Java命令的运行目录 在Java项目下, . Java命令的运行目录从该项目的根目录开始 在web项目下, . java
			 * 命令的的运行目录从tomcat/bin目录开始 所以不能使用 .(点)
			 */
			// FileInputStream in =new FileInputStream("./src/db.properties");

			/**
			 * 应该使用类路径的读取方式 / :斜杠表示classpath的根目录 classpath的根目录在哪里？
			 * 如果是Java项目的时候，classpath的根目录从bin目录开始
			 * 在web项目下，classpath的根目录从WEB-INf/classes目录开始
			 * 
			 */
			InputStream in=JdbcUtil.class.getResourceAsStream("/db.properties");
			
			//加载文件
			props.load(in);
			//读取文件
			//url=props.getProperty("url");
			url = "jdbc:mysql://localhost:3306/day17";
			user=props.getProperty("user");
			password=props.getProperty("password");
			driverClass=props.getProperty("driverClass");
			
			
			
			//注册驱动程序
			Class.forName(driverClass);
			//Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		
		
	}
	
	
	
	
	
	/**
	 * 获取连接对象的方法
	 */
	public static Connection getConnection(){
		try {
			Connection conn=DriverManager.getConnection(url, user, password);
			//Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/day17", "root", "root");
			return conn;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	
	
	
	//以下四个方法是用于关闭资源(释放资源)的各个重载方法
	public static void close(Connection conn, PreparedStatement pstmt,CallableStatement cstmt, ResultSet rs) {
		//关闭资源
		if(cstmt!=null){
			try {
				cstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(pstmt!=null){
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(rs!=null){
			try {
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
		if(conn!=null){
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}

	}

	public static void close(Connection conn, CallableStatement cstmt,ResultSet rs) {
		// 关闭资源
		if (cstmt != null) {
			try {
				cstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (rs != null) {
			try {
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
	}

	public static void close(Connection conn, PreparedStatement pstmt,ResultSet rs) {
		
		if(pstmt!=null){
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(rs!=null){
			try {
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
		if(conn!=null){
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
	}

	public static void close(Connection conn, PreparedStatement pstmt) {	
		if(pstmt!=null){
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
		if(conn!=null){
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
	}
}