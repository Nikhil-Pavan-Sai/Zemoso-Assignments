package com.nikhil.studentCourse.services;

import com.nikhil.studentCourse.daos.daoInterfaces.StudentDAO;
import com.nikhil.studentCourse.helper.GenericDaoMocker;
import com.nikhil.studentCourse.helper.Helper;
import com.nikhil.studentCourse.model.Course;
import com.nikhil.studentCourse.model.Student;
import com.nikhil.studentCourse.services.serviceImpl.StudentServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.mockito.ArgumentMatchers.anyLong;


@RunWith(MockitoJUnitRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StudentService {

    private static StudentServiceImpl studentService;

    @Mock
    static StudentDAO studentDao;

    private static List<Student> testInternList;


    private static boolean initialized = false;


    @Before
    public void setup()throws Exception
    {
        if (!initialized){
            MockitoAnnotations.initMocks(this);
            new GenericDaoMocker<Student>(studentDao);
            specificSetup();
            studentService = new StudentServiceImpl();
            testInternList = new ArrayList<>();
            studentService.setStudentDao(studentDao);
            populateTestObjects();
            initialized=true;
        }
    }

    public void specificSetup()throws Exception
    {
        Mockito.when(studentDao.listCourses(anyLong())).thenAnswer(invocation -> {
            Student intern = studentService.findStudent(invocation.getArgument(0)).get();
            return intern.getCourses();
        });
    }

    private void populateTestObjects()
    {
        for(int i=0;i<10;i++)
        {
            Student randomIntern = new Student();
            Helper.populate(randomIntern,Student.class);
            testInternList.add(randomIntern);
        }
    }




    @Test
    public void addStudentTest()
    {
        for(int i=0;i<5;i++)
            studentService.addStudent(testInternList.get(i));
        Assert.assertEquals(5,studentService.findAll().size());
    }

    @Test
    public void removeStudentTest()
    {
        //Valid Removal
        int prevSize = studentService.findAll().size();
        for(int i=0;i<5;i++)
            Assert.assertTrue(studentService.removeStudent(testInternList.get(i).getId()));
        Assert.assertEquals(prevSize-5,studentService.findAll().size());
        //Invalid Removal
        for(int i=0;i<5;i++)
            Assert.assertFalse(studentService.removeStudent(testInternList.get(i)));
    }

    @Test
    public void findStudent()
    {
        Student randomIntern  = testInternList.get(9);
        Assert.assertEquals(randomIntern,studentService.addStudent(randomIntern));
        Assert.assertTrue(studentService.findStudent(randomIntern.getId()).isPresent());
        studentService.removeStudent(randomIntern.getId());
        Assert.assertFalse(studentService.findStudent(randomIntern.getId()).isPresent());
    }

    @Test
    public void listCourses()
    {
        Set<Course> assignments = new HashSet<>();
        for(int i=0;i<10;i++)
        {
            Course randomAssignment = new Course();
            Helper.populate(randomAssignment,Course.class);
            assignments.add(randomAssignment);
        }

        Student randomIntern  = testInternList.get(5);
        randomIntern.setCourses(assignments);
        studentService.updateStudent(randomIntern);
        Assert.assertEquals(assignments,studentService.getCourses(randomIntern.getId()));

        Assert.assertEquals(0,studentService.getCourses((int)Helper.getRandVal(Long.TYPE)).size());
    }
}