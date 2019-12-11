package com.nikhil.studentCourse.daos.daoInterfaces;

import com.nikhil.studentCourse.model.Course;
import com.nikhil.studentCourse.model.Student;

import java.util.Set;

public interface StudentDAO extends GenericDAO<Student,Long>{
    boolean remove(Long stKey, Long coKey);
    Set<Course> listCourses(long id) throws Exception;
}
