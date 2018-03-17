package b_使用Statement执行sql语句;
/*
JDBC核心的API：
   java.sql.* 和 javax.sql.*
   Driver接口：表示驱动程序接口。所有的具体的数据库厂商要来实现此接口
   Connection(url,properties);连接数据库的方式
   	url：连接数据库的URL
   	URL语法:  jdbc协议:数据库子协议://主机:端口号/要连接的数据库名
		user：数据库的用户名
		password：数据库用户密码
	Drivermanager：驱动管理类，用于管理所有注册的驱动程序
	registerDriver(driver);注册驱动类对象
	Connection getConnection(url,user,password);获取连接对象
	
	Connection接口：表示Java程序和数据库的连接对象
		statement createstatement();创建statement对象
		preparedstatement preparestatement(String sql);创建preparedstatement对象
	Statement接口：用于执行静态的sql语句
		int excuteUpdate(String sql);执行静态的更新sql语句(DDL，DML)
		Resultset executeQuery(String sql);执行的静态的查询sql语句(DQL)
	Preparedstatement接口：用于执行预编译的更新sql语句(DDL,DML)
		int executeUpdate();执行预编译的查询sql语句(DQL)
	Callablestatement接口：用于执行存储过程的sql语句(callxxx)
		Resultset executeQuery();调用存储过程的方法
	ResultSet接口：用于封装查询出来的数据
		boolean next();将光标移动到下一行
		getxxx();获取列的值
		
	
*/

public class JDBC核心的API {

}
