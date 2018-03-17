package gz.itcast.e_annotation;

import java.lang.reflect.Method;

import org.junit.Test;


/**
 * �Զ���ע��
 * @author APPle
 *
 */
public class Demo2 {

	@Author(name = "eric", address = { "�������","�㶫�ع�" }, value = {"����"})
	private String name;
	
	@MyAnnotaion
	@Author(name = "eric", address = { "�������","�㶫�ع�" }, value = {"����"})
	public void save(){
		/**
		 * ����ע�⣺ʹ�÷�������ȡע�����������
		 */
		/*
		Class c;
		Method m;
		Field f;
		Constructor con;
		//c.getAnnotation(null) ��ȡ�������ע��
		//m.getAnnotation(null);//��ȡ���������ע��
		//f.getAnnotation(null);//��ȡ���������ע��
		//con.getAnnotation(null);;//��ȡ���췽�������ע��
		*/
		
		try {
			//1)�õ�save��������
			Method m = this.getClass().getMethod("save", null);
			
			//2)�õ����������ע��
			Author author = m.getAnnotation(Author.class);
			System.out.println(author);
			
			//3)��ȡע����������ԣ����ݣ�
			String name = author.name();
			String time = author.modifyTime();
			System.out.println(name);
			System.out.println(time);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	@Test
	public void test() throws Exception {
		save();
	}
	
}
