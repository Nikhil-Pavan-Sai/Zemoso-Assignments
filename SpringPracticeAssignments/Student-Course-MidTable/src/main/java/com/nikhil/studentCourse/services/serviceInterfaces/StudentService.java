package com.nikhil.studentCourse.services.serviceInterfaces;

import com.nikhil.studentCourse.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService
{

    Student addStudent(Student student);
    boolean removeStudent(Student student);
    boolean removeStudent(Long id);
    boolean removeTaskFromStudent(Long stKey, Long tskKey);
    Optional<Student> findStudent(Long id);
    Student updateStudent(Student student);
    List<Student> findAll();

}
