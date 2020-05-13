package vo;
// VO(Value Object) : 자바에서 특정 값을 저장하는 역할을 하는 클래스
// 회원 한 명의 정보를 저장하는 클래스
public class MemberVO {	
	
	// 속성을 지금 private으로 모두 캡슐화를 시켰다.
	// 외부에서 메소드를 통해서 이 속성들을 다루게 해줘야 한다. 즉 Getter, Setter가 필요하다.
	// 중요한 것! 식별자 변수가 필요하다. ==> id
	private String id;
	private String passwd;
	private String name;
	private String email;
	private String addr;
	private int age;
	private String gender;
	private String tell;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
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
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getTell() {
		return tell;
	}
	public void setTell(String tell) {
		this.tell = tell;
	}
	
	// 회원 정보를 어떻게 보여줄지 나열하기.
	@Override
	public String toString() {
		return "아이디 : " + id +
			   ", 이름 : " + name +
			   ", 주소 : " + addr +
			   ", 이메일 : " + email +
			   ", 비밀번호 : " + passwd +
			   ", 나이 : " + age +
			   ", 전화번호 : " + tell; 
	}
	
}
