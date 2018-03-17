package gz.itcast.exception;
/**
 * 当文件类型出错时的异常
 * @author 贤元
 *
 */
public class FileTypeErrorException extends Exception{

	public FileTypeErrorException(String msg){
		super(msg);
	}
	
	
}
