package com.tracker.AttendanceTracker.Service;

import com.tracker.AttendanceTracker.Entity.LeaveApply;
import com.tracker.AttendanceTracker.Entity.UserProfile;
import com.tracker.AttendanceTracker.Repository.LeaveRepository;
import com.tracker.AttendanceTracker.Repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveService {


    @Autowired
    private UserProfileRepository userProfileRepository;
    @Autowired
    private LeaveRepository leaveRepository;

    public List<LeaveApply> leaveinfo() {
        return leaveRepository.findAll();
    }

    public LeaveApply getById(Integer id) {
        return leaveRepository.findById(id).get();
    }

    public LeaveApply delete(Integer id) {

        leaveRepository.deleteById(id);
        return null;
    }

    public LeaveApply requiredleave(LeaveApply leaveApply) {
        Integer userid=leaveApply.getUserId().getId();
        UserProfile user=userProfileRepository.findById(userid).get();
        leaveApply.setLeaveStatus("Pending");
        leaveApply.setEmpId(user.getEmpId());
        return leaveRepository.save(leaveApply);
    }
}
