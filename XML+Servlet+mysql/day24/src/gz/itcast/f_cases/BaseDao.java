package gz.itcast.f_cases;

import gz.itcast.util.JdbcUtil;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

/**
 * 基础的dao
 * 
 * @author 贤元
 * 
 */
public class BaseDao<T> {

	// 具体的dao上面的泛型类型
	private Class targetClass;
	// 表名
	private String tableName;

	public BaseDao() {
		/**
		 * 需要解决的问题：
		 *  1)得到具体的业务dao运行过程中泛型的具体类型(Student/Teacher),可以封装ResultSet
		 * 2)得到泛型具体类型名称，就是表名
		 * 
		 * 最终是要在TeacherDao中进行增删改查的，而TeacherDao extends BaseDao<Teacher>,所以当前类应该要看作TeacherDao类
		 */
		// 1）this : 代表当前运行的dao对象
		//System.out.println(this.getClass());
		// 2)this.getClass() : 代表当前运行dao对象的Class类型对象
		Class clazz = this.getClass();
		System.out.println("1:"+clazz);//1: class gz.itcast.f_cases.TeacherDao
		
		// 3)clazz.getGenericSuperclass();得到当前dao父类(参数化类型)(也就是得到当前泛型类的父类)
		Type type = clazz.getGenericSuperclass();// 得到BaseDao<Teacher> 这个也叫参数化类型.得到泛型的父类
		System.out.println("2:"+type);//2:gz.itcast.f_cases.BaseDao<gz.itcast.f_cases.Teacher>
		
		// 4)把父类的类型强转成子类类型(参数化类型：ParameterizedType)
		ParameterizedType param = (ParameterizedType) type;
		System.out.println("3:"+param);//3:gz.itcast.f_cases.BaseDao<gz.itcast.f_cases.Teacher>
		
		// 5)param.getActualTypeArguments();得到参数化类型 上面的泛型类型列表(返回表示此类型实际类型参数的 Type 对象的数组。)
		Type[] types = param.getActualTypeArguments();// <Teacher>
		
		// 6）取出泛型类型列表中的第一个泛型类型
		Type target = types[0];// Teacher
		System.out.println("4:"+target);//4:class gz.itcast.f_cases.Teacher
		
		// 7）强转成Class类型
		targetClass = (Class) target;//得到当前类的Class对象
		System.out.println("5:"+targetClass);//5:class gz.itcast.f_cases.Teacher
		
		// System.out.println(targetClass.getSimpleName());
		try {
			/**
			 * 获取的表名 来自类上面的注解
			 */
			Table table = (Table) targetClass.getAnnotation(Table.class);
			tableName = table.name();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException();
		}

	}

	public List<T> findAll() {
		try {
			QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
			return (List<T>) qr.query("select * from " + tableName + "",
					new MyBeanListHandler());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}


	public T findById(int id) {
		try {
			QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
			return (T) qr.query("select * from "+tableName+" where id=?",
					new BeanListHandler(targetClass),new Object[]{id});
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	
	/**
	 * 自行设计一个封装多个对象的List集合的ResultSetHandler
	 */
	class MyBeanListHandler implements ResultSetHandler {
		@Override
		public Object handle(ResultSet rs) throws SQLException {
			try {
				List<T> list = new ArrayList<T>();
				// 得到结果集的元数据
				ResultSetMetaData rsmd = rs.getMetaData();
				// 得到表的列数量
				int columnCount = rsmd.getColumnCount();
				while (rs.next()) {
					// 创建对象
					T obj = (T)targetClass.newInstance();
					// 把字段的值封装到对象中
					// 遍历列
					for (int i = 1; i <= columnCount; i++) {
						// 得到列的值
						Object value = rs.getObject(i);
						// 得到列名称
						String columnName = rsmd.getColumnName(i).toLowerCase();
						// 得到属性名

						// 遍历所有属性
						Field[] fields = targetClass.getDeclaredFields();
						for (Field field : fields) {
							// 得到属性上面的注解
							Column column = field.getAnnotation(Column.class);
							// 得到注解的内容
							String cname = column.name().toLowerCase();

							if (columnName.equals(cname)) {
								field.setAccessible(true);//开启暴力访问
								// 我需要赋值的属性
								field.set(obj, value);
								break;
							}

						}

					}
				//把封装好的对象放入list集合中
				list.add(obj);
				}
				return list;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}

	}

}
