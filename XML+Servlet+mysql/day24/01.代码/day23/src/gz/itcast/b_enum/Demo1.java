package gz.itcast.b_enum;
/**
 * ö���﷨
 * @author APPle
 *
 */
public class Demo1 {
	
	public static void main(String[] args) {
		printScore(Score.C);
	}
	
	
	//��ӡ�ɼ�   �ɼ��ֵȼ��� A B C D E
	public static void printScore(Score s){
		System.out.println(s.getResult());
		
		/**
		 * �õ�ö��������ֵ��ÿ��ö�����Ͷ���һ��values()����
		 */
		Score[] arr  =Score.values();
		for (Score score : arr) {
			System.out.println(score);
		}
		
	}
}

/**
 * �Զ�����ʽ(jdk1.4 ��ǰ����)
 * @author APPle
 */
//�ɼ���
/*class Score{
	private Score(){};// ˽�л����췽�����������û����⹹�����
	
	public static final Score A = new Score();
	public static final Score B = new Score();
	public static final Score C = new Score();
	public static final Score D = new Score();
	public static final Score E = new Score();
}*/

/**
 * ö�����ͣ���jdk1.5�Ժ���ֵ����ͣ�
 *   A (100-90)  B(89-80)  C(79-70) D(69-60) E(59-0)
 */
enum Score{
	A("100-90"),B("89-80"),C("79-70"),D("69-60"),E("59-0");
	
	private String result;//������
	//private Score(){};
	/**
	 * �в����Ĺ��췽��
	 */
	private Score(String result){
		this.result = result;
	}
	
	public String getResult(){
		return result;
	}
	
}

