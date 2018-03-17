package gz.itcast.a_junit;

import junit.framework.Assert;

import org.junit.Test;

public class TestJunit {

	/**
	 * дһ������MathUtil��add�����Ĳ��Է���
	 *    ע�⣺ ��һ����������@Testע�⣬��ô�÷�������һ�����Է�����������һ��main������
	 *    
	 *    1)Junit���Է����Ĺ���
	 *    		1�����Է���һ���ڷ�������Ҫ����@Testע��
	 *          2������������public���Σ������з���ֵ�������в����������׳��쳣
	 *    2)Junit����������У�
	 *    		1������һ�������� ˫����Ӧ�ķ������Ҽ�Run As-��Junit Test (����Outline��ͼ��ѡ�񷽷�Run As-��Junit Test)
	 *    		2������������з�����˫���࣬�Ҽ�Run As-��Junit Test
	 *    		3������һ����Ŀ�����з�����˫����Ŀ���Ҽ�Run As-��Junit Test
	 *    3���鿴Junit�����Ľ��
	 *    		�鿴Junit�ĵ�������
	 *    				��ɫ�����������ͨ���ˣ�
	 *    				��ɫ����������Բ�ͨ����
	 *    4)�Խ�������ж�
	 *    		Assert�����ࣺ
	 *    			Assert.assertEquals  �ж��Ƿ���ȣ�ʹ��equals�����Ƚ�
	 *    			assertSame           �ж��Ƿ���ȣ�ʹ��==�Ƚ�
	 */
	@Test
	public void testAdd()throws Exception{
		MathUtil mu = new MathUtil();
		int result  = mu.add(10,5);
		/**
		 * �ֶ��жϽ��(��רҵ)
		 */
		/*if(result!=15){
			throw new RuntimeException("������");
		}*/
		
		Person p1  = new Person("eric",20);
		Person p2  = new Person("eric",20);
		
		/**
		 * ʹ��Junitרҵ���ж�����ķ�����
		 * 		Assert�ࣺ ������(�ж������ʲô??)
		 */
		//Assert.assertEquals(15, result);// �ж�����ֵ�Ƿ���ȡ����ʱ������ͨ�������򣬲��Բ�ͨ��.
		//Assert.assertNotSame(15, result);// �ж�����ֵ�Ƿ���ȡ������ʱ������ͨ�������򣬲��Բ�ͨ��.
		//Assert.assertSame(15, result);// �ж�����ֵ�Ƿ���ȡ����ʱ������ͨ�������򣬲��Բ�ͨ��.

		
		//Assert.assertEquals(p1, p2); // ���ԱȽϵ��Ƕ�������.��equals�������бȽ�
		Assert.assertSame(p1, p2); // �Ƚϵ��Ƕ����ڴ�ĵ�ַ. ��==�ȽϱȽ�
	}
	
	
	
	
	
	@Test
	public void testDiv(){
		MathUtil mu = new MathUtil();
		int result  = mu.div(10,5);
		if(result!=2){
			throw new RuntimeException("������");
		}
	}
	
}
