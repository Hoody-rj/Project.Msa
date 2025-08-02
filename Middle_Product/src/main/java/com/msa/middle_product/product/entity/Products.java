package com.msa.middle_product.product.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Length;

@Getter
@Setter
@Entity
@Table(name = "tbl_product")
@NoArgsConstructor
@AllArgsConstructor
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


}
