package com.project.ep.managedbeans;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.ep.models.Course;
import com.project.ep.models.Student;
import com.project.ep.services.StudentService;
import com.project.ep.services.AdminService;

@ManagedBean(name="studentbean", eager=true)
public class StudentBean {
    @EJB(lookup="java:global/CollegeERP/StudentServiceImpl!com.project.ep.services.StudentService")
    StudentService studentService;
    
    @EJB(lookup="java:global/CollegeERP/AdminServiceImpl!com.project.ep.services.AdminService")
    AdminService adminService;

    private int id;
    private String name;
    private String gender;
    private String age;
    private String department;
    private String email;
    private String password;
    private List<Course> availableCourses;
    private List<Course> registeredCourses;
    private int selectedCourseId;

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
    public List<Course> getAvailableCourses() {
        return availableCourses;
    }
    public void setAvailableCourses(List<Course> availableCourses) {
        this.availableCourses = availableCourses;
    }
    public List<Course> getRegisteredCourses() {
        return registeredCourses;
    }
    public void setRegisteredCourses(List<Course> registeredCourses) {
        this.registeredCourses = registeredCourses;
    }
    public int getSelectedCourseId() {
        return selectedCourseId;
    }
    public void setSelectedCourseId(int selectedCourseId) {
        this.selectedCourseId = selectedCourseId;
    }

    // Methods
    public void validatestudentlogin() throws IOException {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
      
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();
      
        Student student = studentService.checkstudentlogin(email, password);
       
        if (student != null) {
            HttpSession session = request.getSession();
            session.setAttribute("student", student);
            availableCourses = adminService.getAllCourses();
            registeredCourses = studentService.getRegisteredCourses(student.getId());
            response.sendRedirect("studenthome.jsf");
        } else {
            response.sendRedirect("studentloginfail.jsf");
        }
    }

    public void registerForCourse() throws IOException {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        HttpSession session = (HttpSession) externalContext.getSession(false);
        Student student = (Student) session.getAttribute("student");

        if (student != null) {
            if (adminService.isCourseRegistrationAllowed()) {
                studentService.registerForCourse(student.getId(), selectedCourseId);
                registeredCourses = studentService.getRegisteredCourses(student.getId());
            } else {
                externalContext.redirect("registrationNotAllowed.jsf");
            }
        } else {
            externalContext.redirect("index.jsf");
        }
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
