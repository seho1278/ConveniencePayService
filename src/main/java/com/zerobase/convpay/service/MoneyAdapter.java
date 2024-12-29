package com.zerobase.convpay.service;

import com.zerobase.convpay.type.*;

public class MoneyAdapter implements PaymentInterface {
    // 돈 얼마 쓰는지 확인
    public MoneyUseResult use(Integer payAmount) {
        System.out.println("MoneyAdapter.use : " + payAmount);

        // 금액이 너무 클 경우 실패
        if (payAmount > 1000_000) {
            return MoneyUseResult.USE_FAIL;
        }

        return MoneyUseResult.USE_SUCCESS;
    }

    public MoneyUseCancelResult useCancel(Integer payCancelAmount) {
        System.out.println("MoneyAdapter.useCancel : " + payCancelAmount);

        // 금액이 너무 작을 경우
        if (payCancelAmount < 100) {
            return MoneyUseCancelResult.MONEY_USE_CANCEL_FAIL;
        }

        return MoneyUseCancelResult.MONEY_USE_CANCEL_SUCCESS;

    }

    @Override
    public PayMethodType getPayMethodType() {
        return PayMethodType.MONEY;
    }

    @Override
    public PaymentResult payment(Integer payAmount) {
        MoneyUseResult moneyUseResult = use(payAmount);

        if (moneyUseResult == MoneyUseResult.USE_FAIL) {
            return PaymentResult.PAYMENT_FAIL;
        }

        return PaymentResult.PAYMENT_SUCCESS;
    }

    @Override
    public CancelPaymentResult cancelPayment(Integer cancelAmount) {
        MoneyUseCancelResult moneyUseCancelResult = useCancel(cancelAmount);
        if (moneyUseCancelResult == MoneyUseCancelResult.MONEY_USE_CANCEL_FAIL) {
            return CancelPaymentResult.CANCEL_PAYMENT_FAIL;
        }

        return CancelPaymentResult.CANCEL_PAYMENT_SUCCESS;
    }
}
