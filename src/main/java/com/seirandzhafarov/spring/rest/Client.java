package com.seirandzhafarov.spring.rest;


import com.seirandzhafarov.spring.rest.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class Client {

    @Autowired
    private RestTemplate restTemplate;

    private String URL = "http://localhost:8080/SpringRest/api/employees";

    public List<Employee> getAllEmployees() {
        ResponseEntity<List<Employee>> response = restTemplate.exchange(URL, HttpMethod.GET,
                null, new ParameterizedTypeReference<List<Employee>>() {});

        return response.getBody();
    }

    public Employee getEmployeeById(int id) {

        return restTemplate.getForObject(URL + "/{id}", Employee.class, id);
    }

    public void saveEmployee(Employee employee) {

        int id = employee.getId();

        if (id == 0) {
           ResponseEntity<String> response =
                   restTemplate.postForEntity(URL, employee, String.class);
            System.out.println("Employee saved with id " + id);
            System.out.println(response.getBody());
        }
        else {
            restTemplate.put(URL, employee);
            System.out.println("Employee updated with id " + id);
        }
    }

    public void deleteEmployee(int id) {

        restTemplate.delete(URL + "/{id}", id);
        System.out.println("Employee deleted with id " + id);
    }
}
