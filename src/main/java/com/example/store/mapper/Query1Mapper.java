package com.example.store.mapper;

import com.example.store.entity.Query;
import com.example.store.entity.Query1;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class Query1Mapper implements RowMapper<Query> {

    @Override
    public Query mapRow(ResultSet rs, int rowNum) throws SQLException {
        Query1 query1 = new Query1();
        query1.setAmount(rs.getInt("amount"));
        query1.setName(rs.getString("provider name"));
        return query1;
    }
}
