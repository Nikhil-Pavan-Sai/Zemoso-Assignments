package com.nikhil.studentCourse.model.generators;

import com.nikhil.studentCourse.model.Task;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.util.Objects;

public class TaskIdGenerator implements IdentifierGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object){
        if(object instanceof Task)
        {
            Task task = (Task)object;
            if(task.getStudent()!=null && task.getCourse()!=null)
                return (long) Objects.hash(task.getStudent().getId(),task.getCourse().getId());
            else {

                throw new HibernateException("Intern and task is compulsory for creating an task");
            }
        }
        throw new HibernateException("The object assigned is not an instance of the Task");
    }
}
