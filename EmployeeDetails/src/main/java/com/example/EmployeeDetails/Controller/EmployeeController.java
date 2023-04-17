package com.example.EmployeeDetails.Controller;

import com.example.EmployeeDetails.Entity.EmployeeEntity;
import com.example.EmployeeDetails.Model.Tax;
import com.example.EmployeeDetails.Service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;


@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    

    @PostMapping("/saveEmployee")
    public ResponseEntity<?> saveEmployee(@Valid @RequestBody EmployeeEntity employee, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        EmployeeEntity emp = employeeService.saveEmployee(employee);
        return new ResponseEntity<>(emp, HttpStatus.CREATED);
    }

    @GetMapping("/employees/tax")
    public ResponseEntity<List<Tax>> getEmployeesTax() {
        List<Tax> employeesTax = employeeService.getAllEmployees().stream()
                .map(employee -> employeeService.calculateTax(employee))
                .collect(Collectors.toList());
        return new ResponseEntity<>(employeesTax, HttpStatus.OK);
    }


}
