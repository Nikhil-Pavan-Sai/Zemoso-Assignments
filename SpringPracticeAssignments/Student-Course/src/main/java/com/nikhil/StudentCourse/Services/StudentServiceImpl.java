package com.nikhil.StudentCourse.Services;

import com.nikhil.StudentCourse.DAOs.StudentDAO;
import com.nikhil.StudentCourse.Model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService
{

    @Autowired
    private StudentDAO studentDao;

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
    public boolean removeStudent(long id) {
        return studentDao.remove(id);
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

}
