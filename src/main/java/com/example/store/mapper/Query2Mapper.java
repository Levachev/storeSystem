package com.example.store.mapper;

import com.example.store.entity.Query;
import com.example.store.entity.Query2;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Query2Mapper implements RowMapper<Query> {
    @Override
    public Query mapRow(ResultSet rs, int rowNum) throws SQLException {
        Query2 query2 = new Query2();
        query2.setAmount(rs.getInt("amount"));
        query2.setName(rs.getString("buyer firstname"));
        query2.setLastname(rs.getString("buyer lastname"));
        return query2;
    }
}
