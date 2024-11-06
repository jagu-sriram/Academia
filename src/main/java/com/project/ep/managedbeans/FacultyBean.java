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
import com.project.ep.models.Faculty;
import com.project.ep.services.FacultyService;

@ManagedBean(name="facultybean", eager=true)
public class FacultyBean {
    @EJB(lookup="java:global/CollegeERP/FacultyServiceImpl!com.project.ep.services.FacultyService")
    FacultyService facultyService;

    private int id;
    private String name;
    private String gender;
    private String role;
    private String email;
    private String password;
    private String qualification;
    private List<Course> mappedCourses; // To hold courses mapped to the faculty

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
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
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
    public String getQualification() {
        return qualification;
    }
    public void setQualification(String qualification) {
        this.qualification = qualification;
    }
    public List<Course> getMappedCourses() {
        return mappedCourses;
    }
    public void setMappedCourses(List<Course> mappedCourses) {
        this.mappedCourses = mappedCourses;
    }

    // Methods
    public void validatefacultylogin() throws IOException {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();
        
        Faculty faculty = facultyService.checkfacultylogin(email, password);
        
        if (faculty != null) {
            HttpSession session = request.getSession();
            session.setAttribute("faculty", faculty);
            response.sendRedirect("facultyhome.jsf");
        } else {
            response.sendRedirect("facultyloginfail.jsf");
        }
    }

    public void loadMappedCourses() {
        if (isFacultyLoggedIn()) {
            Faculty faculty = (Faculty) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("faculty");
            this.mappedCourses = facultyService.getMappedCourses(faculty.getId());
        }
    }

    private boolean isFacultyLoggedIn() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpSession session = (HttpSession) externalContext.getSession(false);
        return session != null && session.getAttribute("faculty") != null;
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
