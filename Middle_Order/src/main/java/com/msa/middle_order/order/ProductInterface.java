package com.msa.middle_order.order;

import com.msa.middle_order.order.dto.Response.ResponseOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "middle-product", url = "http://localhost:19093/")
public interface ProductInterface {
    @RequestMapping(method = RequestMethod.GET, value = "/products/{searchid}")
    String getProductsById(@PathVariable Long searchid);
}
