package gz.itcast.entity;
/**
 * 该对象用于封装登录用户的所有数据
 * @author 贤元
 *
 */
public class OnLineBean {

	private String sessionId;//session对象的Id
	private String name;//登录名
	private String ip;//ip
	private String loginTime;//登录时间
	private String lastTime;//最后访问时间
	public String getSessionId() {
		return sessionId;
	}
	public String getName() {
		return name;
	}
	public String getIp() {
		return ip;
	}
	public String getLoginTime() {
		return loginTime;
	}
	public String getLastTime() {
		return lastTime;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}
	public void setLastTime(String lastTime) {
		this.lastTime = lastTime;
	}
	
	

	
	
}
