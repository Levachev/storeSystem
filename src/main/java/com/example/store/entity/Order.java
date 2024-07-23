package com.example.store.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
public class Order {
    private Long id;
    private int order_number;
    private Long provider_id;
    private Long store_organisation_id;
    private Date date;
}
