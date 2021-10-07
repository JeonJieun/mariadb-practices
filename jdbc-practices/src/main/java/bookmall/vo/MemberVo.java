package bookmall.vo;

public class MemberVo {
	private Long no;
	private String name;
	private String email;
	private String password;
	private String phoneNum;
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	@Override
	public String toString() {
		return "MemberVo [no=" + no + ", name=" + name + ", email=" + email + ", password=" + password + ", phone_num="
				+ phoneNum + "]";
	}
	

}
