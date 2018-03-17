package gz.itcast.web;

import gz.itcast.dao.EmpDao;
import gz.itcast.entity.Employee;
import gz.itcast.entity.PageBean;
import gz.itcast.service.EmpService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//http://localhost:8080/day20_page/ListEmpServlet?currentPage=1、2、3
public class ListEmpServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//t1(request, response);
		
		
		//t2(request, response);
		
		/****  1）接收用户输入的参数 ： currentPage参数   ****/
		String currentPage = request.getParameter("currentPage");
		if (currentPage == null || currentPage.equals("")) {
			// 如果用户第一次访问，没有传递currentPage参数，则当前页为1
			currentPage = "1";
		}
		//接收用户输入的每页显示记录数
		String pageSize = request.getParameter("pageSize");
		//如果没有传递这个pageSize参数，则为默认值5
		if(pageSize==null || pageSize.equals("")){
			pageSize = "5";
		}
		
		/*******2)调用业务逻辑方法，获取结果    *****************/
		//1)封装PageBean对象
		EmpService service = new EmpService();
		PageBean pageBean = service.queryPageBean(Integer.parseInt(currentPage),Integer.parseInt(pageSize));

		/********3)把结果转发到jsp页面显示   *********************/
		//2)把PageBean对象放入域对象中
		request.setAttribute("pageBean", pageBean);
		
		//3)转发到jsp页面显示数据
		request.getRequestDispatcher("/listEmp.jsp").forward(request, response);
	
	}

	private void t2(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1)封装PageBean对象
		PageBean pageBean = new PageBean();
		
		//1.3 从用户参数中获取当前页数据(currentPage)
		String currentPage = request.getParameter("currentPage");
		if(currentPage==null || currentPage.equals("")){
			//如果用户第一次访问，没有传递currentPage参数，则当前页为1
			currentPage = "1";
		}
		pageBean.setCurrentPage(Integer.parseInt(currentPage));
		
		EmpDao empDao = new EmpDao();
		//从数据库中读取总记录数
		int count = empDao.queryCount();
		//1.4 总记录数
		pageBean.setTotalCount(count);
		
		//1.5 每页显示记录数
		pageBean.setPageSize(5);
			
		//从数据库中读取当前页数据
		List<Employee> list = empDao.queryData(pageBean.getCurrentPage(), pageBean.getPageSize());
		pageBean.setData(list);

		//2)把PageBean对象放入域对象中
		request.setAttribute("pageBean", pageBean);
		
		//3)转发到jsp页面显示数据
		request.getRequestDispatcher("/listEmp.jsp").forward(request, response);
	}

	private void t1(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1)封装PageBean对象
		PageBean pageBean = new PageBean();
		
		//1.1 当前页数据
		List<Employee> list = new ArrayList<Employee>();
		for(int i=1;i<=5;i++){
			list.add(new Employee(i,"张三"+i,"男","软件开发工程师","zhangsan"+i+"@qq.com",4000+i*1000));
		}
		pageBean.setData(list);
		
		
		//1.2 首页
		pageBean.setFirstPage(1);
		
		//1.3 从用户参数中获取当前页数据(currentPage)
		String currentPage = request.getParameter("currentPage");
		if(currentPage==null || currentPage.equals("")){
			//如果用户第一次访问，没有传递currentPage参数，则当前页为1
			currentPage = "1";
		}
		pageBean.setCurrentPage(Integer.parseInt(currentPage));
		
		
		//1.3 上一页     算法：如果当前页是首页，则为1，否则为（当前页-1）
		pageBean.setPrePage(pageBean.getCurrentPage()==pageBean.getFirstPage()
							?1:
							pageBean.getCurrentPage()-1);
		
		
		//1.4 总记录数
		pageBean.setTotalCount(21);
		
		//1.5 每页显示记录数
		pageBean.setPageSize(5);
		
		//1.6 末页/总页数    算法：  如果   总记录数%每页显示记录数能够整除 ,则为（总记录数/每页显示记录数），否则  （总记录数/每页显示记录数+1）  
		pageBean.setTotalPage(
				pageBean.getTotalCount()%pageBean.getPageSize()==0
				? pageBean.getTotalCount()/pageBean.getPageSize()
				: pageBean.getTotalCount()/pageBean.getPageSize()+1);		
		
		
		//1.7 下一页    算法： 如果当前页是末页，则为末页，否则为（当前页+1）
		pageBean.setNextPage(
				pageBean.getCurrentPage()==pageBean.getTotalPage()
				? pageBean.getTotalPage()
				: pageBean.getCurrentPage()+1);		
		
		
		//2)把PageBean对象放入域对象中
		request.setAttribute("pageBean", pageBean);
		
		//3)转发到jsp页面显示数据
		request.getRequestDispatcher("/listEmp.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
