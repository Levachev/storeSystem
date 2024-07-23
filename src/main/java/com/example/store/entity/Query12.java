package com.example.store.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Query12 implements Query{
    private int amount;
    private String productName;
    private String providerName;

    @Override
    public String title(){
        return String.format("%20s %20s %20s", "количество", "название продукта", "название поставщика");
    }

    @Override
    public String values(){
        return String.format("%20s %20s %20s", amount, productName, providerName);
    }
}
