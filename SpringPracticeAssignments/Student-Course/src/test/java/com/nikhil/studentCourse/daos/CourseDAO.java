package com.nikhil.studentCourse.daos;

import com.nikhil.studentCourse.daos.daoImpl.CourseDAOImpl;
import com.nikhil.studentCourse.helper.Helper;
import com.nikhil.studentCourse.model.Course;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import javax.persistence.EntityManager;
import java.util.*;

import static org.mockito.ArgumentMatchers.*;

@RunWith(MockitoJUnitRunner.class)
public class CourseDAO {

    @Mock
    private static EntityManager entityManager;


    @Mock
    private Session session;


    @Mock
    private CourseDAOImpl courseDAO;


    @Mock
    private Query<Course> resultsList;


    @Mock
    private Course course;

    private List<Course> testCourses;

    private Map<Long,Course> localStorage = new HashMap<>();

    @Before
    public void setup() {
        if (testCourses == null) {
            MockitoAnnotations.initMocks(this);
            Mockito.when(entityManager.getDelegate()).thenReturn(session);

            testCourses = new ArrayList<>();
            courseDAO = new CourseDAOImpl();
            courseDAO.setEntityManager(entityManager);

            Mockito.when(session.save(any())).thenAnswer(invocation -> {
                Course course = invocation.getArgument(0);
                localStorage.put(course.getId(),course);
                return course;
            });

            for (int i = 0; i < 10; i++) {
                course = new Course();
                Helper.populate(course, Course.class);
                testCourses.add(course);
                courseDAO.save(course);
            }

            Mockito.when(session.createQuery("from Course", Course.class)).thenReturn(resultsList);
            Mockito.when(session.createQuery(anyString())).thenAnswer(invocation -> {
                String query = invocation.getArgument(0);
                long id = Long.parseLong(query.substring(query.indexOf("=")+1));
                localStorage.remove(id);
                return resultsList;
            });
            Mockito.when(resultsList.getResultList()).thenReturn(testCourses);
        }

        Mockito.when(entityManager.find(eq(Course.class),any())).thenAnswer(invocation -> localStorage.get(invocation.getArgument(1)));
    }


    @Test
    public void testList() {
        Assert.assertEquals(courseDAO.list(), testCourses);
    }

    @Test
    public void testFind() {

        for (int i = 0; i < 10; i++) {
            Assert.assertEquals(courseDAO.find(testCourses.get(i).getId()).get(), testCourses.get(i));
        }
    }

    @Test
    public void testSave() {
        int randomIndex = (new Random()).nextInt(testCourses.size());
        Assert.assertEquals(courseDAO.save(testCourses.get(randomIndex)), testCourses.get(randomIndex));
    }


    @Test
    public void testRemove() {
        int randomIndex = (new Random()).nextInt(testCourses.size());
        //Remove valid course
        Assert.assertTrue(courseDAO.remove(testCourses.get(randomIndex).getId()));
        testCourses.remove(randomIndex);
        Assert.assertEquals(testCourses.size(),courseDAO.list().size());
        //Remove false course
        Assert.assertFalse(courseDAO.remove(new Course()));
        Assert.assertFalse(courseDAO.remove((long)10));
    }
}