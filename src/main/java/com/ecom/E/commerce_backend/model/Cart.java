package com.ecom.E.commerce_backend.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JsonIgnore
    private User user;

    @OneToMany(mappedBy = "cart",cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonIgnore
    private List<CartItem> cartItems;

    private double cartTotal;
}
