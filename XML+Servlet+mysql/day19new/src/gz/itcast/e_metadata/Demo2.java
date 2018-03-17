package gz.itcast.e_metadata;

import java.sql.Connection;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class Demo2 {
	public static void main(String[] args) throws Exception {
		//t1();
		
		
		//insert();
		
		update();
	}
	/**
	 * 修改
	 * @throws SQLException
	 */
	private static void update() throws SQLException {
		SQLUtil.update("update student set name=?,gender=? where id=?",
				new Object[]{"lily","男",3});
	}
	/**
	 * 插入
	 * @throws SQLException
	 */
	private static void insert() throws SQLException {
		SQLUtil.update("insert into student(id,name,gender,scroe,birth) values(?,?,?,?,?)",
				new Object[]{3,"lucy","女",90.34,"2015-06-27"});
	}

	private static void t1() throws SQLException {
		/**
		 * 参数元数据（ParameterMetaData）
		 */
		ComboPooledDataSource ds = new ComboPooledDataSource();
		Connection conn = ds.getConnection();
		String sql = "insert into student(id,name,gender,scroe,birth) values(?,?,?,?,?)";
		
		//预编译
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		//参数赋值
		/*stmt.setInt(1, 1);
		stmt.setString(2, "eric");
		stmt.setString(3, "男");
		stmt.setDouble(4, 87.65);
		stmt.setString(5, "2015-06-24");*/
		
		//知道预编译的sql有几个参数
		//得到参数元数据
		ParameterMetaData pmd = stmt.getParameterMetaData();
		//得到sql中有几个参数
		int count = pmd.getParameterCount();
		
		//System.out.println(count);
		
		//可以把参数值放入数组中
		Object[] values = new Object[]{2,"jacky","女",90.23,"2015-06-25"};
		//新的赋值方式
		for(int i=1;i<=count;i++){
			//setObject可以设置任何类型参数
			stmt.setObject(i, values[i-1]);
		}
		
		
			
		stmt.executeUpdate();
	}
}
