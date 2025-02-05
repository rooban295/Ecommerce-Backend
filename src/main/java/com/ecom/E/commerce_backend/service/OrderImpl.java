package com.ecom.E.commerce_backend.service;

import com.ecom.E.commerce_backend.model.*;
import com.ecom.E.commerce_backend.repository.AddressRepository;
import com.ecom.E.commerce_backend.repository.OrderItemRepository;
import com.ecom.E.commerce_backend.repository.OrderRepository;
import com.ecom.E.commerce_backend.request.OrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderImpl implements OrderService{


    @Autowired
    private OrderRepository orderRepo;

    @Autowired
    private OrderItemRepository orderItemRepo;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private CartService cartService;

    @Autowired
    private AddressRepository addressRepo;

    @Override
    public ResponseEntity<Order> addOrderItem(OrderRequest req, String jwt) throws Exception {

        System.out.println(req.getAddress());

        Address shippedAddress= addressRepo.save(req.getAddress());

        User user = userService.getUserByJwtToken(jwt); //find user by jwt

        List<CartItem> cartItems=cartService.getProductFromCart(jwt);//find List<CartItem> by jwt

        Order order = new Order(); //create a new order

        Cart cart = cartService.getCart(jwt).getBody(); //find cart by jwt

        order.setOrderStatus("PENDING");
        order.setDeliveryAddress(req.getAddress());
        order.setCustomer(user);
        order.setCreatedDate(LocalDate.now());
        order.setDeliveryDate(LocalDate.now().plusWeeks(1));
        order.setTotalAmount(cart.getCartTotal());
        order.setDeliveryAddress(shippedAddress);

        List<OrderItem> orderItem = new ArrayList<>();

        for(CartItem item:cartItems){

            OrderItem orderItems = new OrderItem();

            orderItems.setQuantity(item.getQuantity());
            orderItems.setProduct(item.getProduct());
            orderItems.setOrderItemTotal(item.getCartItemTotal());

            OrderItem savedOrderitem=orderItemRepo.save(orderItems);
            orderItem.add(savedOrderitem);
        }

        order.setOrderItem(orderItem);

        order.setTotalProduct((long) orderItem.size());


        return new ResponseEntity(orderRepo.save(order), HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<List<Order>> getOrder(String jwt) {

        User user = userService.getUserByJwtToken(jwt);

        List<Order> order= orderRepo.findByCustomerId(user.getId());

        return new ResponseEntity<>(order,HttpStatus.OK);
    }

    @Override
    public void deletedOrder(Long id) {
        orderRepo.deleteById(id);
    }
}
