package gz.itcast.b_dbcp;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;
import org.junit.Test;

/**
 * 使用dbcp连接池工具
 * @author APPle
 *
 */
public class Demo1 {
	private static String url = "jdbc:mysql://localhost:3306/day19";
	private static String user = "root";
	private static String password = "root";
	private static String driverClass = "com.mysql.jdbc.Driver";
	
	public static void main(String[] args) {
		//test1();
		
	
		/**
		 * 使用配置文件方式
		 */
		//4)获取连接
		try {
			//1)使用工厂类来创建dbcp连接池对象（读取配置文件方式）
			Properties prop = new Properties();
			//使用类路径读取配置文件  普通java项目中，  / 代表java的运行目录bin目录下
			InputStream in = Demo1.class.getResourceAsStream("/jdbc.properties");
			//加载配置文件
			prop.load(in);
			//通过基本的数据源工厂加载prop配置并且创建数据源
			BasicDataSource bds = (BasicDataSource)BasicDataSourceFactory.createDataSource(prop);
			/**
			 * 读取jdbc.properties文件内容
			 *   dbcp可以自动识别每个配置信息，但是约定前提： 配置文件的key名称和设置方法的名称保持一致！！！
			 */
			
			//从连接池中获取连接
			for(int i=1;i<=13;i++){
				Connection conn = bds.getConnection(); //注意： 这里返回的Connection对象，不是原来的Connection，而是代理后的Connection对象
				System.out.println(conn);
				
				//注意： 使用连接池，记住释放连接
			/*	if(i==3){
					conn.close();// 把连接对象放回连接池中的。连接池中最大能够保存最大连接数的连接对象
				}*/
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 没有配置文件的做法
	 */
	public  void test1() {
		//1)创建dbcp连接池对象
		BasicDataSource bds = new BasicDataSource();
		
		//2)设置连接参数
		bds.setUrl(url);
		bds.setUsername(user);
		bds.setPassword(password);
		bds.setDriverClassName(driverClass);
		
		//3)设置连接池参数
		bds.setInitialSize(5);//初始化连接
		bds.setMaxActive(10);//最大连接数
		bds.setMaxWait(3000);//当超过最大连接数时，最大等待时间为3秒
		
		//4)获取连接
		try {
			//从连接池中获取连接
			for(int i=1;i<=11;i++){
				Connection conn = bds.getConnection(); //注意： 这里返回的Connection对象，不是原来的Connection，而是代理后的Connection对象
				System.out.println(conn);
				
				//注意： 使用连接池，记住释放连接
				if(i==3){
					conn.close();// 把连接对象放回连接池中的。连接池中最大能够保存最大连接数的连接对象
					//如果这行注释掉的话就会抛异常：Timeout waiting for idle object   超过了连接池的最大连接数
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
