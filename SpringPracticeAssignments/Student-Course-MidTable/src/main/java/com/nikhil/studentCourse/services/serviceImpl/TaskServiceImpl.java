package com.nikhil.studentCourse.services.serviceImpl;

import com.nikhil.studentCourse.daos.daoInterfaces.TaskDAO;
import com.nikhil.studentCourse.model.Task;
import com.nikhil.studentCourse.services.serviceInterfaces.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskDAO taskDAO=null;

    @Override
    @Transactional
    public Task addTask(Task course) {
        return taskDAO.save(course);
    }

    @Override
    @Transactional
    public boolean removeTask(Task course) {
        return taskDAO.remove(course);
    }

    @Override
    @Transactional
    public boolean removeTask(Long id) {
        return taskDAO.remove(id);
    }

    @Override
    @Transactional
    public Optional<Task> findTask(Long id) {
        return taskDAO.find(id);
    }

    @Override
    @Transactional
    public Task updateTask(Task course) {
        return taskDAO.save(course);
    }

    @Override
    @Transactional
    public List<Task> findAll() {
        return taskDAO.list();
    }
}
