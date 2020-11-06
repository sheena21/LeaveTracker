package com.tracker.AttendanceTracker.Controller;
import com.tracker.AttendanceTracker.Entity.LeaveApply;
import com.tracker.AttendanceTracker.Entity.LeaveClaims;
import com.tracker.AttendanceTracker.Service.LeaveClaimsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@PreAuthorize("hasAnyRole('ADMIN','USER')")
@RequestMapping("/profile/leaveclaim")
public class LeaveClaimController {

    @Autowired
    private LeaveClaimsService leaveClaimsService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<LeaveClaims> getAll = leaveClaimsService.getAll();
        return new ResponseEntity<>(getAll, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@Valid @PathVariable Integer id) {
        LeaveClaims getbyid = leaveClaimsService.getById(id);
        return new ResponseEntity<>(getbyid, HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getByUserId(@Valid @PathVariable Integer id)
    {
        List<LeaveClaims> getByUserID=leaveClaimsService.getByUserId(id);
        return new ResponseEntity<>(getByUserID,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> saveClaim(@Valid @RequestBody LeaveClaims leaveClaims) {
        LeaveClaims saved = leaveClaimsService.saveClaim(leaveClaims);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @PathVariable Integer id, @Valid @RequestBody LeaveClaims leaveClaims) {
        LeaveClaims updated = leaveClaimsService.update(id, leaveClaims);
        return new ResponseEntity<>(updated, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@Valid @PathVariable Integer id) {
        LeaveClaims deleted = leaveClaimsService.delete(id);
        return new ResponseEntity<>(deleted, HttpStatus.OK);
    }
}
