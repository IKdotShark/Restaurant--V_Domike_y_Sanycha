package com.test_task.restaurant.controllers;

import com.test_task.restaurant.models.Staff;
import com.test_task.restaurant.services.StaffService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/staff")
public class StaffController {

    private final StaffService staffService;

    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Staff> getStaffById(@PathVariable Long id) {
        Staff staff = staffService.findStaffById(id);
        return ResponseEntity.ok(staff);
    }

    @GetMapping()
    public ResponseEntity<List<Staff>> getAllStaff() {
        List<Staff> staffList = staffService.findAllStaff();
        return ResponseEntity.ok(staffList);
    }

    @PostMapping()
    public ResponseEntity<Staff> createStaff(@RequestBody Staff staff) {
        Staff createdStaff = staffService.createStaff(staff);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdStaff);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Staff> updateStaff(@PathVariable Long id, @RequestBody Staff staffInfo) {
        Staff updatedStaff = staffService.updateStaff(id, staffInfo);
        return ResponseEntity.ok(updatedStaff);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteStaff(@PathVariable Long id) {
        staffService.deleteStaffById(id);
        return ResponseEntity.noContent().build();
    }
}
