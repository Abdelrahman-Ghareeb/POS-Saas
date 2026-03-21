package com.pos.pos.employee.service.impl;

import com.pos.pos.branch.entity.Branch;
import com.pos.pos.branch.repo.BranchRepo;

import com.pos.pos.employee.service.EmployeeService;
import com.pos.pos.store.entity.Store;
import com.pos.pos.store.repo.StoreRepo;
import com.pos.pos.user.dtos.UserDto;
import com.pos.pos.user.entity.User;
import com.pos.pos.user.entity.UserRole;
import com.pos.pos.user.mapper.UserMapper;
import com.pos.pos.user.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final UserRepository userRepository;
    private final BranchRepo branchRepo;
    private final StoreRepo storeRepo;
    private final PasswordEncoder passwordEncoder;
    @Override
    public UserDto createStoreEmployee(UserDto employee, Long storeId) throws Exception {

        Store store = storeRepo.findById(storeId).orElseThrow(() -> new Exception("Store not found with id: " + storeId));

        Branch branch =null;


        if(employee.getRole()==UserRole.ROLE_BRANCH_MANAGER){
            if(employee.getBranchId()==null){
                throw new Exception("Branch ID is required for branch manager");
            }
            branch = branchRepo.findById(employee.getBranchId()).orElseThrow(() -> new Exception("Branch not found with id: " + employee.getBranchId()));
        }

        User user = UserMapper.toEntity(employee);
        user.setStore(store);
        user.setBranch(branch);
        user.setPassword(passwordEncoder.encode(employee.getPassword())); // Set a default password or generate one

        User savedUser = userRepository.save(user);
        if(employee.getRole()==UserRole.ROLE_BRANCH_MANAGER && branch!=null){
            branch.setManager(savedUser);
            branchRepo.save(branch);
        }

        return UserMapper.mapToDTO(savedUser);
    }

    @Override
    public UserDto createBranchEmployee(UserDto employee, Long branchId) throws Exception {

        Branch branch = branchRepo.findById(branchId).orElseThrow(() -> new Exception("Branch not found with id: " + branchId));

        if(employee.getRole()==UserRole.ROLE_BRANCH_MANAGER || employee.getRole()==UserRole.ROLE_BRANCH_CASHIER){

            User user = UserMapper.toEntity(employee);
            user.setBranch(branch);
            user.setPassword(passwordEncoder.encode(employee.getPassword())); // Set a default password or generate one
            return UserMapper.mapToDTO(user);
        }
        throw new  Exception("Branch are not allowed to have employees with role: " + employee.getRole());
    }

    @Override
    public UserDto updateEmployee(UserDto employeeDetails, Long employeeId) throws Exception {

        User existingEmployee = userRepository.findById(employeeId).orElseThrow(() -> new Exception("Employee not found with id: " + employeeId));

        Branch branch = branchRepo.findById(employeeDetails.getBranchId()).orElseThrow(() -> new Exception("Branch not found with id: " + employeeDetails.getBranchId()));
        existingEmployee.setBranch(branch);
        existingEmployee.setFullName(employeeDetails.getFullName());
        existingEmployee.setEmail(employeeDetails.getEmail());
        existingEmployee.setPhone(employeeDetails.getPhone());
        existingEmployee.setRole(employeeDetails.getRole());
        existingEmployee.setPassword(passwordEncoder.encode(employeeDetails.getPassword()));




        return UserMapper.mapToDTO(userRepository.save(existingEmployee));
    }

    @Override
    public void deleteEmployee(Long employeeId) throws Exception {
        User existingEmployee = userRepository.findById(employeeId).orElseThrow(() -> new Exception("Employee not found with id: " + employeeId));

        userRepository.delete(existingEmployee);

    }

    @Override
    public List<UserDto> findStoreEmployees(Long branchId, UserRole userRole) throws Exception {

        Store store = storeRepo.findById(branchId).orElseThrow(() -> new Exception("Store not found for branch id: " + branchId));

        return userRepository.findByStore(store).stream().filter(user -> userRole==null || user.getRole()==userRole)
                .map(UserMapper::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDto> findBranchEmployees(Long branchId, UserRole userRole) throws Exception {

        Branch branch = branchRepo.findById(branchId).orElseThrow(() -> new Exception("Branch not found with id: " + branchId));

        return userRepository.findByBranchId(branchId)
                .stream().filter(user -> userRole==null || user.getRole()==userRole)
                .map(UserMapper::mapToDTO)
                .collect(Collectors.toList());

    }
}
