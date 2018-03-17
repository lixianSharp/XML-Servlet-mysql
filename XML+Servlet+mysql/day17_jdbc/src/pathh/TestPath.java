package pathh;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

public class TestPath {
	public static void main(String[] args) throws Exception {
		File file = new File("properties");
		System.out.println(file.getName());//获取文件名
		InputStream in = TestPath.class.getResourceAsStream("/db.properties");
		
		Properties props = new Properties();
	
		props.load(in);
		
		String user = props.getProperty("user");
		String password = props.getProperty("password");
		String url = props.getProperty("url");
		String driverClass = props.getProperty("driverClass");
		
		System.out.println(user);
		System.out.println(password);
		System.out.println(url);
		System.out.println(driverClass);
	}
}
