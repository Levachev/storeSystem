package com.example.store.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StoreProduct {
    private Long id;
    private Long store_id;
    private Long product_range_id;
    private int price;
    private int amount;
}
