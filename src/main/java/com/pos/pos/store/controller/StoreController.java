package com.pos.pos.store.controller;

import com.pos.pos.store.dto.StoreDto;
import com.pos.pos.store.dto.response.ApiResponse;
import com.pos.pos.store.entity.StoreStatus;
import com.pos.pos.store.mapper.StoreMapper;
import com.pos.pos.store.service.StoreService;
import com.pos.pos.user.entity.User;
import com.pos.pos.user.exception.UserException;
import com.pos.pos.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/stores")
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;
    private final UserService userService;

    @PostMapping
    public ResponseEntity<StoreDto> createStore(
            @RequestBody StoreDto storeDto,@RequestHeader("Authorization") String token) throws UserException {
         User currentUser = userService.getUserFromToken(token);

        return ResponseEntity.ok(storeService.createStore(storeDto, currentUser));
    }

    @GetMapping
    public ResponseEntity<List<StoreDto>> createStore(
          @RequestHeader("Authorization") String token) throws UserException {

        return ResponseEntity.ok(storeService.getAllStores());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StoreDto> getStoreById(@PathVariable Long id) throws Exception {

        return ResponseEntity.ok(storeService.getStoreById(id));
    }

    @GetMapping("/admin")
    public ResponseEntity<StoreDto> getStoresByAdmin(
            @RequestHeader("Authorization") String token) throws UserException {
        return ResponseEntity.ok(StoreMapper.mapToDTO(storeService.getStoreByAdmin()));
     }

     @GetMapping("/employee")
     public ResponseEntity<StoreDto> getStoresByEmployee(
             @RequestHeader("Authorization") String token) throws UserException {
         return ResponseEntity.ok(storeService.getStoreByEmployee());
     }

     @PutMapping("/{id}")
     public ResponseEntity<StoreDto> updateStore(@PathVariable Long id,
                                                 @RequestBody StoreDto storeDto) throws UserException {
         return ResponseEntity.ok(storeService.updateStore(id, storeDto));
     }

    @PutMapping("/{id}/moderate")
    public ResponseEntity<StoreDto> moderateStore(@PathVariable Long id,
                                                @RequestParam StoreStatus storeStatus) throws UserException {
        return ResponseEntity.ok(storeService.moderateStore(id, storeStatus));
    }


     @DeleteMapping("/{id}")
     public ResponseEntity<ApiResponse> deleteStore(@PathVariable Long id,
                                                    @RequestHeader("Authorization") String token) throws UserException {

         storeService.deleteStore(id);
         return ResponseEntity.ok(new ApiResponse("Store deleted successfully"));
     }

}
