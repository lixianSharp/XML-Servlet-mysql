package gz.itcast.d_generic;

import org.junit.Test;

/**
 * ���ͷ����ͷ�����
 * @author APPle
 *
 */
public class Demo2<T,K> {

	/**
	 * ���ͷ���������ȷ�����ڵ��÷�����ʱ��ȷ������
	 * �����������ȷ���� �ڴ���������ʱ��ȷ�����͡�
	 */
	/**
	 * ���һ��ͨ�õķ��������Խ����κ�����
	 * ���ͷ����������ǿ����ÿ�������Ƴ�����ͨ���ķ���
	 * @param dept
	 */
	public  T save(T t,K k){
		return t;
	}
	
	
	public  void update(T t,K k){
		
	}
	
	@Test
	public void test(){
		//Dog g = save(new Dog(),new String());
		Demo2<String,Cat> d2 = new Demo2<String,Cat>();
	}
	
	
	
}
