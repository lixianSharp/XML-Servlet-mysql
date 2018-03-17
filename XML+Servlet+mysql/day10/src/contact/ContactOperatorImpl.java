package contact;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * 实现联系人操作的接口
 * 
 * @author 贤元
 * 
 */
public class ContactOperatorImpl implements ContactOperator {

	/**
	 * 添加联系人 
	 * 方法中的参数contact：就是要进行添加的联系人
	 */
	@Override
	public void addContact(Contact contact) {
		try {
			Document doc = null;
			Element rootElem = null;
			// 先把xml文件读取出来，再添加进去；如果存在该xml文件，则直接操作；如果不存在该xml文件，则创建xml文件
			File file = new File("d:/contact.xml");
			if (!file.exists()) {
				// 1.文件不存在，则创建一个xml文件
				doc = DocumentHelper.createDocument();
				// 2.创建跟标签
				rootElem = doc.addElement("contactList");
			} else {
				// 如果有xml文件，则读取xml文件
				doc = new SAXReader().read(file);
				// 如果有xml文件，则读取跟标签
				rootElem = doc.getRootElement();// 获取跟标签
			}

			// 添加contact标签
			Element contactElem = rootElem.addElement("contact");
			contactElem.addAttribute("id", contact.getId());
			Element nameElem = contactElem.addElement("name");
			nameElem.addText(contact.getName());
			Element genderElem = contactElem.addElement("gender");
			genderElem.addText(contact.getGender());
			Element ageElem = contactElem.addElement("age");
			ageElem.addText(contact.getAge() + "");
			Element phoneElem = contactElem.addElement("phone");
			phoneElem.addText(contact.getPhone());
			Element emailElem = contactElem.addElement("email");
			emailElem.addText(contact.getEmail());
			Element qqElem = contactElem.addElement("qq");
			qqElem.addText(contact.getQq());
			
			//把Document写出到xml文件
			XMLUtil.writeXml(doc);

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	/**
	 * 修改联系人
	 * 方法中的参数contact：就是把数据修改成contact中的数据
	 */
	@Override
	public void updateContact(Contact contact) {
		try {
			/**
			 * 需求：修改id值为2的联系人 
			 * 操作步骤：1、先查询id值为2的contact标签  2、修改contact标签的内容
			 */
			
			//1、读取xml文件
			Document doc=XMLUtil.getDocument();
			
			//查找到id值为2的contact标签
			Element contactElem=(Element)doc.selectNodes("//contact[@id='"+contact.getId()+"']");
			
			//2.修改Contact标签内容
			contactElem.element("name").setText(contact.getName());
			contactElem.element("gender").setText(contact.getGender());
			contactElem.element("age").setText(contact.getAge()+"");
			contactElem.element("phone").setText(contact.getPhone());
			contactElem.element("email").setText(contact.getEmail());
			contactElem.element("qq").setText(contact.getQq());
			
			//3.把Document写出到xml文件
			XMLUtil.writeXml(doc);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}		
	}

	/**
	 * 根据id删除联系人
	 */
	@Override
	public void deleteContact(String id) {
		try {
			/**
			 * 步骤：读取xml文件--》根据id查找到contact标签--》删除标签
			 * 
			 */
			//1、读取xml文件
			Document doc=XMLUtil.getDocument();
			//2.查找需要删除id的contact
			Element contactElem=(Element) doc.selectSingleNode("//contact[@id='"+id+"']");
			//3.删除标签
			contactElem.detach();
			//4.把进行删除指定联系人操作后的Document写出到xml文件
			XMLUtil.writeXml(doc);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}

	/**
	 * 查找联系人
	 */
	@Override
	public List<Contact> findAll() {
		/**
		 * 步骤：读取xml文件-》读取contact标签-》把读取到的数据存放到一个集合中返回
		 */
		//1.读取xml文件
		Document doc=XMLUtil.getDocument();
		
		//创建List对象 用于存放查找结果
		List<Contact> list=new ArrayList<Contact>();
		
		//读取Contact标签
		List<Element> conList = (List<Element>)doc.selectNodes("//contact");
		//遍历
		for(Element e:conList){
			Contact c=new Contact();
			//将读取到的数据存放在Contact对象中
			c.setId(e.attributeValue("id"));
			c.setName(e.elementText("name"));
			c.setGender(e.elementText("gender"));
			c.setAge(Integer.parseInt(e.elementText("age")));
			c.setPhone(e.elementText("phone"));
			c.setEmail(e.elementText("email"));
			c.setQq(e.elementText("qq"));
			
			//把Contact对象存放到list中
			list.add(c);
		}
		return list;
	}

}
