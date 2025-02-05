package com.ecom.E.commerce_backend.controller;


import com.ecom.E.commerce_backend.model.Order;
import com.ecom.E.commerce_backend.request.OrderRequest;
import com.ecom.E.commerce_backend.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/placeorder")
    public ResponseEntity<Order>  placeOrder(@RequestBody OrderRequest orderRequest, @RequestHeader("Authorization") String jwt) throws Exception {

        return orderService.addOrderItem(orderRequest,jwt);
    }

    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getOrder(@RequestHeader("Authorization") String jwt){
        return orderService.getOrder(jwt);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteProduct(@PathVariable Long id){
        orderService.deletedOrder(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
