package com.nikhil.StudentCourse.Controller;

import com.nikhil.StudentCourse.Exception.ResourceNotFoundException;
import com.nikhil.StudentCourse.Model.Course;
import com.nikhil.StudentCourse.Model.Student;
import com.nikhil.StudentCourse.Services.CourseService;
import com.nikhil.StudentCourse.Services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
public class CourseController
{
    @Autowired
    private CourseService courseService;

    @Autowired
    private StudentService studentService;

    @PostMapping("/courses")
    public Course createCourse(@Valid @RequestBody Course course)
    {
        return courseService.addCourse(course);
    }

    @PostMapping("/students/{studentId}/courses")
    public Student addCourseToStudent(@PathVariable (value = "studentId") Long studentId, @Valid @RequestBody Course course)
    {
        System.out.println(studentId + " " + course.getCourseName());
        Student student= studentService.findStudent(studentId).orElseThrow(() -> new ResourceNotFoundException("Courses not found !"));
        Set<Course> set = student.getCourses();
        set.add(course);
        student.setCourses(set);
        studentService.addStudent(student);
        return student;
    }

    @GetMapping("/courses")
    public List<Course> getCourse()
    {
        return courseService.findAll();
    }

    @GetMapping("/courses/{courseId}")
    public Course getCourseById(@PathVariable Long courseId)
    {
        return courseService.findCourse(courseId).orElseThrow(() -> new ResourceNotFoundException("Student: " + courseId + " not found !"));
    }

    @GetMapping("/students/{studentId}/courses")
    public List<Course> getStudentCourse(@PathVariable (value="studentId") Long studentId)
    {
        Student student= studentService.findStudent(studentId).orElseThrow(ResourceNotFoundException::new);
        return new ArrayList<>(student.getCourses());
    }

    @PutMapping("/courses/{courseId}")
    public Course updateCourse(@PathVariable Long courseId, @Valid @RequestBody Course course)
    {
        return courseService.findCourse(courseId).map(course1 -> {
            course1.setCourseName(course.getCourseName());
            return courseService.addCourse(course1);
        }).orElseThrow(() -> new ResourceNotFoundException("Student: " + courseId + " not found !"));
    }

    @DeleteMapping("/courses/{courseId}")
    public void deleteCourse(@PathVariable Long courseId)
    {
        courseService.removeCourse(courseId);
    }
}