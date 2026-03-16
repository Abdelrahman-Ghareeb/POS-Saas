package com.pos.pos.product.service;

import com.pos.pos.product.dto.ProductDTO;
import com.pos.pos.user.entity.User;

import java.util.List;

public interface ProductService {

     ProductDTO createProduct(ProductDTO productDTO,User user) throws Exception;

     ProductDTO updateProduct(Long id, ProductDTO productDTO, User user) throws Exception;

     void  deleteProduct(Long id, User user);
     List<ProductDTO> getAllProductsByStoreId(long storeId);
     List<ProductDTO> getAllProductBySearchKeyword(String keyword, long storeId);


}
