package com.example.store.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Query8 implements Query{
    private String name;
    private String lastname;
    private int salary;

    @Override
    public String title(){
        return String.format("%20s %20s", "имя", "фамилия", "зарплата");
    }

    @Override
    public String values(){
        return String.format("%20s %20s", name, lastname, salary);
    }
}
