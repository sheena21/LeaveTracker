package com.tracker.AttendanceTracker.Repository;


import com.tracker.AttendanceTracker.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    User getById(Long id);

    Optional<User> findById(Long id);

}
