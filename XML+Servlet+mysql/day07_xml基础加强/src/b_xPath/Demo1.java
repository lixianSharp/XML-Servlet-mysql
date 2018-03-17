package b_xPath;

import java.io.File;
import java.io.FileOutputStream;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/**
 * 第一个xpath程序
 * @author 贤元
 *
 */
public class Demo1 {

	public static void main(String[] args) throws Exception{
		/**
		 * 需求：删除id值为2的学生标签
		 */
		Document doc=new SAXReader().read(new File("d:/contact.xml"));
		
		//1 查询id为2的学生标签
		//使用xPath技术
		Element stuElem=(Element)doc.selectSingleNode("//contact[@id='2']");
		
		//2、删除标签
		stuElem.detach();
		
		
		
		
		
		//3、写出xml文件
		//3.1 输出位置
		FileOutputStream out=new FileOutputStream("d:/Student.xml");
		//3.2指定格式
		OutputFormat format=OutputFormat.createPrettyPrint();
		//设置编码
		format.setEncoding("utf-8");
		XMLWriter writer=new XMLWriter();
		//3.3写出内容
		writer.write(doc);
		//3.4关闭流对象
		writer.close();
		
		
	}
	
	
}
