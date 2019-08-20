package com.nikhil.StudentCourse.Repository;

import com.nikhil.StudentCourse.Model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepo extends JpaRepository<Course, Long> {
}