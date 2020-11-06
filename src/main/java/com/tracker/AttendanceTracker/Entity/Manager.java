package com.tracker.AttendanceTracker.Entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Manager {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 100)
    @NotNull(message = "Maanager name should not be null...")
    private String managerName;

    @Column(length = 150)
    @NotNull(message = "Manager department should not be null...")
    private String department;
    private boolean status;

    public Manager() {
    }

    public boolean isStatus() {
        return status;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
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

    public String getManagerName() { return managerName; }

    public void setManagerName(String managerName) { this.managerName = managerName; }
}
