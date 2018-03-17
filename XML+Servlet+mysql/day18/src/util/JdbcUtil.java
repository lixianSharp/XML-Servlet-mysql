package util;

import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtil {

	public static Connection conn;

	// 静态代码块，只加载一次
	static {
		try {
			// 首先得把配置文件中的数据读取出来
			Properties props = new Properties();

			//java普通项目中，/斜杠代表java的运行目录bin目录下
			InputStream in = JdbcUtil.class.getResourceAsStream("/db.properties");

			// 将配置文件中的数据读取出来
			props.load(in);

			//String url = props.getProperty("url");
			String url = "jdbc:mysql://localhost:3306/day18";
			String user = props.getProperty("user");
			String password = props.getProperty("password");
			String className = props.getProperty("className");
			// 注册驱动
			Class.forName(className);
			// 获取连接对象
			conn = conn = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}

	}

	
	// 获取连接对象
	public static Connection getConnection() {
		return conn;
	}


	// 以下四个方法是用于关闭资源(释放资源)的各个重载方法
	public static void close(Connection conn, PreparedStatement pstmt,
			CallableStatement cstmt, ResultSet rs) {
		// 关闭资源
		if (cstmt != null) {
			try {
				cstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
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

	public static void close(Connection conn, CallableStatement cstmt,
			ResultSet rs) {
		// 关闭资源
		if (cstmt != null) {
			try {
				cstmt.close();
			} catch (SQLException e) {
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

	public static void close(Connection conn, PreparedStatement pstmt,
			ResultSet rs) {

		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
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

	public static void close(Connection conn, PreparedStatement pstmt) {
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
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

}
