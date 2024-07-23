package com.example.store.mapper;

import com.example.store.entity.Query;
import com.example.store.entity.Query9;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class Query9Mapper implements RowMapper<Query> {

    @Override
    public Query mapRow(ResultSet rs, int rowNum) throws SQLException {
        Query9 query = new Query9();
        query.setAmount(rs.getInt("amount"));
        query.setName(rs.getString("product name"));
        return query;
    }
}
