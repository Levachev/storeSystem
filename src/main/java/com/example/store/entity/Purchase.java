package com.example.store.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
public class Purchase {
    private Long id;
    private Long store_id;
    private Long seller_id;
    private Date date;
}
