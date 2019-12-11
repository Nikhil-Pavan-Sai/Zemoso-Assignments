package com.nikhil.studentCourse.services;

import com.nikhil.studentCourse.daos.daoInterfaces.UserDAO;
import com.nikhil.studentCourse.helper.GenericDaoMocker;
import com.nikhil.studentCourse.helper.Helper;
import com.nikhil.studentCourse.model.User;
import com.nikhil.studentCourse.services.serviceImpl.UserServiceImpl;
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
public class UserService {

    private static UserServiceImpl userService;

    @Mock
    static UserDAO userDao;

    private static List<User> testUserList;


    private static boolean initialized = false;


    @Before
    public void setup()throws Exception
    {
        if (!initialized){
            MockitoAnnotations.initMocks(this);
            new GenericDaoMocker<User>(userDao);
            userService = new UserServiceImpl();
            testUserList = new ArrayList<>();
            userService.setUserDao(userDao);
            populateTestObjects();
            initialized=true;
        }
    }

    private void populateTestObjects()
    {
        for(int i=0;i<10;i++)
        {
            User randomIntern = new User();
            Helper.populate(randomIntern,User.class);
            testUserList.add(randomIntern);
        }
    }


    @Test
    public void updateUserTest()
    {
        User randomTask = new User();
        Helper.populate(randomTask,User.class);
        userService.addUser(randomTask);
        randomTask.setFirstName("Hello");
        randomTask.setLastName("World");
        randomTask.setEmail("abc@gmail.com");
        randomTask.setPassword("Password");
        userService.updateUser(randomTask);
        Assert.assertEquals("Hello",userService.findUser(randomTask.getId()).get().getFirstName());
        Assert.assertEquals("World",userService.findUser(randomTask.getId()).get().getLastName());
        Assert.assertEquals("abc@gmail.com",userService.findUser(randomTask.getId()).get().getEmail());
        Assert.assertEquals("Password",userService.findUser(randomTask.getId()).get().getPassword());
    }

    @Test
    public void addUserTest()
    {
        for(int i=0;i<5;i++)
            userService.addUser(testUserList.get(i));
        Assert.assertEquals(5,userService.findAll().size());
    }

    @Test
    public void removeUserTest()
    {
        //Valid Removal
        int prevSize = userService.findAll().size();
        for(int i=0;i<5;i++)
            Assert.assertTrue(userService.removeUser(testUserList.get(i).getId()));
        Assert.assertEquals(prevSize-5,userService.findAll().size());
        //Invalid Removal
        for(int i=0;i<5;i++)
            Assert.assertFalse(userService.removeUser(testUserList.get(i)));
    }

    @Test
    public void findUser()
    {
        User randomIntern  = testUserList.get(9);
        Assert.assertEquals(randomIntern,userService.addUser(randomIntern));
        Assert.assertTrue(userService.findUser(randomIntern.getId()).isPresent());
        userService.removeUser(randomIntern.getId());
        Assert.assertFalse(userService.findUser(randomIntern.getId()).isPresent());
    }

}