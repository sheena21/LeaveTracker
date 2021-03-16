package com.tracker.AttendanceTracker.Controller;

import com.tracker.AttendanceTracker.Entity.LeaveApply;
import com.tracker.AttendanceTracker.Service.LeaveService;
import com.tracker.AttendanceTracker.Service.ManagerApprovalService;
import com.tracker.AttendanceTracker.dto.LeaveApplydto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@PreAuthorize("hasAnyRole('MANAGER','ADMIN','SUPER ADMIN')")
@RequestMapping("/manager/approval")
public class ManagerApprovalController {

    @Autowired
    private ManagerApprovalService managerApprovalService;
    @Autowired
    private LeaveService leaveService;

    @PutMapping("/{id}")
    public ResponseEntity<?> approveLeave(@Valid @PathVariable Integer id, @Valid @RequestBody LeaveApply leaveApply) {
        LeaveApply approved = managerApprovalService.approvedleave(id, leaveApply);
        return new ResponseEntity<>(approved, HttpStatus.OK);
    }

    @GetMapping("/logstatus/{log}")
    public ResponseEntity<?> leaveinfo(@Valid @PathVariable String log) {
        List<LeaveApplydto> info = managerApprovalService.leaveinfo(log);
        return new ResponseEntity<>(info, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> viewById(@Valid @PathVariable Integer id) {
        LeaveApplydto getById = managerApprovalService.getById(id);
        return new ResponseEntity<>(getById, HttpStatus.OK);
    }

}
