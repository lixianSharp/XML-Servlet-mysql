package Note;
/**
  课程目标：
  	Jdbc的优化！
  		1、Beanutils组件
  		2、自定义一个持久层的框架
  		3、DbUtils组件
  		4、案例优化
 
 1.1简介：
 	
 	程序中对JavaBean的操作很频繁，所以apache提供了一套开源的api，方便对JavaBean的
 		操作！！即BeanUtil组件。
 	BeanUtils组件：作用是简化JavaBean的操作！
 	
 	用户可以从www.apache.org下载BeanUtils组件，然后再在项目中引入jar文件。
 	
 	使用Beanutils组件：
 		1、引入commons-beanutils-1.8.3.jar包
 		2、引入日志支持包：commons-logging-1.1.3.jar
 	
 	如果缺少日志jar文件，报错
 	java.lang.NoClassDefFoundError: org/apache/commons/logging/LogFactory
	at org.apache.commons.beanutils.ConvertUtilsBean.<init>(ConvertUtilsBean.java:157)
	at org.apache.commons.beanutils.BeanUtilsBean.<init>(BeanUtilsBean.java:117)
	at org.apache.commons.beanutils.BeanUtilsBean$1.initialValue(BeanUtilsBean.java:68)
	
	出现上面异常的两种情况：
		你的类不存在
		缺少jar包

	
	1.2 实例：
		方法1： 对象属性的拷贝
			BeanUtils.copyProperty(admin, "userName", "jack");
			BeanUtils.setProperty(admin, "age", 18);
		方法2： 对象的拷贝
			BeanUtils.copyProperties(newAdmin, admin);
		方法3： map数据拷贝到javabean中  
			【注意：map中的key要与javabean的属性名称一致】
		   BeanUtils.populate(adminMap, map);

		//1. 对javabean的基本操作
		@Test
		public void test1() throws Exception {
		
		// a. 基本操作
		Admin admin = new Admin();
	//	admin.setUserName("Jack");
//		admin.setPwd("999");
		
		// b. BeanUtils组件实现对象属性的拷贝
		BeanUtils.copyProperty(admin, "userName", "jack");
		BeanUtils.setProperty(admin, "age", 18);
		
		// 总结1： 对于基本数据类型，会自动进行类型转换!
		
		
		// c. 对象的拷贝
		Admin newAdmin = new Admin();
		BeanUtils.copyProperties(newAdmin, admin);
		
		// d. map数据，拷贝到对象中
		Admin adminMap = new Admin();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userName", "Jerry");
		map.put("age", 29);
		// 注意：map中的key要与javabean的属性名称一致
		BeanUtils.populate(adminMap, map);
		
		// 测试
		System.out.println(adminMap.getUserName());
		System.out.println(adminMap.getAge());
	}


 
 	1.3 实例,日期类型的拷贝
 		需要注册日期类型转换器，2种方式参见下面代码：

public class App {

	//1. 对javabean的基本操作
	@Test
	public void test1() throws Exception {
		
		// a. 基本操作
		Admin admin = new Admin();
//		admin.setUserName("Jack");
//		admin.setPwd("999");
		
		// b. BeanUtils组件实现对象属性的拷贝
		BeanUtils.copyProperty(admin, "userName", "jack");
		BeanUtils.setProperty(admin, "age", 18);
		
		// 总结1： 对于基本数据类型，会自动进行类型转换!
		
		
		// c. 对象的拷贝
		Admin newAdmin = new Admin();
		BeanUtils.copyProperties(newAdmin, admin);
		
		// d. map数据，拷贝到对象中
		Admin adminMap = new Admin();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userName", "Jerry");
		map.put("age", 29);
		// 注意：map中的key要与javabean的属性名称一致
		BeanUtils.populate(adminMap, map);
		
		// 测试
		System.out.println(adminMap.getUserName());
		System.out.println(adminMap.getAge());
	}
	
	
	//2. 自定义日期类型转换器
	@Test
	public void test2() throws Exception {
		// 模拟表单数据
		String name = "jack";
		String age = "20";
		String birth = "   ";
		
		// 对象
		Admin admin = new Admin();
		
		// 注册日期类型转换器：1， 自定义的方式
		ConvertUtils.register(new Converter() {
			// 转换的内部实现方法，需要重写
			@Override
			public Object convert(Class type, Object value) {
				
				// 判断
				if (type != Date.class) {
					return null;
				}
				if (value == null || "".equals(value.toString().trim())) {
					return null;
				}
				
				
				try {
					// 字符串转换为日期
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					return sdf.parse(value.toString());
				} catch (ParseException e) {
					throw new RuntimeException(e);
				}
			}
		},Date.class);
		
		
		
		// 把表单提交的数据，封装到对象中
		BeanUtils.copyProperty(admin, "userName", name);
		BeanUtils.copyProperty(admin, "age", age);
		BeanUtils.copyProperty(admin, "birth", birth);
		
		//------ 测试------
		System.out.println(admin);
	}
	
	//2. 使用提供的日期类型转换器工具类
	@Test
	public void test3() throws Exception {
		// 模拟表单数据
		String name = "jack";
		String age = "20";
		String birth = null;
		
		// 对象
		Admin admin = new Admin();
		
		// 注册日期类型转换器：2， 使用组件提供的转换器工具类
		ConvertUtils.register(new DateLocaleConverter(), Date.class);
				
		// 把表单提交的数据，封装到对象中
		BeanUtils.copyProperty(admin, "userName", name);
		BeanUtils.copyProperty(admin, "age", age);
		BeanUtils.copyProperty(admin, "birth", birth);
		
		//------ 测试------
		System.out.println(admin);
	}
}

  
    1.4 应用
   	
   	public class WebUtils {

	  @Deprecated
	  public static <T> T copyToBean_old(HttpServletRequest request, Class<T> clazz) {
		try {
			// 创建对象
			T t = clazz.newInstance();
			
			// 获取所有的表单元素的名称
			Enumeration<String> enums = request.getParameterNames();
			// 遍历
			while (enums.hasMoreElements()) {
				// 获取表单元素的名称:<input type="password" name="pwd"/>
				String name = enums.nextElement();  // pwd
				// 获取名称对应的值
				String value = request.getParameter(name);
				// 把指定属性名称对应的值进行拷贝
				BeanUtils.copyProperty(t, name, value);
			}
			
			return t;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 处理请求数据的封装
	 *//*
	public static <T> T copyToBean(HttpServletRequest request, Class<T> clazz) {
		try {
			// （注册日期类型转换器）
			// 创建对象
			T t = clazz.newInstance();
			BeanUtils.populate(t, request.getParameterMap());
			return t;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}

 
 	2、元数据：
 		元数据：数据库、表、列的定义信息。
 		在Jdbc中获取数据库的定义，例如：数据库、表、列的定义信息。就用到元数据。
 		在绝对不是中可以使用：数据库元数据、参数元数据、结果集元数据。
 		(元数据定义相关api)
 		
 		Connection.getDatabaseMetaData()
		DataBaseMetaData对象
				getURL()：返回一个String类对象，代表数据库的URL。
				getUserName()：返回连接当前数据库管理系统的用户名。
				getDatabaseProductName()：返回数据库的产品名称。
				getDatabaseProductVersion()：返回数据库的版本号。
				getDriverName()：返回驱动驱动程序的名称。
				getDriverVersion()：返回驱动程序的版本号。
				isReadOnly()：返回一个boolean值，指示数据库是否只允许读操作。

 
 
 			PreparedStatement . getParameterMetaData() 
				获得代表PreparedStatement元数据的ParameterMetaData对象。 
				Select * from user where name=? And password=?
			ParameterMetaData对象
				getParameterCount() 
				获得指定参数的个数
				getParameterType(int param) 
				获得指定参数的sql类型

 
 
 			ResultSet. getMetaData() 
				获得代表ResultSet对象元数据的ResultSetMetaData对象。 
			ResultSetMetaData对象
				getColumnCount() 
				返回resultset对象的列数
				getColumnName(int column) 
				获得指定列的名称
				 getColumnTypeName(int column)
				获得指定列的类型 

 
 	 3、Dao操作的抽取，BaseDao
 	 	
 	 	Dao操作通用的步骤：
 	 		0、写sql语句
 	 		1、获取连接
 	 		2、创建stmt
 	 		3、执行sql
 	 			a）更新
 	 			b）查询
 			4、关闭/异常
 
 	 	通用的Dao
 	 		1、更新
 	 			String sql="select * from admin";
 	 			String sql="select * from admin where id=? And pwd=?";
 	 			public void update(String sql,Object[]paramValues);
 	 		2.	查询
				String sql = “select * from admin”;
				String sql = “select * from admin  where  id=?  And pwd =?”;
				// 传入的什么类型的对象，就封装为什么类型
				// 要求： 列的名称，要与指定类型的对象的属性名称一样
				Public    List<T>    query  (String sql , Object[] paramValues ,  Class<T> clazz);
				T  t;  // 对象赋值
				对象的属性名与列名一样

 				
 
 	  4. DbUtils组件
			commons-dbutils 是 Apache 组织提供的一个开源 JDBC工具类库，它是对JDBC的简单封装，学习成本极低，并且使用dbutils能极大简化jdbc编码的工作量，同时也不会影响程序的性能。因此dbutils成为很多不喜欢hibernate的公司的首选。

		 DbUtils组件，
			1.	简化jdbc操作
			2.	下载组件，引入jar文件 : commons-dbutils-1.6.jar
		
		实例：
		  |-- DbUtils   关闭资源、加载驱动
		  |-- QueryRunner   组件的核心工具类：定义了所有的与数据库操作的方法(查询、更新)
			Int  update(Connection conn, String sql, Object param);   执行更新带一个占位符的sql
			Int  update(Connection conn, String sql, Object…  param); 执行更新带多个占位符的sql
			Int[]  batch(Connection conn, String sql, Object[][] params)        批处理
			T  query(Connection conn ,String sql, ResultSetHandler<T> rsh, Object... params)   查询方法


			Int  update( String sql, Object param);  
			Int  update( String sql, Object…  param); 
			Int[]  batch( String sql, Object[][] params)       
			注意： 如果调用DbUtils组件的操作数据库方法，没有传入连接对象，那么在实例化QueryRunner对象的时候需要传入数据源对象： QueryRunner qr = new QueryRunner(ds);

 
 		DbUtils提供的封装结果的一些对象：
			1）	BeanHandler: 查询返回单个对象
			2）	BeanListHandler: 查询返回list集合，集合元素是指定的对象
			3)  ArrayHandler, 查询返回结果记录的第一行，封装对对象数组, 即返回：Object[]
			4)  ArrayListHandler, 把查询的每一行都封装为对象数组，再添加到list集合中
			5)  ScalarHandler 查询返回结果记录的第一行的第一列  (在聚合函数统计的时候用)
			6)  MapHandler  查询返回结果的第一条记录封装为map

	  
	  	项目优化
			对之前的注册功能，优化，引入dbutils组件！
		要求：
			BaseDao.java
			查询、更新的通用的方法！
			其他的dao就继承baseDao即可！



		练习：
		1.	DbUtils组件实现更新的测试
		2.	DbUtils组件实现查询的测试
		3.	把项目改为用DbUtils组件实现操作数据库
		4.	beanUtils组件
		5.	元数据、BaseDao编码

 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 * @author 贤元
 *
 */
public class TodayNote {

}
