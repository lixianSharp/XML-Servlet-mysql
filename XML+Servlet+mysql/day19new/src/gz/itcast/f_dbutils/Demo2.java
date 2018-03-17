package gz.itcast.f_dbutils;

import gz.itcast.JdbcUtil;
import gz.itcast.d_beanutils.Student;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

/**
 * 使用dbUtils执行查询操作
 * @author APPle
 *
 */
public class Demo2 {

	public static void main(String[] args) {
		//里面的参数是一个DataSource的数据类型
		QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
		
		/**
		 * ResultSetHandler接口: 用于把结果集封装成不同类型的对象
		 * 	ArrayHandler类: 把结果集的第一行结果封装成对象数组 
		 * 	ArrayListHandler类： 把结果集的每一行结果封装成对象数组，把每个对象数组放入List集合中
		 *  BeanHandler类： 把结果集的第一行，封装成javabean对象(常用)
		 *  BeanListHandler类： 把结果集的每行封装成javabean，把每个javabean放入List集合中（常用）
		 *  ScalarHandler类：查询聚合函数(例如:count(*))  (常用)
		 */
		//t1(qr);
		
		
		//t2(qr);
		
		//t3(qr);

		//t4(qr);
		
		try {
			//增删改用update，查询用query
			long count = (Long)qr.query("select count(*) from admin", new ScalarHandler(), new Object[]{});
			System.out.println(count);//该表中数据的行数
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void t4(QueryRunner qr) {
		try {
			//BeanListHandler类： 把结果集的每行封装成javabean，把每个javabean放入List集合中（常用）
			/**
			 * 注意：new Object[]{} 表示预编译sql语句中的参数，如果不需要参数，
			 * 		则可以写成new Object[]{},否则需要写成new Object[]{参数一，参数二，等等}
			 */
			List<Student> stu= (List<Student>)qr.query("select * from admin", 
								new BeanListHandler(Student.class), new Object[]{});
			for (Student student : stu) {
				System.out.println(student);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void t3(QueryRunner qr) {
		try {
			//BeanHandler类： 把结果集的第一行，封装成javabean对象(常用)
			Student stu= (Student)qr.query("select * from admin where id=?",
								new BeanHandler(Student.class), new Object[]{1});
			System.out.println(stu);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void t2(QueryRunner qr) {
		try {
			//ArrayListHandler类: 把结果集的第每一行结果封装成对象数组，并封装到List集合中 
			List<Object[]> values = (List<Object[]>)qr.query("select * from admin",
								new ArrayListHandler(), new Object[]{});
			for (Object[] arr : values) {//行
				for (Object object : arr) {
					System.out.print(object+"\t");
				}
				System.out.println();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void t1(QueryRunner qr) {
		try {
			//ArrayHandler类: 把结果集的第一行结果封装成对象数组 
			Object[] values = (Object[])qr.query("select * from admin", 
							new ArrayHandler(), new Object[]{});
			for (Object object : values) {
				System.out.println(object);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
}
