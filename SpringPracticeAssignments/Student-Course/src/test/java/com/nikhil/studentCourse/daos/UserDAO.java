package com.nikhil.studentCourse.daos;

import com.nikhil.studentCourse.daos.daoImpl.UserDAOImpl;
import com.nikhil.studentCourse.helper.Helper;
import com.nikhil.studentCourse.model.User;
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
public class UserDAO {

    @Mock
    private static EntityManager entityManager;


    @Mock
    private Session session;


    @Mock
    private UserDAOImpl userDao;


    @Mock
    private Query<User> resultsList;


    @Mock
    private User user;

    private List<User> testUsers;

    private Map<Long,User> localStorage = new HashMap<>();

    @Before
    public void setup() {
        if (testUsers == null) {
            MockitoAnnotations.initMocks(this);
            Mockito.when(entityManager.getDelegate()).thenReturn(session);

            testUsers = new ArrayList<>();
            userDao = new UserDAOImpl();
            userDao.setEntityManager(entityManager);

            Mockito.when(session.save(any())).thenAnswer(invocation -> {
                User user = invocation.getArgument(0);
                localStorage.put(user.getId(),user);
                return user;
            });

            Mockito.when(session.get(eq(User.class),anyLong())).thenAnswer(
                    invocation -> {
                        long id = invocation.getArgument(1);
                        return localStorage.get(id);
                    }
            );

            for (int i = 0; i < 10; i++) {
                user = new User();
                Helper.populate(user, User.class);
                testUsers.add(user);
                userDao.save(user);
            }

            Mockito.when(session.createQuery("from User", User.class)).thenReturn(resultsList);
            Mockito.when(session.createQuery(anyString())).thenAnswer(invocation -> {
                String query = invocation.getArgument(0);
                long id = Long.parseLong(query.substring(query.indexOf("=")+1));
                localStorage.remove(id);
                return resultsList;
            });
            Mockito.when(resultsList.getResultList()).thenReturn(testUsers);
        }

    }


    @Test
    public void testList() {
        Assert.assertEquals(userDao.list(), testUsers);
    }

    @Test
    public void testFind() {

        for (int i = 0; i < 10; i++) {
            Assert.assertEquals(userDao.find(testUsers.get(i).getId()).get(), testUsers.get(i));
        }
    }

    @Test
    public void testSave() {
        int randomIndex = (new Random()).nextInt(testUsers.size());
        Assert.assertEquals(userDao.save(testUsers.get(randomIndex)), testUsers.get(randomIndex));
    }


    @Test
    public void testRemove() {
        int randomIndex = (new Random()).nextInt(testUsers.size());
        //Remove valid course
        Assert.assertTrue(userDao.remove(testUsers.get(randomIndex).getId()));
        testUsers.remove(randomIndex);
        Assert.assertEquals(testUsers.size(),userDao.list().size());
        //Remove false course
        Assert.assertFalse(userDao.remove(new User()));
        Assert.assertFalse(userDao.remove((long)10));
    }
}