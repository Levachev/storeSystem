package com.example.store.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Query13 implements Query{
    private String firstName;
    private String lastName;

    @Override
    public String title(){
        return String.format("%20s %20s", "имя", "фамилия");
    }

    @Override
    public String values(){
        return String.format("%20s %20s", firstName, lastName);
    }
}
