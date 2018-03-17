package contact;

import java.io.FileOutputStream;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/**
 * xml操作的工具类
 * 
 * 用于读取xml文件 和 写出xml文件
 * @author 贤元
 *
 *读取用 new SAXReader().read("path");
 *写出用 new XMLWriter(OutputStream out,OutputFormat format);
 */
public class XMLUtil {

	/**
	 * 用于读取xml文件
	 * @return
	 */
	public static Document getDocument(){
		try {
			//1、读取xml文件
			Document doc=new SAXReader().read("d:/contact.xml");
			
			return doc;
		} catch (DocumentException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	
	/**
	 * 写出xml文件
	 * @param doc
	 */
	public static void writeXml(Document doc){
		
		try {
			//1.把Document写出到xml文件
			FileOutputStream out=new FileOutputStream("d:/contact.xml");
			//指定输出格式
			OutputFormat format=OutputFormat.createPrettyPrint();//以漂亮的格式输出
			//指定编码格式
			format.setEncoding("utf-8");
			XMLWriter writer=new XMLWriter(out,format);
			
			//写出xml文件
			writer.write(doc);
			writer.close();//关闭流
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		
	}
	
	
}
