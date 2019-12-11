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
        Session currSession = (Session) entityManager.getDelegate();
        Optional<User> currUser = find(key);
        if (currUser.isPresent())
        {
            currSession.createQuery("delete from User where id=" + key).executeUpdate();
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(User entry) {
        return remove(entry.getId());
    }

    @Override
    public User save(User entry) {
            Session currentSession = (Session)entityManager.getDelegate();
            if(currentSession.contains(entry)){ currentSession.merge(entry);}
            else{ currentSession.save(entry); }
            return entry;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
