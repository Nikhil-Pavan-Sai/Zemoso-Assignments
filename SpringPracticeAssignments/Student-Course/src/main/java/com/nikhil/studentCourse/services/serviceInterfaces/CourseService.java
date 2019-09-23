package com.nikhil.studentCourse.services.serviceInterfaces;

import com.nikhil.studentCourse.model.Course;

import java.util.List;
import java.util.Optional;

public interface CourseService
{

    Course addCourse(Course course);
    boolean removeCourse(Course course);
    boolean removeCourse(Long id);
    Optional<Course> findCourse(Long id);
    Course updateCourse(Course course);
    List<Course> findAll();

}
