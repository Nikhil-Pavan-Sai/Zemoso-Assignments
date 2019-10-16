package com.nikhil.studentCourse;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentCourseApplicationTests {

	@Test
	public void contextLoads(){
		//Dummy assertion
		assertEquals(2,4/2);
	}

}
