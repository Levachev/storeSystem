package com.example.store.mapper;

import com.example.store.entity.Order;
import com.example.store.entity.Seller;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderMapper implements RowMapper<Order> {
    @Override
    public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
        Order order = new Order();

        order.setId(rs.getLong("id"));
        order.setOrder_number(rs.getInt("order_number"));
        order.setStore_organisation_id(rs.getLong("store_organisation_id"));
        order.setProvider_id(rs.getLong("provider_id"));
        order.setDate(rs.getDate("date"));

        return order;
    }
}
