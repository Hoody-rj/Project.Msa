package com.msa.middleauth.sign.cmmn;


import lombok.Builder;

@Builder
public class ResultData<T> {
    public T resultdata;
    public boolean resultcheck;
    public String resultmessage;
}
