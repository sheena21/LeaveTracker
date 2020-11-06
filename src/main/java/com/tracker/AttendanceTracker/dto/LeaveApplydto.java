package com.tracker.AttendanceTracker.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tracker.AttendanceTracker.Entity.Manager;
import com.tracker.AttendanceTracker.Entity.UserProfile;

public class LeaveApplydto {


    private Integer id;

    private Integer reqleave;
    @JsonIgnore
    private String reason;

    private String leavestatus;

    private Integer duration;

    private String empid;
    @JsonIgnore
    private Manager managerid;
    @JsonIgnore
    private UserProfile userid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmpid() {
        return empid;
    }

    public void setEmpid(String empid) {
        this.empid = empid;
    }

    public Integer getReqleave() {
        return reqleave;
    }

    public void setReqleave(Integer reqleave) {
        this.reqleave = reqleave;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getLeavestatus() {
        return leavestatus;
    }

    public void setLeavestatus(String leavestatus) {
        this.leavestatus = leavestatus;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Manager getManagerid() {
        return managerid;
    }

    public void setManagerid(Manager managerid) {
        this.managerid = managerid;
    }

    public UserProfile getUserid() {
        return userid;
    }

    public void setUserid(UserProfile userid) {
        this.userid = userid;
    }
}
