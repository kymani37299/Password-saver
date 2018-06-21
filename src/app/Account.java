package app;

import java.io.Serializable;

public class Account implements Serializable{

	private static final long serialVersionUID = -1603349280970620391L;

	private final static String testing = "TestStringv1.0";
	private static int next_id = 1;
	
	private transient long id;
	
	private String testingVal;
	
	private String server;
	
	private String username;
	private String password;
	private String email;
	private String other;
	
	public Account(String server,String username,String password,String email,String other,String testingVal) {
		this.server = server;
		this.username = username;
		this.password = password;
		this.email = email;
		this.other = other;
		this.testingVal = testingVal;
		this.setID();
	}
	
	public Account(String server,String username,String password,String email,String other) {
		this(server,username,password,email,other,testing);
	}

	public void setID() {
		this.id = next_id++;
	}
	
	public void setID(long id) {
		this.id = id;
	}
	
	public long getID() {
		return id;
	}
	
	public boolean isValid() {
		return testingVal.equals(testing);
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public String getServer() {
		return server;
	}

	public void setServer(String server) {
		this.server = server;
	}

	public String getTestingVal() {
		return testingVal;
	}

	public void setTestingVal(String testingVal) {
		this.testingVal = testingVal;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Account) {
			Account acc = (Account)obj;
			return acc.id == this.id;
		}
		return super.equals(obj);
	}
	
	@Override
	public String toString() {
		return this.server;
	}
}
