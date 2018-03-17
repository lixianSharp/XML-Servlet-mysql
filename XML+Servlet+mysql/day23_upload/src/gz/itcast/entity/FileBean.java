package gz.itcast.entity;

public class FileBean {

	private int id;
	private String name;
	private String size;
	private String type;
	private String addTime;
	private String file_path;
	private String info;
	public int getId() {
		return id;
	}
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
	public String getFile_path() {
		return file_path;
	}
	public String getInfo() {
		return info;
	}
	public void setId(int id) {
		this.id = id;
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
	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	
}