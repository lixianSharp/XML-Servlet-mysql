package gz.itcast.a_upload;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 模拟服务器处理上传过来的文件(手动解析文件内容)
 * 
 * 手动解析上传文件
 * @author 贤元
 *
 */

/**
 * 手动处理上传文件的逻辑
 * 
 * 具体的思路就是：先获取上传过来的文件内容，之后再将文件内容写入到另外一个地方去
 * @author 贤元
 *
 */
public class UploadDemo1 extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1）获取实体内容
		InputStream in=request.getInputStream();
		//2)转为字符输入流
		BufferedReader br=new BufferedReader(new InputStreamReader(in));
		
		//文件开始分隔符
		String startTag=br.readLine();
		
		System.out.println("startTag:="+startTag);
		//startTag:=-----------------------------27602454418839
		
		//获取文件名称  Content-Disposition: form-data; name="img"; filename="news.txt"
		String str=br.readLine();
		System.out.println("str:="+str);
		//str:=Content-Disposition: form-data; name="attachment1"; filename="承诺书.jpg"
		String fileName=str.substring(str.indexOf("filename=\"")+10,str.length()-1);
		
		System.out.println("文件名:"+fileName);//文件名:承诺书.jpg
		//跳过两行
		String t1 = br.readLine();
		String t2 = br.readLine();
		System.out.println("t1:="+t1);//t1:=Content-Type: image/jpeg
		System.out.println("t2:="+t2);//t2:=     因为只上传了一个文件，所以t2的值为空的
		
		//读取文件实际内容
		String content=null;
		
		BufferedWriter bw=new BufferedWriter(new FileWriter("d:/"+fileName));
		while((content=br.readLine())!=null){
			//遇到文件结束符号，排除文件的结束符号(读到文件结束符时退出循环)
			if(content.equals(startTag+"--")){
				continue;
			}
			
			//把内容写出文件中
			//写出到服务器的一个文件中
			bw.write(content);
			//写入换行符
			bw.newLine();
			bw.flush();			
		}
		//关闭
		bw.close();
		br.close();
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
