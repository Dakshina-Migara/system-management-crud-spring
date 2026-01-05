package com.example1.demo.service.impl;

import com.example1.demo.dto.EmployeeDto;
import com.example1.demo.entity.Employee;
import com.example1.demo.repository.EmployeeRepo;
import com.example1.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepo employeeRepo;

    @Override
    public EmployeeDto updateEmployee(EmployeeDto employeeDto) {
        return employeeDto;
    }

    @Override
    public EmployeeDto getEmployeeByNic(String nic) {
        String<Employee> byNic = employeeRepo.(nic);
        if (byNic.isPresent()) {
            Employee employee = byNic.get();
            return new EmployeeDto(employee.getId(), employee.getNic(), employee.getName(), employee.getAge(), employee.getSalary());
        }
        return null;
    }

    @Override
    public EmployeeDto getEmployeeById(int id) {
        Optional<Employee> byId = employeeRepo.findById(id);
        if (byId.isPresent()) {
            Employee employee = byId.get();
            return new EmployeeDto(employee.getId(), employee.getNic(), employee.getName(), employee.getAge(), employee.getSalary());
        }
        return null;
    }

    @Override
    public List<EmployeeDto> getAllEmployee() {
        return List.of(
                new EmployeeDto(1, "200300903343", "Migara", 23, 2000),
                new EmployeeDto(2, "200300903344", "Kamal", 25, 3000),
                new EmployeeDto(3, "200300903345", "Nimal", 28, 3500)
        );
    }

    @Override
    public boolean deleteEmployee(int id) {
        return true;
    }

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        Employee save = employeeRepo.save(new Employee(employeeDto.getNic(), employeeDto.getName(), employeeDto.getAge(), employeeDto.getSalary()));
        return new EmployeeDto(save.getId(), save.getNic(), save.getName(), save.getAge(), save.getSalary());
    }
}
