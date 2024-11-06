package com.project.ep.services;

import java.util.List;
import com.project.ep.models.Admin;
import com.project.ep.models.Faculty;
import com.project.ep.models.Student;
import com.project.ep.models.Course;

public interface AdminService {
    Admin checkadminlogin(String username, String password);
    String registerfaculty(Faculty faculty);
    String registerstudent(Student student);
    void addCourse(Course course);
    String mapFacultyToCourse(int facultyId, int courseId);
    List<Course> getAllCourses();
    boolean isCourseRegistrationAllowed();
    void setCourseRegistrationAllowed(boolean allowed);
}
