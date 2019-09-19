package com.nikhil.StudentCourse.Controller;

import com.nikhil.StudentCourse.Exception.ResourceNotFoundException;
import com.nikhil.StudentCourse.Model.Student;
import com.nikhil.StudentCourse.Services.ServiceInterfaces.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class StudentController
{
    @Autowired
    private StudentService studentService;

    @PostMapping("/students")
    public Student createStudent(@Valid @RequestBody Student student)
    {
        return studentService.addStudent(student);
    }

    @GetMapping("/students")
    public List<Student> getStudent()
    {
        return studentService.findAll();
    }

    @GetMapping("/students/{studentId}")
    public Student getStudentById(@PathVariable Long studentId)
    {
        return studentService.findStudent(studentId).orElseThrow(() -> new ResourceNotFoundException("Student: " + studentId + " not found !"));
    }

    @PutMapping("/students/{studentId}")
    public Student updateStudent(@PathVariable Long studentId, @Valid @RequestBody Student student)
    {
        return studentService.findStudent(studentId).map(student1 -> {
            student1.setName(student.getName());
            student1.setStandard(student.getStandard());
            student1.setBranch(student.getBranch());
            return studentService.addStudent(student1);
        }).orElseThrow(() -> new ResourceNotFoundException("Student: " + studentId + " not found !"));
    }

    @DeleteMapping("/students/{studentId}")
    public void deleteStudent(@PathVariable Long studentId)
    {
        studentService.removeStudent(studentId);
    }
}