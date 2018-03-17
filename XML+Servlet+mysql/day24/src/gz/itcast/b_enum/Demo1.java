package gz.itcast.b_enum;
/**
 * 枚举语法
 * @author 贤元
 *
 */
public class Demo1 {

	public static void main(String[] args){
		printScore(Score.C);
	}
	
	
	//打印成绩  成绩分等级：A B C D E
	public static void printScore(Score s){
		System.out.println(s.getResult());//79~70
		
		/*
		 * 得到枚举中所有值：每个枚举类型都有一个values()方法
		 */
		Score[] arr=Score.values();
		for(Score score:arr){
			System.out.println(score);// A B C D E
		}
	}
}
/**
 * 自定义类形式(jdk1.4 以前版本)
 * @author 贤元
 *
 */
/*
//成绩类
class Score{
	private Score(){};//私有化构造方法，不能让用户随意构造对象
	
	public static final Score A=new Score();
	public static final Score B=new Score();
	public static final Score C=new Score();
	public static final Score D=new Score();
	public static final Score E=new Score();
}
*/


/**
 * 枚举类型:(jdk1.5 以后出现的类型)
 *  A(100~90) B(89~80) C(79~70) D(69~60) E(59~0)
 */
enum Score{
	A("100~90"),B("89~80"),C("79~70"),D("69~60"),E("59~0");
	
	private String result;//分数段
	//private Score();
	/**
	 * 有参的构造方法
	 */
	private Score(String result){
		this.result=result;
	}
	
	public String getResult(){
		return result;
	}
	
}