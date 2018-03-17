package gz.itcast.c_reflect;

import java.lang.reflect.Constructor;

import org.junit.Test;

/**
 * Constructor�ࣺ������Ĺ��췽��
				���ã����������
 * @author APPle
 *
 */
public class Demo2 {

	@Test
	public void test()throws Exception{
		//1)ͨ��Class��õ�Constructor��
		Class clazz = Class.forName("gz.itcast.c_reflect.Student");
		/**
		 * 1)�����޲����Ĺ��췽��
		 */
		//���ݲ�ͬ�Ĳ����б��ȡ��ͬ�Ĺ��췽��(Constructor)����
		Constructor cons = clazz.getConstructor(null);
		
		//2)ͨ��Constructor��ķ����������
		Object obj = cons.newInstance(null);
		
		System.out.println(obj.getClass().getSimpleName());
		
		/**
		 * �����в����Ĺ��췽��
		 */
		Constructor cons2 = clazz.getConstructor(int.class,String.class);
		Object obj2 = cons2.newInstance(10,"eric");
		System.out.println(obj2.getClass().getSimpleName());
		System.out.println(obj2);
	}
}
