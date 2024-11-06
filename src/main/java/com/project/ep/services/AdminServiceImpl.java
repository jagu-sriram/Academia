package com.project.ep.services;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import com.project.ep.models.Admin;
import com.project.ep.models.Faculty;
import com.project.ep.models.FacultyCourse;
import com.project.ep.models.Student;
import com.project.ep.models.Course;

@Stateless
public class AdminServiceImpl implements AdminService {

    private boolean courseRegistrationAllowed = false; // default to not allowed

    @Override
    public Admin checkadminlogin(String username, String password) {
         EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
        EntityManager em = emf.createEntityManager();
        
        Query qry = em.createQuery("select a from Admin a where a.username=? and a.password=?");
        qry.setParameter(1, username);
        qry.setParameter(2, password);
        
        Admin admin = null;
        
        if(qry.getResultList().size() > 0) {
            admin = (Admin) qry.getSingleResult();
        }
        
        em.close();
        emf.close();
        
        return admin;
    }

    @Override
    public String registerfaculty(Faculty faculty) {
       EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
        EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();
        em.persist(faculty);
        em.getTransaction().commit();
        
        em.close();
        emf.close();
        
        return "Faculty Registered Successfully";
    }

    @Override
    public String registerstudent(Student student) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
        EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();
        em.persist(student);
        em.getTransaction().commit();
        
        em.close();
        emf.close();
        
        return "Student Registered Successfully";
    }

    @Override
    public void addCourse(Course course) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(course);
        em.getTransaction().commit();
        em.close();
        emf.close();
    }

    @Override
    public String mapFacultyToCourse(int facultyId, int courseId) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
        EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();
        
        Faculty faculty = em.find(Faculty.class, facultyId);
        Course course = em.find(Course.class, courseId);
        
        if (faculty == null || course == null) {
            em.getTransaction().rollback();
            em.close();
            emf.close();
            return "Faculty or Course not found";
        }
        
        FacultyCourse facultyCourse = new FacultyCourse();
        facultyCourse.setFaculty(faculty);
        facultyCourse.setCourse(course);
        
        em.persist(facultyCourse);
        em.getTransaction().commit();
        
        em.close();
        emf.close();
        
        return "Faculty Mapped to Course Successfully";
    }

    @Override
    public List<Course> getAllCourses() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT c FROM Course c");
        List<Course> courses = query.getResultList();
        em.close();
        emf.close();
        return courses;
    }

    @Override
    public boolean isCourseRegistrationAllowed() {
        return courseRegistrationAllowed;
    }

    @Override
    public void setCourseRegistrationAllowed(boolean allowed) {
        this.courseRegistrationAllowed = allowed;
    }
}
