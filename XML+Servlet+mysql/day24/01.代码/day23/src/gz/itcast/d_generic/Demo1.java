package gz.itcast.d_generic;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * ���͵�����
 * @author APPle
 *
 */
public class Demo1 {
	@Test
	public void test(){
		/**
		 * jdk1.4����ǰ������
		 */
		List list = new ArrayList();
		//�洢
		list.add(new Cat());
		list.add(new Dog());
		
		//ȡ��
		Cat cat = (Cat)list.get(0);
		//���е�ʱ�򱨴�,����ת������
		//Cat cat2 = (Cat)list.get(1);
		
		/**
		 * jdk1.5 ֮��
		 *  ���͵�����1�� �����еĿ��ܾ������µ�����ת��������ǰ������ʱ������͡�
		 *  ���͵�����2�� �����ֶ�����ת���Ĺ���
		 */
		List<Cat> list2 = new ArrayList<Cat>();
		list2.add(new Cat());
		//�����ʱ�򱨴������޷�����List����
		//list2.add(new Dog());
		
		List<String> list3 = new ArrayList<String>();
		list3.add("eric");
		list3.add("jacky");
		list3.add("rose");
		
		/**
		 *  jdk1.4����ǰ������
		 */
		//����
		for (Object obj : list3) {
			//�����ʱ��Ҫ����String���еķ���
			String str = (String)obj;
			
			System.out.println(obj);
		}
		
		/**
		 *  jdk1.5֮�������
		 */
		for (String obj : list3) {
		
			System.out.println(obj);
		}
	}
}

class Cat{}
class Dog{}