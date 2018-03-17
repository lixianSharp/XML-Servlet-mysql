package gz.itcast.service;

import gz.itcast.dao.DeptDao;
import gz.itcast.entity.Department;

import java.util.List;
/**
 * 部门的service类
 * @author 贤元
 *
 */
public class DeptService {

	DeptDao deptDao=new DeptDao();
	public List<Department> findAll(){
		
		return deptDao.findAll();
		
	}
}
