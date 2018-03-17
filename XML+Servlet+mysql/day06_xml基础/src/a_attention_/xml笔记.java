package a_attention_;
/*
 1 XML入门
		1.1 引入
		HTML: 负责网页的结构	
		CSS： 负责网页的样式（美观）
		Javascript： 负责在浏览器端与用户进行交互。

		负责静态的网页制作的语言
			
		HTML语言特点：
				1）由标签组成。 <title> <p> <hr/> <br/>
				2）语法结构松散的    <p></p>   <p>  <P> 
					大小写不区分
					结束标签和开始标签不一定匹配
				
				
<html>
	<head>
		<title>this is title</title>
	</head>
	<body>
		<p>html标签</p>
		<P>html标签</P>
		<abc>abc标签</abc>  自定义标签
	</body>
</html>

				这种自定义标签可以把他们叫做xml标签。
		
		1.2 HTML和XML的区别	
				HTML                                      XML	
名称：       HyperText Markup Languae（超文本标记语言）    Extend Markup Languge（可扩展标签语言）
标签：     标签是w3c组成指定，固定的，约100来个         标签由开发者自己制定的（要按照一定的语法定															义）
作用：      负责网页的结构                               1）描述带关系的数据（作为软件的配置文件）: 包															含与被包含的关系
															properties文件： key-value 
																		name=eric
																		password=123456

																	<user>
																		<name>eric</name>
																		<password>123456</password>
																	</user>
															场景： 
																	tomcat
																	struts Hibernate spring （三大框架）
														2）作为数据的载体（存储数据，小型的“数据库”）
															

2 XML作用
		2.1 描述带关系的数据（软件的配置文件）
			web服务器（PC）： 
			学生管理系统 -> 添加学生功能 -> 添加学生页面 -> name=eric&email=eric@qq.com  
				前提： 网络（IP地址： oracle：255.43.12.54  端口：1521 ）

				java代码：使用ip（255.43.12.54）地址和端口（1521），连接oracle数据库，保存学生数据。

				把ip地址端口配置到xml文件：
						host.xml
							<host>
								<ip>255.43.12.55</ip>
								<port>1521</port>
							</host>

			数据库服务器（PC）：
						主服务器（255.43.12.54）：Oracle数据库软件（负载）
						副服务器（255.43.12.55）：Oracle数据库软件
		2.2 数据的载体（小型的“数据库”）
			教师管理系统： 	姓名   工龄+1  邮箱 

			发教师数据给财务管理系统： 
					String teacher =    name=张三&email=zhangsan@qq.com&workage=2  字符串
							（问题： 1）不好解析 2）不是规范）

					teacher.xml
						<teacher>	
								<name>张三</name>
								<email>zhangsan@qq.com</email>
								<workage>2</workage>
						</teacher>
					这种一种规范

			财务管理系统：   
						姓名   工龄+1  邮箱
					发奖金：   统计奖金。   工龄
					发邮件功能： 
							邮箱   姓名   金额

			方案一： 在财务管理系统中维护了一套教师信息。
					每年 ： 工龄增加  维护了两个系统的信息。

			方案二： 教师信息只在教学管理系统中维护。
3 XML语法
		xml文件以xml后缀名结尾。
		xml文件需要使用xml解析器去解析。浏览器内置了xml解析器。
		3.1 标签
			语法： <student></student>  开始标签  标签体内容  结束标签
				1）<student/> 或 <student></student> 空标签。没有标签体内容
				2）xml标签名称区分大小写。
				3）xml标签一定要正确配对。
				
				4）xml标签名中间不能使用空格。
				
				5）xml标签名不能以数字开头
				
				6）注意：在一个xml文档中，有且仅有一个根标签。
				
				
				
		3.2 属性	
				
			语法：<Student name="eric">student</Student>
				
			注意事项：
				1）属性值必须以引号包含，不能省略，可以两个双引号或者两个单引号，但是不能单双引号混用！！。
				2)一个标签内可以有多个属性，但是不能出现重复的属性名。
				
		3.3 注释
			 语言： <!-- 注释的内容 -->
				
		练习：
			通讯录系统
			联系人数据：编号(唯一的) 姓名 年龄 电话 邮箱 QQ
				
			要求：
				
			   contact.xml	1）设计一个xml文件，可以用于存储联系人数据
				2）这个xml文件可以存取多个联系人
				
		3.4 文档声明
			语法： <?xml version="1.0" encoding="utf-8"?>
			
			version: xml的版本号
			encoding： 解析xml文件时查询的码表(解码过程时查询的码表)
			注意：1）如果在myeclipse工具中开发xml文件，保存xml文件时自动按照文档声明的encoding来保存文件。
				2)如果用记事本工具修改xml文件，注意保存xml文件按照文档声明的encoding的码表来保存
			
			涉及xml文件编码问题：
				1）保存xml文件的时候（编码过程） （另存为xml文档的时候指定）
				2）打开/解析xml文件的时候（解码过程）(通过文档的声明指定 <?xml version="1.0" encoding="GBK"?>)
	
 			如果要使的读取的xml文件不存在中文乱码问题：
				1）保证以上两个地方的编码一致！！！
				2）编码要支持中文
	    3.5  转义字符
	  		 在xml中内置了一些特殊字符，这些特殊字符不能直接被浏览器原样输出，如果希望原样输出到浏览器，对这些特殊字符进行转义。转义之后的字符就叫转义字符。
	  		
	  			特殊字符	转义字符
	  			<		&lt;
	  			>		&gt;
	  			"		&quot;
	  			&		&amp;
	  			空格		&nbsp;
		
		3.6 CDATA块
		作用：可以让一些需要进行包含特殊字符的内容统一进行原样输出
		语法：
			语法：<![CDATA[ 内容 ]]>
	
			<![CDATA[
				<itcast>
					<br/>
				</itcast>
			]]>

	  3.7 处理指令
	   	作用：告诉xml解析 如何解析xml文档，处理指令用来指挥解析引擎如何解析xml文档内容。
	   	案例： <?xml-stylesheet type="text/css" href="1.css"?> 告诉xml解析该xml文档引用了哪个css文件。

				需要提取xml内容可以使用 xml-stylesheet处理指令
				
			
				

	
			
*/
public class xml笔记 {

}
