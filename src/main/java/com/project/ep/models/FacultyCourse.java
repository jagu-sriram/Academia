package com.project.ep.models;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="faculty_course_table")
public class FacultyCourse implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="fc_id")
    private int id;

    @ManyToOne
    @JoinColumn(name="f_id", nullable=false)
    private Faculty faculty;

    @ManyToOne
    @JoinColumn(name="c_id", nullable=false)
    private Course course;

    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
