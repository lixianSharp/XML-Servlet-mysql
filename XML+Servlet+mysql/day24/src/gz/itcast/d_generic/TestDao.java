package gz.itcast.d_generic;

import java.util.List;

import org.junit.Test;

public class TestDao  {

	@Test
	public void testStu() throws Exception {
		StudentDao stuDao=new StudentDao();
		List<Student> list=stuDao.findAll();
		//stuDao.findAll();
		
		for(Student stu:list){
			System.out.println(stu);
		}
		
	}
	
	@Test
	public void testTea() throws Exception {
		TeacherDao teaDao=new TeacherDao();
		List<Teacher> list=teaDao.findAll();
		//teaDao.findAll();
		
		for(Teacher tea:list){
			System.out.println(tea);
		}
	}
}
