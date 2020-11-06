package com.tracker.AttendanceTracker.Entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class LeaveClaims {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 100)
    @NotNull(message = "Enter leave type either personal or official")
    private String leaveType;



    @Column(nullable = false)
    private Integer amount;
    @Column(length = 200)
    private Integer reimburseAmount;

    @Column(length = 200)
    private String remark;

    private Integer userid;

    @OneToOne
    @JoinColumn(name="leaveId")
    private LeaveApply leaveId;

    public LeaveClaims() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getReimburseAmount() {
        return reimburseAmount;
    }

    public void setReimburseAmount(Integer reimburseAmount) {
        this.reimburseAmount = reimburseAmount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public LeaveApply getLeaveId() {
        return leaveId;
    }

    public void setLeaveId(LeaveApply leaveId) {
        this.leaveId = leaveId;
    }
}
