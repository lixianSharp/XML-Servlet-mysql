package gz.itcast;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * C3p0连接池版本的jdbcUtil
 * @author APPle
 *
 */
public class JdbcUtil {
	//一个数据库只需要一个连接池对象。使用c3p0连接池，会自动加载src目录下的c3p0-config.xml配置文件
	private static DataSource ds  = new ComboPooledDataSource(); 
	
	/**
	 * 获取连接池对象的方法
	 */
	public static DataSource getDataSource(){
		return ds;
	}
	
	/**
	 * 获取连接对象的方法
	 */
	public static Connection getConnectio(){
		try {
			return ds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
}
