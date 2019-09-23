package com.nikhil.studentCourse.JUnitTesting;

import com.nikhil.studentCourse.model.Course;
import com.nikhil.studentCourse.model.Student;
import com.nikhil.studentCourse.model.User;
import com.nikhil.studentCourse.services.serviceInterfaces.CourseService;
import com.nikhil.studentCourse.services.serviceInterfaces.StudentService;
import com.nikhil.studentCourse.services.serviceInterfaces.UserService;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolationException;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource("classpath:test.properties")
public class CourseServiceTest {

    private static Student[] students;
    private static Course[] courses;
    private static User[] users;
    private static AtomicInteger currentIndex = new AtomicInteger(0);

    @Autowired
    private CourseService courseService=null;

    @Autowired
    private StudentService studentService=null;

    @Autowired
    private UserService userService=null;

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
    }

    @Before
    public void init() {
        Student student = new Student();
        student.setName(getRandomString(getRandomInt(8)));
        student.setBranch(getRandomString(getRandomInt(3)));
        student.setStandard(getRandomString(getRandomInt(5)));

        Course course = new Course();
        course.setCourseName(getRandomString(getRandomInt(7)));

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
        currentIndex.set((index+1)%100);
    }

    public Student getCurrentStudent() {
        return students[currentIndex.get()-1];
    }

    public Course getCurrentCourse() {
        return courses[currentIndex.get()-1];
    }

    public User getCurrentUser() { return users[currentIndex.get()-1]; }

    @Test
    public void testDuplicates() {
        //Add original Object
        studentService.addStudent(getCurrentStudent());
        courseService.addCourse(getCurrentCourse());
        userService.addUser(getCurrentUser());

        //Get a copy of the original objects
        Student dupStudent = getCurrentStudent().copy();
        Course dupCourse = getCurrentCourse().copy();
        User dupUser = getCurrentUser().copy();

        //Make sure copies aren't same
        assertNotEquals(getCurrentStudent(),dupStudent);
        assertNotEquals(getCurrentCourse(),dupCourse);
        assertNotEquals(getCurrentUser(),dupUser);

        //Try to add Duplicate Object
        assertThrows(DataIntegrityViolationException.class,()->studentService.addStudent(getCurrentStudent()));
        assertThrows(DataIntegrityViolationException.class,()->courseService.addCourse(getCurrentCourse()));
        assertThrows(DataIntegrityViolationException.class,()->userService.addUser(getCurrentUser()));
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

        User currentUser = new User();
        //currentUser.setId(null);
        currentUser.setFirstName(null);
        currentUser.setLastName(null);
        currentUser.setEmail(null);
        currentUser.setPassword(null);

        //Try to add the invalid objects
        Exception e1 = assertThrows(ConstraintViolationException.class,()->studentService.addStudent(currentStudent));
        Exception e2 = assertThrows(ConstraintViolationException.class,()->courseService.addCourse(currentCourse));
        Exception e3 = assertThrows(ConstraintViolationException.class,()->userService.addUser(currentUser));
        System.out.println(e1.getMessage() + e2.getMessage() + e3.getMessage());
    }

    @Test
    public void testDelete() {
        //Add test objects
        studentService.addStudent(getCurrentStudent());
        courseService.addCourse(getCurrentCourse());
        userService.addUser(getCurrentUser());

        //Track the previous size
        int prevSizeOfStudent = studentService.findAll().size();
        int prevSizeOfTask = courseService.findAll().size();
        int prevSizeOfUser = userService.findAll().size();

        //Remove the current task
        studentService.removeStudent(getCurrentStudent());
        courseService.removeCourse(getCurrentCourse());
        userService.removeUser(getCurrentUser());

        //Check if removal is persistent
        assertEquals(prevSizeOfStudent-1,studentService.findAll().size());
        assertEquals(prevSizeOfTask-1,courseService.findAll().size());
        assertEquals(prevSizeOfUser-1,userService.findAll().size());
    }
}
