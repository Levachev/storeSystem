package com.example.store.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProviderProduct implements Query{
    private Long productId;
    private Long providerId;
    private int amount;

    @Override
    public String title(){
        return String.format("%20s %20s %20s", "идентифиатор продукта", "идентифиатор поставщика", "количество");
    }

    @Override
    public String values(){
        return String.format("%20s %20s %20s", productId, providerId, amount);
    }
}
