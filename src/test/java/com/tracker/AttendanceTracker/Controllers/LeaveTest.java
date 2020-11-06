package com.tracker.AttendanceTracker.Controllers;

import com.google.gson.Gson;
import com.tracker.AttendanceTracker.Entity.LeaveApply;
import com.tracker.AttendanceTracker.Entity.Manager;
import com.tracker.AttendanceTracker.Entity.User;
import com.tracker.AttendanceTracker.Entity.UserProfile;
import com.tracker.AttendanceTracker.Repository.LeaveRepository;
import com.tracker.AttendanceTracker.payload.JWTLoginSuccessResponse;
import com.tracker.AttendanceTracker.payload.LoginRequest;
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

import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LeaveTest {

    @LocalServerPort
    int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    private User user;
    private UserProfile userProfile;
    private Manager manager;
    private LeaveApply leaveApply;


    @MockBean
    private LeaveRepository leaveRepository;


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
    public void leaveLogTest() throws Exception {
        Gson g = new Gson();
        String accessToken = token("demo@gmail.com", "demo12345");
        JWTLoginSuccessResponse token = g.fromJson(accessToken, JWTLoginSuccessResponse.class);
        System.out.println(token.getToken());
        final String baseUrl = "http://localhost:" + port + "/api/leave/1";
        URI uri = new URI(baseUrl);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("Authorization", token.getToken());
        HttpEntity<Void> request = new HttpEntity<>(headers);
        LeaveApply leaveApply = this.testRestTemplate
                .exchange(uri, HttpMethod.GET, request, LeaveApply.class)
                .getBody();
        assert leaveApply != null;
        assertEquals("Fever", leaveApply.getReason());
    }

    @Test
    public void applyLeaveTest() throws Exception {
        Gson g = new Gson();
        String accessToken = token("demo@gmail.com", "demo12345");
        JWTLoginSuccessResponse token = g.fromJson(accessToken, JWTLoginSuccessResponse.class);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("Authorization", token.getToken());
        user = new User();
        user.setId(user.getId());
        user.setUsername(user.getUsername());
        user.setPassword(user.getPassword());
        user.setFullName(user.getFullName());
        userProfile = new UserProfile();
        manager = new Manager();
        manager.setId(1);
        manager.setStatus(true);
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
        leaveApply=new LeaveApply();
        leaveApply.setReqLeave(1);
        leaveApply.setDuration(1);
        leaveApply.setUserId(userProfile);
        leaveApply.setManagerId(manager);
        leaveApply.setReason("Fever");
        when(leaveRepository.save(leaveApply)).thenReturn(leaveApply);
        HttpEntity<LeaveApply> request = new HttpEntity<LeaveApply>(leaveApply, headers);
        ResponseEntity<String> response = testRestTemplate.exchange(URI.create("/api/leave"), HttpMethod.POST, request, String.class);
        assertEquals(201, response.getStatusCodeValue());

    }

}
