package gz.itcast.b_entity;
/**
 * 用于存储上传文件的信息
 * @author 贤元
 *
 */
public class FileBean {

	private String name;//文件名称
	private String size;//文件大小
	private String type;//文件类型
	private String addTime;//上传时间
	
	public String getName() {
		return name;
	}
	public String getSize() {
		return size;
	}
	public String getType() {
		return type;
	}
	public String getAddTime() {
		return addTime;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	public FileBean(String name, String size, String type, String addTime) {
		super();
		this.name = name;
		this.size = size;
		this.type = type;
		this.addTime = addTime;
	}
	public FileBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	
}
