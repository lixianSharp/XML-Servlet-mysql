package gz.itcast.a_pool;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 测试自行开发的连接池工具
 * @author APPle
 *
 */
public class TestPool {

	public static void main(String[] args) {
		//1)构造连接池对象
		MyPool myPool = new MyPool(); //初始化连接
		//模拟用户并发获取连接
		for(int i=1;i<=11;i++){
			Connection conn = myPool.getConnection(); //如果是小于等于初始连接数的时候，不需要真的连接数据库
			System.out.println(conn);
			
			if(i==3){
				//模拟用户释放连接,把连接放回连接池中(注意：使用完连接对象，一定要记得把连接放回连接池中，以供其他用户使用)
				//myPool.releaseConnection(conn);
				
				//如果用户还是使用Connection的close方法来释放连接，会有什么问题？
				
				/**
				 *  解决办法：　用户调用close()方法也能够成功把连接对象放回连接池中
				 *  	1)静态代理类。重写Connection的close方法
				 *  	2)动态代理类。重写Connection的close方法
				 */
				try {
					conn.close(); //其实本质是把连接返回连接池。而不是真的关闭连接。
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}	
		}
	}
}
