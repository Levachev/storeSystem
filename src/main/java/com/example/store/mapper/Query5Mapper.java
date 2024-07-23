package com.example.store.mapper;

import com.example.store.entity.Query;
import com.example.store.entity.Query5;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Query5Mapper implements RowMapper<Query> {
    @Override
    public Query mapRow(ResultSet rs, int rowNum) throws SQLException {
        Query5 query = new Query5();
        query.setAmount(rs.getInt("amount"));
        return query;
    }
}
