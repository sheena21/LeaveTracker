package com.tracker.AttendanceTracker.Service;

import com.tracker.AttendanceTracker.Entity.Manager;
import com.tracker.AttendanceTracker.Repository.ManagerRepositiory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManagerService {

    @Autowired
    private ManagerRepositiory managerRepositiory;

    public Manager addManager(Manager manager) {
        manager.setStatus(true);
        return managerRepositiory.save(manager);
    }

    public List<Manager> getAllManager() {
        return managerRepositiory.findAll();
    }

    public Manager getById(Integer id) {
        return managerRepositiory.findById(id).get();
    }

    public Manager delete(Integer id) {
        managerRepositiory.deleteById(id);
        return null;
    }

    public Manager updateManager(Integer id, Manager manager) {
        manager.setStatus(true);
        managerRepositiory.findById(id);
        return managerRepositiory.save(manager);
    }
}
