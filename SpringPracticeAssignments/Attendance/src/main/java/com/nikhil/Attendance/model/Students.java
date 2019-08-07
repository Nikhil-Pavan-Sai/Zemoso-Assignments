package com.nikhil.Attendance.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "Attendance")
public class Students
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rollno;

    @OneToOne(mappedBy = "students")
    private Attendance attendance;


    @NotNull
    private String studentName;

    @NotNull
    private int standard;

    private String address;

    public int getRollno() {
        return rollno;
    }

    public void setRollno(int rollno) {
        this.rollno = rollno;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public int getStandard() {
        return standard;
    }

    public void setStandard(int standard) {
        this.standard = standard;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


}
