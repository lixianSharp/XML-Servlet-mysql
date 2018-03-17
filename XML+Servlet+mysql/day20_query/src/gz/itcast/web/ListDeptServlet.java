package gz.itcast.web;

import gz.itcast.entity.Department;
import gz.itcast.service.DeptService;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListDeptServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1)得到部门数据
		DeptService deptService=new DeptService();
		List<Department> list=deptService.findAll();
		//2）存储到域对象中
		request.setAttribute("list", list);
		//3)转发
		request.getRequestDispatcher("/listDept.jsp").forward(request, response);
		
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
