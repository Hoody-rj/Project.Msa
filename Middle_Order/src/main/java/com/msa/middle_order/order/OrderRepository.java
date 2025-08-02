package com.msa.middle_order.order;

import com.msa.middle_order.order.entity.Order;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("SELECT o FROM Order o WHERE o.order_id = :Order_id")
    Order findAllByOrder_id(Long Order_id);
    @Query("SELECT o FROM Order o order by o.date")
    @EntityGraph(attributePaths = {"product_ids"})
    List<Order> findAllOrderbyDate();
    //List<Order> findAllorderbyOrder_idOrderList(Long Order_id);

}
