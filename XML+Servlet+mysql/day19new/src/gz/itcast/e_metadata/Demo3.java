package gz.itcast.e_metadata;

import gz.itcast.d_beanutils.Student;

import java.sql.Connection;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class Demo3 {
	public static void main(String[] args) throws Exception {
		//t1();
		
		/**
		 * 需求： 传入一个查询的sql语句，返回对应的List集合
		 */
		List<Student> list = SQLUtil.query("select * from student",null,Student.class);
		for (Student student : list) {
			System.out.println(student);
		}
	}

	private static void t1() throws SQLException {
		ComboPooledDataSource ds = new ComboPooledDataSource();
		Connection conn = ds.getConnection();
		
		String sql = "select * from student";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ParameterMetaData pmd = stmt.getParameterMetaData();
		//得到参数数量
		int paramCount = pmd.getParameterCount();
		//Object[] values = new Object[]{1};
		Object[] values = null;
		if(values!=null){
			//参数赋值
			for(int i=1;i<=paramCount;i++){
				stmt.setObject(i, values[i-1]);
			}
		}
		ResultSet rs = stmt.executeQuery();
		
		//遍历结果集
		/*while(rs.next()){// 行
			//得到列值
			int id = rs.getInt(1);
			String name = rs.getString(2);
			System.out.println(id+"\t"+name);
		}*/
		
		//问题:得到表有几列，每个列的名称是什么
		//得到结果集的元数据
		ResultSetMetaData rsmd = rs.getMetaData();
		//得到列数量
		int columnCount = rsmd.getColumnCount();
		
		while(rs.next()){//行
			//遍历每列
			for(int i=1;i<=columnCount;i++){
				Object value = rs.getObject(i);
				System.out.print(value+"\t");
			}
			System.out.println();
		}
	}
}
