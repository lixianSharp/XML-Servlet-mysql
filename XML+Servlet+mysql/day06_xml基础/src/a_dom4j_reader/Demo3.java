package a_dom4j_reader;

import java.io.File;
import java.util.Iterator;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;

/**
 * 练习： 把写的联系人的xml文件，用dom4j完整的读取并打印出来
 * @author 贤元
 *
 */
public class Demo3 {

	
	/**
	 * 练习：把写的联系人的xml文件，用dom4j完整的读取并打印出来
	 * @throws Exception 
	 */
	@Test
	public void test() throws Exception{
		//1、创建一个xml解析器对象
		SAXReader reader=new SAXReader();
		//2、读取xml文档，返回一个Document对象
		Document doc=reader.read(new File("./src/contact.xml"));
		
		//获取XML文档的根标签  <contactList>
		Element rootElem=doc.getRootElement();
		//打印出根节点
		System.out.println(rootElem.getName());//contactList
		
		//获取当前标签节点下的所有子节点
		Iterator<Element> it=rootElem.elementIterator();
		//遍历   
		while(it.hasNext()){
			Element concatElem=it.next();
			//打印出当前标签名
			System.out.println(concatElem.getName());//contact  contact
			//获取该标签的属性值
			//获取指定名称的属性对象
			//获取属性对象的值
			Attribute idAttr=concatElem.attribute("id");
			String idArrtName=idAttr.getName();//得到id的属性名
			String idArrtValue=idAttr.getValue();//得到id的属性值
			System.out.println(idArrtName+"="+idArrtValue);
			
			//获取当前标签节点的所有子节点
			Iterator<Element> it2=concatElem.elementIterator();
			while(it2.hasNext()){
				
				Element e =it2.next();
				//获取标签名
				String eName=e.getName();
				//打印标签名
				System.out.println(eName);
				//获取当前标签文本的内容
				String text=e.getText();
				//打印标签文本内容
				System.out.println(text);
			}
			System.out.println("\n");
			
		}
		
	}
	
	
	
	
}
