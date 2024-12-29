package com.zerobase.convpay.service;

import com.zerobase.convpay.type.CancelPaymentResult;
import com.zerobase.convpay.type.PayMethodType;
import com.zerobase.convpay.type.PaymentResult;

/*
MoneyAdapter, CardAdapter가 인터페이스를 의존할 수 있도록 설계
 */
public interface PaymentInterface {
    PayMethodType getPayMethodType();
    // interface가 public이기 때문에 접근제어자가 필요없다
    PaymentResult payment(Integer payAmount);
    CancelPaymentResult cancelPayment(Integer cancelAmount);
}
