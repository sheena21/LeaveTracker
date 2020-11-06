package com.tracker.AttendanceTracker.Controller;

import com.tracker.AttendanceTracker.Entity.Roles;
import com.tracker.AttendanceTracker.Repository.RoleRepository;
import com.tracker.AttendanceTracker.Service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping
    public ResponseEntity<?> addRole(@Valid @RequestBody Roles roles)
    {
        Roles added=roleService.addRole(roles);
        return  new ResponseEntity<>(added, HttpStatus.CREATED);

    }
    @GetMapping
    public ResponseEntity<?> getRoles()
    {
        List<Roles> get=roleService.get();
        return new ResponseEntity<>(get,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRoleById(@Valid @PathVariable Integer id)
    {
        Roles getBy=roleService.getById(id);
        return new ResponseEntity<>(getBy,HttpStatus.ACCEPTED);
    }

}
