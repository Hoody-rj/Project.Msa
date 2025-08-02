package com.msa.middle_product.product.cmmn;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductionNotFoundException extends RuntimeException {
    public ProductionNotFoundException(Long id) {
        super("Products with id " + id + " not found");
    }
}
