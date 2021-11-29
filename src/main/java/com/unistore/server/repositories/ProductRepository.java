package com.unistore.server.repositories;

import com.unistore.server.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(value = "SELECT * from product where Name = :name", nativeQuery = true)
    List<Product> findByNamee(@Param("name") String name);

    @Query(value = "SELECT * from Product ", nativeQuery = true)
    List<Product> findAllProduct();

    @Query(value = "SELECT * from  product LIMIT :offset , :limit", nativeQuery = true)
    List<Product> findByPage(@Param("limit") int limit, @Param("offset") int offset);
}
