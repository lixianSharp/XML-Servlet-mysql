package gz.itcast.f_longtest;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.junit.Test;

import util.JdbcUtil;
/**
 注意事项：
 	保存大文本数据：
 		设置参数的时候：(现获取文件路径，路径不能出现中文和空格)
 			String path=App_text.class.getResource("tips.txt").getPath();
			FileReader reader=new FileReader(new File(path));
			pstmt.setCharacterStream(1, reader);
 	
 	读取大文本数据的时候：
 		while(rs.next()){
			//获取长文本数据，方式1：
			//Reader r = rs.getCharacterStream("content");
		
			//获取长文本数据，方式2 推荐使用这种
			System.out.println(rs.getString("content"));
		}
 
 
 * @author 贤元
 *
 */

public class App_text {

	//全局参数
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	
	/**
	 * 1.保存大文本数据类型(写longtext)
	 * @throws Exception
	 */
	@Test
	public void testSaveText() {
		try {
			//1.注册驱动，获取连接对象
			con=JdbcUtil.getConnection();
			//2.准备预编译的sql
			String sql="insert into test(content) values(?)";
			
			//3.执行预编译的sql  返回pstmt对象
			pstmt=con.prepareStatement(sql);
			
			/**
			 * 4.设置参数
			 *    先获取文件路径   注意：路径名称不能出现中文和空格
			 *    通过反射来得到要保存的文件路径
			 */
			String path=App_text.class.getResource("tips.txt").getPath();
			FileReader reader=new FileReader(new File(path));
			pstmt.setCharacterStream(1, reader);
			
			//发送参数，执行sql   返回值为影响的行数
			pstmt.executeUpdate();
			//关闭
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally{
			//关闭资源
			JdbcUtil.close(con, pstmt);
		}
	}
	
	
	/**
	 *  2.读取大文本数据类型(读longtext)
	 */
	@Test
	public void testGetAsText() {
		
		try {
			//1.注册驱动，获取连接对象
			con=JdbcUtil.getConnection();
			//2.准备预编译的sql
			String sql="select content from test where id=4";
			
			//3.执行预编译的sql  返回pstmt对象
			pstmt=con.prepareStatement(sql);
			
			//发送参数，执行sql  返回结果集
			rs=pstmt.executeQuery();
			//遍历结果
			while(rs.next()){
				//获取长文本数据，方式1：
				//Reader r = rs.getCharacterStream("content");
		
				//获取长文本数据，方式2 推荐使用这种
				System.out.println(rs.getString("content"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally{
			//关闭资源
			JdbcUtil.close(con, pstmt, rs);
		}
		
		
	}
	
	
	
}
