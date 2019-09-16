package com.nikhil.StudentCourse.DAOs;

import com.nikhil.StudentCourse.Model.Student;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class StudentDAOImpl implements StudentDAO{

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Student> list() {
        Session currentSession = (Session) entityManager.getDelegate();
        return currentSession.createQuery("from Student",Student.class).getResultList();
    }

    @Override
    public Optional<Student> find(Long key) {
        Session currentSession = (Session) entityManager.getDelegate();
        return Optional.ofNullable(currentSession.get(Student.class,key));
    }

    @Override
    public boolean remove(Long key) {
        try{
            Session session = (Session) entityManager.getDelegate();
            session.remove(key);
            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean remove(Student entry) {
        try{
            Session session = (Session) entityManager.getDelegate();
            session.remove(entry);
            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Student save(Student entry) {
        try{
            Session session=(Session) entityManager.getDelegate();
            session.save(entry);
            return entry;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

}
