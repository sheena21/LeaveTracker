package com.tracker.AttendanceTracker.Controllers;

import com.google.gson.Gson;
import com.tracker.AttendanceTracker.Entity.Manager;
import com.tracker.AttendanceTracker.Entity.User;
import com.tracker.AttendanceTracker.Entity.UserProfile;
import com.tracker.AttendanceTracker.Repository.UserProfileRepository;
import com.tracker.AttendanceTracker.Repository.UserRepository;
import com.tracker.AttendanceTracker.payload.JWTLoginSuccessResponse;
import com.tracker.AttendanceTracker.payload.LoginRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserProfileTest {


    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    int randomServerPort;

    private UserProfile userProfile;
    private User user;
    private Manager manager;

    @MockBean
    private UserProfileRepository userProfileRepository;
    @Before
    public void setUp() {
        user = new User();
        user.setId(user.getId());
        user.setUsername(user.getUsername());
        user.setPassword(user.getPassword());
        user.setFullName(user.getFullName());
        userProfile = new UserProfile();
        manager = new Manager();
        manager.setManagerName("Kartik Dhar");
        manager.setDepartment("IT");
        userProfile.setId(1);
        userProfile.setStatus(true);
        userProfile.setPaidLeave(2);
        userProfile.setUser(user);
        userProfile.setAddress("Delhi");
        userProfile.setDOJ("15/09/20");
        userProfile.setEmpId("E01");
        userProfile.setEmpType("Full Time");
        userProfile.setPhoneNo("7981136354");
        userProfile.setDesignation("Developer");
        userProfile.setEmpDep("IT");
        userProfile.setManager(manager);



    }

    public String token(String username, String password) throws URISyntaxException {
        final String baseUrl = "http://localhost:" + randomServerPort + "/api/user/sign-in";
        URI uri = new URI(baseUrl);
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername(username);
        loginRequest.setPassword(password);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        HttpEntity<LoginRequest> request = new HttpEntity<>(loginRequest, headers);
        ResponseEntity<String> tokenEntity = this.restTemplate.postForEntity(uri, request, String.class);
        return tokenEntity.getBody();
    }

    @Test
    public void getAllUserTest() throws Exception {
        Gson g = new Gson();
        String accessToken = token("demo@gmail.com", "demo12345");
        JWTLoginSuccessResponse token = g.fromJson(accessToken, JWTLoginSuccessResponse.class);
        System.out.println(token.getToken());
        final String baseUrl = "http://localhost:" + randomServerPort + "/api/userdash/1";
        URI uri = new URI(baseUrl);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("Authorization", token.getToken());
        HttpEntity<Void> request = new HttpEntity<>(headers);
        UserProfile userProfile = this.restTemplate
                .exchange(uri, HttpMethod.GET, request, UserProfile.class)
                .getBody();
        assert userProfile != null;
        assertEquals("Delhi", userProfile.getAddress());
    }

    @Test
    public void addUserTest() throws Exception {
        Gson g = new Gson();
        String accessToken = token("demo@gmail.com", "demo12345");
        JWTLoginSuccessResponse token = g.fromJson(accessToken, JWTLoginSuccessResponse.class);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("Authorization", token.getToken());
        HttpEntity<UserProfile> request = new HttpEntity<UserProfile>(userProfile, headers);
        ResponseEntity<String> response = restTemplate.exchange(URI.create("/api/userdash"), HttpMethod.POST, request, String.class);
        assertEquals(201, response.getStatusCodeValue());
        System.out.println(userProfile.getId());
    }



}












