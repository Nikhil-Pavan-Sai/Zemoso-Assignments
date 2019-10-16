package com.nikhil.studentCourse.JUnitTesting;

import com.nikhil.studentCourse.model.Course;
import com.nikhil.studentCourse.model.Student;
import com.nikhil.studentCourse.model.Task;
import com.nikhil.studentCourse.model.User;
import com.nikhil.studentCourse.services.serviceInterfaces.CourseService;
import com.nikhil.studentCourse.services.serviceInterfaces.StudentService;
import com.nikhil.studentCourse.services.serviceInterfaces.TaskService;
import com.nikhil.studentCourse.services.serviceInterfaces.UserService;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolationException;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
public class ServiceTests {

    private static Student[] students;
    private static Course[] courses;
    private static User[] users;
    private static Task[] tasks;
    private static AtomicInteger currentIndex = new AtomicInteger(0);

    @Autowired
    private CourseService courseService=null;

    @Autowired
    private StudentService studentService=null;

    @Autowired
    private UserService userService=null;

    @Autowired
    private TaskService taskService = null;

    private String getRandomString(int size) {
        StringBuilder sb = new StringBuilder(size);
        for(int i=0;i<size;i++)
            sb.append((char)('a'+getRandomInt(8)));
        return sb.toString();
    }

    private int getRandomInt(int bound) {
        return new Random().nextInt(bound);
    }

    @BeforeClass
    public static void initAll() {
        students = new Student[100];
        courses = new Course[100];
        users = new User[100];
        tasks = new Task[100];
    }

    @Before
    public void init() {
        Student student = new Student();
        student.setName(getRandomString(getRandomInt(8)));
        student.setBranch(getRandomString(getRandomInt(3)));
        student.setStandard(getRandomString(getRandomInt(5)));

        Course course = new Course();
        course.setCourseName(getRandomString(getRandomInt(7)));

        Task task = new Task();
        task.setCourse(course);
        task.setStudent(student);

        User user = new User();
        user.setFirstName(getRandomString(getRandomInt(5)));
        user.setLastName(getRandomString(getRandomInt(5)));
        String emailPojo = getRandomString(getRandomInt(7));
        user.setEmail(emailPojo + "@gmail.com");
        user.setPassword(getRandomString(getRandomInt(10)));


        int index = currentIndex.get();
        students[index] = student;
        courses[index] = course;
        users[index] = user;
        tasks[index] = task;
        currentIndex.set((index+1)%100);
    }

    public Student getCurrentStudent() {
        return students[currentIndex.get()-1];
    }

    public Course getCurrentCourse() {
        return courses[currentIndex.get()-1];
    }

    public User getCurrentUser() { return users[currentIndex.get()-1]; }

    public Task getCurrentTask() { return tasks[currentIndex.get()-1]; }

    @Test
    public void testDuplicates() {
        //Add original Object
        studentService.addStudent(getCurrentStudent());
        courseService.addCourse(getCurrentCourse());
        userService.addUser(getCurrentUser());
        taskService.addTask(getCurrentTask());

        //Get a copy of the original objects
        Student dupStudent = getCurrentStudent().copy();
        Course dupCourse = getCurrentCourse().copy();
        Task dupTask = getCurrentTask().copy();
        User dupUser = getCurrentUser().copy();

        //Make sure copies aren't same
        assertNotEquals(getCurrentStudent(),dupStudent);
        assertNotEquals(getCurrentCourse(),dupCourse);
        assertNotEquals(getCurrentUser(),dupUser);
        assertNotEquals(getCurrentTask(), dupTask);

        //Try to add Duplicate Object
        assertThrows(DataIntegrityViolationException.class,()->studentService.addStudent(getCurrentStudent()));
        assertThrows(DataIntegrityViolationException.class,()->courseService.addCourse(getCurrentCourse()));
        assertThrows(DataIntegrityViolationException.class,()->userService.addUser(getCurrentUser()));
        assertThrows(DataIntegrityViolationException.class,()->taskService.addTask(getCurrentTask()));
    }

    @Test
    public void testInvalidEntry() {
        //Initialize all Not Null attributes to null
        Student currentStudent = getCurrentStudent();
        //currentStudent.setId(null);
        currentStudent.setName(null);
        currentStudent.setBranch(null);
        currentStudent.setStandard(null);

        Course currentCourse = getCurrentCourse();
        //currentCourse.setId(null);
        currentCourse.setCourseName(null);

        Task currentTask = getCurrentTask();
        currentTask.setStudent(null);
        currentTask.setCourse(null);

        User currentUser = new User();
        //currentUser.setId(null);
        currentUser.setFirstName(null);
        currentUser.setLastName(null);
        currentUser.setEmail(null);
        currentUser.setPassword(null);

        //Try to add the invalid objects
        assertThrows(ConstraintViolationException.class,()->studentService.addStudent(currentStudent));
        assertThrows(ConstraintViolationException.class,()->courseService.addCourse(currentCourse));
        assertThrows(ConstraintViolationException.class,()->userService.addUser(currentUser));
        assertThrows(JpaSystemException.class,()->taskService.addTask(currentTask));
    }

    @Test
    public void testDelete() {
        //Add test objects
        studentService.addStudent(getCurrentStudent());
        courseService.addCourse(getCurrentCourse());
        userService.addUser(getCurrentUser());
        taskService.addTask(getCurrentTask());

        //Track the previous size
        int prevSizeOfStudent = studentService.findAll().size();
        int prevSizeOfCourse = courseService.findAll().size();
        int prevSizeOfUser = userService.findAll().size();
        int prevSizeOfTask = taskService.findAll().size();

        //Remove the current task
        studentService.removeStudent(getCurrentStudent());
        courseService.removeCourse(getCurrentCourse());
        userService.removeUser(getCurrentUser());
        taskService.removeTask(getCurrentTask());

        //Check if removal is persistent
        assertEquals(prevSizeOfStudent-1,studentService.findAll().size());
        assertEquals(prevSizeOfCourse-1,courseService.findAll().size());
        assertEquals(prevSizeOfUser-1,userService.findAll().size());
        assertEquals(prevSizeOfTask-1,taskService.findAll().size());
    }
}
