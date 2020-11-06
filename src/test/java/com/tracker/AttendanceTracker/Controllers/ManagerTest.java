package com.tracker.AttendanceTracker.Controllers;

import com.google.gson.Gson;
import com.sun.jndi.toolkit.url.Uri;
import com.tracker.AttendanceTracker.Entity.Manager;
import com.tracker.AttendanceTracker.Entity.User;
import com.tracker.AttendanceTracker.Entity.UserProfile;
import com.tracker.AttendanceTracker.Repository.ManagerRepositiory;
import com.tracker.AttendanceTracker.payload.JWTLoginSuccessResponse;
import com.tracker.AttendanceTracker.payload.LoginRequest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ManagerTest {

    @LocalServerPort
    int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    private User user;
    private Manager manager;




    @Before
    public void setUp()
    {
        user=new User();
        user.setId(user.getId());
        user.setFullName(user.getFullName());
        user.setUsername(user.getUsername());
        user.setPassword(user.getPassword());
        manager=new Manager();
        manager.setId(1);
        manager.setStatus(true);
        manager.setManagerName("Mahima");
        manager.setDepartment("IT");
    }


    public String token(String username, String password) throws URISyntaxException {
        final String baseUrl = "http://localhost:" + port + "/api/user/sign-in";
        URI uri = new URI(baseUrl);
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername(username);
        loginRequest.setPassword(password);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        HttpEntity<LoginRequest> request = new HttpEntity<>(loginRequest, headers);
        ResponseEntity<String> tokenEntity = this.testRestTemplate.postForEntity(uri, request, String.class);
        return tokenEntity.getBody();
    }
    @Test
    public void addManagerTest() throws URISyntaxException {
        Gson g=new Gson();
        String accessToken=token("demo@gmail.com","demo12345");
        JWTLoginSuccessResponse token=g.fromJson(accessToken,JWTLoginSuccessResponse.class);
        HttpHeaders headers=new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("Authorization", token.getToken());
        HttpEntity<Manager> request=new HttpEntity<Manager>(manager,headers);
        ResponseEntity<String> response=testRestTemplate.exchange(URI.create("/api/manager"), HttpMethod.POST,request,String.class);
        assertEquals(201,response.getStatusCodeValue());

    }
    @Test
    public void getManagerTest() throws Exception {
        Gson g = new Gson();
        String accessToken = token("demo@gmail.com", "demo12345");
        JWTLoginSuccessResponse token = g.fromJson(accessToken, JWTLoginSuccessResponse.class);
        System.out.println(token.getToken());
        final String baseUrl = "http://localhost:" + port + "/api/manager/1";
        URI uri = new URI(baseUrl);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("Authorization", token.getToken());
        HttpEntity<Void> request = new HttpEntity<>(headers);
       Manager manager = this.testRestTemplate
                .exchange(uri, HttpMethod.GET, request, Manager.class)
                .getBody();

        Assert.assertEquals("Mahima", manager.getManagerName());
    }
}
