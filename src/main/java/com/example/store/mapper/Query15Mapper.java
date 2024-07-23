package com.example.store.mapper;

import com.example.store.entity.Query;
import com.example.store.entity.Query15;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class Query15Mapper implements RowMapper<Query> {

    @Override
    public Query mapRow(ResultSet rs, int rowNum) throws SQLException {
        Query15 query = new Query15();
        query.setTradeTurnover(rs.getInt("trade turnover"));
        query.setStoreId(rs.getLong("store id"));
        return query;
    }
}
