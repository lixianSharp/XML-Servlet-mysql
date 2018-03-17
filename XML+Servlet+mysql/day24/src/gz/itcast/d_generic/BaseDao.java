package gz.itcast.d_generic;

import gz.itcast.util.JdbcUtil;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

/**
 * 基础的dao
 * @author 贤元
 *
 */
public class BaseDao<T> {

	//具体的dao上面的泛型类型
	private Class targetClass;
	//表名
	private String tableName;
	
	
	public BaseDao(){
		/**
		 * 需要解决的问题：
		 * 约定：具体泛型类型的类名 和 表名 保持一致！！！
		 * 1)得到具体的业务dao运行过程中泛型的具体类型(Student/Teacher),可以封装ResultSet
		 * 2)得到泛型具体类型名称，就是表名 (最终结果-》得到泛型具体类型的名称)
		 * 
		 * 
		 */
		//1）this : 代表当前运行的dao对象
		//System.out.println(this.getClass());
		//2)this.getClass() : 代表当前运行dao对象的Class类型对象
		Class clazz=this.getClass();//public class TeacherDao extends BaseDao<Teacher>
		
		//3)clazz.getGenericSuperclass();得到当前dao父类(参数化类型)
		Type type=clazz.getGenericSuperclass();//得到BaseDao<Teacher> 这个也叫参数化类型
		
		//4)把父类的类型强转成子类类型(参数化类型：ParameterizedType) 
		ParameterizedType param=(ParameterizedType)type;
		
		//5)param.getActualTypeArguments();得到参数化类型 上面的泛型类型列表
		Type[] types=param.getActualTypeArguments();//<Teacher>
		
		//6）去除泛型类型列表中的第一个泛型类型
		Type target=types[0];//Teacher
		
		//7）强转成Class类型
		 targetClass=(Class)target;
		
		//System.out.println(targetClass.getSimpleName());
		
		 tableName=targetClass.getSimpleName().toLowerCase();
	}

	public List<T>  findAll(){
		try {
			QueryRunner qr=new QueryRunner(JdbcUtil.getDataSource());
			return (List<T>)qr.query("select * from "+tableName+"", new BeanListHandler(targetClass));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	
	
	public T  findById(int id){
		try {
			QueryRunner qr=new QueryRunner(JdbcUtil.getDataSource());
			return (T)qr.query("select * from teacher where id=?", new BeanListHandler(targetClass));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	
}
