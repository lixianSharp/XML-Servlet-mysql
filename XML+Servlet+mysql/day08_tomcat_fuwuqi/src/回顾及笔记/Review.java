package 回顾及笔记;
/**
 XML加强：
 	1）Dom4j修改XML文档
 			XMLWriter writer=new XMLWriter();
 			writer.write(doc);写出xml文档
 
 		增加：
 			DocumentHelper.createDocumetn() 增加新文档
 			Element.addElement("name")；增加子标签
 			Element.addAttribute("name","value");增加属性
 		
 		修改：
 			Attribute.setValue("value");删除标签
 			Element.setText("value");修改文本内容
 		
 		删除：
 			Element.detach();删除标签
 			Attribute.detach();删除标签
 			
 	2）XPath技术：快速找到xml元素(标签、属性、文本)
 		2.1dom4j使用xpath
 				List<Node>list=Element.selectNodes("xpath表达式");拿到多个节点对象
 				Node node=Element.selectSingleNode("xpath表达式");拿到一个节点对象
 		
 		xpath表达式：
 			/	表示根位置 或者 子标签
 			// 	表示后代标签(不分层次结构)
 			*	表示所有元素
 			[]	表示条件
 			@	表示选择属性
 			text()	表示选择文本
 			and		表示与条件
 			
 	3）SAX解析：原理：加载一点，解析一点，处理一点，对内存的要求不高！！（基于事件）
 		
 		SAXParser类：
 			parser(File file,DefaultHandler handler) 该方法使用SAX解析方式去解析xml文档
 	
 		DefaultHandler类：重写该类中的一些方法，用于处理xml文档
 			startElement(.. String qName);读到开始标签时调用
 			characters(char[] ch,int start,int length);读到文本内容是调用(包括空格和换行)
 			endElement(...String qName);读到结束标签时调用
 
 * @author 贤元
 *
 */



public class Review {

}
