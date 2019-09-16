package com.nikhil.StudentCourse.Services;

import com.nikhil.StudentCourse.Model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService
{

    Student addStudent(Student student);
    boolean removeStudent(Student student);
    boolean removeStudent(long id);
    Optional<Student> findStudent(Long id);
    Student updateStudent(Student student);
    List<Student> findAll();

}
