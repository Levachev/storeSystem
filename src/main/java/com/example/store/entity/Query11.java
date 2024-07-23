package com.example.store.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Query11 implements Query{
    private Long store_id;
    private int result;

    @Override
    public String title(){
        return String.format("%20s %20s", "store id", "результат");
    }

    @Override
    public String values(){
        return String.format("%20s %20s", store_id, result);
    }
}
