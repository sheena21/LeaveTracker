package com.tracker.AttendanceTracker.Repository;

import com.tracker.AttendanceTracker.Entity.LeaveClaims;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaveClaimRepository extends JpaRepository<LeaveClaims,Integer> {

    List<LeaveClaims> findAllByUserid(Integer id);
}
