package gz.itcast.a_auto;

import org.junit.Test;

public class App {
	//保存员工
	@Test
	public void testSave(){
		//模拟数据
		//部门
		Dept d=new Dept();
		d.setDeptName("应用开发部");
		//员工
		Employee emp=new Employee();
		emp.setEmpName("李贤元");
		emp.setDept(d);//关联 
		
		//调用dao保存
		EmpDao empDao=new EmpDao();
		empDao.save(emp);
		
	}
	
	
}
