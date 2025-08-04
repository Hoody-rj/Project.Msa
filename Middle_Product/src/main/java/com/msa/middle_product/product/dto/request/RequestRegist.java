package com.msa.middle_product.product.dto.request;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RequestRegist {
    private String product_name;
    private float product_price;
    private float product_supply_price;
    private String currency;
}
