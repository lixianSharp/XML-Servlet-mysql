package gz.itcast.b_context;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JdbcUtil {

	//会自动加载src下的c3p0-config.xml文件。加载默认的配置
	private static ComboPooledDataSource ds=new ComboPooledDataSource();
	

	public static DataSource getDataSource() {
		// TODO Auto-generated method stub
		return ds;
	}
	
	
}
