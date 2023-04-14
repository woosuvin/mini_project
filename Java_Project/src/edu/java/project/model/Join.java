package edu.java.project.model;

public class Join {
	public interface Entity {
		String TBL_NAME = "USERJOIN";
		String COL_NO = "NO";
		String COL_NAME = "NAME";
		String COL_ID = "ID";
		String COL_PASSWORD = "PASSWORD";
		String COL_EMAIL = "EMAIL";
	}
	
	// field
	private Integer no;
	private String name;
	private String id;
	private String password;
	private String email;
	
	public Join() {}

	public Join(Integer no, String name, String id, String password, String email) {
		this.no = no;
		this.name = name;
		this.id = id;
		this.password = password;
		this.email = email;
	}

	public Integer getNo() {
		return no;
	}

	public void setNo(Integer no) {
		this.no = no;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		return String.format("Join | no= %d, name= %s, id= %s, password= %s, email= %s",
				no, name, id, password, email);
	}
	
}
