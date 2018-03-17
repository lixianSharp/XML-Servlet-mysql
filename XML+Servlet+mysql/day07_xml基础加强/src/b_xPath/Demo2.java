package b_xPath;

import java.io.File;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

/**
 * 学习xPath表达式的语法
 * @author 贤元
 *
 */
public class Demo2 {

	public static void main(String[] args) throws Exception{
		Document doc=new SAXReader().read(new File("./src/contact1.xml"));
		
		/**
		 * 1、 	 /    绝对路径    表示从xml文档的根位置开始或子元素(一个层次结构)
		 */
		
		String xpath="/contactList";
		xpath="/contactList/contact";
		
		/**
		 * 2、  //   相对路径   表示不分任何层次结构的选择元素
		 */
		xpath="//contact/name";
		xpath="//name";
		
		
		/**
		 * 3.    *   通配符   表示匹配所有元素
		 */
		xpath="/contactList/*";//根标签contactList下的所有子标签
		xpath="/contactList//*";//根标签contactList下的所有标签(不分层次结构)
		
		
		/**
		 * 4、  []   条件     表示选择什么条件下的元素
		 */
		//需求：带有id属性的contact标签
		xpath="//contact[@id]";
		//第二个的contact标签
		xpath="//contact[2]";
		//选取最后一个contact标签
		xpath="//contact[last()]";
		
		
		/**
		 * 5、     @	属性		表示选择属性节点
		 */
		xpath="//@id";//选取的是id的节点(属性)对象，返回Attribute对象
		xpath="//contact[not(@id)]";//选择的是不包含id属性的contact标签节点
		//选择id属性值为002的contact标签
		xpath="//contact[@id='002']";
		//选择id属性值为001，且name属性为eric的contact对象
		xpath="//contact[@id='001' and @name='eric']";
		
		
		List<Node> list=doc.selectNodes(xpath);
		for(Node node:list){
			System.out.println(node);
		}
		
		
		System.out.println("---------------------------");
		
		/**
		 * 6、  text()    表示选择文本内容
		 */
		//选择所有name标签内的文本，返回的是Text对象
		xpath="//name/text()";
		//选择姓名为张三的name标签
		xpath="//contact/name[text()='张三']";
		
		List<Node> list2=doc.selectNodes(xpath);//查找符合xpath表达式要求的多个节点对象
		for(Node node:list2){
			System.out.println(node);
		}
	}
}
