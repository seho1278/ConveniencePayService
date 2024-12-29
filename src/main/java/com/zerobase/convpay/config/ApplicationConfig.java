package com.zerobase.convpay.config;

import com.zerobase.convpay.service.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.HashSet;

@Configuration
public class ApplicationConfig {
    // config에서 의존관계 조작
    @Bean
    public ConveniencePayService conveniencePayService() {
        return new ConveniencePayService(
            new HashSet<>(
                Arrays.asList(moneyAdapter(), cardAdapter())
            ),
            discountByConvenience()
        );
    }

    @Bean
    private static CardAdapter cardAdapter() {
        return new CardAdapter();
    }

    @Bean
    private static MoneyAdapter moneyAdapter() {
        return new MoneyAdapter();
    }

    @Bean
    private static DiscountByConvenience discountByConvenience() {
        return new DiscountByConvenience();
    }
}
