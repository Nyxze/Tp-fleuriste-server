package com.formation.jpa.util.dto;

public class UserDto {
	private String username;
	private String pswd;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPswd() {
		return pswd;
	}

	public void setPsw(String pswd) {
		this.pswd = pswd;
	}

	@Override
	public String toString() {
		return "UserDto [username=" + username + ", psw=" + pswd + "]";
	}

}
