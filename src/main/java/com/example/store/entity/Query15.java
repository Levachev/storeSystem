package com.example.store.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Query15 implements Query{
    private Long storeId;
    private int tradeTurnover;

    @Override
    public String title(){
        return String.format("%20s %20s", "идентификатор торговой точки", "товарооборот");
    }

    @Override
    public String values(){
        return String.format("%20s %20s", storeId, tradeTurnover);
    }
}
