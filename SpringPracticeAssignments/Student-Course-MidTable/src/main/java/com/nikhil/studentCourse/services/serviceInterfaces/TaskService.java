package com.nikhil.studentCourse.services.serviceInterfaces;

import com.nikhil.studentCourse.model.Task;

import java.util.List;
import java.util.Optional;

public interface TaskService {
    Task addTask(Task task);
    boolean removeTask(Task task);
    boolean removeTask(Long id);
    Optional<Task> findTask(Long id);
    Task updateTask(Task task);
    List<Task> findAll();
}
