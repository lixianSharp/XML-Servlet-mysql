package gz.itcast;

import java.io.File;
/**
 * 这是一个普通的java项目下的
 * @author 贤元
 *
 */
public class TestPath {

	/**
	 *   . 表示相对路径中的当前路径、相对于java命令运行的目录
	 *   结论：
	 *   	在java项目中  . 代表java项目的根目录下开始
	 * @param args
	 */
	public static void main(String[] args) {
		File file=new File(".");
		//查看当前路径的位置
		System.out.println(file.getAbsolutePath());//D:\EnCoding\JavaCode\JAVAEEuseMyEclipseWrite\day11_test\.
	    
		
		//File file =new File("");
		//System.out.println(file.getAbsolutePath());//D:\Encoding\Java\EE\JavaEE\day11_test
		
	}
}
