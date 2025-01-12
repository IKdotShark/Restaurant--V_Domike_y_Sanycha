package com.test_task.restaurant.services;

import com.test_task.restaurant.exception.ResourceNotFoundException;
import com.test_task.restaurant.models.Employee;
import com.test_task.restaurant.models.Staff;
import com.test_task.restaurant.repositories.EmployeeRepository;
import com.test_task.restaurant.repositories.StaffRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StaffService {

    private final StaffRepository staffRepository;
    private final EmployeeRepository employeeRepository;

    public StaffService(StaffRepository staffRepository, EmployeeRepository employeeRepository) {
        this.staffRepository = staffRepository;
        this.employeeRepository = employeeRepository;
    }

    public Staff createStaff(Staff staff) {
        Optional<Employee> employee = employeeRepository.findById(staff.getEmployee().getId());
        if (employee.isEmpty()) {
            throw new ResourceNotFoundException("Not found such employee");
        }
        staff.setEmployee(employee.get());
        return staffRepository.save(staff);
    }

    public Staff updateStaff(Long id, Staff staffInfo) {
        Staff existingStaff = findStaffById(id);

        if (staffInfo.getName() != null) {
            existingStaff.setName(staffInfo.getName());
        }
        if (staffInfo.getSurName() != null) {
            existingStaff.setSurName(staffInfo.getSurName());
        }
        if (staffInfo.getPhoneNumber() != null) {
            existingStaff.setPhoneNumber(staffInfo.getPhoneNumber());
        }
        if (staffInfo.getEmployee() != null) {
            existingStaff.setEmployee(staffInfo.getEmployee());
        }

        return staffRepository.save(existingStaff);
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
