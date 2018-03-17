package b_xPath;

import java.io.File;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * 使用xpath技术读取一个规范的html文档
 * @author 贤元
 *
 */
public class Demo4 {

	private static final String Element = null;

	public static void main(String[] args) throws Exception {
		Document doc=new SAXReader().read(new File("./src/personList.html"));
		
		System.out.println(doc);
		
		//读取title标签
		Element titleElem=(Element)doc.selectSingleNode("//title");
		String title=titleElem.getText();
		//System.out.println(title);
		
		
		/**
		 * 练习：读取联系人的所有信息
		 * 按照一些格式输出：
		 * 		编号：001 姓名：张三 性别：男 年龄：18 地址：xxx 电话： 
		 * 		编号：002 姓名：李四 性别：女 年龄：18 地址：xxx 电话：
		 * 		... 
		 */
		
		//1、获取所有tbody中的标签    获取tbody标签下所有tr标签的子节点   !!:Node可以强转为一个Element
		List<Element> list = (List<Element>)doc.selectNodes("//tbody/tr");
		//2、遍历
		for(Element elem:list){
			/*第一种做法  使用dom4j技术来做
			 * //获取编号
			String id=((Element)elem.elements().get(0)).getText();
			//获取姓名
			String name=((Element)elem.elements().get(1)).getText();
			//性别
			String gender=((Element)elem.elements().get(2)).getText();
			//年龄
			String age=((Element)elem.elements().get(3)).getText();
			//地址
			String address=((Element)elem.elements().get(4)).getText();
			//电话
			String phone=((Element)elem.elements().get(5)).getText();
			*/
			
			//第二种做法 全部使用xpath技术来做  
			String id=elem.selectSingleNode("td[1]").getText();//代表当前的第一个td节点
			String name=elem.selectSingleNode("td[2]").getText();
			String gender=elem.selectSingleNode("td[3]").getText();
			String age=elem.selectSingleNode("td[4]").getText();
			String address=elem.selectSingleNode("td[5]").getText();
			String phone=elem.selectSingleNode("td[6]").getText();
			
			System.out.println("编号："+id+"\t姓名："+name+"\t性别"+gender
					+"\t年龄"+age+"\t地址"+address+"\t电话"+phone);
		}
	}
}
