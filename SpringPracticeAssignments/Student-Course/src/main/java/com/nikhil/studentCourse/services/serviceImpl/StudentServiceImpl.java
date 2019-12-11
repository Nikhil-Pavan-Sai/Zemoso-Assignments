package com.nikhil.studentCourse.services.serviceImpl;

import com.nikhil.studentCourse.daos.daoInterfaces.StudentDAO;
import com.nikhil.studentCourse.model.Course;
import com.nikhil.studentCourse.model.Student;
import com.nikhil.studentCourse.services.serviceInterfaces.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class StudentServiceImpl implements StudentService
{

    @Autowired
    private StudentDAO studentDao = null;

    private Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    @Override
    @Transactional
    public Student addStudent(Student student) {
        return studentDao.save(student);
    }

    @Override
    @Transactional
    public boolean removeStudent(Student student) {
        return studentDao.remove(student);
    }

    @Override
    @Transactional
    public boolean removeStudent(Long id) {
        return studentDao.remove(id);
    }

    @Transactional
    public boolean removeCourseFromStudent(Long stKey, Long coKey)
    {
        return studentDao.remove(stKey,coKey);
    }

    @Override
    @Transactional
    public Optional<Student> findStudent(Long id) {
        return studentDao.find(id);
    }

    @Override
    @Transactional
    public Student updateStudent(Student student) {
        return studentDao.save(student);
    }

    @Override
    @Transactional
    public List<Student> findAll() {
        return studentDao.list();
    }

    @Transactional
    @Override
    public Set<Course> getCourses(long id) {
        try {

            return studentDao.listCourses(id);
        }
        catch (Exception e)
        {
            logger.error(e.getMessage());
            return new HashSet<>();
        }
    }

    public void setStudentDao(StudentDAO studentDao) {
        this.studentDao = studentDao;
    }

}
