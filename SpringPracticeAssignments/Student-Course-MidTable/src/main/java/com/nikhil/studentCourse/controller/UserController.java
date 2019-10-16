package com.nikhil.studentCourse.controller;

import com.nikhil.studentCourse.exception.ResourceNotFoundException;
import com.nikhil.studentCourse.model.User;
import com.nikhil.studentCourse.services.serviceInterfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController
{
    @Autowired
    private UserService userService = null;

    @PostMapping("/user/validate")
    public ResponseEntity<User> validate(@RequestBody Credentials credentials)
    {
        User u = userService.findByEmail(credentials.getEmail()).orElseThrow(ResourceNotFoundException::new);
        if(u.getPassword().equals(credentials.getPassword()))
        {
            return ResponseEntity.ok(u);
        }
        else
            throw new ResourceNotFoundException();
    }

    @PostMapping("user/add")
    public User addUser(@Valid @RequestBody User user)
    {
        return userService.addUser(user);
    }

    @GetMapping("/users")
    public List<User> getUser()
    {
        return userService.findAll();
    }

    @GetMapping("/user/{userId}")
    public User getStudentById(@PathVariable Long userId)
    {
        return userService.findUser(userId).orElseThrow(() -> new ResourceNotFoundException("Student: " + userId + " not found !"));
    }

    @PutMapping("/user/{userId}")
    public User updateUser(@PathVariable Long userId, @Valid @RequestBody User user)
    {
        return userService.findUser(userId).map(user1 -> {
            user1.setEmail(user.getEmail());
            user1.setPassword(user.getPassword());
            user1.setFirstName(user.getFirstName());
            user1.setLastName(user.getLastName());
            return userService.addUser(user1);
        }).orElseThrow(() -> new ResourceNotFoundException("Student: " + userId + " not found !"));
    }

    @DeleteMapping("/user/{userId}")
    public void deleteUser(@PathVariable Long userId)
    {
        User u = userService.findUser(userId).orElseThrow(ResourceNotFoundException::new);
        userService.removeUser(u);
    }

}
