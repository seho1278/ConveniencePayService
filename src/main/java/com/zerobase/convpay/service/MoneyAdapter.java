package com.zerobase.convpay.service;

import com.zerobase.convpay.type.MoneyUseCancelResult;
import com.zerobase.convpay.type.MoneyUseResult;

public class MoneyAdapter {
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
}
