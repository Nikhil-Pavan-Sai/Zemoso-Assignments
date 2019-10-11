package com.nikhil.studentCourse.daos.daoImpl;

import com.nikhil.studentCourse.daos.daoInterfaces.CourseDAO;
import com.nikhil.studentCourse.daos.daoInterfaces.StudentDAO;
import com.nikhil.studentCourse.model.Course;
import com.nikhil.studentCourse.model.Student;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public class StudentDAOImpl implements StudentDAO {

    @Autowired
    private EntityManager entityManager = null;

    @Autowired
    private CourseDAO courseDAO = null;

    @Override
    public List<Student> list() {
        Session currentSession = (Session) entityManager.getDelegate();
        return currentSession.createQuery("from Student",Student.class).getResultList();
    }

    @Override
    public Optional<Student> find(Long key) {
        Session currentSession = (Session) entityManager.getDelegate();
        return Optional.ofNullable(currentSession.get(Student.class,key));
    }

    @Override
    public boolean remove(Long key) {
        Session currSession = (Session) entityManager.getDelegate();
        Optional<Student> currStudent = find(key);
        if (currStudent.isPresent())
        {
            currSession.createQuery("delete from Student where id=" + key).executeUpdate();
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(Student entry) {
        return remove(entry.getId());
    }

    @Override
    public boolean remove(Long stKey, Long coKey)
    {
        Optional<Student> currStudent = find(stKey);
        Optional<Course>  currCourse = courseDAO.find(coKey);
        if (currStudent.isPresent() && currCourse.isPresent())
        {
            Student student = currStudent.get();
            Set<Course> stCourse = student.getCourses();
            stCourse.remove(currCourse.get());
            student.setCourses(stCourse);
            save(student);
            return true;
        }
        return false;
    }

    @Override
    public Student save(Student entry) {

        Session currentSession = (Session)entityManager.getDelegate();
        if (currentSession.contains(entry)){ currentSession.merge(entry); }
        else { currentSession.save(entry); }
        return entry;
    }

}
