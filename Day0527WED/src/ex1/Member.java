package ex1;
public class Member {
	
	private String id;
	private String pw;
	private String mail;
	private String programming;
	
	public Member(String id, String pw, String mail, String programming){
		this.id = id;
		this.pw = pw;
		this.mail = mail;
		this.programming = programming;
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
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getProgramming() {
		return programming;
	}
	public void setProgramming(String programming) {
		this.programming = programming;
	}

	
	
}
