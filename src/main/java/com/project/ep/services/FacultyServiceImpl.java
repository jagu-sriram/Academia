package com.project.ep.services;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

import com.project.ep.models.Faculty;
import com.project.ep.models.Course;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class FacultyServiceImpl implements FacultyService {

    @Override
    public Faculty checkfacultylogin(String email, String password) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
        EntityManager em = emf.createEntityManager();
        
        Query qry = em.createQuery("select f from Faculty f where f.email=? and f.password=?");
        qry.setParameter(1, email);
        qry.setParameter(2, password);
        
        Faculty faculty = null;
        
        if (qry.getResultList().size() > 0) {
            faculty = (Faculty) qry.getSingleResult();
        }
        
        em.close();
        emf.close();
        
        return faculty;
    }

    @Override
    public List<Course> getMappedCourses(int facultyId) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
        EntityManager em = emf.createEntityManager();
        
        Query qry = em.createQuery("select fc.course from FacultyCourse fc where fc.faculty.id = :facultyId");
        qry.setParameter("facultyId", facultyId);
        
        List<Course> courses = qry.getResultList();
        
        em.close();
        emf.close();
        
        return courses;
    }
}
