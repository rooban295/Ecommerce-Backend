package com.ecom.E.commerce_backend.repository;


import com.ecom.E.commerce_backend.model.Cart;
import com.ecom.E.commerce_backend.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem,Long> {

    List<CartItem> findByCartId(Long id);

    List<CartItem>findByProductId(Long id); //working

}
