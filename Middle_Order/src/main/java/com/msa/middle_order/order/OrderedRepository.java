package com.msa.middle_order.order;

import com.msa.middle_order.order.entity.Ordered;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderedRepository extends JpaRepository<Ordered,Long> {

}
