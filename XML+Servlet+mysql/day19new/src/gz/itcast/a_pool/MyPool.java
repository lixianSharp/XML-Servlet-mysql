package gz.itcast.a_pool;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * 自行设计一个连接池
 * @author APPle
 *
 */
public class MyPool {
	private static String url = "jdbc:mysql://localhost:3306/day19";
	private static String user = "root";
	private static String password = "root";
	private static String driverClass = "com.mysql.jdbc.Driver";

	//存储连接对象的容器
	private static LinkedList<Connection> pool = new LinkedList<Connection>();
	
	public static LinkedList<Connection> getPool() {
		return pool;
	}

	public static void setPool(LinkedList<Connection> pool) {
		MyPool.pool = pool;
	}


	//连接池的初始化连接数
	private int initCount = 5;
	//连接池的最大连接数
	private int maxCount = 10;
	//用于记录当前连接数量
	private int currentCount = 0;
	
	static{
		//注册驱动
		try {
			Class.forName(driverClass);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public MyPool(){
		//获取连接,初始化连接对象
		for(int i=1;i<=initCount;i++){
			pool.addLast(createConnection());
			currentCount++;
		}
	}
	
	//创建连接的方法
	private Connection createConnection(){
		final Connection conn;
		try {
			//原来的真实的连接对象
			conn = DriverManager.getConnection(url, user, password);
			
			//1)使用静态代理类的方式去创建Connection的代理类
			//MyConnection myConn = new MyConnection(this,conn);
			
			//2)使用动态代理类的方式去创建Connection的代理类
			/**
			 *  使用到jdk的api：  Proxy类
			 *  		用于创建动态代理类对象：
			 *  		static Object newProxyInstance(
			 *  						ClassLoader loader,
			 *   						Class<?>[] interfaces, 
			 *   						InvocationHandler h
			 *   						)  
			 * 			参数一：类加载器。
			 * 			参数二： 代理类实现的接口列表
			 * 			参数三： 接口 InvocationHandler： 代理类的调用处理程序的接口。（代理完代理对象之后，对其中的方法如何处理？？？）
			 * 					Object invoke(
			 * 						Object proxy,  代理类对象      
			 * 						Method method,  代理类对象调用的方法。 
			 * 						Object[] args  调用代理类对象方法时传入的参数列表
			 * 					)   
			 * 							 
			 * 		
			 * 
			 */
			Connection myConn = (Connection)Proxy.newProxyInstance(
							MyPool.class.getClassLoader(), 
							new Class[]{Connection.class},
							new InvocationHandler(){
	
								@Override
								public Object invoke(Object proxy, Method method,
										Object[] args) throws Throwable {
									//1)重写需要重写的方法。close方法
									//获取当前调用的方法的方法名称
									String methodName = method.getName();
									if("close".equals(methodName)){
										MyPool.getPool().addLast(conn);
										return null;
									}else{
										//2)调用回原来的方法，获取返回值
										Object value = method.invoke(conn, args);
										//返回结果
										return value;
									}
								}
						    }
						);
			
			return myConn;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}	
	}
	
	/**
	 * 对外提供给java程序一个获取连接的方法
	 */
	public Connection getConnection(){
		//1）当并发连接树小于等于初始化连接数量的时候，才从池中取出
		if(pool.size()>0){
			System.out.println("1)======");
			//从容器中取出一个连接对象返回给java程序
			return pool.removeFirst();
		}
		
		//2)当并发数量超过初始化连接数据的时候，程序自行获取数连接对象.但是一旦超过了最大连接数的时候，不能获取
		if(currentCount<maxCount){
			System.out.println("2)=====");
			currentCount++;
			return createConnection();
		}
		
		//3)当超过了最大连接数时，不能再获取连接了。
		throw new RuntimeException("已经超过了最大连接数");
	}
	
	
	/**
	 * 对外提供释放连接对象的方法
	 */
	public void releaseConnection(Connection conn){
		//放回连接池容器中
		pool.addLast(conn);
	}	
}
