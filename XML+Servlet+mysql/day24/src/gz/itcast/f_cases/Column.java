package gz.itcast.f_cases;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//自定义注解  
/**
 * 字段的注解
 * @author 贤元
 *
 */
//@Target的作用是定义注解使用在哪个范围
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Column {

	String name();//字段名称
}
