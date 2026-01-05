package com.example1.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class EmployeeDto {
    private Integer id;
    private String nic;
    private String name;
    private int age;
    private double salary;
}
