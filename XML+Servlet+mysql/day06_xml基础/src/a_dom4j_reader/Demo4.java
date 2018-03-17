package a_dom4j_reader;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.Text;
import org.dom4j.io.SAXReader;
import org.junit.Test;
/**
 * 使用dom4j读取xml文档(将xml文档中的全部内容读取出来)
 * @author 贤元
 *
 */
public class Demo4 {

	@Test
	public void test() throws Exception {
		// 读取xml文档
		SAXReader reader = new SAXReader();
		Document doc = reader.read(new File("./src/contact.xml"));
		// 读取根标签
		Element rootELem = doc.getRootElement();
		StringBuffer sb = new StringBuffer();
		getChildNodes(rootELem, sb);
		System.out.println(sb.toString());
	}

	/**
	 * 获取当前标签的所有子标签
	 */
	private void getChildNodes(Element elem, StringBuffer sb) {
		// System.out.println(elem.getName());
		// 开始标签
		sb.append("<" + elem.getName());
		// 得到标签的属性列表
		List<Attribute> attrs = elem.attributes();
		if (attrs != null) {
			for (Attribute attr : attrs) {
				// System.out.println(attr.getName()+":"+attr.getValue());
				sb.append(" " + attr.getName() + "=\"" + attr.getValue() + "\"");
			}
		}
		sb.append(">");
		// 获取当前标签的所有子节点(标签或者Attribute或者Text)
		Iterator<Node> it = elem.nodeIterator();
		// 遍历
		while (it.hasNext()) {
			Node node = it.next();
			// 标签 判断是否是标签节点
			if (node instanceof Element) {
				Element e1 = (Element) node;
				getChildNodes(e1, sb);
			}

			// 文本 判断是否是文本节点
			if (node instanceof Text) {
				Text text = (Text) node;
				sb.append(text.getText());
			}
		}
		// 结束标签
		sb.append("</" + elem.getName() + ">");
	}
}
