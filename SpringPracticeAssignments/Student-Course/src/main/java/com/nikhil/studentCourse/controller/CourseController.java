package com.nikhil.studentCourse.controller;

import com.nikhil.studentCourse.exception.ResourceNotFoundException;
import com.nikhil.studentCourse.model.Course;
import com.nikhil.studentCourse.services.serviceInterfaces.CourseService;
import com.nikhil.studentCourse.services.serviceInterfaces.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class CourseController
{
    @Autowired
    private CourseService courseService = null;

    @Autowired
    private StudentService studentService = null;

    @PostMapping("/courses")
    public Course createCourse(@Valid @RequestBody Course course)
    {
        return courseService.addCourse(course);
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