package com.example.EmployeeDetails.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tax {
    private String employeeCode;
    private String firstName;
    private String lastName;
    private double yearlySalary;
    private double taxAmount;
    private double cessAmount;



}
