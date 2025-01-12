package com.test_task.restaurant.services;

import com.test_task.restaurant.Dto.EmployeeRequest;
import com.test_task.restaurant.exception.ResourceNotFoundException;
import com.test_task.restaurant.models.Dish;
import com.test_task.restaurant.models.Employee;
import com.test_task.restaurant.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee createEmployee(EmployeeRequest employeeRequest) {
        Employee employee = new Employee();
        if (employeeRequest.getEmployee() == null) {
            throw new RuntimeException("Employee must not be null");
        }

        Employee updatedEmployee = updatedEmployee(employee, employeeRequest);

        return employeeRepository.save(updatedEmployee);
    }

//    public List<Dish> findDishesByIds(List<Long> ids) {
//        List<Dish> dishes = new ArrayList<>();
//        for (Long id : ids) {
//            dishRepository.findById(id).ifPresent(dish -> {
//                if (dish.getIngredients() != null) {
//                    List<String> ingredientsList = Arrays.asList(dish.getIngredients().split(","));
//                    dish.setTransientIngredients(ingredientsList);
//                }
//                dishes.add(dish);
//            });
//        }
//        return dishes;
//    }

    public List<Employee> findEmployeesByIds(List<Long> ids) {
        List<Employee> employees = new ArrayList<>();
        for (Long id : ids) {
            employeeRepository.findById(id).ifPresent(employees::add);
        }
        return employees;
    }

    public Employee updateEmployee(Long id, EmployeeRequest employeeRequest) {
        Optional<Employee> foundEmployee = employeeRepository.findById(id);
        if (foundEmployee.isEmpty()) {
            throw new ResourceNotFoundException("");
        }

        Employee employee = foundEmployee.get();

        if (employeeRequest.getEmployee() != null) {
            Employee updatedEmployee = updatedEmployee(employee, employeeRequest);
            employee.setStaffRole(updatedEmployee.getStaffRole());
        }

        if (employeeRequest.getOrders() != null) {
            employee.setOrders(employeeRequest.getOrders());
        }

        return employeeRepository.save(employee);
    }

    private Employee updatedEmployee(Employee employee, EmployeeRequest employeeRequest) {
        switch (employeeRequest.getEmployee().toLowerCase()) {
            case "cooker":
                employee.setStaffRole(Employee.StaffRole.COOKER);
                break;
            case "waiter":
                employee.setStaffRole(Employee.StaffRole.WAITER);
                break;
            case "washer":
                employee.setStaffRole(Employee.StaffRole.WASHER);
                break;
            case "manager":
                employee.setStaffRole(Employee.StaffRole.MANAGER);
                break;
            case "barman":
                employee.setStaffRole(Employee.StaffRole.BARMAN);
                break;
            case "security":
                employee.setStaffRole(Employee.StaffRole.SECURITY);
                break;
            default:
                throw new ResourceNotFoundException("Not found such role: " + employeeRequest.getEmployee());
        }
        return employee;
    }

    public Employee findEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found employee with such id: " + id));
    }

    public List<Employee> findAllEmployees() {
        return employeeRepository.findAll();
    }

    public List<Employee> findEmployeesByRole(Employee.StaffRole role) {
        return employeeRepository.findByStaffRole(role);
    }

    public void deleteEmployeeById(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isEmpty()) throw new ResourceNotFoundException("Not found employee with such id: " + id);
        employeeRepository.deleteById(id);
    }
}
