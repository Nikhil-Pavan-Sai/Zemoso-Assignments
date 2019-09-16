package com.nikhil.StudentCourse.Services;

import com.nikhil.StudentCourse.Model.User;

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
