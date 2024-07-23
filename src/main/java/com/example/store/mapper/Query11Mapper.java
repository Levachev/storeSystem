package com.example.store.mapper;

import com.example.store.entity.Query;
import com.example.store.entity.Query11;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class Query11Mapper implements RowMapper<Query> {

    @Override
    public Query mapRow(ResultSet rs, int rowNum) throws SQLException {
        Query11 query = new Query11();
        query.setStore_id(rs.getLong("store id"));
        query.setResult(rs.getInt("result"));
        return query;
    }
}
