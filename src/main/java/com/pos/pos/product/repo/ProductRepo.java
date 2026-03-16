package com.pos.pos.product.repo;

import com.pos.pos.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product, Long> {

    List<Product> findByStoreId(long storeId);

    @Query("select p from Product p where p.store = :storeId and (lower(p.name) like lower(concat('%', :query, '%')) " +
            "or lower(p.sku) like lower(concat('%', :query, '%')) or lower(p.brand) like lower(concat('%', :query, '%')))")
    List<Product> searchByKeyword(@Param("storeId") long storeId,
                                  @Param("query") String keyword);
}
