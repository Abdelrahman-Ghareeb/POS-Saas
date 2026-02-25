package com.pos.pos.store.service;


import com.pos.pos.store.dto.StoreDto;
import com.pos.pos.store.entity.Store;
import com.pos.pos.store.entity.StoreStatus;
import com.pos.pos.user.entity.User;
import com.pos.pos.user.exception.UserException;

import java.util.List;

public interface StoreService {


    StoreDto createStore(StoreDto storeDto, User user);
    StoreDto getStoreById(Long id) throws Exception;
    StoreDto updateStore(Long id, StoreDto storeDto) throws UserException;
    void deleteStore(Long id) throws UserException;
    List<StoreDto> getAllStores();

    Store getStoreByAdmin() throws UserException;
    StoreDto getStoreByEmployee() throws UserException;
    StoreDto moderateStore(Long id, StoreStatus storeStatus) throws UserException;

}
