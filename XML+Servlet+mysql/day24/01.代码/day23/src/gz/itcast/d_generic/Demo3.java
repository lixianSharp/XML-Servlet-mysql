package gz.itcast.d_generic;
/**
 * ���ͽӿ�
 *  ���ͽӿڵ�����ȷ����
 *  	1��ֱ��ʵ�ַ��ͽӿڵ�ʱ�����ȷ������
 *      2���̳з��ͽӿڵ�ʵ�����ʱ�����ȷ������
 * @author APPle

 */
public class Demo3 {

}

class Employee{}
/*ͨ�õ�dao�ӿ�*/
interface IBaseDao<T>{
	public void save(T t);
	public void update(T t);
}
/**
 * �����ҵ��dao
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
 * ͨ�õ�daoʵ����
 */
/*class BaseDao<T> implements IBaseDao<T>{

	@Override
	public void save(T t) {
		//дͨ�õ����ӷ���
	}

	@Override
	public void update(T t) {
		//ͨ�õ��޸ķ���
	}
}*/

/**
 * �����ҵ��daoʵ����
 * @author APPle
 *
 */
/*class EmpDao extends BaseDao<Employee>{


}*/
