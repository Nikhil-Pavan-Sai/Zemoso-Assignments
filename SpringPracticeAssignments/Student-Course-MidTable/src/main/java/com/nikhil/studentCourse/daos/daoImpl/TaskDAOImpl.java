package com.nikhil.studentCourse.daos.daoImpl;

import com.nikhil.studentCourse.daos.daoInterfaces.TaskDAO;
import com.nikhil.studentCourse.model.Task;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class TaskDAOImpl implements TaskDAO {
    @Autowired
    private EntityManager entityManager = null;

    @Override
    public List<Task> list() {
        Session currentSession = (Session) entityManager.getDelegate();
        return currentSession.createQuery("from Task",Task.class).getResultList();
    }

    @Override
    public Optional<Task> find(Long key) {
        return Optional.ofNullable(entityManager.find(Task.class,key));
    }

    @Override
    public boolean remove(Long key) {
        Session currSession = (Session) entityManager.getDelegate();
        Optional<Task> currTask = find(key);
        if (currTask.isPresent())
        {
            currSession.createQuery("delete from Task where id=" + key).executeUpdate();
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(Task entry) {
        return remove(entry.getId());
    }

    @Override
    public Task save(Task entry) {
        Session currentSession = (Session)entityManager.getDelegate();
        if (currentSession.contains(entry)){ currentSession.merge(entry); }
        else{ currentSession.save(entry); }
        return entry;
    }

}
