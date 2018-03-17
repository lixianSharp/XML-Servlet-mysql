package gz.itcast.ContactSys__web.service.impl;

import gz.itcast.ContactSys__web.dao.ContactDao;
import gz.itcast.ContactSys__web.dao.impl.ContactDaoImpl;
import gz.itcast.ContactSys__web.entity.Contact;
import gz.itcast.ContactSys__web.exception.NameRepeatException;
import gz.itcast.ContactSys__web.service.ContactService;

import java.util.List;
/**
 * 业务逻辑层
 * 处理项目中出现的业务逻辑
 * @author 贤元
 *
 */
public class ContactServiceImpl implements ContactService {

ContactDao dao = new ContactDaoImpl();
	
	public void addContact(Contact contact) throws NameRepeatException {
		//执行业务逻辑判断
		if(dao.checkContact(contact.getName())){
			//重复
			/**
			 * 注意： 如果业务层方法出现任何业务异常，则返回标记（自定义异常）到servlet
			 */
			throw new NameRepeatException("姓名重复，不可使用");
		}
		//如果不重复，才执行添加方法
		dao.addContact(contact);
	}

	public void deleteContact(String id) {
		dao.deleteContact(id);
	}

	public List<Contact> findAll() {
		return dao.findAll();
	}

	public Contact findById(String id) {
		return dao.findById(id);
	}

	public void updateContact(Contact contact) {
		dao.updateContact(contact);
	}

}