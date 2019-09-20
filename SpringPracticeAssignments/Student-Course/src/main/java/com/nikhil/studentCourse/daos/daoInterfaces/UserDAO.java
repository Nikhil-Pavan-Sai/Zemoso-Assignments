package com.nikhil.studentCourse.daos.daoInterfaces;

import com.nikhil.studentCourse.model.User;

import java.util.Optional;

public interface UserDAO extends GenericDAO<User,Long>{

    Optional<User> findByEmail(String email);
}
