package com.nikhil.studentCourse.daos.daoImpl;

import com.nikhil.studentCourse.daos.daoInterfaces.CourseDAO;
import com.nikhil.studentCourse.model.Course;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class CourseDAOImpl implements CourseDAO {

    @Autowired
    private EntityManager entityManager = null;

    @Override
    public List<Course> list() {
        Session currentSession = (Session) entityManager.getDelegate();
        return currentSession.createQuery("from Course",Course.class).getResultList();
    }

    @Override
    public Optional<Course> find(Long key) {
        return Optional.ofNullable(entityManager.find(Course.class,key));
    }

    @Override
    public boolean remove(Long key) {
        Session currSession = (Session) entityManager.getDelegate();
        Optional<Course> currCourse = find(key);
        if (currCourse.isPresent())
        {
            currSession.remove(currCourse.get());
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(Course entry) {
        return remove(entry.getId());
    }

    @Override
    public Course save(Course entry) {
            Session currentSession = (Session)entityManager.getDelegate();
            if (currentSession.contains(entry)){ currentSession.merge(entry); }
            else{ currentSession.save(entry); }
            return entry;
    }
}