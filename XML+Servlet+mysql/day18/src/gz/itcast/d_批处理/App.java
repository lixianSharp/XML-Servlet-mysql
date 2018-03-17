package gz.itcast.d_批处理;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class App {

	/**
	 * 测试批处理操作
	 * @throws Exception
	 */
	@Test
	public void testBatch(){
		//模拟数据
		List<Admin> list=new ArrayList<Admin>();
		//初始化数据
		for(int i=1;i<21;i++){
			Admin admin=new Admin();
			admin.setUserName("jack"+i);
			admin.setPwd("888"+i);
			list.add(admin);
		}
		
		//保存
		AdminDao dao=new AdminDao();
		dao.save(list);
	}
}