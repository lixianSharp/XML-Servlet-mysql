<%@ page language="java" 
	import="java.util.*,b_entity.*"
	pageEncoding="UTF-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>EL输出不同类型的数据</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>

<body>
	<%--
  	1)EL输出对象的属性
   --%>
	<%
		//保存数据
		Student student = new Student("eric", 20);
		//放入域中
		pageContext.setAttribute("studnet", student);

		//List
		List<Student> list = new ArrayList<Student>();
		list.add(new Student("rose", 18));
		list.add(new Student("jack", 28));
		list.add(new Student("lucy", 38));
		//放入域中
		pageContext.setAttribute("list", list);
		
		
		//Map
		Map<String,Student> map=new HashMap<String,Student>();
		map.put("100",new Student("mark",20));
		map.put("200",new Student("lixian",23));
		map.put("300",new Student("lixianyuan",22));
		//放入域中
		pageContext.setAttribute("map",map);
	%>

	<%--使用EL获取对象 		--%>
	 ${student.name } - ${student.age }
	<%--
		${student.name}  等价于   （ .点相当于调用getXxx()方法） 
			(Student)pageContext.findAttribute("student").getName();
	 --%>
	<hr/>
	
	${list[0].name } - ${list[0].age }<br/>
	${list[1].name } - ${list[1].age }<br/>
	${list[2].name } - ${list[2].age }<br/>
	<%--
		list[0]等价于        （中括号相当于调用get(参数)方法）
		  ((List)pageContext.findAttribute("list")).get(0);
	 --%>
	 
	 <hr/>
	 <%--使用EL获取Map对象 --%>
	 ${map['100'].name }  -  ${map['100'].age }<br/>
	 ${map['200'].name }  -  ${map['200'].age }<br/>
	 ${map['300'].name }  -  ${map['300'].age }<br/>
	 <%--注意：　［key］相当于get（key）方法 --%>
	 
</body>
</html>
