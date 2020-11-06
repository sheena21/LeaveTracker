package com.tracker.AttendanceTracker.Service;

import com.tracker.AttendanceTracker.Entity.LeaveApply;
import com.tracker.AttendanceTracker.Entity.LeaveClaims;
import com.tracker.AttendanceTracker.Repository.LeaveClaimRepository;
import com.tracker.AttendanceTracker.Repository.LeaveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveClaimsService {

    @Autowired
    private LeaveClaimRepository leaveClaimRepository;

    @Autowired
    private LeaveRepository leaveRepository;

    public List<LeaveClaims> getAll() {
        return leaveClaimRepository.findAll();
    }

    public LeaveClaims getById(Integer id) {
        return leaveClaimRepository.findById(id).get();
    }

    public LeaveClaims saveClaim(LeaveClaims leaveClaims) {
           Integer id=leaveClaims.getLeaveId().getId();
           LeaveApply leaveApply=leaveRepository.findById(id).get();
           Integer duration=leaveApply.getDuration();
        if (leaveClaims.getLeaveType().equalsIgnoreCase("Official")) {
            leaveClaims.setReimburseAmount(leaveClaims.getAmount() * duration);
            leaveClaims.setRemark("Reimbursement Done");
        } else {
            leaveClaims.setAmount(0);
            leaveClaims.setReimburseAmount(0);
            leaveClaims.setRemark("Not Applicable");
        }
        return leaveClaimRepository.save(leaveClaims);
    }

    public LeaveClaims update(Integer id, LeaveClaims leaveClaims) {
        leaveClaimRepository.findById(id);
        return leaveClaimRepository.save(leaveClaims);
    }

    public LeaveClaims delete(Integer id) {
        leaveClaimRepository.deleteById(id);
        return null;
    }

    public List<LeaveClaims> getByUserId(Integer id) {
        return leaveClaimRepository.findAllByUserid(id);
    }
}
