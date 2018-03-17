package b_使用Statement执行sql语句;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

/**
 * 使用Statement执行DQL语句
 * 
 * @author 贤元
 * 
 */
public class Demo3 {

	// 执行数据库的URL
	// jdbc协议:mysql数据库自协议://主机:端口/要连接的数据库
	String url = "jdbc:mysql://localhost:3306/day17";
	String user = "root";// 数据库名
	String password = "root";// 密码

	Statement stmt = null;
	Connection conn = null;

	@Test
	public void test1() {
		try {
			// 1.注册驱动程序 通过加载静态的字节码文件从而加载静态代码块，从而注册驱动程序
			Class.forName("com.mysql.jdbc.Driver");
			// 2.获取连接对象
			conn = DriverManager.getConnection(url, user, password);
			// 3.创建Statement对象
			stmt = conn.createStatement();
			// 4.准备一个sql
			String sql = "select * from student";
			// 5.执行sql返回一个ResultSet
			ResultSet rs = stmt.executeQuery(sql);

			// 遍历结果(不推荐使用这种)
			// t1(rs);

			// 遍历结果(遍历所有结果)
			while (rs.next()) {//是一行数据一行数据进行遍历的哦！
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String gender = rs.getString("gender");
				System.out.println(id + "," + name + "," + gender);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		} finally {
			// 关闭资源
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

	public void t1(ResultSet rs) {
		try {
			// 移动光标
			boolean flag = rs.next();

			flag = rs.next();
			flag = rs.next();// 拿到第三行的值，因为走了三个next()

			if (flag) {

				// 取出列的值
				// 根据索引取值

				int id = rs.getInt(1);
				String name = rs.getString(2);
				String gender = rs.getString(3);
				System.out.println(id + "," + name + "," + gender);

				// 根据列名称取值
				int id1 = rs.getInt("id");
				String name1 = rs.getString("name");
				String gender1 = rs.getString("gender");
				System.out.println(id1 + "," + name1 + "," + gender1);

			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

}
