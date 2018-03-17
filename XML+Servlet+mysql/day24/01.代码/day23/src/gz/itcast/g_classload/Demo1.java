package gz.itcast.g_classload;
public class Demo1 {
	
	public static void main(String[] args) {
		
		/**
		 * 1)ϵͳ�ṩ�������������������
		 * 			BootStrap�������� ���ص�jdk/jre/lib/rt.jar(������ʱ���õĺ�����)
		 * 			ExtClassLoad�������� ����jdk/jre/lib/ext/*.jar (��չ��)
		 * 			AppClassLoad�������� ����CLASSPATH�е�jar����class�ļ�
		 * 
		 *     MyClasLoader1������   MyClassLoader2������   ����AppClassLoad������
		 * 
		 * 			
		 * 2) �����������������״�ṹ.
		 * 3) ����صĹ��̣�
		 * 		3.1 һ����A����һ������������صģ������A��ʹ�õ�����B,��BҲ������A�������������
		 * 4)ί�л��ƣ�
		 * 		4.1 ������������� ȥ�������ʱ����ί���丸�����, ������и���������������ί����ȥ��ֱ��û�и�������Ϊֹ��
		 * 			��������ؾ���Ҫ������ȥ����ָ���࣬���������Ŀ¼���Ҳ�������࣬���������ң��ҵ��������������Ϊֹ������
		 * 
		 * 	ί�л��Ƶĺô��� 
		 * 			�����ô�����ظ��Ӱ�ȫ�͸�Ч�� ��֤��������ֽ���ֻ��һ�����ڴ��С�
		 * 		
		 */
		//�õ�ĳ���౻�ĸ������������
		//System.out.println(Demo1.class.getClassLoader().getClass());
		
		//��������BootStrap����
		//System.out.println(java.util.Date.class.getClassLoader());
		
		//�鿴ĳ�����������״�ṹ
		ClassLoader classLoader = Demo1.class.getClassLoader();
		while(classLoader!=null){
			System.out.println(classLoader.getClass());
			classLoader = classLoader.getParent();
		}
		System.out.println(classLoader);	
		
	}
}
