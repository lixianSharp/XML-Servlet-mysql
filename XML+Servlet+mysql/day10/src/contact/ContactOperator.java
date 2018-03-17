package contact;

import java.util.List;

/**
 * 该接口用于对联系人的一些相关操作(CRUD增、删、改、查)
 * @author 贤元
 *
 */
public interface ContactOperator {

	//对联系人相关操作的方法
	public void addContact(Contact contact);//增加联系人
	public void updateContact(Contact contact);//修改联系人
	public void deleteContact(String id);//根据ID删除联系人
	public List<Contact> findAll();//查询所有联系人

}
