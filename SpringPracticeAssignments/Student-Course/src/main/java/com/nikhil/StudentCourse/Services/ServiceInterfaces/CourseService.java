package com.nikhil.StudentCourse.Services.ServiceInterfaces;

import com.nikhil.StudentCourse.Model.Course;

import java.util.List;
import java.util.Optional;

public interface CourseService
{

    Course addCourse(Course course);
    boolean removeCourse(Course course);
    boolean removeCourse(long id);
    Optional<Course> findCourse(Long id);
    Course updateCourse(Course course);
    List<Course> findAll();

}
