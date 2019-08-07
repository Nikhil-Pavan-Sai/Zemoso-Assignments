package com.nikhil.Attendance.repository;

import com.nikhil.Attendance.model.Students;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface StudentsRepo extends JpaRepository<Students, Integer> {
}