package com.example.store.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Buyer {
    private Long id;
    private String firstname;
    private String lastname;
    private String surname;
}
