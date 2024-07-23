package com.example.store.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DepStorePurchase {
    private Long purchase_id;
    private Long buyer_id;
}
