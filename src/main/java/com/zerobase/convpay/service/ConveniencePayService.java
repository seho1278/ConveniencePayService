package com.zerobase.convpay.service;

import com.zerobase.convpay.dto.PayCancelRequest;
import com.zerobase.convpay.dto.PayCancelResponse;
import com.zerobase.convpay.dto.PayRequest;
import com.zerobase.convpay.dto.PayResponse;
import com.zerobase.convpay.type.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ConveniencePayService {
    private final Map<PayMethodType, PaymentInterface> paymentInterfaceMap =
        new HashMap<>();
    private final DiscountInterface discountInterface;

    // 생성자 생성
    public ConveniencePayService(Set<PaymentInterface> paymentInterfaceSet,
                                 DiscountInterface discountInterface) {
        paymentInterfaceSet.forEach(
            paymentInterface -> paymentInterfaceMap.put(
                paymentInterface.getPayMethodType(),
                paymentInterface
            )
        );
        this.discountInterface = discountInterface;
    }

    public PayResponse pay(PayRequest payRequest) {
        PaymentInterface paymentInterface = paymentInterfaceMap.get(payRequest.getPayMethodType());

        Integer discountAmount = discountInterface.getDiscountedAmount(payRequest);
        PaymentResult paymentResult = paymentInterface.payment(discountAmount);

        // fail case
        if (paymentResult == PaymentResult.PAYMENT_FAIL) {
            return new PayResponse(PayResult.FAIL, 0);
        }

        // Success Case
        return new PayResponse(PayResult.SUCCESS, discountAmount);
    }

    public PayCancelResponse payCancel(PayCancelRequest payCancelRequest) {
        PaymentInterface paymentInterface = paymentInterfaceMap.get(payCancelRequest.getPayMethodType());

        CancelPaymentResult cancelPaymentResult = paymentInterface.cancelPayment(payCancelRequest.getPayCancelAmount());

        if (cancelPaymentResult == CancelPaymentResult.CANCEL_PAYMENT_FAIL) {
            return new PayCancelResponse(PayCancelResult.PAY_CANCEL_FAIL, 0);
        }

        // Success Case
        return new PayCancelResponse(PayCancelResult.PAY_CANCEL_SUCCESS,
            payCancelRequest.getPayCancelAmount());
    }
}
