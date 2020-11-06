package com.tracker.AttendanceTracker.ServiceTest;

import com.tracker.AttendanceTracker.Entity.Manager;
import com.tracker.AttendanceTracker.Entity.User;
import com.tracker.AttendanceTracker.Entity.UserProfile;
import com.tracker.AttendanceTracker.Repository.UserProfileRepository;
import com.tracker.AttendanceTracker.Service.UserProfileService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserProfileTest {

    @MockBean
    private UserProfileRepository userProfileRepository;

    @Autowired
    private UserProfileService userProfileService;

    private User user;
    private Manager manager;
    private UserProfile userProfile;
    @Before
    public void init()
    {
        user=new User();
        userProfile=new UserProfile();
        manager=new Manager();
        manager.setManagerName("Kartik Dhar");
        manager.setDepartment("IT");
        user.setUsername("xyz@gmail.com");
        user.setFullName("XYZ");
        user.setConfirmPassword("xyz123");
        user.setPassword("xyz123");
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
        when(userProfileRepository.save(userProfile)).thenReturn(userProfile);

    }
    @Test
    public void addUserTest() {

        assertEquals(userProfile, userProfileService.addUser(userProfile));
    }
    @Test
    public void getAllTest() {

        when(userProfileRepository.findAll()).thenReturn(Collections.singletonList(userProfile));
        assertEquals(1, userProfileService.getAllUser().size());
    }

    @Test
    public void getUserByIdTest() {
        Integer x = 4;
        when(userProfileRepository.findById(x)).thenReturn(java.util.Optional.of(userProfile));
        assertEquals(userProfile.getId(), userProfileService.getUserById(x).getId());
    }

    @Test
    public void deleteUserTest() {
        Integer x = 5;
        when(userProfileRepository.findById(x)).thenReturn(java.util.Optional.of(userProfile));
        assertEquals(userProfile.isStatus(), userProfileService.getUserById(x).isStatus());
    }
}
