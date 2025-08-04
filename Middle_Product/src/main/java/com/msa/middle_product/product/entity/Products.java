package com.msa.middle_product.product.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tbl_product")
@NoArgsConstructor
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long product_id;
    @Column(nullable = false, length = 125)
    private String product_name;
    @Column(nullable = false)
    private float product_price;
    private float product_supply_price;
    @Column(nullable = false, length = 3 )
    private String currency;

    public Products(String product_name, float product_price, float product_supply_price, String currency) {
        this.product_name = product_name;
        this.product_price = product_price;
        this.product_supply_price = product_supply_price;
        this.currency = currency;
    }
}
