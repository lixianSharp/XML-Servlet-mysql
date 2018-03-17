package gz.itcast.util;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * 连接池版本的jdbcUtil
 * @author 贤元
 *
 */
public class JdbcUtil {

	//创建连接池对象
	private static DataSource ds=new ComboPooledDataSource();
	
	/**
	 * 获取连接池对象
	 */
	public static DataSource getDataSource(){
		//注意：不要这么写，这样会导致连接很快就用完了
		//private static DataSource ds=new ComboPooledDataSource();
		return ds;
	}

}
