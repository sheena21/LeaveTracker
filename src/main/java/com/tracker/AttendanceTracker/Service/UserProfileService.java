package com.tracker.AttendanceTracker.Service;

import com.tracker.AttendanceTracker.Entity.UserProfile;
import com.tracker.AttendanceTracker.Repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserProfileService {

    @Autowired
    private UserProfileRepository userProfileRepository;

    public UserProfile addUser(UserProfile userProfile) {

        userProfile.setStatus(true);
        return userProfileRepository.save(userProfile);
    }

    public List<UserProfile> getAllUser() {
        return userProfileRepository.findAll();
    }

    public UserProfile updateUser(Integer id, UserProfile userProfile) {
        userProfileRepository.findById(id);
        userProfile.setStatus(true);
        return userProfileRepository.save(userProfile);
    }

    public Object deleteUser(Integer id, UserProfile userProfile) {
        Optional<UserProfile> optional = userProfileRepository.findById(id);
        if (optional.isPresent()) {
            userProfile = optional.get();
            userProfile.setStatus(false);
         return    userProfileRepository.save(userProfile);

        } else {
            return  "User not exist.Please Register first !!";
        }

    }

    public UserProfile getUserById(Integer id) {
        return userProfileRepository.findById(id).get();
    }
}
