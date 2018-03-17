package gz.itcast.e_metadata;

import java.sql.Connection;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class SQLUtil {

	/**
	 * 通用的更新方法
	 */
	public static void update(String sql,Object[] values){
		try {
			ComboPooledDataSource ds = new ComboPooledDataSource();
			Connection conn = ds.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			ParameterMetaData pmd = stmt.getParameterMetaData();
			//得到sql中有几个参数
			int count = pmd.getParameterCount();
			
			//可以把参数值放入数组中
			//新的赋值方式
			for(int i=1;i<=count;i++){
				//setObject可以设置任何类型参数
				stmt.setObject(i, values[i-1]);
			}
			
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 通用的查询方法
	 */
	public static <T> List<T> query(String sql,Object[] values,Class<T> clazz){
		try {
			ComboPooledDataSource ds = new ComboPooledDataSource();
			Connection conn = ds.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			ParameterMetaData pmd = stmt.getParameterMetaData();
			//得到参数数量
			int paramCount = pmd.getParameterCount();
			//如果是null，则不赋值了
			if(values!=null){
				//参数赋值
				for(int i=1;i<=paramCount;i++){
					stmt.setObject(i, values[i-1]);
				}
			}
			ResultSet rs = stmt.executeQuery();

			//问题:得到表有几列，每个列的名称是什么
			//得到结果集的元数据
			ResultSetMetaData rsmd = rs.getMetaData();
			//得到列数量
			int columnCount = rsmd.getColumnCount();
			List list = new ArrayList();
			while(rs.next()){//行
				//每一行数据就是一个对象
				//构造对象
				Object obj = clazz.newInstance();
				//遍历每列
				for(int i=1;i<=columnCount;i++){
					//得到表的列值
					Object value = rs.getObject(i);
					//通过结果集元数据可以得到字段名称
					String columnName = rsmd.getColumnName(i);
					//表中的每列的值就封装到对象的每个属性中
					//约定一个前提： 表中的每个字段名称和javabean对象中属性名称保持一致！！！！
					//把值拷贝到javabean对象中
					BeanUtils.copyProperty(obj, columnName, value);
				}
				list.add(obj);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
