package com.nikhil.studentCourse.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Course")
public class Course
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NaturalId
    private String courseName;

    @JsonIgnore
    @OneToMany(mappedBy = "course", cascade = {CascadeType.PERSIST, CascadeType.MERGE,CascadeType.REMOVE})
    private Set<Task> tasks = new HashSet<>();

    public Course() {
    }

    public Course(String courseName){
        this.courseName = courseName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    private Set<Task> getTasks() {
        return tasks;
    }

    private void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }

    public Course copy(){
        Course course = new Course();
        course.setId(this.id);
        course.setCourseName(this.getCourseName());
        course.setTasks(this.getTasks());
        return course;
    }
}
