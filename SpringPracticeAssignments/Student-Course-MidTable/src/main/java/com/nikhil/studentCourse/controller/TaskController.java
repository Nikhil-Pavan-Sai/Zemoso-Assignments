package com.nikhil.studentCourse.controller;

import com.nikhil.studentCourse.model.Task;
import com.nikhil.studentCourse.services.serviceInterfaces.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public class TaskController
{

    @Autowired
    private TaskService taskService = null;

    @GetMapping("/")
    public List<Task> findAll()
    {
        return taskService.findAll();
    }

}
