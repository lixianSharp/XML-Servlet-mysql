package c_server;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * socket服务器端程序
 * @author 贤元
 *
 */
public class Server {

	public static void main(String[] args) throws Exception {
		//1、创建ServerSocket
		ServerSocket server=new ServerSocket(8888);//填写一个要监听的端口号
		
		System.out.println("服务器已经启动成功");
		
		while(true){
		//2、接受客户端的连接
		Socket socket=server.accept();
		
		//读取本地的text.html文件
		FileInputStream in=new FileInputStream(new File("d:/test.html"));
		
		//4、构建一个数据输出通道
		OutputStream out=socket.getOutputStream();
		
		//5、发送数据
		byte[] buf=new byte[1024];
		int len=0;
		while((len=in.read(buf))!=-1){
			out.write(buf, 0, len);
		}
		
		//6、关闭资源
		out.close();
		in.close();
		}
		
		
	}
	
}
