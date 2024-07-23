package com.example.store.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Query5 implements Query{
    private int amount;

    @Override
    public String title(){
        return String.format("%20s", "количество");
    }

    @Override
    public String values(){
        return String.format("%20s", amount);
    }
}
