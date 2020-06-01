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
	public Member setId(String id) {
		this.id = id;
		return this;
	}
	public String getPw() {
		return pw;
	}
	public Member setPw(String pw) {
		this.pw = pw;
		return this;
	}
	public String getMail() {
		return mail;
	}
	public Member setMail(String mail) {
		this.mail = mail;
		return this;
	}
	public String getProgramming() {
		return programming;
	}
	public Member setProgramming(String programming) {
		this.programming = programming;
		return this;
	}

	
	
}
