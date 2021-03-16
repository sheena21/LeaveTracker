package com.tracker.AttendanceTracker.Controller;

import com.tracker.AttendanceTracker.Entity.LeaveClaims;
import com.tracker.AttendanceTracker.Entity.UserProfile;
import com.tracker.AttendanceTracker.Service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@PreAuthorize("hasAnyRole('USER','ADMIN','SUPER ADMIN')")
@RequestMapping("/profile")
public class UserProfileController {

    @Autowired
    private UserProfileService userProfileService;

    @PostMapping
    public ResponseEntity<?> addUser(@Valid @RequestBody UserProfile userProfile) {
        UserProfile userdash = userProfileService.addUser(userProfile);
        return new ResponseEntity<>(userdash, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAllUser() {
        List<UserProfile> list = userProfileService.getAllUser();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@Valid @PathVariable Integer id) {
        UserProfile getUser = userProfileService.getUserById(id);
        return new ResponseEntity<>(getUser, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@Valid @PathVariable Integer id, @Valid @RequestBody UserProfile userProfile) {
        UserProfile updated = userProfileService.updateUser(id, userProfile);
        return new ResponseEntity<>(updated, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")

        public ResponseEntity<?> delete(@Valid @PathVariable Integer id,@Valid @RequestBody UserProfile userProfile) {
            Object deleted = userProfileService.deleteUser(id,userProfile);
            return new ResponseEntity<>(deleted, HttpStatus.OK);
    }

}
