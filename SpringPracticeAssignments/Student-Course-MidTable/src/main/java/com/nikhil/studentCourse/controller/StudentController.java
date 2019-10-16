package com.nikhil.studentCourse.controller;

import com.nikhil.studentCourse.exception.ResourceNotFoundException;
import com.nikhil.studentCourse.model.Student;
import com.nikhil.studentCourse.model.Task;
import com.nikhil.studentCourse.services.serviceInterfaces.CourseService;
import com.nikhil.studentCourse.services.serviceInterfaces.StudentService;
import com.nikhil.studentCourse.services.serviceInterfaces.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController
{

    private Logger logger = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    private StudentService studentService=null;

    @Autowired
    private CourseService courseService = null;

    @Autowired
    private TaskService taskService = null;

    @PostMapping("/students")
    public Student createStudent(@Valid @RequestBody Student student)
    {
        return studentService.addStudent(student);
    }

    @PostMapping("/students/{studentId}/tasks")
    public Student addTask(@PathVariable (value = "studentId")Long studentId,
                                     @RequestParam List<String> taskIds)
    {
        Student student = studentService.findStudent(studentId).orElseThrow(() -> new ResourceNotFoundException("Invalid id"));
        try {
            for (String id : taskIds) {
                Task task = new Task();
                task.setStudent(student);
                task.setCourse(courseService.findCourse(Long.parseLong(id)).orElseThrow(() -> new ResourceNotFoundException("Invalid Task")));
                taskService.addTask(task);
            }
            return studentService.updateStudent(student);
        }
        catch (Exception e)
        {
            logger.debug(e.getMessage());
        }
        return student;
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

    @GetMapping("/students/{studentId}/tasks")
    public List<Task> getStudentTask(@PathVariable (value="studentId") Long studentId)
    {
        Student student= studentService.findStudent(studentId).orElseThrow(ResourceNotFoundException::new);
        return new ArrayList<>(student.getTasks());
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

    @DeleteMapping("/students/{studentId}/tasks/{taskId}")
    public void deleteTaskFromStudent(@PathVariable Long studentId, @PathVariable Long taskId)
    {
        studentService.removeTaskFromStudent(studentId,taskId);
    }
}