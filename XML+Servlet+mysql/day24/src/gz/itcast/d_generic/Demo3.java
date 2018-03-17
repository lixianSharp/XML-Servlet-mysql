package gz.itcast.d_generic;
/**
 * 泛型接口
 * 	泛型接口的类型确定：
 * 		1）直接即实现泛型接口的时候可以确定类型
 * 		2）继承泛型接口的实现类的时候可以确定类型
 * @author 贤元
 *
 */
public class Demo3 {
   
}

/*通用的dao接口*/
interface IBaseDao<T>{
	public void save(T t);
	public void update(T t);
}

class Employee{}

/**
 * 具体的业务dao
 * @author 贤元
 *
 */
/*class EmpDao implements IBaseDao<Employee>{

	@Override
	public void save(Employee t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Employee t) {
		// TODO Auto-generated method stub
		
	}
}
*/

/**
 * 通用的dao实现类
 * @author 贤元
 *
 */
class BaseDao1<T> implements IBaseDao<T>{

	@Override
	public void save(T t) {
		//可以写通用的增加方法
	}

	@Override
	public void update(T t) {
		// 通用的修改方法
		
	}
	
}

/**
 * 具体的业务到实现类
 * @author 贤元
 *
 */
class EmpDao extends BaseDao1<Employee>{
	
	
}











