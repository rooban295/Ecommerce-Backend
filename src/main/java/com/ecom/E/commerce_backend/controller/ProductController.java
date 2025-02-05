package com.ecom.E.commerce_backend.controller;


import com.ecom.E.commerce_backend.model.Cart;
import com.ecom.E.commerce_backend.model.CartItem;
import com.ecom.E.commerce_backend.model.Product;
import com.ecom.E.commerce_backend.repository.CartRepository;
import com.ecom.E.commerce_backend.request.ProductRequest;
import com.ecom.E.commerce_backend.service.CartService;
import com.ecom.E.commerce_backend.service.ProductService;
import com.ecom.E.commerce_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@CrossOrigin
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CartRepository cartRepo;


    @Autowired
    private UserService userService;

    @Autowired
    private CartService cartService;

    @GetMapping
    public ResponseEntity<List<Product>> getAllProduct(){
        return productService.getAllProduct();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable  Long id) throws Exception {
        return productService.getProductByID(id);
    }


    @GetMapping("/category/{id}")
    public ResponseEntity<List<Product>> getAllProductByCategory(@PathVariable Long id){
        return productService.getAllProductByCategory(id);
    }

    @PostMapping("/add")
    public ResponseEntity<Product> addProduct(@RequestBody Product product){
        return productService.addProduct(product);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id,
                                                 @RequestBody ProductRequest req) throws Exception {
        return productService.updateProduct(id,req);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) throws Exception {

//        Product product= productService.getProductByID(id).getBody();
//        double cartAmount =0.0;
//        List<CartItem> cartItems=cartService.cartItems(id);
//
//        for(CartItem item :cartItems){
//            cartAmount+=item.getCartItemTotal();
//        }

        return productService.deleteProduct(id);

    }

    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProduct(@RequestParam String keyword){
        return productService.searchProduct(keyword);
    }

//    @GetMapping("findallcart")
//    public List<Cart> getallCart(){
//        return cartRepo.findAll();
//    }
}
