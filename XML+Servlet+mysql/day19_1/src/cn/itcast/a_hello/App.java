package cn.itcast.a_hello;

import java.sql.Connection;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.junit.Test;

import cn.itcast.utils.JdbcUtil;

public class App {

	/**
	 * 1、使用DbUtils组件更新
	 * 
	 * @throws Exception
	 */
	@Test
	public void testUpdate() throws Exception {
		String sql = "delete from admin where id=1";
		// 1.1获取连接
		Connection con = JdbcUtil.getConnection();
		// 1.2创建核心工具
		QueryRunner qr = new QueryRunner();
		// 1.3 更新
		qr.update(con, sql);
		
		con.close();
	}

	/**
	 * 1、使用DbUtils组件进行查询
	 * 
	 * @throws Exception
	 */
	@Test
	public void testQuery() throws Exception {
		String sql = "select * from admin";
		// 1.1获取连接
		Connection con = JdbcUtil.getConnection();
		// 1.2创建核心工具
		QueryRunner qr = new QueryRunner();
		//1.3查询
		List<Admin> list=qr.query(con, sql, new BeanListHandler<Admin>(Admin.class));
		
		System.out.println(list);
		con.close();
	}

}
