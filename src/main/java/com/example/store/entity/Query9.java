package com.example.store.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Query9 implements Query{
    private int amount;
    private String name;

    @Override
    public String title(){
        return String.format("%20s %20s", "количество", "название");
    }

    @Override
    public String values(){
        return String.format("%20s %20s", amount, name);
    }
}
