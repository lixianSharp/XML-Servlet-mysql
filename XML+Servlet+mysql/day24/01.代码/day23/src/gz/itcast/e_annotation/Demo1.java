package gz.itcast.e_annotation;

import java.util.ArrayList;
import java.util.List;

/**
 * ������ע��
 * @author APPle
 *
 */
public class Demo1 {
	
	/**
	 * 1)���߱�����ǿ�ƶԷ������и���
	 */
	@Override
	public String toString() {
		return super.toString();
	}
	

	/**
	 * 2�����߱�����ѹ�ƴ����г��ֵľ���
	 * @return
	 */
	@SuppressWarnings(value = { "unchecked" })
	public List save(){
		List list = new ArrayList();
		return list;
	}
	
	/**
	 * 3��������ʱ�÷�����ʾ����
	 */
	@Deprecated
	public void update(){
		
	}
	
	public void update(String name){
		
	}
	
	public static void main(String[] args) {
		new Demo1().update();
	}
	
}
