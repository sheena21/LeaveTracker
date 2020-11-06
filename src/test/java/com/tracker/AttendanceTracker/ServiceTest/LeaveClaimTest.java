package com.tracker.AttendanceTracker.ServiceTest;

import com.tracker.AttendanceTracker.Entity.*;
import com.tracker.AttendanceTracker.Repository.LeaveClaimRepository;
import com.tracker.AttendanceTracker.Service.LeaveClaimsService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
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
public class LeaveClaimTest {
    @MockBean
    private LeaveClaimRepository leaveClaimRepository;

    @Autowired
    private LeaveClaimsService leaveClaimsService;
    private UserProfile userProfile;
    private LeaveApply leaveApply;
    private User user;
    private LeaveClaims leaveClaims;
    private Manager manager;

    @Before
    public void init()
    {
        leaveClaims=new LeaveClaims();
        leaveClaims.setLeaveType("Official");
        leaveClaims.setAmount(1000);
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
        when(leaveClaimRepository.save(leaveClaims)).thenReturn(leaveClaims);
    }

    @Test
    public void getAllTest() {
        when(leaveClaimRepository.findAll()).thenReturn(Collections.singletonList(leaveClaims));
        assertEquals(1, leaveClaimsService.getAll().size());
    }

   /* @Test
    public void getByIdTest() {

        Integer x = 1;
        when(leaveClaimRepository.findByUserid(x))
                .thenReturn(leaveClaims);
        assertEquals(leaveClaims.getId(), leaveClaimsService.getById(x));
    }   */

    @Test
    public void saveClaimtTest() {
        assertEquals(leaveClaims, leaveClaimsService.saveClaim(leaveClaims));
    }

    @Test
    public void deleteTest() {
        Integer x = 1;
        leaveClaimsService.delete(x);
        verify(leaveClaimRepository, times(1)).deleteById(x);
    }
}
