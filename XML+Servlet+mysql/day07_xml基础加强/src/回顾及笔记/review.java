package 回顾及笔记;
/*
  XML基础：
  	1）XML的作用
  		1.1作为软件配置文件
  		1.2作为小型的"数据库"
 	2）XML语法(由w3c组织规定的)
 		标签：
 			标签名不能以数字开头，中间不能有空格，区分大小写，有且只有一个根标签
 		属性：
 			可以有多个属性，但属性值必须用引号包含(单引号或者双引号),但不能省略，但是不嫩单双混用。
 		文档声明：
 			<?xml version="1.0" encoding="utf-8"?>
 			encoding="utf-8" 表示打开或解析xml时的编码
 		   注意：
 			保存xml文档时的编码 和 解析xml的编码要保持一致，才能避免乱码问题。
	3）XML解析
		 程序读取或操作XML文档
		 
		 两种解析方式：
		 	DOM解析     
		 		原理：一次性把xml文档加载成document树，通过document得到一个节点对象，通过节点对象访问xml文档内容（标签、属性、文本、注释）
		 		
		 		Dom4j工具(基于DOM解析原理)
		 			读取xml文档
		 				Document document=new SAXReader().read(new File("xml文档的路径url"));
		 			节点：
		 				nodeIterator();所有子节点
		 			标签：
		 				element("名称"); 指定名称的第一个子标签对象
		 				elementIterator("名称");指定名称的所有子标签对象
		 				elements();所有子标签对象
		 			属性：（首先要拿到标签）
		 				attributeValue("名称");指定名称的属性值
		 				attribute("名称");指定名称的属性对象
		 						getName();属性名称
		 						getValue();属性值
		 				attributeIterator();所有属性对象 (Iterator)
		 				attributes();所有的属性对象(List)
		 			文本：
		 				getText();得到当前标签的文本内容
		 				elementText("子标签名称");得到子标签文本
		 		
		 		
		 	SAX解析
	

*/
public class review {

}
