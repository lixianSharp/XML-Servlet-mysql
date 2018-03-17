package a_attention_;

import java.util.Iterator;

import org.dom4j.Element;

/*
  4、XML解析
  	4.1引入
  		xml文件除了给开发者看，更多的情况程序读取xml文件的内容，这叫xml解析。
	
	4.2 XML解析方式(原理不同，所以只有两种)
	
		DOM解析
		SAX解析
	
	4.3 XML解析工具
		
		DOM解析原理：
			1)JAXP (sun->oracle 公司官方)
			2)JDOM 工具 (非官方)
			3)Dom4j 工具(非官方)(最好用的)
				三大框架(默认读取xml的工具就是Dom4j)
			...
		
		SAX解析原理:
			1)Sax解析工具((sun->oracle 公司官方))

  XML解析器把解析xml文档时，把xml文件的各个内容封装成对象，通过这些对象操作xml文档，
  		这种做法叫做DOM解析(DOM编程)。
  
  	
  	Document树：
  		树只有一个根标签。树上的分支，叫节点(Node)

		Document对象，代表一个完整的XML文档。
		通过Document对象可以得到	其下面的其他节点对象，
		通过节点对象访问xml文档的内容(节点、标签、属性、文本、、、)
		
		节点信息：   	    Node
			节点名称
			
		标签节点:		Element
			标签名称
		
		属性节点:		Attribute
			属性名称
			属性值
		
		文本节点：		Text
			文本内容
			
	4.4 什么是DOM解析
		DOM解析原理：xml解析器一次性把整个xml文档加载进内存，然后在内存中构建一颗Document的对象树，通过Document对象，
					得到树上的节点对象，通过节点对象访问(操作)到xml文档的内容。

	4.5 Dom4j工具：
			原理：DOM解析
			
			非官方，不在JDK中
		
		使用步骤：
			1)导入Dom4j的核心包，dom4j-1.6.1.jar
			2)编写Dom4j读取xml文件代码
		
    4.6 Dom4j读取xml文件
			节点：
			   Iterator	Element.nodeIterator();//获取当前标签节点下的所有子节点
			
			标签：
				Element	Document.getRootElement();//获取xml文档的根标签
				Element	Element.element("标签名");//指定名称的第一个子标签
			?	Iterator<Element>  Element.elementIterator("标签名");//指定名称的所有的子标签
				List<Element>   Element.elements();获取所有子标签
			
			属性：
				String Element.attributeValue("属性名");获取指定名称的属性值
				Attribute  Element.attribute("属性名");获取指定名称的属性对象
						   Attribute.getName();获取属性名称
						   Arrribute.getValue();获取属性值
				List<Element>  Element.attributes();//获取所有属性对象
				Iterator<Attribute> Element.iterator();//获取所有属性对象
				
				
			文本：
				Element.getText();获取当前标签的文本
				Element.elementText("标签名");获取当前标签的指定名称的子标签的文本内容
	
		
    练习：
    1）把写的联系人的xml文件，用dom4j完整的读取并打印出来
	
				
				
	总结：
		1）XML作用：
			配置文件(最常见)
			作为数据库
			
		2）XML语法
		3）XML解析(DOM解析原理)
			3.1 DOM解析原理
			3.2 Dom4j工具(基于DOM解析原理)
				读取：
					节点：
						标签节点
						属性节点
						文本节点
			
*/			
public class xml笔记2 {

}
