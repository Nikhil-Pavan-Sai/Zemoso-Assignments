package com.nikhil.studentCourse.daos;

import com.nikhil.studentCourse.daos.daoImpl.StudentDAOImpl;
import com.nikhil.studentCourse.helper.Helper;
import com.nikhil.studentCourse.model.Student;
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
public class StudentDAO {

    @Mock
    private static EntityManager entityManager;


    @Mock
    private Session session;


    @Mock
    private StudentDAOImpl studentDao;


    @Mock
    private Query<Student> resultsList;


    @Mock
    private Student student;

    private List<Student> testStudents;

    private Map<Long,Student> localStorage = new HashMap<>();

    @Before
    public void setup() {
        if (testStudents == null) {
            MockitoAnnotations.initMocks(this);
            Mockito.when(entityManager.getDelegate()).thenReturn(session);

            testStudents = new ArrayList<>();
            studentDao = new StudentDAOImpl();
            studentDao.setEntityManager(entityManager);

            Mockito.when(session.save(any())).thenAnswer(invocation -> {
                Student student = invocation.getArgument(0);
                localStorage.put(student.getId(),student);
                return student;
            });

            Mockito.when(session.get(eq(Student.class),anyLong())).thenAnswer(
                    invocation -> {
                        long id = invocation.getArgument(1);
                        return localStorage.get(id);
                    }
            );

            for (int i = 0; i < 10; i++) {
                student = new Student();
                Helper.populate(student, Student.class);
                testStudents.add(student);
                studentDao.save(student);
            }

            Mockito.when(session.createQuery("from Student", Student.class)).thenReturn(resultsList);
            Mockito.when(session.createQuery(anyString())).thenAnswer(invocation -> {
                String query = invocation.getArgument(0);
                long id = Long.parseLong(query.substring(query.indexOf("=")+1));
                localStorage.remove(id);
                return resultsList;
            });
            Mockito.when(resultsList.getResultList()).thenReturn(testStudents);
        }

    }


    @Test
    public void testList() {
        Assert.assertEquals(studentDao.list(), testStudents);
    }

    @Test
    public void testFind() {

        for (int i = 0; i < 10; i++) {
            Assert.assertEquals(studentDao.find(testStudents.get(i).getId()).get(), testStudents.get(i));
        }
    }

    @Test
    public void testSave() {
        int randomIndex = (new Random()).nextInt(testStudents.size());
        Assert.assertEquals(studentDao.save(testStudents.get(randomIndex)), testStudents.get(randomIndex));
    }


    @Test
    public void testRemove() {
        int randomIndex = (new Random()).nextInt(testStudents.size());
        //Remove valid course
        Assert.assertTrue(studentDao.remove(testStudents.get(randomIndex).getId()));
        testStudents.remove(randomIndex);
        Assert.assertEquals(testStudents.size(),studentDao.list().size());
        //Remove false course
        Assert.assertFalse(studentDao.remove(new Student()));
        Assert.assertFalse(studentDao.remove((long)10));
    }
}