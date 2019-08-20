package com.nikhil.StudentCourse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@CrossOrigin("localhost:3000")
public class StudentCourseApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentCourseApplication.class, args);
	}

}
