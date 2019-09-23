package com.nikhil.studentCourse.daos.daoImpl;

import com.nikhil.studentCourse.daos.daoInterfaces.StudentDAO;
import com.nikhil.studentCourse.model.Student;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class StudentDAOImpl implements StudentDAO {

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
            session.remove(find(key).get());
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
        Optional<Student> currStudent = find(entry.getId());
        if (currStudent.isPresent()) {
            entityManager.remove(currStudent.get());
            return true;
        }
        return false;
    }

    @Override
    public Student save(Student entry) {

        Session currentSession = (Session)entityManager.getDelegate();
        if (currentSession.contains(entry)){ currentSession.merge(entry); }
        else { currentSession.save(entry); }
        return entry;
    }

}
