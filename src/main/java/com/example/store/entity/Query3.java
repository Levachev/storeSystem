package com.example.store.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Query3 implements Query{
    private String name;
    private int amount;

    @Override
    public String title(){
        return String.format("%20s %20s", "название", "количество");
    }

    @Override
    public String values(){
        return String.format("%20s %20s", name, amount);
    }
}
