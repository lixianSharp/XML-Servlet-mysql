package gz.itcast.b_pstmt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 使用PreparedStatement防止sql被注入
 * @author 贤元
 *
 */
public class App {
  
	public static void main(String[] args) {
		new App().testLogin2();
	}
	
	
	//创建执行的url
	//private String url="jdbc:mysql:///jdbc_day18";//这是一种简写
	private  String url ="jdbc:mysql://localhost:3306/day18";//jdbc协议:mysql子协议：//主机名：端口号/shu数据库名
	private  String user="root";
	private String password="root";
	Connection conn;
	Statement stmt;
	PreparedStatement pstmt;
	ResultSet rs;
	
	//1、Statement，没有使用防止sql注入的案例
	public void testLogin(){
		//模拟登陆的用户名，密码
		String userName="Tom";
		//String pwd="888";
		String pwd=" ' or 1=1 -- ";
		
		//SQL语句
		String sql="SELECT * FROM admin WHERE userName='"+userName+"' AND pwd='"+pwd+"' ";
		 try{
		//1、加载驱动，创建连接
		 Class.forName("com.mysql.jdbc.Driver");
		 conn=DriverManager.getConnection(url, user, password);
		
	
			 
		//2、创建stmt对象
		 stmt=conn.createStatement();
		//3、执行查询
		 rs=stmt.executeQuery(sql);
		//
		 if(rs.next()){
			 System.out.println("登陆成功，编号:"+rs.getInt("id"));
			 
		 }
		 
		 
		 }catch(Exception e){
		  e.printStackTrace();	 
			 throw new RuntimeException();
		 }finally{
			//4、关闭
			 if(conn!=null)
				try {
					conn.close();							
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					throw new RuntimeException();
				}
			 if(stmt!=null){
				 try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					throw new RuntimeException();
				}
			 }
			 if(rs!=null){
				 try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					throw new RuntimeException();
				}
			 }
		 } 	
	}
	
	
	/**
	 * 使用PreparedStatement防止sql被注入
	 */
	public void testLogin2(){
			//模拟登陆的用户名，密码
			String userName="Tom";
			String pwd="888";
			//String pwd=" ' or 1=1 -- ";//用这个的话就登陆不了
			//SQL语句
			String sql="SELECT * FROM admin WHERE userName=? AND pwd=? ";
			 try{
			//1、加载驱动，创建连接
			  Class.forName("com.mysql.jdbc.Driver");
			 conn=DriverManager.getConnection(url, user, password);
			
			//准备sql  执行预编译的sql
			 pstmt=conn.prepareStatement(sql);
			 //设置参数
			 pstmt.setString(1, userName);
			 pstmt.setString(2,pwd);
			 //发送参数
			 rs=pstmt.executeQuery();
			//
			 if(rs.next()){
				 System.out.println("登陆成功，编号:"+rs.getInt("id"));
				 
			 }else{
				 System.out.println("登陆失败");
			 }
			 
			 
			 }catch(Exception e){
			  e.printStackTrace();	 
				 throw new RuntimeException();
			 }finally{
				//4、关闭
				 if(conn!=null)
					try {
						conn.close();							
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						throw new RuntimeException();
					}
				 if(pstmt!=null){
					 try {
						pstmt.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						throw new RuntimeException();
					}
				 }
				 if(rs!=null){
					 try {
						rs.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						throw new RuntimeException();
					}
				 }
			 }
		
	}
	
	
}
