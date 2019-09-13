package com.nikhil.Attendance.controller;


import com.nikhil.Attendance.model.Attendance;
import com.nikhil.Attendance.repository.AttendanceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class AttendanceController
{

    @Autowired
    private AttendanceRepo attendanceRepo;

    @PostMapping("/Attendance")
    public Attendance addAttendance(@Valid @RequestBody Attendance attendance)
    {

        return attendanceRepo.save(attendance);

    }

    @GetMapping("/Attendance")
    public List<Attendance> getAttendance()
    {

        return attendanceRepo.findAll();

    }

}