package com.project.ep.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="student_table")
public class Student implements Serializable
{
	@Id //primary = unique + not null
	@Column(name="s_id")
	private int id;
	@Column(name="s_name",nullable=false,length=50)
	private String name;
	@Column(name="s_gender",nullable=false,length=10)
	private String gender;
	@Column(name="s_age",nullable=false,length=10)
	private String age;
	@Column(name = "s_department",nullable = false,length = 30)
	private String department;
	@Column(name="s_email",nullable=false,length=50,unique=true)
	private String email;
	@Column(name="s_password",nullable=false,length=50)
	private String password;
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
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
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

}
