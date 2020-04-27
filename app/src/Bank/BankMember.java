package Bank;

public class BankMember {
	private String name;
	private String id;
	private String passwd;
	
	
	public BankMember(String name, String id, String passwd) {
		this.name = name;
		this.id = id;
		this.passwd = passwd;
	}


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


	public String getPasswd() {
		return passwd;
	}


	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	
	
}
