package gz.itcast.c_c3p0;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * 使用c3p0连接池
 * @author APPle
 *
 */
public class Demo1 {
	private static String url = "jdbc:mysql://localhost:3306/day18";
	private static String user = "root";
	private static String password = "root";
	private static String driverClass = "com.mysql.jdbc.Driver";


	public static void main(String[] args) throws Exception {
		//test1();
		
		
		/**
		 * 使用xml配置文件读取c3p0的方法
		 * 	注意：
		 * 		1）需要把一个名称为c3p0-config.xml文件放在src目录下，c3p0会自动读取这个文件。
		 * 		2)xml配置文件中属性名称和手动设置方法名保持一致！！！！c3p0则会自动读取
		 */
		
		//1)创建连接池对象
		
		/**
		 *   1)new ComboPooledDataSource(): 使用没有参数的构造方法，读取的是default-config的配置信息
		 *   2)new ComboPooledDataSource(name的属性值)： 使用有参的构造方法，读取的是name-config配置信息
		 */
		//ComboPooledDataSource cds = new ComboPooledDataSource();
		ComboPooledDataSource cds = new ComboPooledDataSource("oracle");
		
		//2)获取连接
		for(int i=1;i<=13;i++){
			Connection conn = cds.getConnection();
			System.out.println(conn);
			
			//关闭第3个
			if(i==3){
				conn.close();//本质是把连接对象放回连接池中
			}
		}
	}

	/**
	 * 没有配置文件的方式
	 * @throws PropertyVetoException
	 * @throws SQLException
	 */
	private static void test1() throws PropertyVetoException, SQLException {
		//1)创建连接池对象
		ComboPooledDataSource cds = new ComboPooledDataSource();
		
		//2)设置连接参数
		cds.setJdbcUrl(url);
		cds.setUser(user);
		cds.setPassword(password);
		cds.setDriverClass(driverClass);
		
		//3)设置连接池相关的参数
		cds.setInitialPoolSize(5);//初始化连接数
		cds.setMaxPoolSize(10);//最大连接数
		cds.setCheckoutTimeout(3000);//最大等待时间
		cds.setMinPoolSize(3); //最小连接数
		
		//4)获取连接
		for(int i=1;i<=11;i++){
			Connection conn = cds.getConnection();
			System.out.println(conn);
			
			//关闭第3个
			if(i==3){
				conn.close();//本质是把连接对象放回连接池中
			}
		}
	}
}
