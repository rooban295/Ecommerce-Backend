package com.ecom.E.commerce_backend.repository;

import com.ecom.E.commerce_backend.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {


    List<Product> findProductByCategoryId(Long id);


    @Query("SELECT p FROM Product p WHERE p.productName LIKE %:keyword% OR p.description LIKE %:keyword%")
    List<Product> searchProduct(@Param("keyword") String keyword);

}
