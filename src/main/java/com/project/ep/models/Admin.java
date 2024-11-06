package com.project.ep.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="admin_table")
public class Admin implements Serializable 
{
   @Id // primary key = unique + key
   @Column(name="a_username",length = 30)
   private String username;
   @Column(name="a_password",length = 30,nullable = false)
   private String password;
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
}
