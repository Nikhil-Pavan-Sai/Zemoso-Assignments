package com.nikhil.StudentCourse.Controller;

import com.nikhil.StudentCourse.Exception.ResourceNotFoundException;
import com.nikhil.StudentCourse.Model.Course;
import com.nikhil.StudentCourse.Repository.CourseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class CourseController
{
    @Autowired
    private CourseRepo courseRepo;

    @PostMapping("/courses")
    public Course createCourse(@Valid @RequestBody Course course)
    {
        return courseRepo.save(course);
    }

    @GetMapping("/courses")
    public List<Course> getCourse()
    {
        return courseRepo.findAll();
    }

    @GetMapping("/courses/{courseId}")
    public Course getCourseById(@PathVariable Long courseId)
    {
        return courseRepo.findById(courseId).orElseThrow(() -> new ResourceNotFoundException("Student: " + courseId + " not found !"));
    }

    @PutMapping("/courses/{courseId}")
    public Course updateCourse(@PathVariable Long courseId, @Valid @RequestBody Course course)
    {
        return courseRepo.findById(courseId).map(course1 -> {
            course1.setCourseName(course.getCourseName());
            return courseRepo.save(course1);
        }).orElseThrow(() -> new ResourceNotFoundException("Student: " + courseId + " not found !"));
    }

    @DeleteMapping("/courses/{courseId}")
    public void deleteCourse(@PathVariable Long courseId)
    {
        courseRepo.deleteById(courseId);
    }
}