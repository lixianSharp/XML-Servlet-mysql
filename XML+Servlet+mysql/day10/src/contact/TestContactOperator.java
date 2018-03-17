package contact;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class TestContactOperator {

	ContactOperator operator = null;

	/**
	 * 初始化对象实例
	 */
	@Before
	public void init() {
		operator = new ContactOperatorImpl();
	}

	@Test
	public void addtestAddContact() {
		Contact contact = new Contact();
		contact.setId("2");
		contact.setName("eric");
		contact.setGender("男");
		contact.setAge(20);
		contact.setPhone("2343253253");
		contact.setEmail("eric@qq.com");
		contact.setQq("213345436");
		operator.addContact(contact);
	}

	@Test
	public void testUpdateContact() {
		Contact contact = new Contact();
		contact.setId("2");
		contact.setName("eric");
		contact.setGender("女");
		contact.setAge(12);
		contact.setPhone("2343253253");
		contact.setEmail("eric@qq.com");
		contact.setQq("213345436");
		operator.updateContact(contact);

	}

	@Test
	public void testDelete() {
		operator.deleteContact("2");
	}

	@Test
	public void testFindAll() {
		List<Contact> list = operator.findAll();
		for (Contact contact : list) {
			System.out.println(contact);
		}

	}
}
