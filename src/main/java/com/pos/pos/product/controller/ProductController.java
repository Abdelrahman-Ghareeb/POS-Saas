package com.pos.pos.product.controller;


import com.pos.pos.product.dto.ProductDTO;
import com.pos.pos.product.service.ProductService;
import com.pos.pos.store.dto.response.ApiResponse;
import com.pos.pos.user.entity.User;
import com.pos.pos.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final UserService userService;

    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO
                                                    ,@RequestHeader("Authorization") String jwt) throws Exception {
        // You can add authentication and authorization logic here using userService

        User user = userService.getUserFromToken(jwt);

        return ResponseEntity.ok(productService.createProduct(productDTO,user));
    }

    @GetMapping("/Store/{storeId}")
    public ResponseEntity<List<ProductDTO>> getStoreId(@PathVariable Long storeId
    ,@RequestHeader("Authorization") String jwt) {

        return ResponseEntity.ok(productService.getAllProductsByStoreId(storeId));

    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id,@RequestBody ProductDTO productDTO
            ,@RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.getUserFromToken(jwt);

        return ResponseEntity.ok(productService.updateProduct(id,productDTO,user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Long id, @RequestHeader("Authorization") String jwt) throws Exception {

        User user = userService.getUserFromToken(jwt);
        productService.deleteProduct(id,user);
        return ResponseEntity.ok(ApiResponse.builder().message("Product deleted successfully").build());
    }

    @GetMapping("/Store/{storeId}/search")
    public ResponseEntity<List<ProductDTO>> searchBtKeyword(@PathVariable Long storeId
                                                            ,@RequestParam String keyword
            ,@RequestHeader("Authorization") String jwt) {

        return ResponseEntity.ok(productService.getAllProductBySearchKeyword(keyword,storeId));

    }


}
