package com.msa.middle_order.order.dto.Response;

import jakarta.persistence.Column;

public class ResponseProduct {
    private Long product_id;
    private String product_name;
    private float product_price;
    private float product_supply_price;
    private String currency;
}
