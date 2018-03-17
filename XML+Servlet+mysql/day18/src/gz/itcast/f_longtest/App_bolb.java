package gz.itcast.f_longtest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.junit.Test;

import util.JdbcUtil;
/**
 注意：图片要以二进制的方式存、取
 	存入的时候以反射的方式获取图片流
 	取出来的时候：
 			先获取图片流  之后获取图片输出流，将图片输出到硬盘的某个地方(类似图片复制)
 	
 * @author 贤元
 *
 */
public class App_bolb {

	// 全局参数
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;

	/**
	 * 1.二进制数据类型(写longbolb)  将图片保存在数据库中
	 * @throws Exception
	 */
	@Test
	public void testSaveText() throws Exception {
		
		try {
			//1.注册驱动，获取连接对象
			con=JdbcUtil.getConnection();
			//2.准备预编译的sql
			String sql="insert into test(img) values(?)";
			
			//3.执行预编译的sql  返回pstmt对象
			pstmt=con.prepareStatement(sql);
			
			
			/**
			 * 4.设置参数
			 * 获取图片流(通过反射的方式获取)
			 */
			InputStream in=App_bolb.class.getResourceAsStream("7.jpg");//获取图片输入流
			pstmt.setBinaryStream(1, in);//设置参数  参数一：表示第几个占位符，占位符从1开始    参数二：内容
	
			//5.发送参数，执行sql   返回值为影响的行数
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally{
			//关闭资源
			JdbcUtil.close(con, pstmt);
		}
		
	}
	
	/**
	 * 2.读取大文本数据类型(读longblob) 将图片从数据库中读取出来
	 * @throws Exception
	 */
	@Test
	public void testGetAsText(){

		try {
			//1.注册驱动，获取连接对象
			con=JdbcUtil.getConnection();
			//2.准备预编译的sql
			String sql="select img from test where id=3";
			
			//3.执行预编译的sql  返回pstmt对象
			pstmt=con.prepareStatement(sql);
			
			//4.发送参数，执行sql   返回结果集
			rs=pstmt.executeQuery();
			
			//读取
			//遍历结果
			if(rs.next()){
				//获取图片流
				InputStream in=rs.getBinaryStream("img");
				//图片输出流 将图片输出到这个地方(类似将该图片赋值到1.jpg中)
				FileOutputStream out=new FileOutputStream(new File("C:\\Users\\贤元\\Desktop\\1.jpg"));
				int len=1;
				byte[] b=new byte[1024];
				while((len=in.read(b))!=-1){
					out.write(b,0,len);
				}
				//释放资源
				out.close();
				in.close();
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally{
			//关闭资源
			JdbcUtil.close(con, pstmt);
		}
		
	}
	
}