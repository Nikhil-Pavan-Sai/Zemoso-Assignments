package com.nikhil.Attendance.repository;

import com.nikhil.Attendance.model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AttendanceRepo extends JpaRepository<Attendance, Integer> {
}