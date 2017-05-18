package org.xi.quick.usermanager.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "user")
public class UserEntity {

	public UserEntity() {
		super();
	}

	public UserEntity(String username, String password, String email, String phone) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.phone = phone;
	}

	public UserEntity(Integer userId, String username, String password, String email, String phone,
			Timestamp createTime, Integer status) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.createTime = createTime;
		this.status = status;
	}

	@Id
	@Column(name = "user_id")
	private Integer userId;

	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;

	@Column(name = "email")
	private String email;

	@Column(name = "phone")
	private String phone;

	@Column(name = "create_time")
	private Timestamp createTime;

	@Column(name = "status")
	private Integer status;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStaus(Integer status) {
		this.status = status;
	}
	
}
