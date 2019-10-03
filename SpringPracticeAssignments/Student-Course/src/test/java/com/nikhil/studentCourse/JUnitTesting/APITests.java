package com.nikhil.studentCourse.JUnitTesting;

import com.nikhil.studentCourse.model.Course;
import com.nikhil.studentCourse.model.Student;
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
    private TestRestTemplate restTemplate = null;

    @LocalServerPort
    private int randomServerPort = 0;

    @Test
    public void postApiTest() throws URISyntaxException {
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
        HttpEntity<Course> requestEntity1 = new HttpEntity<>(null, headers);
        ResponseEntity<String> result1 = restTemplate1.exchange(uri1, HttpMethod.GET, requestEntity1, String.class);
        assertEquals(200, result1.getStatusCodeValue());
    }
}
