package com.project.ep.services;

import com.project.ep.models.Faculty;
import com.project.ep.models.Course;
import java.util.List;

public interface FacultyService {
    public Faculty checkfacultylogin(String email, String password);
    public List<Course> getMappedCourses(int facultyId);
}
