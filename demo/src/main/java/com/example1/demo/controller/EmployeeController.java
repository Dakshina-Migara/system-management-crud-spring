package com.example1.demo.controller;

import com.example1.demo.dto.EmployeeDto;
import com.example1.demo.service.EmployeeService;
import com.example1.demo.service.impl.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @PostMapping("/save")
    public ResponseEntity<EmployeeDto> saveEmployee(@RequestBody EmployeeDto employeeDto) {
        EmployeeDto savedEmployee = employeeService.saveEmployee(employeeDto);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteEmployee(@PathVariable int id) {
        boolean deleted = employeeService.deleteEmployee(id);
        return new ResponseEntity<>(deleted, HttpStatus.CREATED);
    }


    @PutMapping("/update")
    public ResponseEntity<EmployeeDto> updateEmployee(@RequestBody EmployeeDto employeeDto) {
        EmployeeDto updatedEmployee = employeeService.updateEmployee(employeeDto);
        return new ResponseEntity<>(updatedEmployee, HttpStatus.CREATED);
    }


    @GetMapping("/getAll")
    public ResponseEntity<List<EmployeeDto>> getAllEmployee() {
        List<EmployeeDto> getAllEmployee = employeeService.getAllEmployee();
        return new ResponseEntity<>(getAllEmployee, HttpStatus.CREATED);
    }


    @GetMapping("/getByNic/{nic}")
    public ResponseEntity<EmployeeDto> getEmployeeByNic(@PathVariable String nic) {
        EmployeeDto getEmployeeByNic = employeeService.getEmployeeByNic(nic);
        return new ResponseEntity<>(getEmployeeByNic, HttpStatus.CREATED);
    }


    @GetMapping("/getById{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable int id) {
        EmployeeDto getEmployeeById = employeeService.getEmployeeById(id);
        return new ResponseEntity<>(getEmployeeById, HttpStatus.CREATED);
    }

    @PostMapping("/uploadPhoto/{id}")
    public ResponseEntity<String> uploadPhoto(@PathVariable int id, @RequestParam("file") MultipartFile file) {
        String path = employeeService.uploadPhoto(id, file);
        return ResponseEntity.ok("Photo uploaded: " + path);
    }

    @GetMapping("/photo/{id}")
    public ResponseEntity<ByteArrayResource> getPhoto(@PathVariable int id) {
        byte[] data = employeeService.getPhoto(id);
        ByteArrayResource resource = new ByteArrayResource(data);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=uploads/")
                .contentType(MediaType.IMAGE_JPEG)
                .contentLength(data.length)
                .body(resource);
    }
}
