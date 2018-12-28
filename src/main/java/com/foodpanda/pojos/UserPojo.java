package com.foodpanda.pojos;

import java.sql.ResultSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "foodpanda_user_details ")
@Component
@Scope(value="prototype")
public class UserPojo {
	@Id
	private String emailId;
	private String password;
	private String mobile;
	private String name;

	@Version
	private long version;
	
	private String role="USER";

	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	public UserPojo( ) {

	}
	

	public UserPojo(String emailId , String password, String mobile, String name) {
	
	}

	public long getVersion() {
		System.out.println(version);
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
