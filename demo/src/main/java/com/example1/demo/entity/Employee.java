package com.example1.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nic;
    private String name;
    private int age;
    private double salary;
    private String photoPath;


    public Employee(String nic, String name, int age, double salary) {
        this.nic = nic;
        this.name = name;
        this.age = age;
        this.salary = salary;
    }
}
