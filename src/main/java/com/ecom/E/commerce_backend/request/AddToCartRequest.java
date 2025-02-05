package com.ecom.E.commerce_backend.request;


import com.ecom.E.commerce_backend.model.Product;
import lombok.Data;

@Data
public class AddToCartRequest {

    private Long productId;

    private int quantity;
}
