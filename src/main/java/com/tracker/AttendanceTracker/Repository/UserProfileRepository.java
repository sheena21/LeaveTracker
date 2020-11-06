package com.tracker.AttendanceTracker.Repository;

import com.tracker.AttendanceTracker.Entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Integer> {

    UserProfile getById(Integer id);
}
