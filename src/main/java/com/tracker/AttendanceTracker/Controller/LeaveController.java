package com.tracker.AttendanceTracker.Controller;

import com.tracker.AttendanceTracker.Entity.LeaveApply;
import com.tracker.AttendanceTracker.Entity.User;
import com.tracker.AttendanceTracker.Service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@PreAuthorize("hasAnyRole('USER','MANAGER')")
@RequestMapping("/profile/leave")
public class LeaveController {

    @Autowired
    private LeaveService leaveService;

    @PostMapping
    public ResponseEntity<?> requiredleave(@Valid @RequestBody LeaveApply leaveApply) {
        LeaveApply added = leaveService.requiredleave(leaveApply);
        return new ResponseEntity<>(added, HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<?> leaveinfo() {
        List<LeaveApply> info = leaveService.leaveinfo();
        return new ResponseEntity<>(info, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> viewById(@Valid @PathVariable Integer id) {
        LeaveApply getById = leaveService.getById(id);
        return new ResponseEntity<>(getById, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@Valid @PathVariable Integer id) {
        LeaveApply deleted = leaveService.delete(id);
        return new ResponseEntity<>(deleted, HttpStatus.OK);
    }
}
