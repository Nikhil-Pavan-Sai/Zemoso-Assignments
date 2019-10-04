package com.nikhil.studentCourse.JUnitTesting;

import com.nikhil.studentCourse.controller.CourseController;
import com.nikhil.studentCourse.controller.StudentController;
import com.nikhil.studentCourse.controller.UserController;
import com.nikhil.studentCourse.model.Course;
import com.nikhil.studentCourse.model.Student;
import com.nikhil.studentCourse.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
public class APITests {

    @Autowired
    private StudentController studentController = null;

    @Autowired
    private CourseController courseController = null;

    @Autowired
    private UserController userController = null;

    @Autowired
    private TestRestTemplate restTemplate = null;

    @LocalServerPort
    private int randomServerPort = 0;

    @Test
    public void postApiTest() throws URISyntaxException {
        int studentSize = studentController.getStudent().size();
        int courseSize = courseController.getCourse().size();
        int userSize = userController.getUser().size();

        System.out.println("StudentSize: " + studentSize + "\nCourseSize: " + courseSize + "\nUserSize: " + userSize);

        final String baseUrl = "http://localhost:"+randomServerPort+"/students";
        URI uri = new URI(baseUrl);
        Student student = new Student("nikhil","Fourth","CSE");
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");
        HttpEntity<Student> request = new HttpEntity<>(student, headers);
        ResponseEntity<String> result = this.restTemplate.postForEntity(uri, request, String.class);
        assertEquals(200, result.getStatusCodeValue());

        final String baseUrl1 = "http://localhost:"+randomServerPort+"/courses";
        URI uri1 = new URI(baseUrl1);
        Course course = new Course("Some Random Course");
        HttpHeaders headers1 = new HttpHeaders();
        headers1.set("X-COM-PERSIST", "true");
        HttpEntity<Course> request1 = new HttpEntity<>(course, headers1);
        ResponseEntity<String> result1 = this.restTemplate.postForEntity(uri1, request1, String.class);
        assertEquals(200, result1.getStatusCodeValue());

        final String baseUrl2 = "http://localhost:"+randomServerPort+"/user/add";
        URI uri2 = new URI(baseUrl2);
        User user = new User("Nikhil", "Pavan Sai", "nikhil@gmail.com", "nikhil1234");
        HttpHeaders headers2 = new HttpHeaders();
        headers2.set("X-COM-PERSIST", "true");
        HttpEntity<User> request2 = new HttpEntity<>(user, headers2);
        ResponseEntity<String> result2 = this.restTemplate.postForEntity(uri2, request2, String.class);
        assertEquals(200, result2.getStatusCodeValue());

        //Testing whether the POST API is working correctly or not. The sizes of the Tables(Student, Course, User)
        // initially will be 0 and after adding the Student, Course and User the sizes of the Tables will become 1 each.
        assertEquals(studentSize+1,studentController.getStudent().size());
        assertEquals(courseSize+1,courseController.getCourse().size());
        assertEquals(userSize+1,userController.getUser().size());
    }

    @Test
    public void getApiTest() throws URISyntaxException{
        RestTemplate restTemplate = new RestTemplate();
        final String baseUrl = "http://localhost:"+randomServerPort+"/students";
        URI uri = new URI(baseUrl);
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");
        HttpEntity<Student> requestEntity = new HttpEntity<>(null, headers);
        ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.GET, requestEntity, String.class);
        assertEquals(200, result.getStatusCodeValue());

        RestTemplate restTemplate1 = new RestTemplate();
        final String baseUrl1 = "http://localhost:"+randomServerPort+"/courses";
        URI uri1 = new URI(baseUrl1);
        HttpHeaders headers1 = new HttpHeaders();
        headers1.set("X-COM-PERSIST", "true");
        HttpEntity<Course> requestEntity1 = new HttpEntity<>(null, headers1);
        ResponseEntity<String> result1 = restTemplate1.exchange(uri1, HttpMethod.GET, requestEntity1, String.class);
        assertEquals(200, result1.getStatusCodeValue());

        RestTemplate restTemplate2 = new RestTemplate();
        final String baseUrl2 = "http://localhost:"+randomServerPort+"/users";
        URI uri2 = new URI(baseUrl2);
        HttpHeaders headers2 = new HttpHeaders();
        headers2.set("X-COM-PERSIST", "true");
        HttpEntity<User> requestEntity2 = new HttpEntity<>(null, headers2);
        ResponseEntity<String> result2 = restTemplate2.exchange(uri2, HttpMethod.GET, requestEntity2, String.class);
        assertEquals(200, result2.getStatusCodeValue());
    }
}
