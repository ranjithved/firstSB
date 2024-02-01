package com.example.firstSB.dto;

import com.example.firstSB.model.Employee;

import java.util.List;

public class EmployeeDto {

    private int count;

    private List<Employee> EmployeeList;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Employee> getEmployeeList() {
        return EmployeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        EmployeeList = employeeList;
    }
}
