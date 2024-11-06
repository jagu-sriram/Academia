package com.project.ep.services;

import com.project.ep.models.Course;
import com.project.ep.models.Student;
import java.util.List;

public interface StudentService {
    public Student checkstudentlogin(String email, String password);
    public List<Course> getAllCourses();
    public String registerForCourse(int studentId, int courseId);
    public List<Course> getRegisteredCourses(int studentId);
}
