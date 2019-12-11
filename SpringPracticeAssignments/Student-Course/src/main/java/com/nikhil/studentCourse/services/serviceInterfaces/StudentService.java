package com.nikhil.studentCourse.services.serviceInterfaces;

import com.nikhil.studentCourse.model.Course;
import com.nikhil.studentCourse.model.Student;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface StudentService
{

    Student addStudent(Student student);
    boolean removeStudent(Student student);
    boolean removeStudent(Long id);
    boolean removeCourseFromStudent(Long stKey, Long coKey);
    Optional<Student> findStudent(Long id);
    Student updateStudent(Student student);
    List<Student> findAll();
    Set<Course> getCourses(long id);
}