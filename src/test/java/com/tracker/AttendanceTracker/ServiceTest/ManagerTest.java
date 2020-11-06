package com.tracker.AttendanceTracker.ServiceTest;

import com.tracker.AttendanceTracker.Entity.Manager;
import com.tracker.AttendanceTracker.Repository.ManagerRepositiory;
import com.tracker.AttendanceTracker.Service.ManagerService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ManagerTest {

    @MockBean
    private ManagerRepositiory managerRepositiory;

    @Autowired
    private ManagerService managerService;
    private Manager manager;
    @Before
    public void init()
    {
        manager=new Manager();
        manager.setStatus(true);
        manager.setDepartment("IT");
        manager.setManagerName("Mahima");
        when(managerRepositiory.save(manager)).thenReturn(manager);
    }
    @Test
    public void addManagerTest(){
        assertEquals(manager,managerService.addManager(manager));
    }
    @Test
    public void getAllManager(){
        when(managerRepositiory.findAll()).thenReturn(Collections.singletonList(manager));
        assertEquals(1, managerService.getAllManager().size());
    }
    @Test
    public void getByIdTest(){
        Integer id = 1;
        when(managerRepositiory.findById(id)).thenReturn(java.util.Optional.of(manager));
        assertEquals(manager.getId(), managerService.getById(id).getId());
    }
    @Test
    public void delete() {
        Integer id = 1;
        managerService.delete(id);
        verify(managerRepositiory, times(1)).deleteById(id);
    }
}
