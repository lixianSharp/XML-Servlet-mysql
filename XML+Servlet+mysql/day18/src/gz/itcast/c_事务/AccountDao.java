package gz.itcast.c_事务;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Savepoint;

import org.junit.Test;

import util.JdbcUtil;

/**
 事务基本概念：
       事务指一组最小逻辑操作单元，里面有多个操作组成。
       组成事务的每一部分必须要同时提交成功，如果有一个操作失败，整个操作就回滚。
 
   事务的特性：
 	原子性：是一个最小逻辑操作单元
 	一致性：事务过程中，数据处于一致状态
 	持久性：事务一旦提交成功，对数据的更改会反映到数据库中
 	隔离性：事务与事务之间是隔离的。
 

 
----------------------------------------- 
 案例：
   需求：张三给李四转账
   设计：账户表：
   -- 设计一个账户表account
CREATE TABLE account(
 id INT PRIMARY KEY AUTO_INCREMENT,
 accountName VARCHAR(20),
 money DOUBLE
);

-- 转账
UPDATE account SET money=money-1000 WHERE accountName='张三';
UPDATE account SET money=money+1000 WHERE accountName='李四';

   
   要用到的技术：
    |--Connection:
 		  void setAutoCommit(boolean autoCommit);设置事务是否自动提交，如果设置为false，则表示手动提交事务。
 		  void rollback();回滚(出现异常的时候，所有已经执行成功的代码需要回退到事务开始前的状态)
 		  void commit();手动提交事务
 		  Savepoint setSavepoint(String name);
 		  
 
  
 * 
 * @author 贤元
 * 
 */
public class AccountDao {

	// 全局参数
	private Connection con;
	private PreparedStatement pstmt;

	/**
	 * 1.转账，没有使用事务
	 * 注意：如果没有使用事务，则其中一个环节出错内容没更新成功的话所有数据就不同步了
	 * @throws Exception
	 */
	@Test
	public void trans() {

		try {
			// 1.注册驱动，获取连接对象
			con = JdbcUtil.getConnection();
			// 准备sql
			String sql_zs = "UPDATE account SET money=money-1000 WHERE accountName='张三';";
			//让这条sql语句故意出错，结果就是所有数据都不同步了
			String sql_ls = "UPDATE account SET money=money+1000 HERE accountName='李四';";
			//默认开启隐式 事务  设置事务是否手动提交  false:表示手动提交  true:表示自动提交
			con.setAutoCommit(true);
			/** 第一次执行 **/
			//执行预编译的sql，返回pstmt对象
			pstmt=con.prepareStatement(sql_zs);
			//执行sql  执行更新
			pstmt.executeUpdate();
			
			/**第二次执行**/
			//执行预编译的sql，返回pstmt对象
			pstmt=con.prepareStatement(sql_ls);
			//执行sql
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally{
			//释放资源
			JdbcUtil.close(con, pstmt);
		}

	}
	
	
	/**
	 * 转账，使用事务
	 * 只要有一个出错，就回滚到初始状态(即没有执行任何一条sql语句之前)
	 *  一、设置事务为手动提交
	 *  二、出现异常，需要回滚事务(在异常处理中)  
	 *  三、所有的操作执行成功，提交事务(在finally中)
	 * 
	 * @throws Exception
	 */
	@Test
	public void trans2()  {
		try {
			// 1.注册驱动，获取连接对象
			con = JdbcUtil.getConnection();
			// 准备sql
			String sql_zs = "UPDATE account SET money=money-1000 WHERE accountName='张三';";
			//这个sql语句是不正确的，故意让它出错的
			String sql_ls = "UPDATE account SET money=money+1000 HERE accountName='李四';";
			//默认开启隐式 事务  设置事务是否手动提交  false:表示手动提交
			/**一、设置事务为手动提交***/
			con.setAutoCommit(false);
			
			/** 第一次执行 **/
			//执行预编译的sql，返回pstmt对象
			pstmt=con.prepareStatement(sql_zs);
			//执行sql
			pstmt.executeUpdate();
			
			/**第二次执行**/
			//执行预编译的sql，返回pstmt对象
			pstmt=con.prepareStatement(sql_ls);
			//执行sql
			pstmt.executeUpdate();
		} catch (Exception e) {
			//如果出现异常，则需要对事务进行回滚
			//二、出现异常，需要回滚事务
			try {
				con.rollback();
			} catch (Exception e1) {
				e1.printStackTrace();
				throw new RuntimeException();
			}
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally{
			try {
				//三、所有的操作执行成功，提交事务
				con.commit();
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
			//释放资源
			JdbcUtil.close(con, pstmt);
		}

	}
	
	
	
	/**
	 * 3.转账。使用事务，回滚到指定的代码段
	 * 只要有一个sql语句出错，就回滚到第一个sql语句出错的那里。
	 * 注意：出错的sql语句之后的sql语句不执行，在出错的sql语句的前面的未出错的sql语句是已经执行了的。
	 *
	 * 步骤：
	 * 	1.设置事务为手动提交  
	 * 	2.设置一个指定位置，回滚到该指定位置(Savepoint保存点)  
	 * 	3.出现异常的时候回滚到指定代码段(在异常处理中)  
	 * 	4.所有操作成功，提交事务(在finally中)
	 * 
	 * @throws Exception
	 */
	@Test
	public void trans3() throws Exception {
		Savepoint sp=null;
		try {
			// 1.注册驱动，获取连接对象
			con = JdbcUtil.getConnection();
			// 准备sql
			//第一次转账的sql
			String sql_zs = "UPDATE account SET money=money-1000 WHERE accountName='张三';";
			String sql_ls = "UPDATE account SET money=money+1000 WHERE accountName='李四';";
			//第二次转账的sql  sql_ls2的sql语句是有错的，故意让它出错的
			String sql_zs2="UPDATE account SET money=money-500 WHERE accountName='张三';";
			String sql_ls2="UPDATE account SET money=money+500 HERE accountName='李四';";
		
			//默认开启隐式 事务  设置事务是否手动提交  false:表示手动提交
			/**设置事务为手动提交**/
			con.setAutoCommit(false);
			
			/**
			 * 第一次转账
			 */
			/** 第一次执行 **/
			//执行预编译的sql，返回pstmt对象
			pstmt=con.prepareStatement(sql_zs);
			//执行sql
			pstmt.executeUpdate();
			/**第二次执行**/
			//执行预编译的sql，返回pstmt对象
			pstmt=con.prepareStatement(sql_ls);
			//执行sql
			pstmt.executeUpdate();
			
			/**
			 * 二、回滚到这个位置
			 */
			sp=con.setSavepoint();//定义一个保存点(切入点)
			
			/**
			 * 第二次转账
			 */
			/** 第一次执行 **/
			//执行预编译的sql，返回pstmt对象
			pstmt=con.prepareStatement(sql_zs2);
			//执行sql
			pstmt.executeUpdate();
			/**第二次执行**/
			//执行预编译的sql，返回pstmt对象
			pstmt=con.prepareStatement(sql_ls2);
			//执行sql
			pstmt.executeUpdate();
		} catch (Exception e) {
			//三.出现异常，回滚到指定代码段
			con.rollback(sp);//也就是出现异常的时候回滚到保存点的位置(Savepoint)
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally{
			//四.所有操作成功，提交事务
			con.commit();
			//释放资源
			JdbcUtil.close(con, pstmt);
		}

	}

}//类的括号