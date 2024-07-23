package com.example.store.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ApplicationProduct {
    private Long id;
    private Long application_id;
    private int amount;
}
