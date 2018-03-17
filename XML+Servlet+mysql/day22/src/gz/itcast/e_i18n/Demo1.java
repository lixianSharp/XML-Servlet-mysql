package gz.itcast.e_i18n;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * 国家名字国际化
 * @author 贤元
 *
 */
public class Demo1 {
	public static void main(String[] args) {
		//1)使用ResourceBoundle类加载不同的资源包
		/**
		 * 参数一：资源包名称(只写资源包名)	    默认指向类路径的根目录
		 * 参数二：国家locale
		 */
		ResourceBundle bundle= ResourceBundle.getBundle("message",Locale.US);
	
		System.out.println(bundle.getString("username"));
		System.out.println(bundle.getString("password"));
		System.out.println(bundle.getString("login"));
		
		
	}
}
