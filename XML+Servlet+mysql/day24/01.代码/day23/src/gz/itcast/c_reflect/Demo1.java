package gz.itcast.c_reflect;

import org.junit.Test;

/**
 * Class�ࣺ
 * 	���ã����������ƣ����ʵ�ֵĽӿڣ��̳е���..
 * @author APPle
 *
 */
public class Demo1 {

	@Test
	public void test()throws Exception{
		//1)�õ�Class��Ķ���
		//��ʽһ�� 
		//Class clazz = Student.class;
		//��ʽ���� (�����ԱȽϵͣ�ֻ�����ַ���)
		Class clazz = Class.forName("gz.itcast.c_reflect.Student");
		//��ʽ����
		//Class clazz = new Student().getClass();
		
		
		//�õ�����
		System.out.println(clazz.getName());//ȫ�����������ṹ��
		System.out.println(clazz.getSimpleName());//����
		
		//�õ���ļ̳нṹ
		//�õ�Student��ĸ���
		Class parent = clazz.getSuperclass();
		System.out.println(parent.getSimpleName());
		
		//�õ���Ľӿ�
		Class[] interArray = clazz.getInterfaces();
		for (Class cla : interArray) {
			System.out.println(cla.getSimpleName());
		}	
	}
	
}
