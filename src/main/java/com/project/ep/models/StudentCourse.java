package com.project.ep.models;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="student_course_table")
public class StudentCourse implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="sc_id")
    private int id;

    @ManyToOne
    @JoinColumn(name="s_id", nullable=false)
    private Student student;

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

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
