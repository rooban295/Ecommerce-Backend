package com.ecom.E.commerce_backend.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    private List<OrderItem> orderItem;

    @ManyToOne
    @JoinColumn(name = "address_id")
    @JsonIgnore
    private Address deliveryAddress;

    private LocalDate createdDate;

    private LocalDate deliveryDate;

    private String orderStatus;

    private Long totalProduct;

    private double totalAmount;

    @ManyToOne
    @JoinColumn(name="Customer_id")
    @JsonIgnore
    private User customer;
}
