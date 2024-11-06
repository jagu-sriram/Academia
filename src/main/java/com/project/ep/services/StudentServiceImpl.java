package com.project.ep.services;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.project.ep.models.Student;
import com.project.ep.models.Course;

import java.util.List;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class StudentServiceImpl implements StudentService {

    @Override
    public Student checkstudentlogin(String email, String password) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
        EntityManager em = emf.createEntityManager();

        Query qry = em.createQuery("select s from Student s where s.email=? and s.password=?");
        qry.setParameter(1, email);
        qry.setParameter(2, password);

        Student student = null;

        if (qry.getResultList().size() > 0) {
            student = (Student) qry.getSingleResult();
        }
        em.close();
        emf.close();

        return student;
    }

    @Override
    public List<Course> getAllCourses() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
        EntityManager em = emf.createEntityManager();

        Query qry = em.createQuery("select c from Course c");
        List<Course> courses = qry.getResultList();

        em.close();
        emf.close();

        return courses;
    }

    @Override
    public String registerForCourse(int studentId, int courseId) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        Query qry = em.createQuery("INSERT INTO Student_Course (student_id, course_id) VALUES (?, ?)");
        qry.setParameter(1, studentId);
        qry.setParameter(2, courseId);
        qry.executeUpdate();
        em.getTransaction().commit();

        em.close();
        emf.close();

        return "Registered for Course Successfully";
    }

    @Override
    public List<Course> getRegisteredCourses(int studentId) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
        EntityManager em = emf.createEntityManager();

        Query qry = em.createQuery("SELECT c FROM Course c JOIN Student_Course sc ON c.id = sc.course_id WHERE sc.student_id = ?");
        qry.setParameter(1, studentId);
        List<Course> courses = qry.getResultList();

        em.close();
        emf.close();

        return courses;
    }
}
