package com.nikhil.studentCourse.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "Task")
@EntityListeners(AuditingEntityListener.class)
public class Task{

    @Id
    @GenericGenerator(name="taskGenerator",strategy = "com.nikhil.studentCourse.model.generators.TaskIdGenerator")
    @GeneratedValue(generator = "taskGenerator")
    private long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name="student_id")
    @JsonIgnore
    private Student student;

    @NotNull
    @ManyToOne
    @JoinColumn(name="course_id")
    private Course course;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Task copy(){
        Task task = new Task();
        task.setId(Objects.hash(this.student.getId(),this.course.getId()));
        task.setCourse(this.course);
        task.setStudent(this.student);
        return task;
    }
}
