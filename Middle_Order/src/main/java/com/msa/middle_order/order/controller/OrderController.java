package com.msa.middle_order.order.controller;


import com.msa.middle_order.cmmn.ResultData;

import com.msa.middle_order.order.OrderService;

import com.msa.middle_order.order.dto.Request.RequestOrder;
import com.msa.middle_order.order.dto.Response.ResponseOrder;
import com.msa.middle_order.order.entity.Order;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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


    @PostMapping
    public ResponseEntity<ResultData<List<String>>> createOrder(@RequestBody @Valid RequestOrder order) {

        var resultData = orderService.getProducts(order);
        String resultMsg = "저장 되었습니다.";;
        if (!resultData.isEmpty())
        {
            resultMsg = "미등록 상품이 존재합니다.";
        }

        orderService.createOrder(order);
        return ResponseEntity.ok(ResultData.<List<String>>builder()
                .resultCheck(true)
                .resultData(resultData)
                .resultMessage(resultMsg)
                .build());
    }

    //예외처리 필요
    @PutMapping("/{orderId}")
    public ResponseEntity<ResultData<Order>> updateOrderbyorderid(
            @PathVariable("orderId") Long orderId,
            @RequestBody RequestOrder productId) {
        orderService.addProductsToOrder(orderId, productId);
        return ResponseEntity.ok(ResultData.<Order>builder()
                .resultCheck(true)
                .resultData(null)
                .resultMessage("")
                .build());
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<ResultData<Order>> getOrderbyorderid(
            @PathVariable("orderId") Long orderId
    ) {
        return ResponseEntity.ok(ResultData.<Order>builder()
                .resultCheck(true)
                .resultData(orderService.getOrderByid(orderId))
                .resultMessage("")
                .build());

    }


}
