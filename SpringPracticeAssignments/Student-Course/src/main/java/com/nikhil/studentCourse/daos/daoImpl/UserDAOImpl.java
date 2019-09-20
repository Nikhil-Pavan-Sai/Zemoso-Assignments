package com.nikhil.studentCourse.daos.daoImpl;

import com.nikhil.studentCourse.daos.daoInterfaces.UserDAO;
import com.nikhil.studentCourse.model.User;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDAOImpl implements UserDAO {
    @Autowired
    private EntityManager entityManager;

    @Override
    public Optional<User> findByEmail(String email)
    {
        Session session = (Session) entityManager.getDelegate();
        return Optional.ofNullable(session.createQuery("from User where email="+"'"+email+"'",User.class).getSingleResult());
    }


    @Override
    public List<User> list() {
        Session currentSession = (Session) entityManager.getDelegate();
        return currentSession.createQuery("from User",User.class).getResultList();
    }

    @Override
    public Optional<User> find(Long key) {
        Session currentSession = (Session) entityManager.getDelegate();
        return Optional.ofNullable(currentSession.get(User.class,key));
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
    public boolean remove(User entry) {
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
    public User save(User entry) {
            Session session=(Session) entityManager.getDelegate();
            session.persist(entry);
            return entry;
    }
}
