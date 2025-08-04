package com.msa.middle_order.order;

import com.msa.middle_order.cmmn.ResultData;
import com.msa.middle_order.order.dto.Request.RequestuserAuth;
import com.msa.middle_order.order.dto.Response.Responseuser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "middle-auth", url = "http://localhost:19095")
public interface AuthInterface {
    @PostMapping("/auth/confirm")
    ResultData<Responseuser> getuserinfoById(@RequestBody RequestuserAuth authHeader);
}
