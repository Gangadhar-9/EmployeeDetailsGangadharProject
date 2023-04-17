package com.example.EmployeeDetails.Service;

import com.example.EmployeeDetails.Entity.EmployeeEntity;
import com.example.EmployeeDetails.Model.Tax;
import com.example.EmployeeDetails.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public EmployeeEntity saveEmployee(EmployeeEntity employee) {
        return employeeRepository.save(employee);
    }


    public Tax calculateTax(EmployeeEntity employee) {

        LocalDate doj = LocalDate.parse(employee.getDoj());

        // Check if the employee joined before April
        if (doj.isBefore(LocalDate.of(doj.getYear(), Month.APRIL, 1))) {
            doj = LocalDate.of(doj.getYear() + 1, Month.APRIL, 1);
        } else {
            doj = LocalDate.of(doj.getYear(), Month.APRIL, 1);
        }

        double totalSalary = employee.getSalary() * (12 - doj.getMonthValue() + 1);

        double lossOfPayPerDay = employee.getSalary() / 30;
        int numDaysWorked = Month.APRIL.length(doj.isLeapYear()) - doj.getDayOfMonth() + 1;
        if (numDaysWorked < 30) {
            totalSalary -= lossOfPayPerDay * (30 - numDaysWorked);
        }

        double taxAmount = 0;
        if (totalSalary > 250000 && totalSalary <= 500000) {
            taxAmount = (totalSalary - 250000) * 0.05;
        } else if (totalSalary > 500000 && totalSalary <= 1000000) {
            taxAmount = 12500 + (totalSalary - 500000) * 0.1;
        } else if (totalSalary > 1000000) {
            taxAmount = 112500 + (totalSalary - 1000000) * 0.2;
        }

        double cessAmount = totalSalary > 2500000 ? (totalSalary - 2500000) * 0.02 : 0;

        return new Tax(String.valueOf(employee.getId()), employee.getFirstName(), employee.getLastName(), totalSalary, taxAmount, cessAmount);
    }


    public List<EmployeeEntity> getAllEmployees() {
        List<EmployeeEntity> e = employeeRepository.findAll();
        System.out.println(e.size());
       for(EmployeeEntity ee:e)
           System.out.println("f: "+e);
        return employeeRepository.findAll();
    }
}
