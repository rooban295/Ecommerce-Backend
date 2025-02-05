package com.ecom.E.commerce_backend.controller;


import com.ecom.E.commerce_backend.model.Cart;
import com.ecom.E.commerce_backend.model.CartItem;
import com.ecom.E.commerce_backend.repository.CartItemRepository;
import com.ecom.E.commerce_backend.repository.CartRepository;
import com.ecom.E.commerce_backend.request.AddToCartRequest;
import com.ecom.E.commerce_backend.request.UpdateCartItemRequest;
import com.ecom.E.commerce_backend.service.CartService;
import com.ecom.E.commerce_backend.service.ProductService;
import com.ecom.E.commerce_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/cart")
@CrossOrigin
public class CartController {

    @Autowired
    private CartItemRepository cartItemRepo;

    @Autowired
    private CartRepository cartRepo;

    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;


    @GetMapping("/getcartitem")
    public ResponseEntity<List<CartItem>> getProductFromCart(@RequestHeader("Authorization") String jwt){
        return new ResponseEntity<>(cartService.getProductFromCart(jwt), HttpStatus.OK);
    }


    @PostMapping("/addtocart")
    public ResponseEntity<CartItem> addToCart(@RequestBody AddToCartRequest req ,
                              @RequestHeader("Authorization") String jwt) throws Exception {

        return cartService.addToCart(req,jwt);
    }



    @GetMapping()
    public ResponseEntity<Cart> getCart(@RequestHeader("Authorization") String jwt){
       return cartService.getCart(jwt);
    }



    @DeleteMapping("deletecartitem/{id}")
    public ResponseEntity<String> deleteCartItem(@PathVariable Long id,
                                                 @RequestHeader("Authorization") String jwt) throws Exception {
        return cartService.deleteCartItem(id,jwt);
    }


    @PutMapping("/update")
    public ResponseEntity<CartItem> updateCartItem(
                                   @RequestBody UpdateCartItemRequest req,
                                   @RequestHeader("Authorization")String jwt) throws Exception {
       return cartService.updateCartItem(req,jwt);
    }




    @GetMapping("cartitem/{id}")
    public List<CartItem> getCartItemByProductId(@PathVariable Long id){
        return cartService.cartItems(id);
    }

    @GetMapping("/msg")
    public String hello(){
        return "path api/cart";
    }

    @GetMapping("findallcart")
    public List<Cart> getallCart(){
        return cartRepo.findAll();
    }
}
