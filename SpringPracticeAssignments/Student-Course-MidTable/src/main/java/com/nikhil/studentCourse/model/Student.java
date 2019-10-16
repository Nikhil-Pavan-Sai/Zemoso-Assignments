package com.nikhil.studentCourse.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Student")
public class Student
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NaturalId
    private String name;

    @NotNull
    @NaturalId
    private String standard;

    @NotNull
    @NaturalId
    private String branch;

    @JsonIgnore
    @OneToMany(mappedBy = "student", cascade = {CascadeType.PERSIST, CascadeType.MERGE,CascadeType.REMOVE})
    private Set<Task> tasks = new HashSet<>();

    public Student() {
    }

    public Student(String name, String standard, String branch) {
        this.name = name;
        this.standard = standard;
        this.branch = branch;
    }

    public Student copy(){
        Student student = new Student();
        student.setId(this.id);
        student.setName(this.name);
        student.setBranch(this.getBranch());
        student.setStandard(this.getStandard());
        student.setTasks(this.getTasks());
        return student;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }
}
