package com.nikhil.studentCourse.repository;

import com.nikhil.studentCourse.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepo extends JpaRepository<Student, Long> {
}