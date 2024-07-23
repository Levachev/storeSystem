package com.example.store.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Query10 implements Query{
    private int result;

    @Override
    public String title(){
        return String.format("%20s", "результат");
    }

    @Override
    public String values(){
        return String.format("%20s", result);
    }
}
