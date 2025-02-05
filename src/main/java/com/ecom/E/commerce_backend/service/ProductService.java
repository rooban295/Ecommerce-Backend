package com.ecom.E.commerce_backend.service;

import com.ecom.E.commerce_backend.model.Product;
import com.ecom.E.commerce_backend.request.ProductRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {

    public ResponseEntity<Product> addProduct(Product product);

    public ResponseEntity<List<Product>> getAllProduct();

    public ResponseEntity<Product> getProductByID(Long id) throws Exception;

    public ResponseEntity<List<Product>> getAllProductByCategory(Long id);

    public ResponseEntity<Product> updateProduct(Long id,ProductRequest request) throws Exception;

    public ResponseEntity<String> deleteProduct(Long id) throws Exception;

    public ResponseEntity<List<Product>> searchProduct(String keyword);

}
