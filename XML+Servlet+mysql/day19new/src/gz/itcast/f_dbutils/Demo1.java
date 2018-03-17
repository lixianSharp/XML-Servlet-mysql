package gz.itcast.f_dbutils;

import java.sql.SQLException;

import gz.itcast.JdbcUtil;

import org.apache.commons.dbutils.QueryRunner;

public class Demo1 {

	public static void main(String[] args) {
		/*
		 * DateSource接口是jdbc的接口，统一一个所有第三方连接池工具获取连接的方法。
		 */
		//1）创建QueryRunner
		QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
		
		//2)执行更新
		try {
			qr.update("insert into student(id,name,gender) values(?,?,?)", new Object[]{5,"张三","男"});
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
