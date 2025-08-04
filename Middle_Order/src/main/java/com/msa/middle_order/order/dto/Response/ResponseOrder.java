package com.msa.middle_order.order.dto.Response;

import com.msa.middle_order.order.entity.Ordered;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Setter
@AllArgsConstructor
public class ResponseOrder {
    public Long order_id;
    public List<Long> product_ids;
    public Date register_date;
}
