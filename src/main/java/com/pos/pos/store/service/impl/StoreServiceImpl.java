package com.pos.pos.store.service.impl;

import com.pos.pos.store.dto.StoreDto;
import com.pos.pos.store.entity.Store;
import com.pos.pos.store.entity.StoreContent;
import com.pos.pos.store.entity.StoreStatus;
import com.pos.pos.store.mapper.StoreMapper;
import com.pos.pos.store.repo.StoreRepo;
import com.pos.pos.store.service.StoreService;
import com.pos.pos.user.entity.User;
import com.pos.pos.user.exception.UserException;
import com.pos.pos.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.StoreManager;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {


    private final UserService userService;
    private final StoreRepo storeRepository;

    @Override
    public StoreDto createStore(StoreDto storeDto, User user) {

        Store store = StoreMapper.mapToEntity(storeDto,user);
        return StoreMapper.mapToDTO(storeRepository.save(store));
    }

    @Override
    public StoreDto getStoreById(Long id) throws Exception {

        Store store = storeRepository.findById(id).orElseThrow(()-> new Exception("Store not found"));
        return StoreMapper.mapToDTO(store);
    }

    @Override
    public StoreDto updateStore(Long id, StoreDto storeDto) throws UserException {

        User currentUser = userService.getCurrentUser();
        Store existing = storeRepository.findByStoreAdminId(currentUser.getId());

        if(existing == null ){
            throw new UserException("You don't have access to this resource");
        }

        existing.setBrand(storeDto.getBrand());
        existing.setDescription(storeDto.getDescription());
        if(storeDto.getStoreType() != null){
            existing.setStoreType(storeDto.getStoreType());
        }
        if(storeDto.getStoreContent() != null){
            StoreContent oldContent = StoreContent.builder()
                    .address(storeDto.getStoreContent().getAddress())
                    .email(storeDto.getStoreContent().getEmail())
                    .phone(storeDto.getStoreContent().getPhone())

                    .build();
            existing.setStoreContent(oldContent);
        }
       Store updatedStore= storeRepository.save(existing);
        return StoreMapper.mapToDTO(updatedStore);
    }

    @Override
    public void deleteStore(Long id) throws UserException {

        Store store =getStoreByAdmin();
        storeRepository.delete(store);

    }

    @Override
    public List<StoreDto> getAllStores() {

        List<Store> stores = storeRepository.findAll();
        return stores.stream().map(StoreMapper::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public Store getStoreByAdmin() throws UserException {
        User currentUser = userService.getCurrentUser();
        return storeRepository.findByStoreAdminId(currentUser.getId());
    }

    @Override
    public StoreDto getStoreByEmployee() throws UserException {

        User currentUser = userService.getCurrentUser();
        if(currentUser == null){
            throw new UserException("You don't have access to this resource");
        }
        return StoreMapper.mapToDTO(currentUser.getStore());
    }

    @Override
    public StoreDto moderateStore(Long id, StoreStatus storeStatus) throws UserException {

        Store store = storeRepository.findById(id).orElseThrow(()-> new UserException("Store not found with id: "+ id));

        store.setStoreStatus(storeStatus);
        Store updatedStore = storeRepository.save(store);
        return StoreMapper.mapToDTO(updatedStore);
    }
}
