package com.nikhil.StudentCourse.Services.ServiceImpl;

import com.nikhil.StudentCourse.DAOs.DAOInterfaces.CourseDAO;
import com.nikhil.StudentCourse.Model.Course;
import com.nikhil.StudentCourse.Services.ServiceInterfaces.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService
{

    @Autowired
    private CourseDAO courseDAO;

    @Override
    @Transactional
    public Course addCourse(Course course) {
        return courseDAO.save(course);
    }

    @Override
    @Transactional
    public boolean removeCourse(Course course) {
        return courseDAO.remove(course);
    }

    @Override
    @Transactional
    public boolean removeCourse(long id) {
        return courseDAO.remove(id);
    }

    @Override
    @Transactional
    public Optional<Course> findCourse(Long id) {
        return courseDAO.find(id);
    }

    @Override
    @Transactional
    public Course updateCourse(Course course) {
        return courseDAO.save(course);
    }

    @Override
    @Transactional
    public List<Course> findAll() {
        return courseDAO.list();
    }

}
