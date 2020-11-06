package com.tracker.AttendanceTracker.Repository;

import com.tracker.AttendanceTracker.Entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerRepositiory extends JpaRepository<Manager,Integer> {
}
