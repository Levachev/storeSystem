package com.example.store.mapper;

import com.example.store.entity.Buyer;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BuyerMapper  implements RowMapper<Buyer> {
    @Override
    public Buyer mapRow(ResultSet rs, int rowNum) throws SQLException {
        Buyer buyer = new Buyer();

        buyer.setId(rs.getLong("id"));
        buyer.setFirstname(rs.getString("firstname"));
        buyer.setLastname(rs.getString("lastname"));
        buyer.setSurname(rs.getString("surname"));

        return buyer;
    }
}
