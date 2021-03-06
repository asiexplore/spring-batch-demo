package com.example.springbatchdemo.model;

public class UserModel {
	
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

	@Override
	public String toString() {
		return "UserModel [userId=" + userId + ", name=" + name + ", dept=" + dept + ", account=" + account + "]";
	}
}