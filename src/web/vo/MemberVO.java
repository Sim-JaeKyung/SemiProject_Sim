package web.vo;

public class MemberVO {
	//변수 선언
    private String name, id, pw, address;
    private int age;
    //기본 생성자
	public MemberVO() {
		super();
	}
	public MemberVO(String name, int age, String id, String pw, String address) {
		super();
		setId(id);
		setPw(pw);
		setName(name);
		setAddress(address);
		setAge(age);
	}
	public MemberVO(String id, String pw) {
		this.id = id;
		this.pw = pw;
	}
	public MemberVO(String name, String id, String pw) {
		super();
		this.name = name;
		this.id = id;
		this.pw = pw;
	}
	//세터 & 게터 (유효성 검증 必)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	//toString
	@Override
	public String toString() {
		return "MemberVO [name=" + name + ", id=" + id + ", pw=" + pw + ", address=" + address + ", age=" + age + "]";
	}
	
    
}
