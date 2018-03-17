package c_PreparedStatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;

import Util.JdbcUtil;
/**
 * Statement和PreparedStatement的区别？
 * 		PreparedStatement可以防止sql注入，建议使用PreparedStatement
 */
/**
 * 模拟用户登录的效果
 * 
 * @author 贤元
 * 
 */

public class Demo2 {
	private String name = "aricwerwer' OR 1=1 -- ";// 如果这样的话，任意一个密码都可以登录成功
	private String password = "123456qwer";//

	private String name1 = "aric";
	private String password1 = "123456";

	/**
	 * Statement是存在sql被注入的风险的
	 */
	@Test
	public void testByStatement() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			// 1、注册驱动，获取连接对象
			conn = JdbcUtil.getConnection();
			// 2、创建Statement
			stmt = conn.createStatement();
			// 3、准备sql
			String sql = "SELECT * FROM users WHERE NAME='" + name + "' AND PASSWORD='" + password + "'";


			// 4、执行sql
			rs = stmt.executeQuery(sql);

			if (rs.next()) {
				// 登录成功
				System.out.println("登录成功");
			} else {
				System.out.println("登录失败");
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		} finally {
			// 释放资源
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
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException();
				}
			}
		}

	}

	/**
	 * PrepareStatement可以有效地防止sql被注入
	 */
	public void testByPreparedStatement() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			// 1、注册驱动，获取连接对象
			conn = JdbcUtil.getConnection();

			// 2、准备sql
			String sql = "SELECT * FROM users WHERE NAME=? AND PASSWORD=?";
			// 3、执行预编译的sql语句 (检查语法)
			stmt = conn.prepareStatement(sql);
			// 4、设置参数
			/**
			 * 参数一：参数位置 从1开始
			 */
			stmt.setString(1, name1);
			stmt.setString(2, password1);

			// 5、执行sql
			rs = stmt.executeQuery();

			if (rs.next()) {
				// 登录成功
				System.out.println("登录成功");
			} else {
				System.out.println("登录失败");
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		} finally {
			// 释放资源
			JdbcUtil.close(conn, stmt, rs);
		}

	}


}
