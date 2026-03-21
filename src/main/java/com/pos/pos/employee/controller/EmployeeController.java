package com.pos.pos.employee.controller;

import com.pos.pos.employee.service.EmployeeService;
import com.pos.pos.store.dto.response.ApiResponse;
import com.pos.pos.user.dtos.UserDto;
import com.pos.pos.user.entity.User;
import com.pos.pos.user.entity.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;


    @PostMapping("/store/{storeId}")
    public ResponseEntity<UserDto> createStoreEmployee(@PathVariable Long storeId,  @RequestBody UserDto userDto) throws Exception {
        return ResponseEntity.ok(employeeService.createStoreEmployee(userDto,storeId));
    }

    @PostMapping("/branch/{branchId}")
    public ResponseEntity<UserDto> createBranchEmployee(@PathVariable Long branchId,  @RequestBody UserDto userDto) throws Exception {
        return ResponseEntity.ok(employeeService.createBranchEmployee(userDto,branchId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateEmployee(@PathVariable Long id,  @RequestBody UserDto userDto) throws Exception {
        return ResponseEntity.ok(employeeService.updateEmployee(userDto,id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteEmployee(@PathVariable Long id) throws Exception {
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok(ApiResponse.builder().message("Employee Deleted Successfully").build());
    }

    @GetMapping("/store/{id}")
    public ResponseEntity<List<UserDto>> getStoreEmployees(@PathVariable Long id, @RequestParam(required = false) UserRole userRole) throws Exception {
        return ResponseEntity.ok(employeeService.findStoreEmployees(id,userRole));
    }

    @GetMapping("/branch/{id}")
    public ResponseEntity<List<UserDto>> getBranchEmployees(@PathVariable Long id, @RequestParam(required = false) UserRole userRole) throws Exception {
        return ResponseEntity.ok(employeeService.findBranchEmployees(id,userRole));
    }
}
