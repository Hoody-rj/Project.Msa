package com.msa.middle_order.cmmn;

import lombok.Builder;

@Builder
public class ResultData <T>{
    public T resultData;
    public boolean resultCheck;
    public String resultMessage;
}
