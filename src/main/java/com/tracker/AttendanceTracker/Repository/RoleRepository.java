package com.tracker.AttendanceTracker.Repository;

import com.tracker.AttendanceTracker.Entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Roles,Integer> {
}
