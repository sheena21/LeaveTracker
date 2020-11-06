package com.tracker.AttendanceTracker.ServiceTest;

import com.tracker.AttendanceTracker.Entity.LeaveApply;
import com.tracker.AttendanceTracker.Entity.Manager;
import com.tracker.AttendanceTracker.Entity.User;
import com.tracker.AttendanceTracker.Entity.UserProfile;
import com.tracker.AttendanceTracker.Repository.LeaveRepository;
import com.tracker.AttendanceTracker.Service.LeaveService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class LeaveApplyTest {

    @MockBean
    private LeaveRepository leaveRepository;

    @Autowired
    private LeaveService leaveService;

    private LeaveApply leaveApply;
    private User user;
    private UserProfile userProfile;
    private Manager manager;
    @Before
    public void init() {
        user=new User();
        userProfile=new UserProfile();
        leaveApply=new LeaveApply();
        user.setUsername("xyz@gmail.com");
        user.setFullName("XYZ");
        user.setConfirmPassword("xyz123");
        user.setPassword("xyz123");
        userProfile.setId(1);
        userProfile.setStatus(true);
        userProfile.setPaidLeave(2);
        userProfile.setUser(user);
        userProfile.setDesignation("Developer");
        userProfile.setEmpDep("IT");
        userProfile.setManager(manager);
        userProfile.setAddress("Delhi");
        userProfile.setDOJ("15/09/20");
        userProfile.setEmpId("E01");
        userProfile.setEmpType("Full Time");
        userProfile.setPhoneNo("7981136354");
        leaveApply.setReqLeave(1);
        leaveApply.setUserId(userProfile);
        leaveApply.setDuration(1);
        manager=new Manager();
        manager.setStatus(true);
        manager.setDepartment("IT");
        manager.setManagerName("Mayank");
        when(leaveRepository.save(leaveApply)).thenReturn(leaveApply);

    }

     @Test
     public void requiredleaveTest()
     {


          assertEquals(leaveApply,leaveService.requiredleave(leaveApply));
     }
    @Test
    public void leaveinfoTest() {


        when(leaveRepository.findAll()).thenReturn(Collections.singletonList(leaveApply));
        assertEquals(1, leaveService.leaveinfo().size());
    }

    @Test
    public void getByIdTest() {

        Integer id = 1;
        when(leaveRepository.findById(id)).thenReturn(java.util.Optional.of(leaveApply));
        assertEquals(leaveApply.getId(), leaveService.getById(id).getId());
    }

    @Test
    public void delete() {
        Integer id = 1;
        leaveService.delete(id);
        verify(leaveRepository, times(1)).deleteById(id);
    }
}
