package gz.itcast.e_annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义一个注解
 * @author 贤元
 *
 */
/**
 * @Target的作用是定义注解的使用范围
 *
 *
 */
@Target({ElementType.METHOD,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Author {

	//声明属性
	String name();
	String modifyTime() default "2015-06-25";//给属性带上默认值
	String[] address();//带有数组类型的属性
	//如果注解的属性名称为value，那么在使用注解的时候可以不写value=
	String[] value();
	//String[] names();
	//String value();
	
}
