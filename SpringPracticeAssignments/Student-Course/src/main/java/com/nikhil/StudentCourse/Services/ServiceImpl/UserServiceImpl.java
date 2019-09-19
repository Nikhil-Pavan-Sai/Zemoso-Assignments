package com.nikhil.StudentCourse.Services.ServiceImpl;

import com.nikhil.StudentCourse.DAOs.DAOInterfaces.UserDAO;
import com.nikhil.StudentCourse.Model.User;
import com.nikhil.StudentCourse.Services.ServiceInterfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService
{

    @Autowired
    private UserDAO userDAO;

    @Override
    @Transactional
    public User addUser(User user) {
        return userDAO.save(user);
    }

    @Override
    @Transactional
    public boolean removeUser(User user) {
        return userDAO.remove(user);
    }

    @Override
    @Transactional
    public boolean removeUser(long id) {
        return userDAO.remove(id);
    }

    @Override
    @Transactional
    public Optional<User> findUser(Long id) {
        return userDAO.find(id);
    }

    @Override
    @Transactional
    public User updateUser(User user) {
        return userDAO.save(user);
    }

    @Override
    @Transactional
    public List<User> findAll() {
        return userDAO.list();
    }

    @Override
    @Transactional
    public Optional<User> findByEmail(String email) {
        return userDAO.findByEmail(email);
    }

}