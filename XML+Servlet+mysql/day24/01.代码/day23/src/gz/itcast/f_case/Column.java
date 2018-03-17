package gz.itcast.f_case;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ×Ö¶ÎµÄ×¢½â
 * @author APPle
 *
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Column {
	String name();//×Ö¶ÎÃû³Æ
}
