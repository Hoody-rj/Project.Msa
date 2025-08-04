package com.msa.middle_order.order;


import com.msa.middle_order.cmmn.ResultData;
import com.msa.middle_order.order.dto.Response.ResponseProduct;
import com.msa.middle_order.order.dto.Response.Responseuser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "middle-product", url = "http://localhost:19093")
public interface ProductInterface {
    @GetMapping("/products/{user_id}")
    ResultData<ResponseProduct> getProductsById(@PathVariable Long user_id);
}
