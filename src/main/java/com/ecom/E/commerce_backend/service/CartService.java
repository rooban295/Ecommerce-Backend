package com.ecom.E.commerce_backend.service;

import com.ecom.E.commerce_backend.model.Cart;
import com.ecom.E.commerce_backend.model.CartItem;
import com.ecom.E.commerce_backend.request.AddToCartRequest;
import com.ecom.E.commerce_backend.request.UpdateCartItemRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CartService {

    List<CartItem> cartItems (Long id);

    List<CartItem> getProductFromCart(String jwt);

    ResponseEntity<CartItem> addToCart(AddToCartRequest req, String jwt) throws Exception;

    ResponseEntity<Cart> getCart(String jwt);

    ResponseEntity<String> deleteCartItem(Long id,String jwt) throws Exception;

    ResponseEntity<CartItem> updateCartItem(UpdateCartItemRequest req,String jwt) throws Exception;

}
