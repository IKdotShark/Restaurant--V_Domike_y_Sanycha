package com.test_task.restaurant.services;

import com.test_task.restaurant.exception.ResourceNotFoundException;
import com.test_task.restaurant.models.Staff;
import com.test_task.restaurant.repositories.StaffRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StaffService {

    private final StaffRepository staffRepository;

    public StaffService(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    public Staff createStaff(Staff staff) {
        return staffRepository.save(staff);
    }

    public Staff findStaffById(Long id) {
        return staffRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found staff with such id: " + id));
    }

    public List<Staff> findAllStaff() {
        return staffRepository.findAll();
    }

    public void deleteStaffById(Long id) {
        Optional<Staff> staff = staffRepository.findById(id);
        if (staff.isEmpty()) throw new ResourceNotFoundException("Not found staff with such id: " + id);
        staffRepository.deleteById(id);
    }
}
