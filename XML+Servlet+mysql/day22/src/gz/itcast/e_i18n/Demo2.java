package gz.itcast.e_i18n;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 日期时间国际化
 * @author 贤元
 *
 */
public class Demo2 {

	public static void main(String[] args) {
		/**
		 * 参数一：日期格式， short medium long full
		 * 参数二：时间格式. short medium long full
		 * 参数三：国家的locale
		 */
		DateFormat df=DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL, Locale.US);
		
		String curDate=df.format(new Date());
		System.out.println(curDate);
		
		
	}
}
