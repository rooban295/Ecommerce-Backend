package com.ecom.E.commerce_backend.repository;

import com.ecom.E.commerce_backend.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {


    Cart findByUserId(Long userId);
}
