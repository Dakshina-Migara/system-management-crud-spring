package com.example1.demo.service;

import com.example1.demo.dto.EmployeeDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface EmployeeService {
    EmployeeDto saveEmployee(EmployeeDto employeeDto);

    boolean deleteEmployee(int id);

    List<EmployeeDto> getAllEmployee();

    EmployeeDto getEmployeeByNic(String nic);

    EmployeeDto updateEmployee(int id,EmployeeDto employeeDto);

    EmployeeDto getEmployeeById(int id);

    String uploadPhoto(int id, MultipartFile file);

    byte[] getPhoto(int id);
}
