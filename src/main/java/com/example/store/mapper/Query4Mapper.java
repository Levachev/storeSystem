package com.example.store.mapper;

import com.example.store.entity.Query;
import com.example.store.entity.Query4;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Query4Mapper implements RowMapper<Query> {
    @Override
    public Query mapRow(ResultSet rs, int rowNum) throws SQLException {
        Query4 query = new Query4();
        query.setAmount(rs.getInt("store product amount"));
        query.setName(rs.getString("product name"));
        query.setPrice(rs.getInt("store product price"));
        return query;
    }
}
