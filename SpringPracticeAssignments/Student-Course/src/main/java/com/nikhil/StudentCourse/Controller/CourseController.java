package com.nikhil.StudentCourse.Controller;

import com.nikhil.StudentCourse.Exception.ResourceNotFoundException;
import com.nikhil.StudentCourse.Model.Course;
import com.nikhil.StudentCourse.Model.Student;
import com.nikhil.StudentCourse.Repository.CourseRepo;
import com.nikhil.StudentCourse.Repository.StudentRepo;
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
    private CourseRepo courseRepo;

    @Autowired
    private StudentRepo studentRepo;

    @PostMapping("/courses")
    public Course createCourse(@Valid @RequestBody Course course)
    {
        return courseRepo.save(course);
    }

    @PostMapping("/students/{studentId}/courses")
    public Student addCourseToStudent(@PathVariable (value = "studentId") Long studentId, @Valid @RequestBody Course course)
    {
        Student student=studentRepo.findById(studentId).orElseThrow(() -> new ResourceNotFoundException("Courses not found !"));
        Set<Course> set = student.getCourses();
        set.add(course);
        student.setCourses(set);
        studentRepo.save(student);
        return student;
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

    @GetMapping("/students/{studentId}/courses")
    public List<Course> getStudentCourse(@PathVariable (value="studentId") Long studentId)
    {
        Student student=studentRepo.findById(studentId).orElseThrow(ResourceNotFoundException::new);
        return new ArrayList<>(student.getCourses());
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