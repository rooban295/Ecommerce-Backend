package com.ecom.E.commerce_backend.repository;

import com.ecom.E.commerce_backend.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {

    List<Order> findByCustomerId(Long id);
}
