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
    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "Student_Course",
            joinColumns = { @JoinColumn(name = "student_id") },
            inverseJoinColumns = { @JoinColumn(name = "course_id") })
    private Set<Course> courses = new HashSet<>();

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
        student.setCourses(this.getCourses());
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

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }
}
