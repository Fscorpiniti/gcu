package com.edu.untref.gcu.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "security_token_user")
public class SecurityTokenUser extends PersistibleObject {

	private static final long serialVersionUID = 2732312713022172711L;

	@Column(length = 255, nullable = false)
	private String username;
	
	@Column(length = 255, nullable = false)
	private String token;
	
	public SecurityTokenUser() {}

	public SecurityTokenUser(String username, String token) {
		this.username = username;
		this.token = token;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
}
