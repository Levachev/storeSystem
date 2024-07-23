package com.example.store.mapper;

import com.example.store.entity.Seller;
import com.example.store.entity.StoreOrganisation;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SellerMapper implements RowMapper<Seller> {
    @Override
    public Seller mapRow(ResultSet rs, int rowNum) throws SQLException {
        Seller seller = new Seller();

        seller.setId(rs.getLong("id"));
        seller.setStore_id(rs.getLong("store_id"));
        seller.setFirstname(rs.getString("firstname"));
        seller.setLastname(rs.getString("lastname"));
        seller.setSurname(rs.getString("surname"));
        seller.setSalary(rs.getInt("salary"));

        return seller;
    }
}
