package com.tracker.AttendanceTracker.Service;

import com.tracker.AttendanceTracker.Entity.Roles;
import com.tracker.AttendanceTracker.Repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Roles addRole(Roles roles) {
      return   roleRepository.save(roles);
    }

    public List<Roles> get() {
        return roleRepository.findAll();
    }

    public Roles getById(Integer id) {
        return roleRepository.findById(id).get();

    }
}
