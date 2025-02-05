package com.ecom.E.commerce_backend.request;

import lombok.Data;

@Data
public class UpdateCartItemRequest {

    private Long id;

    private int quantity;
}
