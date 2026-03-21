package com.pos.pos.employee.service;

import com.pos.pos.user.dtos.UserDto;
import com.pos.pos.user.entity.User;
import com.pos.pos.user.entity.UserRole;

import java.util.List;

public interface EmployeeService {
    UserDto createStoreEmployee(UserDto userDto, Long storeId) throws Exception;
    UserDto createBranchEmployee(UserDto userDto, Long branchId) throws Exception;
        UserDto updateEmployee(UserDto employeeDetails, Long employeeId) throws Exception;
        void deleteEmployee(Long employeeId) throws Exception;

        List<UserDto> findStoreEmployees(Long storeId, UserRole userRole) throws Exception;
    List<UserDto> findBranchEmployees(Long branchId, UserRole userRole) throws Exception;

}
