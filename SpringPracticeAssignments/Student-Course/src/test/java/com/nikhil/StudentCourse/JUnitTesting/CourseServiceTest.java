package com.nikhil.StudentCourse.JUnitTesting;

import com.nikhil.StudentCourse.Model.Course;
import com.nikhil.StudentCourse.Services.ServiceInterfaces.CourseService;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CourseServiceTest {

    private Course course;

    @Autowired
    private CourseService courseService;

    @BeforeEach
    public void init()
    {
        
    }

    @Test
    public void createCourse() {
        course = new Course();
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "abcdefghijklmnopqrstuvxyz";

        StringBuilder sb = new StringBuilder(8);
        for (int i = 0; i < 8; i++) {
            int index
                    = (int)(AlphaNumericString.length()
                    * Math.random());
            sb.append(AlphaNumericString
                    .charAt(index));
        }
        String string = sb.toString();
        course.setCourseName(string);
        courseService.addCourse(course);
        assertEquals(course.getCourseName(),courseService.findCourse(course.getId()).get().getCourseName());
    }


}
