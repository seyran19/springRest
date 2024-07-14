package com.seirandzhafarov.spring.rest.controller;

import com.seirandzhafarov.spring.rest.entity.Employee;
import com.seirandzhafarov.spring.rest.exceptionHandling.EmployeeIncorrectData;
import com.seirandzhafarov.spring.rest.exceptionHandling.NoSuchEmployeeException;
import com.seirandzhafarov.spring.rest.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MyRestController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public List<Employee> showAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/employees/{id}")
    public Employee getEmployeeById(@PathVariable("id") int id) {

        Employee employee = employeeService.getEmployeeById(id);

        if (employee == null) {
            throw new NoSuchEmployeeException("There is no employee with id " + id);
        }

        return employee;
    }

    @PostMapping("/employees")
    public List<Employee> addNewEmployee(@RequestBody Employee employee) {
        employeeService.saveEmployee(employee);
        return employeeService.getAllEmployees();

    }

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee) {
        employeeService.saveEmployee(employee);
        return employee;
    }

    @DeleteMapping("employees/{id}")
    public String deleteEmployee(@PathVariable("id") int id) {
        Employee employee = employeeService.getEmployeeById(id);
        if (employee == null) {
            throw new NoSuchEmployeeException("There is no employee with id " + id);
        }

        employeeService.deleteEmployee(id);
        return "Deleted employee with id " + id;
    }

}
