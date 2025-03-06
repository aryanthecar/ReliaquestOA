package com.challenge.api.service;

import com.challenge.api.exception.EmployeeNotFoundException;
import com.challenge.api.model.CompanyEmployee;
import com.challenge.api.model.Employee;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;

/**
 * EmployeeService implementation for interactions with mock database
 */
@Service
public class EmployeeService {

    // Mock persistence layer using hashmap to represent key-value database in form of {UUID:Employee}
    private final HashMap<UUID, Employee> employeeDatabase = new HashMap<>();

    public EmployeeService() {
        // Create mock employees and add to database
        Employee e1 = new CompanyEmployee(
                "John", "Doe", "John Doe", 100000, 45, "Recruiter", "johndoe@company.com", Instant.now());
        Employee e2 =
                new CompanyEmployee("Jane", "Doe", "Jane Doe", 150000, 25, "Dev", "janedoe@company.com", Instant.now());

        e1.setUuid(UUID.randomUUID());
        e2.setUuid(UUID.randomUUID());
        employeeDatabase.put(e1.getUuid(), e1);
        employeeDatabase.put(e2.getUuid(), e2);
    }

    public Employee getEmployeeByUuid(UUID uuid) {
        Employee employee = employeeDatabase.get(uuid);

        // Check to ensure employee is found
        if (employee == null) {
            throw new EmployeeNotFoundException(uuid);
        }
        return employee;
    }

    public List<Employee> getAllEmployees() {
        return List.copyOf(employeeDatabase.values());
    }

    public Employee createEmployee(Employee employee) {
        // ensure new employee UUID is unique
        do {
            employee.setUuid(UUID.randomUUID());
        } while (employeeDatabase.containsKey(employee.getUuid()));

        // Add employee to database and return
        employeeDatabase.put(employee.getUuid(), employee);
        return employee;
    }
}
