package a_dom4j_write;

import java.io.File;
import java.io.FileOutputStream;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.junit.Test;

/**
 * 
 * 修改xml文档内容 
 * 
 * 增加：标签，属性 
 * 
 * 修改：属性值，文本 
 * 
 * 删除：标签，属性
 * 
 * @author 贤元
 * 
 */
public class Demo3 {

	/**
	 * 增加：标签、属性
	 */
	@Test
	public void test1() throws Exception {
		/**
		 * 1、创建文档
		 */
		Document doc = DocumentHelper.createDocument();

		/**
		 * 2、增加一个标签
		 */
		Element rootElem = doc.addElement("contactList");
		Element contactElem = rootElem.addElement("contact");
		contactElem.addElement("name");

		/**
		 * 3、增加属性
		 */
		contactElem.addAttribute("id", "001");
		contactElem.addAttribute("name", "李贤元");

		// 4、把Document对象写出到xml文档中
		//指定文件的输出位置
		FileOutputStream out = new FileOutputStream("d:/contact.xml");
		//指定一个写出的格式
		OutputFormat format = OutputFormat.createPrettyPrint();
		//生成xml文档的编码
		format.setEncoding("utf-8");
		//创建一个xml写出对象
		XMLWriter writer = new XMLWriter(out,format);
		writer.write(doc);
		writer.close();
	}

	/**
	 * 修改：属性值，文本
	 * @throws Exception 
	 */
	@Test
	public void test2() throws Exception {
		Document doc=new SAXReader().read(new File("./src/contact.xml"));
		
		/**
		 * 修改属性值 方案一：	1、得到标签对象 2、得到属性对象 3、修改属性值
		 */
		/*//1、1得到标签对象
		Element contactElem=doc.getRootElement().element("contact");
		//1、2得到属性对象
		Attribute idAttr=contactElem.attribute("id");
		//1.3修改属性值
		idAttr.setValue("950");*/
		
		/**
		 * 修改属性值 方案二：修改属性值
		 */
		/*//1.1得到标签对象
		Element contactElem=doc.getRootElement().element("contact");
		//1.2 通过增加同名属性的方法，修改属性值
		contactElem.addAttribute("id", "1520");
		*/
		
		/**
		 * 修改文本 :  1、得到标签对象 2、修改文本
		 */
		Element nameElem=doc.getRootElement().element("contact").element("name");
		nameElem.setText("李贤元");
		
		//指定文件输出位置
		FileOutputStream out=new FileOutputStream("d:/contact.xml");
		//指定一个输出格式
		OutputFormat format=OutputFormat.createPrettyPrint();
		//指定输出编码格式
		format.setEncoding("utf-8");
		//创建一个xml写出对象
		XMLWriter writer=new XMLWriter(out,format);
		//写出xml文档
		writer.write(doc);
		writer.close();
	}
	
	
	
	/**
	 * 删除: 标签  、属性
	 * @throws Exception 
	 */
	@Test
	public void test3() throws Exception{
		Document doc =new SAXReader().read(new File("./src/contact.xml"));
		
		/**
		 * 1删除标签: 1.1得到标签对象  2.1删除标签   
		 * 注意: 如果该标签下还有子孙..标签，会连同子孙..标签给一起删除
		 */
		//1.1得到标签对象
		/*
		Element ageElem=doc.getRootElement().element("contact").element("age");
		//如果有同名的标签，则只获取第一个同名的标签
		
		
		//1.2删除标签对象
		ageElem.detach();//第一种删除方式
		//ageElem.getParent().remove(ageElem);//第二种删除
		*/
		
		
		/**
		 * 2 删除属性：2.1.1 得到标签对象    2.1得到属性对象 2.2 删除属性
		 */
		//2.1得到属性对象
		//得到第二个contact标签
		Element contactElem=(Element) doc.getRootElement()
				.elements().get(1);
		//2.2得到属性对象
		Attribute idAttr=contactElem.attribute("id");
		//2.3删除属性
		idAttr.detach();//第一种删除方式
		//idAttr.getParent().remove(idAttr);//第二种删除方式
		
		
		//指定文件的输出位置
		FileOutputStream out=new FileOutputStream("d:/contact.xml");
		//设置输出格式
		OutputFormat format=OutputFormat.createPrettyPrint();
		//设置输出的编码格式
		format.setEncoding("utf-8");
		//创建一个xml写出对象
		XMLWriter writer=new XMLWriter(out,format);
		//写出xml文档
		writer.write(doc);
		writer.close();
	}
}
