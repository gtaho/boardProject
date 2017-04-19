package vo;
//학생 클래스 같은거에여 ㅁㅏㅈㅏ여
public class MemberVO {

	private String memId;
	private String jumin;
	private String password;
	private String name;
	private int postNo;
	private String phone;
	private String email;
	private String memo;
	private String grade;
	private String address;
	
	
	public MemberVO() {
		super();
	}

	public MemberVO(String memId, String jumin, String password, String name, int postNo, String phone, String email,
			String memo, String address) {
		super();
		this.memId = memId;
		this.jumin = jumin;
		this.password = password;
		this.name = name;
		this.postNo = postNo;
		this.phone = phone;
		this.email = email;
		this.memo = memo;
		this.address = address;
	}
	
	public MemberVO(String memId, String jumin, String password, String name, int postNo, String phone, String email,
			String memo, String grade, String address) {
		super();
		this.memId = memId;
		this.jumin = jumin;
		this.password = password;
		this.name = name;
		this.postNo = postNo;
		this.phone = phone;
		this.email = email;
		this.memo = memo;
		this.grade = grade;
		this.address = address;
	}


	public String getMemId() {
		return memId;
	}


	public void setMemId(String memId) {
		this.memId = memId;
	}


	public String getJumin() {
		return jumin;
	}


	public void setJumin(String jumin) {
		this.jumin = jumin;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getPostNo() {
		return postNo;
	}


	public void setPostNo(int postNo) {
		this.postNo = postNo;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getMemo() {
		return memo;
	}


	public void setMemo(String memo) {
		this.memo = memo;
	}


	public String getGrade() {
		return grade;
	}


	public void setGrade(String grade) {
		this.grade = grade;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	@Override
	public String toString() {
		return "MemberVO [memId=" + memId + ", jumin=" + jumin + ", password=" + password + ", name=" + name
				+ ", postNo=" + postNo + ", phone=" + phone + ", email=" + email + ", memo=" + memo + ", grade=" + grade
				+ ", address=" + address + "]";
	}


	
	
	
	
	
	
}
