package com.nikhil.Attendance.model;


import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Table
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Attendance
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rollno;

    @NotNull
    private double attendance;

    @OneToOne
    @JoinColumn(name = "students_rollno")
    private Students students;

    public int getRollno() {
        return rollno;
    }

    public void setRollno(int rollno) {
        this.rollno = rollno;
    }

    public double getAttendance() {
        return attendance;
    }

    public void setAttendance(double attendance) {
        this.attendance = attendance;
    }


}
