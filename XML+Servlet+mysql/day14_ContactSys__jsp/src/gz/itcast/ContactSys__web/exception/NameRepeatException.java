package gz.itcast.ContactSys__web.exception;
/**
 * 姓名重复的自定义异常类
 * @author 贤元
 *
 */
public class NameRepeatException extends Exception{

	public NameRepeatException(String msg){
		super(msg);
	}
	
}
