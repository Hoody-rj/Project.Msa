package com.msa.middle_order.order;


import com.msa.middle_order.cmmn.ResultData;
import com.msa.middle_order.order.dto.Request.RequestOrder;
import com.msa.middle_order.order.dto.Response.ResponseOrder;
import com.msa.middle_order.order.dto.Response.ResponseProduct;
import com.msa.middle_order.order.entity.Order;
import com.msa.middle_order.order.entity.Ordered;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderedRepository orderedRepository;
    private final ProductInterface productInterface;

    @Transactional
    public void createOrder(RequestOrder productIds) {
        Order temporder = new Order();
        orderRepository.save(temporder);

        List<Ordered> orderedList = productIds.orderedList.stream()
                .map(productId -> {
                    Ordered ordered = new Ordered();
                    ordered.setOrder(temporder);
                    ordered.setProduct_id(productId);
                    return ordered;
                })
                .toList();
        orderedRepository.saveAll(orderedList);
    }

    @Transactional
    public void addProductsToOrder(Long order, RequestOrder productIds) {
        Order temporder = orderRepository.findAllByOrder_id(order);
        List<Ordered> orderedList = productIds.orderedList.stream()
                .map(productId -> {
                    Ordered ordered = new Ordered();
                    ordered.setOrder(temporder);
                    ordered.setProduct_id(productId);
                    return ordered;
                })
                .toList();
        orderedRepository.saveAll(orderedList);
    }


    public List<ResponseOrder> getAllOrder() {
        var getResult = orderRepository.findAllOrderbyDate();

        // ordered.getProductId()가 있어야 함

        return getResult.stream()
                .map(order -> {
                    List<Long> productIds = order.getProduct_ids().stream()
                            .map(Ordered::getProduct_id)  // ordered.getProductId()가 있어야 함
                            .collect(Collectors.toList());

                    return new ResponseOrder(
                            order.getOrder_id(),
                            productIds,
                            order.getDate()
                    );
                }).toList();
    }

    public Order getOrderByid(Long id) {
        return orderRepository.findAllByOrder_id(id);
    }

    public List<String> getProducts(RequestOrder order) {
        List<String> unregisteredId = new ArrayList<>();

        for (Long item : order.orderedList) {

            ResultData<ResponseProduct> results = productInterface.getProductsById(item);
            if (!results.resultCheck) {
                unregisteredId.add(item.toString());
            }
            System.out.println("미등록 상품 아이디 목록: " + unregisteredId);
        }
        return  unregisteredId;
    }


}
