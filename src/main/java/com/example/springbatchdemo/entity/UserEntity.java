package com.example.springbatchdemo.entity;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity(name = "USER")
public class UserEntity {
	
	@Id
	private Long userId;
	
	private String name;
	
	private String dept;
	
	private Long account;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public Long getAccount() {
		return account;
	}

	public void setAccount(Long account) {
		this.account = account;
	}
}