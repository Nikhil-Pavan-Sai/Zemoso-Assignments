package com.nikhil.studentCourse.repository;

import com.nikhil.studentCourse.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepo extends JpaRepository<Course, Long> {
}