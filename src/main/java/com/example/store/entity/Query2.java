package com.example.store.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Query2 implements Query{
    private int amount;
    private String name;
    private String lastname;

    @Override
    public String title(){
        return String.format("%20s %20s %20s", "количество", "имя", "фамилия");
    }

    @Override
    public String values(){
        return String.format("%20s %20s %20s", amount, name, lastname);
    }
}
