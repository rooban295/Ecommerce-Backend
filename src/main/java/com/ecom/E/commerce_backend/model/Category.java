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
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String categoryName;

    private String categoryImageUrl;

    private String bannerImageUrl;

    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL)
    @JsonIgnore//it is very importance to stop loop response
    private List<Product> product;
}
