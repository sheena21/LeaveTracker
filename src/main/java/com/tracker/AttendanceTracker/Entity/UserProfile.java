package com.tracker.AttendanceTracker.Entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 20, unique = true, updatable = true)
    @NotNull(message = "Employee Id should be in the format of E(digits)")
    private String empId;

    @Column(length = 50, updatable = true)
    @NotNull(message = "Enter whether on prohibition or fulltime employee")
    private String empType;

    @Column(length = 200, insertable = true)
    @NotNull(message = "Enter your current address")
    private String address;

    @Column(length = 15, unique = true)
    @NotNull(message = "Enter your phone No.")
    private String phoneNo;

    @Column(length = 20, updatable = false)
    @NotNull(message = "Format should be dd/mm/yy or dd-mm-yy")
    private String DOJ;

    @Column(length = 50)
    @NotNull(message = "Enter paid leaves")
    private Integer paidLeave;

    @Column(length = 50)
    @NotNull(message = "Enter employee department...")
    private String empDep;

    @OneToOne()
    @JoinColumn(name = "userId")
    private User user;

    @OneToOne()
    @JoinColumn(name = "managerId")
    private Manager manager;

    @Column(length = 100)
    @NotNull(message = "Enter your role...")
    private String designation;

    @Column(length = 100)
    @NotNull(message = "Name should not be null..")
    private String empName;

    private boolean status;

    public UserProfile() {
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getDOJ() {
        return DOJ;
    }

    public void setDOJ(String DOJ) {
        this.DOJ = DOJ;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getEmpType() {
        return empType;
    }

    public void setEmpType(String empType) {
        this.empType = empType;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public Integer getPaidLeave() {
        return paidLeave;
    }

    public void setPaidLeave(Integer paidLeave) {
        this.paidLeave = paidLeave;
    }

    public String getEmpDep() {
        return empDep;
    }

    public void setEmpDep(String empDep) {
        this.empDep = empDep;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }
}
