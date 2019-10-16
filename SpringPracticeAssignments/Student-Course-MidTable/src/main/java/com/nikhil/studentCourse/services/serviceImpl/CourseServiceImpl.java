package com.nikhil.studentCourse.services.serviceImpl;

import com.nikhil.studentCourse.daos.daoInterfaces.CourseDAO;
import com.nikhil.studentCourse.model.Course;
import com.nikhil.studentCourse.services.serviceInterfaces.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService
{

    @Autowired
    private CourseDAO courseDAO=null;

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
    public boolean removeCourse(Long id) {
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
