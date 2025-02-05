package com.ecom.E.commerce_backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String city;

    private String street;

    private int pincode;

    private String state;

    private String country;

    private long mobile;


    @OneToMany(mappedBy = "deliveryAddress",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Order> order;
}
