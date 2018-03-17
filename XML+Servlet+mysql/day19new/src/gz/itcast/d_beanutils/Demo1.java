package gz.itcast.d_beanutils;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;

/**
 * 使用beanutils工具操作javabean
 * @author APPle
 *
 */
public class Demo1 {
	public static void main(String[] args) throws Exception {
		//t1();	
		
		//t2();
		
		/**
		 * 3)从一个map集合中拷贝到一个javabean中
		 * 注意： 
		 * 	1）只拷贝javabean存在的哪些属性(setXXX方法)
		 *  2)需要拷贝的数据是数组类型，那么只拷贝数组中的第一个元素。
		 *
		 */
		ConvertUtils.register(new DateLocaleConverter(), java.util.Date.class);
		Map map = new HashMap();
		//map.put("name", "jacky");
		map.put("name", new String[]{"jacky","eric"});//字符串数组
		map.put("id", "4");
		map.put("gender", "true");
		map.put("scroe", "86.43");
		map.put("birth","2015-06-05");
		
		//创建student对象，通过反射来创建
		Object s2 = Class.forName("gz.itcast.d_beanutils.Student").newInstance();
		
		//把一个map的数据拷贝到s2中
		BeanUtils.copyProperties(s2, map);
		
		System.out.println(s2);
		
	}

	private static void t2() throws InstantiationException,
			IllegalAccessException, ClassNotFoundException,
			InvocationTargetException {
		/**
		 * 2)从一个javabean对象拷贝到另一个javabean对象
		 *
		 */
		Student s = new Student();
		s.setId(1);
		s.setName("eric");
		s.setScroe(87.23);
		s.setGender(true);
		s.setBirth(new Date());
		
		
		Object s2 = Class.forName("gz.itcast.d_beanutils.Student").newInstance();
		
		//把s对象拷贝到s2对象中
		/**
		 * 参数一：拷贝到的对象
		 * 参数二： 原来的对象 
		 */
		BeanUtils.copyProperties(s2, s);
		
		System.out.println(s2);
	}

	private static void t1() throws InstantiationException,
			IllegalAccessException, ClassNotFoundException,
			InvocationTargetException {
		/**
		 * 1)拷贝一个javabean对象的属性
		 */
		Student s = new Student();
		s.setId(1);
		s.setName("eric");
		s.setScroe(87.23);
		s.setGender(true);
		s.setBirth(new Date());
		
		
		//Student s2 = new Student();
		/**
		 * 通过反射构造对象
		 */
		Object s2 = Class.forName("gz.itcast.d_beanutils.Student").newInstance();
		
		//1)把id，name属性值从s对象拷贝到s2的对象中
		/**
		 * 参数一： 拷贝到的对象
		 * 参数二： 拷贝的属性
		 * 参数三： 拷贝的值
		 */
		//s2.setId(s.getId());
		/*BeanUtils.copyProperty(s2, "id", s.getId()); //不需要依赖Student对象
		BeanUtils.copyProperty(s2, "name", "jacky");
		BeanUtils.copyProperty(s2, "gender", true);
		BeanUtils.copyProperty(s2, "scroe", 90.23); 
		BeanUtils.copyProperty(s2, "birth", new Date()); */
		
		/**
		 * 注意：
		 * 	1）拷贝的方法能够自动将非String的类型转换对应的类型的属性.
		 * 		例如 ：  "2"（String） -》 int(String->int)  "97.23"(String) -> double (String->double)
		 *  
		 *  2）如果要支持从String转为Date，那么需要手动注册一个日期转换器 
		 * 		
		 */
		/**
		 * 注册一个日期转换器
		 * 参数一： 需要注册的转换器
		 *    DateLocaleConverter: 支持从String->Date
		 * 参数二： 转换到的类型
		 *     
		 */
		ConvertUtils.register(new MyDateConvert(), java.util.Date.class);
		
		//使用内部提供的日期转换器S
		//ConvertUtils.register(new DateLocaleConverter(), java.util.Date.class);
		
		
		BeanUtils.copyProperty(s2, "id", "2"); //不需要依赖Student对象
		BeanUtils.copyProperty(s2, "name", "jacky");
		BeanUtils.copyProperty(s2, "gender", "true");
		BeanUtils.copyProperty(s2, "scroe", "90.23"); 
		 //String->Date（默认情况，不支持直接从String转为Date）
		BeanUtils.copyProperty(s2, "birth","2015/06/24 16:11:30"); 
		
		System.out.println(s2);
	}
	
}
/**
 * 自定义日期转换器
 * @author APPle
 *
 */
class MyDateConvert implements Converter{

	/**
	 * target: 转换为什么类型
	 * value: 需要转换的值
	 * 该方法返回的转换后的值
	 */
	@Override
	public Object convert(Class target, Object value) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
			//如果需要转换成Date类型，则进行转换
			if(target==Date.class){
				String date = (String)value;
				//把string按照某种转换Date
				Date curDate = sdf.parse(date);
				return curDate;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
}
