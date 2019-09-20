package com.nikhil.studentCourse.services.serviceInterfaces;

import com.nikhil.studentCourse.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService
{

    User addUser(User user);
    boolean removeUser(User user);
    boolean removeUser(long id);
    Optional<User> findUser(Long id);
    User updateUser(User user);
    List<User> findAll();
    Optional<User> findByEmail(String email);

}
