package gz.itcast.e_metadata;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * 数据库元数据
 * @author APPle
 *
 */
public class Demo1 {

	public static void main(String[] args) throws Exception {
		ComboPooledDataSource ds = new ComboPooledDataSource();
		
		Connection conn = ds.getConnection();
		
		//System.out.println(conn);
		
		/**
		 * 使用数据库元数据（DatabaseMetaData）
		 */
		DatabaseMetaData dbmd = conn.getMetaData();
		
		//指定哪个数据库
		System.out.println(dbmd.getDatabaseProductName());
		
		//数据库版本
		System.out.println(dbmd.getDatabaseMajorVersion());
		System.out.println(dbmd.getDatabaseMinorVersion());
		
		
		//使用驱动程序的版本
		System.out.println(dbmd.getDriverMajorVersion());
		System.out.println(dbmd.getDriverMinorVersion());
	}
}
