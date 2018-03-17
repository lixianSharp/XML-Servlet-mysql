package gz.itcast.f_cases;

import java.util.List;

import org.junit.Test;

public class TestDao  {


	@Test
	public void testTea() throws Exception {
		TeacherDao teaDao = new TeacherDao();
		List<Teacher> list = teaDao.findAll();
		for (Teacher teacher : list) {
			System.out.println(teacher);
		}
	}
	
	
	@Test
	public void testStu() throws Exception {
		StudentDao stuDao=new StudentDao();
		List<Student> list=stuDao.findAll();
		//stuDao.findAll();
		
		for(Student stu:list){
			System.out.println(stu);
		}
		
	}
	
}
