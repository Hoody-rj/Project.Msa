package com.msa.middle_product.product.controller;


import com.msa.middle_product.product.ProductsService;
import com.msa.middle_product.product.cmmn.ResultData;
import com.msa.middle_product.product.dto.request.RequestRegist;
import com.msa.middle_product.product.entity.Products;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/products")
public class ProductsController {

    private final ProductsService productsService;

    @Value("${server.port}")
    private String port;

    @GetMapping
    public ResponseEntity<List<Products>> getAllProducts() {
        return ResponseEntity.ok(productsService.productsfindAll());
    }

    @GetMapping("/{searchid}")
    public ResponseEntity<ResultData<Products>> getAllProductsById(@PathVariable Long searchid) {
        Products tempData = productsService.productsfindById(searchid);
        if (tempData == null) {
            return ResponseEntity.ok(ResultData.<Products>builder()
                    .resultcheck(false)
                    .resultdata(null)
                    .resultmessage("데이터가 존재하지 않습니다.")
                    .build());
        }
        return ResponseEntity.ok(ResultData.<Products>builder()
                .resultcheck(true)
                .resultdata(tempData)
                .resultmessage("")
                .build());
    }


    @PostMapping("/registration")
    public ResponseEntity<String> saveProducts(@RequestBody RequestRegist products) {
        productsService.productssave(products);
        return ResponseEntity.ok("port is " + port);
    }


}
