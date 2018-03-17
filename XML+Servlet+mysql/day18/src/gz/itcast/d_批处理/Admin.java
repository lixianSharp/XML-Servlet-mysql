package gz.itcast.d_批处理;
/**
 * 实体封装数据
 * @author 贤元
 */
public class Admin {
	private String userName;
	private String pwd;
	
	public void setUserName(String userName){
		this.userName=userName;
	}
	public String getUserName(){
		return this.userName;
	}
	
	public void setPwd(String pwd){
		this.pwd=pwd;
	}
	public String getPwd(){
		return this.pwd;
	}
	
}