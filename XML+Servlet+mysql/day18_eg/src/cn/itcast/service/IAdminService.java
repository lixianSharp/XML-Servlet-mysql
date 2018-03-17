package cn.itcast.service;

import cn.itcast.entity.Admin;
import cn.itcast.exception.UserExistsException;

/**
 * 业务逻辑层接口设计
 * @author Jie.Yuan
 *
 */
public interface IAdminService {

	/**
	 * 注册
	 */
	void register(Admin admin) throws UserExistsException;
	
	/**
	 * 登陆
	 */
	Admin login(Admin admin);
}
