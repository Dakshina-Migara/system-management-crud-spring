package com.example1.demo.service.impl;

import com.example1.demo.dto.EmployeeDto;
import com.example1.demo.entity.Employee;
import com.example1.demo.repository.EmployeeRepo;
import com.example1.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
            return new EmployeeDto(updated.getId(), updated.getNic(), updated.getName(), updated.getAge(), updated.getSalary(), updated.getPhotoPath());
        }
        return null;
    }

    @Override
    public EmployeeDto getEmployeeByNic(String nic) {
        Optional<Employee> byNic = employeeRepo.findByNic(nic);

        if (byNic.isPresent()) {
            Employee employee = byNic.get();
            return new EmployeeDto(employee.getId(), employee.getNic(), employee.getName(), employee.getAge(), employee.getSalary(), employee.getPhotoPath());
        }
        return null;
    }

    @Override
    public EmployeeDto getEmployeeById(int id) {
        Optional<Employee> byId = employeeRepo.findById(id);
        if (byId.isPresent()) {
            Employee employee = byId.get();
            return new EmployeeDto(employee.getId(), employee.getNic(), employee.getName(), employee.getAge(), employee.getSalary(), employee.getPhotoPath());
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
                        employee.getSalary(),
                        employee.getPhotoPath()
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
        return new EmployeeDto(save.getId(), save.getNic(), save.getName(), save.getAge(), save.getSalary(), save.getPhotoPath());
    }

    @Override
    public String uploadPhoto(int id, MultipartFile file) {
        try {
            Employee employee = employeeRepo.findById(id)
                    .orElseThrow(() -> new RuntimeException("Employee not found with ID: " + id));
            String uploadDir = "uploads/";
            Files.createDirectories(Paths.get(uploadDir));
            Path filePath = Paths.get(uploadDir + file.getOriginalFilename());
            Files.write(filePath, file.getBytes());
            employee.setPhotoPath(filePath.toString());
            employeeRepo.save(employee);

            return filePath.toString();
        } catch (IOException e) {
            throw new RuntimeException("Failed to upload file: " + e.getMessage());
        }
    }

    @Override
    public byte[] getPhoto(int id) {
        try {
            Employee employee = employeeRepo.findById(id)
                    .orElseThrow(() -> new RuntimeException("Employee not found with ID: " + id));
            if (employee.getPhotoPath() == null) throw new RuntimeException("No photo uploaded");
            Path path = Paths.get(employee.getPhotoPath());
            return Files.readAllBytes(path);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read photo: " + e.getMessage());
        }
    }

}
