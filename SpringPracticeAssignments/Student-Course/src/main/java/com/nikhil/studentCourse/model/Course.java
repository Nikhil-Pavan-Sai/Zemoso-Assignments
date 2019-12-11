package com.nikhil.studentCourse.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.NaturalId;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Course")
@EntityListeners(AuditingEntityListener.class)
public class Course implements Serializable,Cloneable,Model
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @NaturalId
    private String courseName;

    @NotNull
    @Column(length = 500)
    private String courseDescription;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "courses")
    private Set<Student> students = new HashSet<>();

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    public Course() {
    }

    public Course(String courseName, String courseDescription){
        this.courseName = courseName;
        this.courseDescription = courseDescription;
    }

    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    private Set<Student> getStudents() {
        return students;
    }

    private void setStudents(Set<Student> students) {
        this.students = students;
    }

    public Course copy(){
        Course course = new Course();
        course.setId(this.id);
        course.setCourseName(this.getCourseName());
        course.setCourseDescription(this.getCourseDescription());
        course.setStudents(this.getStudents());
        return course;
    }
}
