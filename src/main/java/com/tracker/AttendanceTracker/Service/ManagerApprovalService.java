package com.tracker.AttendanceTracker.Service;

import com.tracker.AttendanceTracker.Entity.LeaveApply;
import com.tracker.AttendanceTracker.Entity.Manager;
import com.tracker.AttendanceTracker.Entity.UserProfile;
import com.tracker.AttendanceTracker.Repository.LeaveRepository;
import com.tracker.AttendanceTracker.Repository.ManagerRepositiory;
import com.tracker.AttendanceTracker.Repository.UserProfileRepository;
import com.tracker.AttendanceTracker.dto.LeaveApplydto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ManagerApprovalService {

    @Autowired
    private ConvertorLeavedto convertorLeavedto;
    @Autowired
    private LeaveRepository leaveRepository;

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private ManagerRepositiory managerRepositiory;

    public LeaveApply approvedleave(Integer id, LeaveApply leaveApply) {
        LeaveApply op = leaveRepository.findById(id).get();

        Integer managerid = leaveApply.getManagerId().getId();
        Manager manager = managerRepositiory.findById(managerid).get();

        Integer userid = leaveApply.getUserId().getId();
        UserProfile userProfile = userProfileRepository.findById(userid).get();
        Integer leavebalance = userProfile.getPaidLeave();
        if (manager.getDepartment().equalsIgnoreCase(userProfile.getEmpDep())) {
            if (leavebalance > 0 & op.getReqLeave() <= leavebalance ) {
                op.setLeaveStatus("Accepted");
                userProfile.setPaidLeave(leavebalance - op.getReqLeave());
            } else {
                op.setLeaveStatus("Rejected");
            }
        } else {
            System.out.println("Not permissible");
        }
        return leaveRepository.save(op);

    }

    public List<LeaveApplydto> leaveinfo(String log) {
        List<LeaveApply> list=leaveRepository.findAllByLeaveStatus(log);
        return list.stream().map(convertorLeavedto::leavedto).collect(Collectors.toList());
    }

    public LeaveApplydto getById(Integer id) {

        LeaveApply get= leaveRepository.findById(id).get();
        return convertorLeavedto.leavedto(get);
    }


}
