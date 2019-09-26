package com.nikhil.studentCourse.controller;

import com.nikhil.studentCourse.exception.ResourceNotFoundException;
import com.nikhil.studentCourse.model.Course;
import com.nikhil.studentCourse.model.Student;
import com.nikhil.studentCourse.services.serviceInterfaces.CourseService;
import com.nikhil.studentCourse.services.serviceInterfaces.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
public class StudentController
{
    @Autowired
    private StudentService studentService=null;

    @Autowired
    private CourseService courseService=null;

    @PostMapping("/students")
    public Student createStudent(@Valid @RequestBody Student student)
    {
        return studentService.addStudent(student);
    }

    @PostMapping("/students/{studentId}/courses")
    public Student addCourseToStudent(@PathVariable (value = "studentId") Long studentId, @Valid @RequestBody Course course)
    {
        Student student= studentService.findStudent(studentId).orElseThrow(() -> new ResourceNotFoundException("Student not found !"));
        Set<Course> set = student.getCourses();
        set.add(course);
        student.setCourses(set);
        studentService.addStudent(student);
        return student;
    }

    @PostMapping("/students/{studentId}/courses/{courseId}")
    public Student addParticularCourse(@PathVariable (value = "studentId")Long studentId,
                                       @PathVariable (value = "courseId")Long courseId)
    {
        Student student = studentService.findStudent(studentId).orElseThrow(() -> new ResourceNotFoundException("Student not found !"));
        Course course = courseService.findCourse(courseId).orElseThrow(() -> new ResourceNotFoundException("Course not found !"));
        Set<Course> set = student.getCourses();
        set.add(course);
        student.setCourses(set);
        studentService.addStudent(student);
        return null;
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

    @GetMapping("/students/{studentId}/courses")
    public List<Course> getStudentCourse(@PathVariable (value="studentId") Long studentId)
    {
        Student student= studentService.findStudent(studentId).orElseThrow(ResourceNotFoundException::new);
        return new ArrayList<>(student.getCourses());
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

    @DeleteMapping("/students/{studentId}/courses/{courseId}")
    public void deleteCourseFromStudent(@PathVariable Long studentId, @PathVariable Long courseId)
    {
        studentService.removeCourseFromStudent(studentId,courseId);
    }
}