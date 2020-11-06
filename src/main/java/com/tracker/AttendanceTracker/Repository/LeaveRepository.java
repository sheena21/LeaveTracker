package com.tracker.AttendanceTracker.Repository;

import com.tracker.AttendanceTracker.Entity.LeaveApply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaveRepository extends JpaRepository<LeaveApply, Integer> {

    LeaveApply findByUserId(Integer id);

    List<LeaveApply> findAllByLeaveStatus(String status);


    /*
    @Query("FROM LeaveApply  WHERE UPPER(leavestatus)=UPPER(:status)")
    List<LeaveApply> findByLeavestatus(@Param("status") String leavestatus);
     */
}
