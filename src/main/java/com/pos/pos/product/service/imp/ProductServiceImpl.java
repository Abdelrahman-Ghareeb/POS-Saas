package com.pos.pos.product.service.imp;

import com.pos.pos.category.entity.Category;
import com.pos.pos.category.repo.CategoryRepo;
import com.pos.pos.product.dto.ProductDTO;
import com.pos.pos.product.entity.Product;
import com.pos.pos.product.mapper.ProductMapper;
import com.pos.pos.product.repo.ProductRepo;
import com.pos.pos.product.service.ProductService;
import com.pos.pos.store.entity.Store;
import com.pos.pos.store.repo.StoreRepo;
import com.pos.pos.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepo productRepository;
    private final StoreRepo storeRepository;
    private final CategoryRepo categoryRepo;
    @Override
    public ProductDTO createProduct(ProductDTO productDTO, User user) throws Exception {

        Store store = storeRepository.findById(productDTO.getStoreId())
                .orElseThrow(() -> new Exception("Store not found with id: " + productDTO.getStoreId()));


        Category category = categoryRepo.findById(productDTO.getCategoryId().getId())
                .orElseThrow(() -> new Exception("Category not found with id: " + productDTO.getCategoryId()));
        Product product =ProductMapper.mapToEntity(productDTO,store,category);
        Product savedProduct = productRepository.save(product);
        return ProductMapper.mapToDTO(savedProduct);

    }

    @Override
    public ProductDTO updateProduct(Long id, ProductDTO productDTO, User user) throws Exception {

        Product product = productRepository.findById(id).orElseThrow(() -> new Exception("Store not found with id: " ));


        if(productDTO.getCategoryId() != null){
            Category category = categoryRepo.findById(productDTO.getCategoryId().getId())
                    .orElseThrow(() -> new Exception("Category not found with id: " + productDTO.getCategoryId()));
            product.setCategory(category);
        }

        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setSellingPrice(productDTO.getSellingPrice());
        product.setBrand(product.getBrand());
        product.setMrp(product.getMrp());
        product.setImage(product.getImage());
        product.setSku(product.getSku());
        product.setUpdatedAt(LocalDateTime.now());
        Product savedProduct = productRepository.save(product);
        return ProductMapper.mapToDTO(savedProduct);

    }

    @Override
    public void deleteProduct(Long id, User user) {

        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        productRepository.delete(product);

    }

    @Override
    public List<ProductDTO> getAllProductsByStoreId(long storeId) {

        List<Product> products = productRepository.findByStoreId(storeId);
        return products.stream().map(ProductMapper::mapToDTO).collect(Collectors.toList());

    }

    @Override
    public List<ProductDTO> getAllProductBySearchKeyword(String keyword, long storeId) {

        List<Product> products = productRepository.searchByKeyword(storeId, keyword);
        return products.stream().map(ProductMapper::mapToDTO).collect(Collectors.toList());
    }
}
