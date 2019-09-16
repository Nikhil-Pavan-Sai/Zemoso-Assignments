package com.nikhil.StudentCourse.DAOs;

import com.nikhil.StudentCourse.Model.User;

import java.util.Optional;

public interface UserDAO extends GenericDAO<User,Long>{

    Optional<User> findByEmail(String email);
}
