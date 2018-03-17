package gz.itcast.d_generic;
/**
 * 泛型接口
 *  泛型接口的类型确定：
 *  	1）直接实现泛型接口的时候可以确定类型
 *      2）继承泛型接口的实现类的时候可以确定类型
 * @author APPle

 */
public class Demo3 {

}

class Employee{}
/*通用的dao接口*/
interface IBaseDao<T>{
	public void save(T t);
	public void update(T t);
}
/**
 * 具体的业务dao
 * @author APPle
 *
 */
/*class EmpDao implements IBaseDao<Employee>{

	@Override
	public void save(Employee t) {
		
	}

	@Override
	public void update(Employee t) {
		
	}
}*/

/**
 * 通用的dao实现类
 */
/*class BaseDao<T> implements IBaseDao<T>{

	@Override
	public void save(T t) {
		//写通用的增加方法
	}

	@Override
	public void update(T t) {
		//通用的修改方法
	}
}*/

/**
 * 具体的业务dao实现类
 * @author APPle
 *
 */
/*class EmpDao extends BaseDao<Employee>{


}*/
