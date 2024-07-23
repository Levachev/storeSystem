package com.example.store.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PurchaseElement {
    private Long id;
    private Long store_product_id;
    private int sold_amount;
    private Long purchase_id;
}
