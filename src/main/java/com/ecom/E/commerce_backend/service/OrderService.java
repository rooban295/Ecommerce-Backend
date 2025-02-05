package com.ecom.E.commerce_backend.service;


import com.ecom.E.commerce_backend.model.Order;
import com.ecom.E.commerce_backend.model.OrderItem;
import com.ecom.E.commerce_backend.request.OrderRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OrderService {

    ResponseEntity<Order> addOrderItem(OrderRequest req, String jwt) throws Exception;

    ResponseEntity<List<Order>> getOrder(String jwt);

    void deletedOrder(Long id);
}
