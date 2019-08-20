package com.nikhil.StudentCourse.Repository;

import com.nikhil.StudentCourse.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepo extends JpaRepository<Student, Long> {
}