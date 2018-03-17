package 回顾及笔记;
public class TodayNote {

}
/*
今天的目标：   Dom4j修改xml  + xPath技术 +SAX解析+XML约束

	2、Dom4j修改xml文档
		
		2、1 写出内容到xml文档
			
		  XMLWriter writer=	new XMLWriter(OutputStream,OutputFormat);
		  writer.write(Document);
		
		2、2 修改xml文档的API
			
			增加：
				DocumentHelper.createDocument();增加一个Document文档
				addElement("名称");增加标签
				addAttribute("名称");增加一个属性
			修改：
				Attribute.setValue("值");修改属性值
				Element.addAttribute("同名的属性名","值");修改属性值
				Element.setText("内容");

			删除：
				Element.detach();删除标签
				Attribute.detach();删除标签
	
	3、xPath技术(xPath是一门独立的语言，dom4j内置支持xPath,xPath的语法是通用的)
	
		3.1 引入
			
			问题：当使用dom4j查询 比较深的层次结构的节点(标签、属性、文本),比较麻烦！！
	    
	    3.2 xPath作用
	    	 主要是用于快速获取所需的节点对象
		
		3.3 在dom4j中如何使用xPath技术
			
			1） 导入xPath支持jar包，   jaxen-1.1-beta-6.jar
			2）使用xPath方法
				List<Node> selectNodes("xPath的表达式");查找多个节点对象
				Node	   selectSingleNode("xPath的表达式");查找一个节点对象

	   
	   3.4 xPath语法
	   		
	   		/    绝对路径    表示从xml文档的根位置开始或子元素(一个层次结构)
	   		//	 相对路径	表示不分任何层次选择元素(表示选择文档中所有满足双斜线//之后规则的元素(无论层级关系))
	   		*    通配符	表示匹配所有的元素
			[]   条件		表示选择什么条件下的元素
			@	 属性		表示选取属性节点
			and   关系	表示条件的一种与关系(等价于&&的意思)
			text()  文本     表示选择文本内容
			
	
	  3.5 案例
	  		用户登录功能：
	  			用户输入用户名和密码--->到"数据库"查询是否有对应的用户-》
	  				有，则表示登录成功
	  				没有，表示登录不失败
			用xml当做数据库
				user.xml  用来存储用户的数据
				
				
				
				
	 4 SAX解析
	 	4.1 回顾Dom解析
	 		DOM解析原理：一次性把xml文档加载进内存，然后在内存中构建Document树。(会受到内存的限制)
	 			缺点：不适合去读取一些大容量的文件，读大容量的容量容易导致内存的溢出
	 			student.xml 1M	1G
	 			
	 	SAX解析原理：加载一点，读取一点，处理一点，对内存的要求比较低。
	 		
	 	4.2 SAX解析工具
	 		
	 		 SAX解析工具： Sun公司提供的。内置在JDK中。org.xml.sax.*
	 		
	 		 核心的API:
	 		 	SAXParser类(相当于SAXReader):用于读取和解析xml文件
	 				parse(File f, DefaultHandler dh) 方法：用于解析xml文件
			
					参数一：File 表示 读取的xml文件所在的路径
					参数二：DefaultHandler ： SAX事件的处理程序  使用一个DefaultHandler
					
			    
			    DefaultHandler类的API：
			    	void startDocument();在读到文档开始时调用

					void endDocument();在读到文档结束时调用
					
					startElement(String uri, String localName, String qName, Attributes attributes) 读到开始标签时调用
					
				    endElement(String uri, String localName, String qName)  读到结束标签时调用

					characters(char[] ch, int start, int length) 读到文本内容时调用


		
		
========================================================
		
		===========DOM解析	  		vs			SAX解析==============
	原理：一次加载xml文档不适合大容量的文件读取		加载一点，读取一点，处理一点，适合大容量文件读取
	
	DOM解析可以任意进行增删改查					SAX只能读取
	
	DOM解析可以任意读取任何位置的数据，甚至往回读		SAX只能从上往下，按顺序读取，不能往回读。
	
	DOM解析面向对象的编程方式(Node. Element,	基于事件的编程方法。java开发者编码相对复杂。
	Attribute),Java开发者编码比较简单
	

	
=======================================
	总结：
		1）Dom4j修改xml文档
			new XMLWriter();
			......

		2）xPath技术，快速查询xml节点
			selectNodes();
			selectSingleNode();
		  xpath表达式语言
		
		3、SAX解析
			SAXParser parse
				parse() ....
			
		 DefaultHandler类：
		 		startElement();
		 		characters();
		 		endElemetn();

作业：设计一个通讯里程序(dom4j)
		联系人： 编号 姓名 性别  年龄 电话 qq email
		功能要求：
			添加、修改、删除、查询所有联系人
			
		 要求:
		 	1）交互使用console(控制台)
			20数据存储到xml文件(作为"数据库")
			
		提示：
			启动程序
				======
				【1】添加联系人
				【2】修改联系人
				【3】删除联系人
				【4】查询所有联系人
				【q】退出程序
			输入1：
				请输入编号
				请输入姓名
				请输入年龄
			   .....
			        添加成功！ 数据保存成功
			 
		  	输入4：
		  		编号   姓名  年龄  ....
		  		001 张三    30  ...
		  		002 李四   29   ...
*/



