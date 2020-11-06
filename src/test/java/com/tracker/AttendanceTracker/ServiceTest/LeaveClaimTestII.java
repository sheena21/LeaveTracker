package com.tracker.AttendanceTracker.ServiceTest;

import com.tracker.AttendanceTracker.Entity.LeaveClaims;
import com.tracker.AttendanceTracker.Repository.LeaveClaimRepository;
import com.tracker.AttendanceTracker.Service.LeaveClaimsService;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import java.util.Collections;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest//(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ContextConfiguration
public class LeaveClaimTestII {

    @MockBean
    private LeaveClaimRepository leaveClaimRepository;

    @Autowired
    private LeaveClaimsService leaveClaimsService;

    private LeaveClaims leaveClaims;

    @Before
    public void init()
    {
        leaveClaims=new LeaveClaims();
        leaveClaims.setLeaveType("Official");
        leaveClaims.setAmount(1000);

        when(leaveClaimRepository.save(leaveClaims)).thenReturn(leaveClaims);
    }

    @Test
    public void getAllTest() {
        when(leaveClaimRepository.findAll()).thenReturn(Collections.singletonList(leaveClaims));
        Assertions.assertEquals(2, leaveClaimsService.getAll().size());
    }
}
