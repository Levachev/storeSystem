package com.example.store.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Store implements Query{
    private Long id;
    private int outlet_size;
    private int rent_payments;
    private int utilities;
    private int counter_number;
    private int hall_amount;
    private int floor_amount;
    private int section_amount;
    private Long store_organisation_id;
    private String type;
    private String address;
    private int area;

    private static String formatStr = "%10s %10s %10s %10s %10s %10s %10s %10s %10s %10s %10s %10s";

    @Override
    public String title(){
        return String.format(formatStr,
                "идентифиатор", "размер", "арендная плата", "комунальные услуги", "количество прилавков",
                "количество зало", "количество этажей", "количество секций", "идентифиатор организации",
                "тип", "адрес", "площадь");
    }

    @Override
    public String values(){
        return String.format(formatStr,
                id, outlet_size, rent_payments, utilities, counter_number, hall_amount,
                floor_amount, section_amount, store_organisation_id, type, address, area);
    }
}
