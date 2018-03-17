package gz.itcast.d_批处理;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import util.JdbcUtil;
/**
 步骤：
 	1.添加批处理  PreparedStatement  pstmt.addBatch();
 	2.执行批处理 PreparedStatement  pstmt.executeBatch();
 	3.清空批处理 PreparedStatement  pstmt.clearBatch();
 
 
 * @author 贤元
 *
 */
public class AdminDao {

	//全局参数
	private Connection con=null;
	private PreparedStatement pstmt=null;
	private ResultSet rs=null;
	
	//批量保存管理员
	public void save(List<Admin> list){
		
		try {
			//1.注册驱动，获取连接对象
			con=JdbcUtil.getConnection();
			//2.准备预编译的sql
			String sql="insert into admin(userName,pwd) values(?,?)";
			
			//3.执行预编译的sql 返回pstmt对象
			pstmt=con.prepareStatement(sql);
			
			//4.设置参数
			/**
			 * 参数一： 参数位置 从1开始
			 */
			for(int i=0;i<list.size();i++){
				Admin admin=list.get(i);
				//设置参数
				pstmt.setString(1, admin.getUserName());
				pstmt.setString(2, admin.getPwd());
				
				/**
				 * 添加批处理
				 */
				pstmt.addBatch();//不需要传入sql
				
				//测试：每5条执行一次批处理
				if(i%5==0){
					//执行批处理
					pstmt.executeBatch();
					//清空批处理
					pstmt.clearBatch();					
				}
			}//for的括号
			
			/**
			 * 因为最后一次可能没有5条，所以最后一次即使没有5条，也都应该执行
			 */
			/**
			 * 批量执行  执行批处理
			 */
			pstmt.executeBatch();
			/**
			 * 清空批处理
			 */
			pstmt.clearBatch();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally{
			//关闭资源
			JdbcUtil.close(con, pstmt, rs);
		}
	}
}
