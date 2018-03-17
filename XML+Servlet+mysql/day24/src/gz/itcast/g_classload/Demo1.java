package gz.itcast.g_classload;

import static org.junit.Assert.*;

import org.junit.Test;

public class Demo1 {

	public static void main(String[] args) throws Exception {
		new Demo1().test();
	}
	
	public void test() throws Exception {
		/**
		 * 1)系统提供给我们三个类加载器：
		 * 		BootStrap加载器：加载jdk/jre/lib/rt.jar(开发时用的核心类)
		 * 		ExtClassLoad加载器：加载jdk/jre/lib/ext/*.jar (扩展包)	
		 * 		AppClassLoad加载器：加载CLASSPATH中的jar包和class文件
		 *      MyClassLoader加载器     MyClassLoader2加载器  都是AppClassLoader的子类
		 * 
		 * 2)这三个类加载器是树状结构：
		 * 3)类加载的过程：
		 * 		3.1 一个类A是一个类加载器加载的，如果类A中使用到了类B，类B也是由类A的类加载器加载
		 * 4)委托机制：
		 * 		4.1 发起者类加载器  去加载类的时候，先委托其父类加载器，如果还有父加载器，则继续委托上去，直到没有父加载器为止。
		 * 				最顶层的类加载器就需要真正的去加载这个指定的类，如果在其类目录中国找不到这个类，那就继续往下找，找到发起者类加载器为止！！
		 * 																					如果还没有找到就报错
		 * 
		 * 委托机制的好处：
		 * 		可以让代码加载更加安全和高效！ 保证核心类的字节码只有一份在内存中。
		 * 
		 * 
		 * 
		 * 
		 */
		//得到某个列被哪个类加载器加载
		System.out.println(Demo1.class.getClassLoader());//sun.misc.Launcher$AppClassLoader@456d3d51
		
		//这个列就由BootStrap加载
		System.out.println(java.util.Date.class.getClassLoader());//null
		
		//查看某个类加载器树状结构
		ClassLoader classLoader=Demo1.class.getClassLoader();
		while(classLoader!=null){
			System.out.println(classLoader.getClass());
			classLoader=classLoader.getParent();
		}
		/**
		 * class sun.misc.Launcher$AppClassLoader
			class sun.misc.Launcher$ExtClassLoader
		 */
		System.out.println(classLoader);//null
		
	}
	
	
}
