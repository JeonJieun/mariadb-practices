package bookmall.vo;

public class CategoryVo {
	private Long no;
	private Long name;
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public Long getName() {
		return name;
	}
	public void setName(Long name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "CategoryVo [no=" + no + ", name=" + name + "]";
	}
}
