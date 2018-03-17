package gz.itcast.a_junit;

public class TestMath {
	public static void main(String[] args) {
		//testAddd();
		
		/*MathUtil mu = new MathUtil();
		int a = 10;
		int b =  5;
		int result = mu.div(a, b);
		if(result!=2){
			throw new RuntimeException("错误结果");
		}*/
	}

	private static void testAdd() {
		MathUtil mu = new MathUtil();
		int a = 10;
		int b =  5;
		int result = mu.add(a, b);
		if(result!=15){
			throw new RuntimeException("错误结果");
		}
	}
}
