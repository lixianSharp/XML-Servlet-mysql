package gz.itcast.d_generic;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * ���͹ؼ���
 * @author APPle
 *
 */
public class Demo4 {

	public ArrayList getList(){
		return new ArrayList();
	}
	
	/**
	 * �÷���ֻ�ܽ��մ����Number���͵���������List����
	 *   extends : ֻ�ܴ���ָ����������ָ���������
	 * @param list
	 */
	public void add(List<? extends Number> list){
		
	}
	
	/**
	 * �÷���ֻ�ܽ��մ����Number���͵ĸ�������List����
	 * 	super: ֻ�ܴ���ָ����Ķ������ָ����ĸ���
	 * @param list
	 */
	public void add2(List<? super Number> list){
		
	}

	
	@Test
	public void test() throws Exception {
		/**
		 * 1)? ��  ��ĳ���ౣ�ַ�������
		 */
		Class<?> clazz = Class.forName("gz.itcast.Student");
		
		/**
		 * û�м��Ϸ��ͣ���ᱨ���棬ϣ�����ַ�������
		 * ע�⣺
		 * 		���һ����������ϣ��ŷ��ͣ�����಻���ٽ��б��룬ֻ�����ڽ�������
		 */
		List<?> list = getList();
		
		/**
		 * ���� �޶���List���Ա���Integer��Float��Double���͵�����,����֮�⣬������Ͳ��ܱ���
		 * 2)extends: ֻ����ָ�����͵�����
		 */
		List<Integer> list2 = new ArrayList<Integer>();
		List<Float> list3 = new ArrayList<Float>();
		List<Double> list4 = new ArrayList<Double>();
		
		add(list4);
		/*
		��������
		list1.add(1);
		//��������
		list1.add(1f);
		*/
		
		
		
		List<Integer> list5 = new ArrayList<Integer>();
		/**
		 *  3)super:  ֻ����ָ����ĸ���
		 */
		//����
		//add2(list5);
	}
}
