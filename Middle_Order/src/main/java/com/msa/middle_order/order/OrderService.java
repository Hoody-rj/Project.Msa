package com.msa.middle_order.order;


import com.msa.middle_order.cmmn.ResultData;
import com.msa.middle_order.order.dto.Request.RequestOrder;
import com.msa.middle_order.order.dto.Request.RequestuserAuth;
import com.msa.middle_order.order.dto.Response.ResponseOrder;
import com.msa.middle_order.order.dto.Response.ResponseProduct;
import com.msa.middle_order.order.dto.Response.Responseuser;
import com.msa.middle_order.order.entity.Order;
import com.msa.middle_order.order.entity.Ordered;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderedRepository orderedRepository;
    private final ProductInterface productInterface;
    private final AuthInterface authInterface;

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
        Optional<Order> temporder = orderRepository.findAllByOrder_id(order);
        if (temporder.isPresent()) {
            List<Ordered> orderedList = productIds.orderedList.stream()
                    .map(productId -> {
                        Ordered ordered = new Ordered();
                        ordered.setOrder(temporder.get());
                        ordered.setProduct_id(productId);
                        return ordered;
                    })
                    .toList();
            orderedRepository.saveAll(orderedList);
        }
        else{
            throw new IllegalArgumentException("해당 order ID에 대한 주문이 존재하지 않습니다: " + order);

        }

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

    public ResponseOrder getOrderByid(Long id) {
        Optional<Order> getResult = orderRepository.findAllByOrder_id(id);
        if (getResult.isEmpty()) {
            throw new RuntimeException("해당 id 값이 존재하지 않습니다.");
        }
        else{
            Order order = getResult.get();
            List<Long> productIds = order.getProduct_ids().stream()
                    .map(Ordered::getProduct_id)  // ordered.getProductId()가 있어야 함
                    .collect(Collectors.toList());
            return new ResponseOrder(
                    order.getOrder_id(),
                    productIds,
                    order.getDate()
            );
        }


    }

    public List<String> getProducts(RequestOrder order) {
        List<String> unregisteredId = new ArrayList<>();

        for (Long item : order.orderedList) {

            ResultData<ResponseProduct> results = productInterface.getProductsById(item);
            if (!results.resultcheck) {
                unregisteredId.add(item.toString());
            }
            System.out.println("미등록 상품 아이디 목록: " + unregisteredId);
        }
        return  unregisteredId;
    }

    public ResultData<Responseuser> confirmAuth(String authHeader){
        var results =authInterface.getuserinfoById(new RequestuserAuth(authHeader));
        return results;
    }


}
