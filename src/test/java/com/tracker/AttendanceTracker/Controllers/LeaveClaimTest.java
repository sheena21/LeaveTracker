/*package com.tracker.AttendanceTracker.Controllers;

import com.google.gson.Gson;
import com.tracker.AttendanceTracker.Entity.*;
import com.tracker.AttendanceTracker.Repository.LeaveClaimRepository;
import com.tracker.AttendanceTracker.Repository.LeaveRepository;
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

import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LeaveClaimTest {

    @LocalServerPort
    int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    private User user;
    private UserProfile userProfile;
    private Manager manager;
    private LeaveApply leaveApply;
    private LeaveClaims leaveClaims;

    @MockBean
    private LeaveClaimRepository leaveClaimRepository;
    @Before
    public void setUp() {
        user = new User();
        user.setId(user.getId());
        user.setUsername(user.getUsername());
        user.setPassword(user.getPassword());
        user.setFullName(user.getFullName());
        manager = new Manager();
        manager.setId(1);
        manager.setStatus(true);
        manager.setManagername("Kartik Dhar");
        manager.setDepartment("IT");
        userProfile = new UserProfile();

        userProfile.setStatus(true);
        userProfile.setPaidleave(2);
        userProfile.setUser(user);
        userProfile.setAddress("Delhi");
        userProfile.setDOJ("15/09/20");
        userProfile.setEmpid("E01");
        userProfile.setEmptype("Full Time");
        userProfile.setPhoneno("7981136354");
        userProfile.setRoles("Developer");
        userProfile.setEmpdep("IT");
        userProfile.setManager(manager);
        leaveApply=new LeaveApply();
        leaveApply.setReqleave(1);
        leaveApply.setDuration(1);
        leaveApply.setUserid(userProfile);
        leaveApply.setManagerid(manager);
        leaveApply.setReason("Fever");
        leaveClaims=new LeaveClaims();
        leaveClaims.setId(1);
        leaveClaims.setAmount(100);
        leaveClaims.setLeavetype("Official");
        leaveClaims.setLeaveid(leaveApply);
        leaveClaims.setUserid(1);



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
    public void getleaveClaimTest() throws Exception {
        Gson g = new Gson();
        String accessToken = token("demo@gmail.com", "demo12345");
        JWTLoginSuccessResponse token = g.fromJson(accessToken, JWTLoginSuccessResponse.class);
        System.out.println(token.getToken());
        final String baseUrl = "http://localhost:" + port + "/api/leaveclaims/1";
        URI uri = new URI(baseUrl);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("Authorization", token.getToken());
        HttpEntity<Void> request = new HttpEntity<>(headers);
        LeaveClaims leaveClaims = this.testRestTemplate
                .exchange(uri, HttpMethod.GET, request, LeaveClaims.class)
                .getBody();
        assert leaveClaims != null;
        assertEquals("Official", leaveClaims.getLeavetype());
    }

    @Test
    public void applyLeaveTest() throws Exception {
        Gson g = new Gson();
        String accessToken = token("demo@gmail.com", "demo12345");
        JWTLoginSuccessResponse token = g.fromJson(accessToken, JWTLoginSuccessResponse.class);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("Authorization", token.getToken());
        HttpEntity<LeaveClaims> request = new HttpEntity<LeaveClaims>(leaveClaims, headers);
        ResponseEntity<String> response = testRestTemplate.exchange(URI.create("/api/leaveclaims"), HttpMethod.POST, request, String.class);
        assertEquals(201, response.getStatusCodeValue());

    }

}
*/