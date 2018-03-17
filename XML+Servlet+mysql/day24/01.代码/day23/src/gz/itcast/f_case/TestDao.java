package gz.itcast.f_case;

import java.util.List;

import org.junit.Test;

public class TestDao {

	@Test
	public void testStu(){
		StudentDao stuDao = new StudentDao();
		List<Student> list = stuDao.findAll();
		
		for (Student student : list) {
			System.out.println(student);
		}
	}
	@Test
	public void testTea(){
		TeacherDao teaDao = new TeacherDao();
		List<Teacher> list = teaDao.findAll();
		for (Teacher teacher : list) {
			System.out.println(teacher);
		}
		
	}
}
