package com.ecom.E.commerce_backend.request;


import com.ecom.E.commerce_backend.model.Category;
import lombok.Data;

@Data
public class ProductRequest {

    private String productName;

    private String description;

    private String productImg;

    private double productPrice;

    private Category category;
}
