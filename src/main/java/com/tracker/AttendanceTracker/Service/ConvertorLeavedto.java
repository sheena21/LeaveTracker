package com.tracker.AttendanceTracker.Service;

import com.tracker.AttendanceTracker.Entity.LeaveApply;
import com.tracker.AttendanceTracker.dto.LeaveApplydto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConvertorLeavedto {

    @Autowired
    private ModelMapper modelMapper;

    public LeaveApplydto leavedto(LeaveApply leaveApply){
        return modelMapper.map(leaveApply,LeaveApplydto.class);
    }
}
