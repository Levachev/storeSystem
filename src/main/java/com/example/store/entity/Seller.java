package com.example.store.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Seller {
    private Long id;
    private Long store_id;
    private String firstname;
    private String lastname;
    private int salary;
    private String surname;
}
