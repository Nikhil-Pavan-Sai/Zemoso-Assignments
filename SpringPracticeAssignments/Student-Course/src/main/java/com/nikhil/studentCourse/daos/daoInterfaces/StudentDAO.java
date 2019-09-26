package com.nikhil.studentCourse.daos.daoInterfaces;

import com.nikhil.studentCourse.model.Student;

public interface StudentDAO extends GenericDAO<Student,Long>{
    boolean remove(Long stKey, Long coKey);
}
