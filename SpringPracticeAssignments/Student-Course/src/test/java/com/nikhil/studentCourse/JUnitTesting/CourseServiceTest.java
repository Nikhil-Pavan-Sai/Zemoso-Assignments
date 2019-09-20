package com.nikhil.studentCourse.JUnitTesting;

import com.nikhil.studentCourse.model.Course;
import com.nikhil.studentCourse.model.Student;
import com.nikhil.studentCourse.services.serviceInterfaces.CourseService;
import com.nikhil.studentCourse.services.serviceInterfaces.StudentService;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolationException;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CourseServiceTest {

    private static Student[] students;
    private static Course[] courses;
    private AtomicInteger currentIndex = new AtomicInteger(0);

    @Autowired
    private CourseService courseService=null;

    @Autowired
    private StudentService studentService=null;

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
    }

    @Before
    public void init() {
        Student student = new Student();
        student.setName(getRandomString(getRandomInt(8)));
        student.setBranch(getRandomString(getRandomInt(3)));
        student.setStandard(getRandomString(getRandomInt(5)));

        Course course = new Course();
        course.setCourseName(getRandomString(getRandomInt(7)));


        int currentIndex = this.currentIndex.get();
        students[currentIndex] = student;
        courses[currentIndex] = course;
        this.currentIndex.set((currentIndex+1)%100);
    }

    public Student getCurrentStudent() {
        return students[currentIndex.get()-1];
    }

    public Course getCurrentCourse() {
        return courses[currentIndex.get()-1];
    }

    @Test
    public void testDuplicates() {
        //Add original Object
        studentService.addStudent(getCurrentStudent());
        courseService.addCourse(getCurrentCourse());

        //Get a copy of the original objects
        Student dupStudent = getCurrentStudent().copy();
        Course dupCourse = getCurrentCourse().copy();

        //Make sure copies aren't same
        assertNotEquals(getCurrentStudent(),dupStudent);
        assertNotEquals(getCurrentCourse(),dupCourse);

        //Try to add Duplicate Object
        assertThrows(DataIntegrityViolationException.class,()->studentService.addStudent(getCurrentStudent()));
        assertThrows(Exception.class,()->courseService.addCourse(getCurrentCourse()));
    }

    @Test
    public void testInvalidEntry() {
        //Initialize all Not Null attributes to null
        Student currentStudent = getCurrentStudent();
        currentStudent.setName(null);
        currentStudent.setBranch(null);
        currentStudent.setStandard(null);

        Course currentCourse = getCurrentCourse();
        currentCourse.setCourseName(null);

        //Try to add the invalid objects
        assertThrows(ConstraintViolationException.class,()->studentService.addStudent(currentStudent));
        Exception e1 = assertThrows(Exception.class,()->courseService.addCourse(currentCourse));
        System.out.println(e1.getMessage());
    }

    @Test
    public void testDelete() {
        //Add test objects
        studentService.addStudent(getCurrentStudent());
        courseService.addCourse(getCurrentCourse());

        //Track the previous size
        int prevSizeOfTask = courseService.findAll().size();

        //Remove the current task
        courseService.removeCourse(getCurrentCourse());

        //Check if removal is persistent
        assertEquals(prevSizeOfTask,courseService.findAll().size());
    }
}
