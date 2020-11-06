package com.tracker.AttendanceTracker.Entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "LeaveApply")
public class LeaveApply {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 50, nullable = false)
    private Integer reqLeave;

    @Column(length = 100)
    @NotNull(message = "Mention the reason for leave....")
    private String reason;

    private String empId;

    @Column(length = 50)
    private String leaveStatus;

    @Column(length = 5)
    @NotNull(message = "Enter duration of leave...")
    private Integer duration;

    @OneToOne
    @JoinColumn(name = "managerid")
    private Manager managerId;

    @ManyToOne
    @JoinColumn(name = "userid")
    private UserProfile userId;

    public LeaveApply() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getReqLeave() {
        return reqLeave;
    }

    public void setReqLeave(Integer reqLeave) {
        this.reqLeave = reqLeave;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getLeaveStatus() {
        return leaveStatus;
    }

    public void setLeaveStatus(String leaveStatus) {
        this.leaveStatus = leaveStatus;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Manager getManagerId() {
        return managerId;
    }

    public void setManagerId(Manager managerId) {
        this.managerId = managerId;
    }

    public UserProfile getUserId() {
        return userId;
    }

    public void setUserId(UserProfile userId) {
        this.userId = userId;
    }
}
