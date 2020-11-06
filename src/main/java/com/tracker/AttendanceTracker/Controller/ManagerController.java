package com.tracker.AttendanceTracker.Controller;

import com.tracker.AttendanceTracker.Entity.Manager;
import com.tracker.AttendanceTracker.Service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.Valid;
import java.util.List;

@RestController
@PreAuthorize("hasAnyRole('MANAGER','ADMIN')")
@RequestMapping("/manager/profile")
public class ManagerController {

    @Autowired
    private ManagerService managerService;

   @PostMapping
    public ResponseEntity<?> addManager(@Valid @RequestBody Manager manager)
   {
       Manager added=managerService.addManager(manager);
       return  new ResponseEntity<>(added, HttpStatus.CREATED);
   }
   @GetMapping
    public ResponseEntity<?> getManager()
    {
        List<Manager> getall=managerService.getAllManager();
        return new ResponseEntity<>(getall,HttpStatus.ACCEPTED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@Valid @PathVariable Integer id)
    {
        Manager getbyid=managerService.getById(id);
        return  new ResponseEntity<>(getbyid,HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateManager(@Valid @PathVariable Integer id, @Valid @RequestBody Manager manager)
    {
        Manager updated=managerService.updateManager(id,manager);
        return  new ResponseEntity<>(updated,HttpStatus.ACCEPTED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteManager(@Valid @PathVariable Integer id)
    {
        Manager deleted=managerService.delete(id);
        return  new ResponseEntity<>(deleted,HttpStatus.ACCEPTED);
    }


}
