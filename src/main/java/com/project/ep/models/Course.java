package com.project.ep.models;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="course_table")
public class Course implements Serializable {

    @Id
    @Column(name="c_id")
    private int id;

    @Column(name="c_name", nullable=false, length=100)
    private String name;

    @Column(name="c_code", length=500)
    private String code;

    @Column(name="c_credits", nullable=false)
    private int credits;
    
    @Column(name="c_ltps", nullable=false)
    private String ltps;

    @Column(name="c_department", nullable=false, length=30)
    private String department;

    // Getters and Setters

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

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLtps() {
		return ltps;
	}

	public void setLtps(String ltps) {
		this.ltps = ltps;
	}
}
