package com.project.ep.managedbeans;

import java.io.IOException;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.ep.models.Admin;
import com.project.ep.models.Faculty;
import com.project.ep.models.Student;
import com.project.ep.models.Course;
import com.project.ep.services.AdminService;

@ManagedBean(name="adminbean", eager=true)
public class AdminBean {
    @EJB(lookup="java:global/CollegeERP/AdminServiceImpl!com.project.ep.services.AdminService")
    AdminService adminService;
    
    private String username;
    private String password;
    private Faculty faculty = new Faculty();
    private Student student = new Student();
    private Course course = new Course();
    private int facultyId;
    private int courseId;
    private boolean courseRegistrationAllowed;
    
    // Getters and Setters
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
    public Faculty getFaculty() {
        return faculty;
    }
    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
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
    public int getFacultyId() {
        return facultyId;
    }
    public void setFacultyId(int facultyId) {
        this.facultyId = facultyId;
    }
    public int getCourseId() {
        return courseId;
    }
    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }
    public boolean isCourseRegistrationAllowed() {
        return courseRegistrationAllowed;
    }
    public void setCourseRegistrationAllowed(boolean courseRegistrationAllowed) {
        this.courseRegistrationAllowed = courseRegistrationAllowed;
    }

    // Methods
    public void validateadminlogin() throws IOException {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();
        
        Admin admin = adminService.checkadminlogin(username, password);
        
        if (admin != null) {
            HttpSession session = request.getSession();
            session.setAttribute("admin", admin);
            response.sendRedirect("adminhome.jsf");
        } else {
            response.sendRedirect("adminloginfail.jsf");
        }
    }

    public void addfaculty() throws IOException {
        if (isAdminLoggedIn()) {
            adminService.registerfaculty(faculty);
        } else {
            redirectToLogin();
        }
    }

    public void addstudent() throws IOException {
        if (isAdminLoggedIn()) {
            adminService.registerstudent(student);
        } else {
            redirectToLogin();
        }
    }

    public void addcourse() throws IOException {
        if (isAdminLoggedIn()) {
            adminService.addCourse(course);
        } else {
            redirectToLogin();
        }
    }

    public void mapFacultyToCourse() throws IOException {
        if (isAdminLoggedIn()) {
            adminService.mapFacultyToCourse(facultyId, courseId);
        } else {
            redirectToLogin();
        }
    }

    public void updateCourseRegistrationStatus() throws IOException {
        if (isAdminLoggedIn()) {
            adminService.setCourseRegistrationAllowed(courseRegistrationAllowed);
        } else {
            redirectToLogin();
        }
    }

    private boolean isAdminLoggedIn() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpSession session = (HttpSession) externalContext.getSession(false);
        return session != null && session.getAttribute("admin") != null;
    }

    private void redirectToLogin() throws IOException {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();
        response.sendRedirect("index.jsf");
    }

    public void logout() throws IOException {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpSession session = (HttpSession) externalContext.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();
        response.sendRedirect("index.jsf");
    }
}
