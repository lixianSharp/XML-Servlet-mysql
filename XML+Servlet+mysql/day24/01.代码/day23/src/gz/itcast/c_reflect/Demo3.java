package gz.itcast.c_reflect;

import java.lang.reflect.Method;

import org.junit.Test;

/**
 * Method�ࣺ���������ͨ����
		���ã����÷��� 
 * @author APPle
 *
 */
public class Demo3 {
	@Test
	public void test() throws Exception{
		//1)ͨ��Class�����õ�Method�����
		Class clazz = Class.forName("gz.itcast.c_reflect.Student");
		Object obj = clazz.getConstructor(null).newInstance(null);
		/**
		 *  getMethod() :��ȡ���ϵĹ����ķ�����public��
		 *  getDeclaredMethod(): ��ȡ���ϵ����з�����������˽�еķ�����
		 */
		/**
		 * ����һ�� ������
		 * �������� ��ʽ�����б�
		 */
		Method setName = clazz.getMethod("setName", String.class);
		
		
		//2)ͨ��Method������÷���
		/**
		 * ����һ: ���÷�������Ķ���
		 * ��������ʵ�ʲ���ֵ
		 */
		setName.invoke(obj, "jacky"); // obj.setName("jacky")
		
		System.out.println(obj);
		
		
		Method getName = clazz.getMethod("getName", null);
		//���÷��������շ�������ֵ
		Object result = getName.invoke(obj, null); // obj.getName();
		
		System.out.println(result);
		
		
		
	}
}
