package com.nikhil.studentCourse.services;

import com.nikhil.studentCourse.daos.daoInterfaces.CourseDAO;
import com.nikhil.studentCourse.helper.GenericDaoMocker;
import com.nikhil.studentCourse.helper.Helper;
import com.nikhil.studentCourse.model.Course;
import com.nikhil.studentCourse.services.serviceImpl.CourseServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;


@RunWith(MockitoJUnitRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CourseService {

    private static CourseServiceImpl courseService;

    @Mock
    static CourseDAO courseDao;

    private static List<Course> testTaskList;


    private static boolean initialized = false;


    @Before
    public void setup() {
        if (!initialized){
            MockitoAnnotations.initMocks(this);
            new GenericDaoMocker<Course>(courseDao);
            courseService = new CourseServiceImpl();
            testTaskList = new ArrayList<>();
            courseService.setCourseDao(courseDao);
            populateTestObjects();
            initialized=true;
        }
    }

    private void populateTestObjects()
    {
        for(int i=0;i<10;i++)
        {
            Course randomTask = new Course();
            Helper.populate(randomTask,Course.class);
            testTaskList.add(randomTask);
        }
    }

    @Test
    public void updateCourseTest()
    {
        Course randomTask = new Course();
        Helper.populate(randomTask,Course.class);
        courseService.addCourse(randomTask);
        randomTask.setCourseName("Hello World");
        randomTask.setCourseDescription("This is sample description");
        courseService.updateCourse(randomTask);
        Assert.assertEquals("Hello World",courseService.findCourse(randomTask.getId()).get().getCourseName());
        Assert.assertEquals("This is sample description",courseService.findCourse(randomTask.getId()).get().getCourseDescription());
    }

    @Test
    public void addCourseTest()
    {
        for(int i=0;i<5;i++)
            courseService.addCourse(testTaskList.get(i));
        Assert.assertEquals(5, courseService.findAll().size());
    }

    @Test
    public void removeCourseTest()
    {
        //Valid Removal
        int prevSize = courseService.findAll().size();
        for(int i=0;i<5;i++)
            Assert.assertTrue(courseService.removeCourse(testTaskList.get(i).getId()));
        Assert.assertEquals(prevSize-5, courseService.findAll().size());
        //Invalid Removal
        for(int i=0;i<5;i++)
            Assert.assertFalse(courseService.removeCourse(testTaskList.get(i)));
    }

    @Test
    public void findCourse()
    {
        Course randomTask  = testTaskList.get(9);
        Assert.assertEquals(randomTask, courseService.addCourse(randomTask));
        Assert.assertTrue(courseService.findCourse(randomTask.getId()).isPresent());
        courseService.removeCourse(randomTask.getId());
        Assert.assertFalse(courseService.findCourse(randomTask.getId()).isPresent());
    }
}