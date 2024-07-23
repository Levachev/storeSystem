package com.example.store.mapper;

import com.example.store.entity.Query;
import com.example.store.entity.Query12;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class Query12Mapper implements RowMapper<Query> {

    @Override
    public Query mapRow(ResultSet rs, int rowNum) throws SQLException {
        Query12 query = new Query12();
        query.setAmount(rs.getInt("order product amount"));
        query.setProductName(rs.getString("product name"));
        query.setProviderName(rs.getString("provider name"));
        return query;
    }
}
