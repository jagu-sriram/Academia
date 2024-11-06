package com.project.ep.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="faculty_table")
public class Faculty implements Serializable
{
	@Id //primary = unique + not null
	@Column(name="f_id")
	private int id;
	@Column(name="f_name",nullable=false,length=50)
	private String name;
	@Column(name="f_gender",nullable=false,length=10)
	private String gender;
	@Column(name="f_role",nullable=false,length=20)
	private String role;
	@Column(name="f_email",nullable=false,length=50,unique=true)
	private String email;
	@Column(name="f_password",nullable=false,length=50)
	private String password;
	@Column(name="f_qualification",nullable=false,length=50)
	private String qualification;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getQualification() {
		return qualification;
	}
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

}
