package com.example1.demo.service.impl;

import com.example1.demo.dto.EmployeeDto;
import com.example1.demo.entity.Employee;
import com.example1.demo.repository.EmployeeRepo;
import com.example1.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepo employeeRepo;

    @Override
    public EmployeeDto updateEmployee(EmployeeDto employeeDto) {
        Optional<Employee> update = employeeRepo.findById(employeeDto.getId());

        if (update.isPresent()) {
            Employee updated = employeeRepo.save(new Employee(employeeDto.getNic(), employeeDto.getName(), employeeDto.getAge(), employeeDto.getSalary()));
            return new EmployeeDto(updated.getId(), updated.getNic(), updated.getName(), updated.getAge(), updated.getSalary());
        }

        return null;
    }

    @Override
    public EmployeeDto getEmployeeByNic(String nic) {
        Optional<Employee> byNic = employeeRepo.findByNic(nic);

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
        List<Employee> employees = employeeRepo.findAll();

        return employees.stream()
                .map(employee -> new EmployeeDto(
                        employee.getId(),
                        employee.getNic(),
                        employee.getName(),
                        employee.getAge(),
                        employee.getSalary()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public boolean deleteEmployee(int id) {
        if (employeeRepo.existsById(id)) {
            employeeRepo.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        Employee save = employeeRepo.save(new Employee(employeeDto.getNic(), employeeDto.getName(), employeeDto.getAge(), employeeDto.getSalary()));
        return new EmployeeDto(save.getId(), save.getNic(), save.getName(), save.getAge(), save.getSalary());
    }
}
