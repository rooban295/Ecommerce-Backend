package com.ecom.E.commerce_backend.request;

import com.ecom.E.commerce_backend.model.Address;
import com.ecom.E.commerce_backend.model.Order;
import com.ecom.E.commerce_backend.model.Product;
import lombok.Data;

@Data
public class OrderRequest {

    private Address address;

}
