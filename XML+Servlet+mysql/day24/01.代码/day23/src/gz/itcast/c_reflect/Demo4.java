package gz.itcast.c_reflect;

import java.lang.reflect.Field;

import org.junit.Test;

/**
 *  Field�ࣺ ����������ԡ�
				���ã� �õ�����ֵ����������ֵ
 * @author APPle
 *
 */
public class Demo4 {
	@Test
	public void test() throws Exception{
		//1)ͨ��Class�õ�Field�����
		Class clazz = Class.forName("gz.itcast.c_reflect.Student");
		//Object obj = clazz.getConstructor(null).newInstance(null);
		Object obj = clazz.newInstance();//���Ǽ򻯰汾����ֻ��ʹ���޲εĹ��췽������
		/**
		 *  getDeclaredField���õ��������ε����ԣ�public/private��
		 *  getField(): �õ����������ԣ�public��
		 */
		Field name = clazz.getDeclaredField("name");
		
		//2)�õ�������
		System.out.println(name.getName());//name
		//�õ����Ե�����
		System.out.println(name.getType()); //String
		
		/**
		 * �����Ը�ֵ�� 
		 * 	     ��ǰ�� s.name="eric"
		 *    ���ڣ�ʹ�÷��似��ֱ�����Ƶ�ֱ�Ӹ����Ը�ֵ,�����ǵ���setName������ֵ
		 */
		/**
		 * ����һ�� ��ֵ���ĸ�����
		 * �������� ��ֵ
		 */
		//����˽�����η�������
		name.setAccessible(true);//����private���η�
		
		name.set(obj, "rose");
			
		
		/**
		 * ��ȡ����ֵ
		 */
		Object result = name.get(obj);
		System.out.println(result);
	}
}
