package com.mycompany.myapp.dto;

public class Member {
	private String usesrname;
	private String email;
	private String firstName;	
	private String lastName;
	private String authorities;
	public String getUsesrname() {
		return usesrname;
	}
	public void setUsesrname(String usesrname) {
		this.usesrname = usesrname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getAuthorities() {
		return authorities;
	}
	public void setAuthorities(String authorities) {
		this.authorities = authorities;
	}

}
