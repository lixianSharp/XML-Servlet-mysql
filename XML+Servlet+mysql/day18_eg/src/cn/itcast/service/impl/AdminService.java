package cn.itcast.service.impl;

import cn.itcast.dao.IAdminDao;
import cn.itcast.dao.impl.AdminDao;
import cn.itcast.entity.Admin;
import cn.itcast.exception.UserExistsException;
import cn.itcast.service.IAdminService;

/**
 * 3. 业务逻辑层实现
 * @author Jie.Yuan
 *
 */
public class AdminService implements IAdminService {

	// 调用的dao
	private IAdminDao adminDao = new AdminDao();
	
	@Override
	public Admin login(Admin admin) {
		try {
			return adminDao.findByNameAndPwd(admin);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void register(Admin admin) throws UserExistsException  {
		
		try {
			// 1. 先根据用户名查询用户是否存在
			boolean flag = adminDao.userExists(admin.getUserName());
			
			// 2. 如果用户存在，不允许注册
			if (flag){
				// 不允许注册, 给调用者提示
				throw new UserExistsException("用户名已经存在，注册失败！");
			}
			
			// 3. 用户不存在，才可以注册
			adminDao.save(admin);
		
		} catch (UserExistsException e) {
			throw e;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
