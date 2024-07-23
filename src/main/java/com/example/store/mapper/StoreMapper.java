package com.example.store.mapper;

import com.example.store.entity.Query;
import com.example.store.entity.Store;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StoreMapper implements RowMapper<Query> {
    @Override
    public Store mapRow(ResultSet rs, int rowNum) throws SQLException {
        Store store = new Store();

        store.setId(rs.getLong("id"));
        store.setStore_organisation_id(rs.getLong("store_organisation_id"));
        store.setOutlet_size(rs.getInt("outlet_size"));
        store.setRent_payments(rs.getInt("rent_payments"));
        store.setUtilities(rs.getInt("utilities"));
        store.setCounter_number(rs.getInt("counter_number"));
        store.setHall_amount(rs.getInt("hall_amount"));
        store.setFloor_amount(rs.getInt("floor_amount"));
        store.setSection_amount(rs.getInt("section_amount"));
        store.setType(rs.getString("type"));
        store.setAddress(rs.getString("address"));
        store.setArea(rs.getInt("area"));

        return store;
    }
}
