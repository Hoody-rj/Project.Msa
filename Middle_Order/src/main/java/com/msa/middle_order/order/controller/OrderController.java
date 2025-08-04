package com.msa.middle_order.order.controller;


import com.msa.middle_order.cmmn.ResultData;
import com.msa.middle_order.order.AuthInterface;
import com.msa.middle_order.order.OrderService;
import com.msa.middle_order.order.dto.Request.RequestOrder;
import com.msa.middle_order.order.dto.Response.ResponseOrder;
import com.msa.middle_order.order.dto.Response.Responseuser;
import com.msa.middle_order.order.entity.Order;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    //모든 주문 내역에 대한 값 반환
    @GetMapping
    public ResponseEntity<List<ResponseOrder>> getAllOrders() {
        List<ResponseOrder> tempOrders = orderService.getAllOrder();
        return ResponseEntity.ok(tempOrders);

    }

    //권한 확인 필요
    @PostMapping
    public ResponseEntity<ResultData<List<String>>> createOrder(
            @RequestBody @Valid RequestOrder order,
            @RequestHeader("authorization") String authHeader
    ) {
        ResultData<Responseuser> tempAuth = orderService.confirmAuth(authHeader);
        Responseuser tempuser = tempAuth.resultdata;
        if (tempuser == null) {
            System.out.println("tempuser is null");
        }
        if(tempuser.user_auth.equalsIgnoreCase("user"))
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResultData.<List<String>>builder()
                    .resultcheck(false)
                    .resultdata(null)
                    .resultmessage("권한이 없습니다")
                    .build());

        }

        var resultData = orderService.getProducts(order);
        String resultMsg = "저장 되었습니다.";;
        if (!resultData.isEmpty())
        {
            resultMsg = "미등록 상품이 존재합니다.";
        }

        orderService.createOrder(order);
        return ResponseEntity.ok(ResultData.<List<String>>builder()
                .resultcheck(true)
                .resultdata(resultData)
                .resultmessage(resultMsg)
                .build());
    }

    //예외처리 필요
    @PutMapping("/{orderId}")
    public ResponseEntity<ResultData<Order>> updateOrderbyorderid(
            @PathVariable("orderId") Long orderId,
            @RequestBody RequestOrder productId) {
        try {
            orderService.addProductsToOrder(orderId, productId);

            return ResponseEntity.ok(ResultData.<Order>builder()
                    .resultcheck(true)
                    .resultdata(null)
                    .resultmessage("주문목록에 추가 되었습니다.")
                    .build());
        }
        catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ResultData.<Order>builder()
                            .resultcheck(false)
                            .resultdata(null)
                            .resultmessage("주문 목록 확인 및 ID를 확인 해주세요.")
                            .build());

        }
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<ResultData<ResponseOrder>> getOrderbyorderid(
            @PathVariable("orderId") Long orderId
    ) {
        try {
            return ResponseEntity.ok(ResultData.<ResponseOrder>builder()
                    .resultcheck(true)
                    .resultdata(orderService.getOrderByid(orderId))
                    .resultmessage("주문목록이 반환되었습니다.")
                    .build());
        }
        catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ResultData.<ResponseOrder>builder()
                    .resultcheck(false)
                    .resultdata(null)
                    .resultmessage("존재하지 않는 주문 목록입니다.")
                    .build());
        }
    }


}
