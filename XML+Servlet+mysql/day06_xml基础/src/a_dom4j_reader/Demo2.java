package a_dom4j_reader;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.junit.Test;

/**
 * 第二个dom4j读取xml文件内容
 * 
 * 节点
 * 标签
 * 属性
 * 文本
 * @author 贤元
 *
 */
public class Demo2 {

	/**
	 * 得到根节点和子节点标签
	 * 
	 * @throws Exception 
	 */
	@Test
	public void test1() throws Exception{
		//1、创建一个xml解析器对象
		SAXReader reader=new SAXReader();
		//2、读取xml文档，返回Document文档对象
		Document doc=reader.read(new File("./src/contact.xml"));
		//3、nodeIterator : 得到当前节点下的所有子节点对象(不包括孙节点以下的节点)
		Iterator<Node> it=doc.nodeIterator();
		while(it.hasNext()){//判断是否有下一个元素
		   Node node =	it.next();//取出这个元素
		   String name=node.getName();//得到节点的名称
		   System.out.println(name);//contactList
		   //继续取出其下面的子节点
		   //只有标签节点才有子节点
		   //判断当前节点是否是标签节点
		   if(node instanceof Element){
			   Element elem=(Element)node;
			   Iterator<Node> it2=elem.nodeIterator();
			   while(it2.hasNext()){
				   Node n2=it2.next();
				   String name2=n2.getName();//得到节点的名称
				   System.out.println(name2);
			   }
		   }
		}		
	}
	
	
	/**
	 * 遍历xml文档的所有节点
	 * @throws Exception 
	 * 
	 */
	@Test
	public void test2() throws Exception{
	   //1、创建一个xml解析器对象
	   SAXReader reader=new SAXReader(); 
	   //2、读取xml文档，返回一个Document对象
	   Document doc=reader.read(new File("./src/contact.xml"));
	
	   //得到根标签(一个xml文档有且只有一个根标签)
	   Element rootElem=doc.getRootElement();
	   //System.out.println(rootElement.getName());
	   //得到根标签的名称 contactList
	   getChildNodes(rootElem);
	}
	
	/**
	 * 获取传入的标签下的所有子节点
	 * @param elem
	 */
	private void getChildNodes(Element elem){
		System.out.println(elem.getName());
		
		//得到子节点
	   Iterator<Node> it=elem.nodeIterator();
	   while(it.hasNext()){
		   Node node=it.next();
		   //1、判断是否是标签节点
		   if(node instanceof Element){
			   Element e1=(Element)node;
			   //递归
			   getChildNodes(e1);
		   }
	   }	
	}
	
	/**
	 * 获取标签
	 * @throws Exception 
	 */
	@Test
	public void test3() throws Exception{
		//1、创建一个xml解析器对象
		SAXReader reader =new SAXReader();
		//2、读取xml文档，返回一个Document对象
		Document doc=reader.read(new File("./src/contact.xml"));
		
		//3、得到根标签
		Element rootElem=doc.getRootElement();
		//、得到标签名称
		/*String name=rootElem.getName();
		System.out.println(name);//contactList
		*/
		
		//4、得到当前标签下的第一个指定名称的子标签对象
		Element contactElem=rootElem.element("contact");
		//System.out.println(contactElem.getName());//contact
		
		//5、得到当前标签下 指定名称的所有子标签
		Iterator<Element> it=rootElem.elementIterator("contact");
		/*while(it.hasNext()){
			Element elem=it.next();
			System.out.println(elem.getName());
		
		}
		//结果:
		  contact
		  contact
		*/
		
		//6得到当前标签下的所有子标签
		List<Element> list=rootElem.elements();
		//遍历List的方法
		//1)传统for循环 2）增强for循环  3）迭代器
		
		/*for(int i=0;i<list.size();i++){
			Element e=list.get(i);
			System.out.println(e.getName());
		}*/
		
		/*for(Element e:list){
			System.out.println(e.getName());
		}*/
		
		Iterator<Element> it2 = list.iterator();//ctrl+alt+l(字母)自动返回结果
		while(it2.hasNext()){
			Element elen = it2.next();
			System.out.println(elen.getName());
		}
		/**
		 * contact
		 * contact
		 */
		
		//获取更深层次的标签(方法只能一层一层的获取)
		//获取name标签
		Element nameElem=doc.getRootElement().
				element("contact").element("name");
		System.out.println(nameElem.getName());//name
		
		
	}
	
	/**
	 * 获取属性
	 */
	@Test
	public void test4()throws Exception{
		//1、创建一个xml解析器对象
		SAXReader reader =new SAXReader();
		//2、读取xml文档，返回一个Document对象
		
		
		Document doc=reader.read(new File("./src/contact.xml"));
		//获取属性：(先获取属性所在的标签对象，然后才能获取属性)
		//1、得到标签对象(如果有同名的，则获取第一个)
		Element contactElem=doc.getRootElement().element("contact");
		//2、得到属性 
		//2.1 得到指定名称 的属性值
		
		/*方式一
		 * String idValue=contactElem.attributeValue("id");
		System.out.println(idValue);//001
		*/
	
		//2。2 得到指定名称的属性对象
		/*Attribute idAttr=contactElem.attribute("id");
		String idName=idAttr.getName();//获取的属性名
		String idValue=idAttr.getValue();//获得属性值
		System.out.println(idName+"="+idValue);*/
		
		
		//2.3得到所有的属性对象  返回List集合
		/*List<Attribute> list=contactElem.attributes();
		//遍历所有的属性
		for(Attribute e:list){
			System.out.println(e.getName());
		}*/
		
		
		//2.4 得到所有属性对象， 返回迭代器
		Iterator<Attribute>it = contactElem.attributeIterator();
		while(it.hasNext()){
			Attribute attr=it.next();
			System.out.println(attr.getName()+"="+attr.getValue());
		}
	
	}
	
	/**
	 * 获取文本内容
	 */
	@Test
	public void test5()throws Exception{
		//1、创建一个xml解析器对象
		SAXReader reader=new SAXReader();
		//2、读取xml文档，返回一个Document对象
		Document doc=reader.read(new File("./src/contact.xml"));
		
		/**
		 * 注意: 空格和换行也是xml的内容
		 * 在xml中，空格换行都作为原始数据处理
		 */
		String content=doc.getRootElement().getText();
		System.out.println(content);
		System.out.println("======");//结果是===号的上面有好几行空格
	
		//获取文本(先获取标签，在获取标签上的文本)
		Element nameElement=doc.getRootElement().
				element("contact").element("name");
		//1、得到文本
		String text=nameElement.getText();
		System.out.println(text);
		
		//2、获取指定子标签名的文本内容
		String text2=doc.getRootElement().element("contact")
							.elementText("name");
		System.out.println(text2);
	}
}
