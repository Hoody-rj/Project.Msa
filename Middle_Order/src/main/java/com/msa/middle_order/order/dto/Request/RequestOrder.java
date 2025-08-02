package com.msa.middle_order.order.dto.Request;

import com.msa.middle_order.order.entity.Ordered;
import jakarta.validation.constraints.NotNull;

import java.util.List;


public class RequestOrder {
    @NotNull(message = "검색 조건은 비어 있을 수 없습니다.")
    public List<Long> orderedList;
}
