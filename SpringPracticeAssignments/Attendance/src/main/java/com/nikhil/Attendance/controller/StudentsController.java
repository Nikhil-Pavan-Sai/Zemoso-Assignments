package com.nikhil.Attendance.controller;


import com.nikhil.Attendance.model.Attendance;
import com.nikhil.Attendance.model.Students;
import com.nikhil.Attendance.repository.AttendanceRepo;
import com.nikhil.Attendance.repository.StudentsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class StudentsController
{

    @Autowired
    private StudentsRepo repo;

    @Autowired
    private AttendanceRepo attendanceRepo;

    @PostMapping("/students/post")
    public Attendance addStudentsToList(@Valid @RequestBody Attendance attendance)
    {

        return attendanceRepo.save(attendance);

    }

    @GetMapping("/students")
    public List<Students> getAttendanceList()
    {

        return repo.findAll();

    }


    @GetMapping("/student/{id}")
    public Attendance getAttend(@PathVariable(name = "id") int id)
    {

        return attendanceRepo.findById(id).orElse(null);
    }
}