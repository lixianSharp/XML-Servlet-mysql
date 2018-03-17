package gz.itcast.f_case;

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
import org.apache.commons.dbutils.handlers.BeanHandler;

/**
 * ����dao
 * @author APPle
 *
 */
public class BaseDao<T> {
	//�����dao����ķ�������
	private Class targetClass;
	//����
	private String tableName;
	
	public BaseDao(){
		/**
		 * ��Ҫ��������⣺ 
		 * 1�� �õ������ҵ��dao���й����еķ��;������ͣ�Student/Teacher��,���Է�װResultSet
		 * 2) �õ����;����������� �����Ǳ���
		 */
		//1)this : ����ǰ���е�dao����
		//System.out.println(this.getClass());
		//2)this.getClass(): ����ǰ����dao�����Class����
		Class clazz = this.getClass();   //public class TeacherDao extends BaseDao<Teacher>
		//3)clazz.getGenericSuperclass()�� �õ���ǰdao����ĸ��ࣨ���������ͣ�
		Type type = clazz.getGenericSuperclass(); // BaseDao<Teacher>
		//4)�Ѹ��������ǿת�����ࣨ����������: ParameterizedType��
		ParameterizedType param = ( ParameterizedType)type; // BaseDao<Teacher>
		//5)param.getActualTypeArguments():�õ����������� ����ķ��������б�
		Type[] types = param.getActualTypeArguments(); // <Teacher>
		//6)ȡ�����������б��еĵ�һ����������
		Type target = types[0];  //  Teacher
		//7)ǿ�Ƴ�Class����
		targetClass = (Class)target;
		
		try {
			//System.out.println(targetClass.getSimpleName());
			/**
			 * ��ȡ���� ������ �������ע��
			 */
			Table table = (Table)targetClass.getAnnotation(Table.class);
			tableName = table.name();
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	
	
	public List<T> findAll(){
		try {
			QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
			return (List<T>)qr.query("select * from "+tableName+"", new MyBeanListHandler());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public T findById(int id){
		try {
			QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
			return (T)qr.query("select * from "+tableName+" where id=?", new BeanHandler(targetClass),new Object[]{id});
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	
	/**
	 * �������һ����װ��������List���ϵ�ResultSetHandler
	 */
	class MyBeanListHandler implements ResultSetHandler{

		@Override
		public Object handle(ResultSet rs) throws SQLException {
			try {
				List<T> list = new ArrayList<T>();
				//�õ��������Ԫ����
				ResultSetMetaData rsmd = rs.getMetaData();
				//�õ����������
				int columnCount = rsmd.getColumnCount();
				while(rs.next()){//��
					//��������
					T obj = (T)targetClass.newInstance();
					//���ֶ�ֵ��װ��������
					//������
					for(int i=1;i<=columnCount;i++){
						//�õ��е�ֵ
						Object value = rs.getObject(i);
						//�õ�������
						String columnName = rsmd.getColumnName(i).toLowerCase();
						
						//������������
						Field[] fields = targetClass.getDeclaredFields();
						for (Field field : fields) {
							//�õ����������ע��
							Column column = field.getAnnotation(Column.class);
							//�õ�ע�������
							String cname = column.name().toLowerCase();
							
							if(columnName.equals(cname)){
								field.setAccessible(true);
								//����Ҫ��ֵ������,�����Ը�ֵ
								field.set(obj, value);
								break;
							}
						}
					}
					//�ѷ�װ�õĶ������LIst������
					list.add(obj);
				}
				return list;
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
		
	}
}







