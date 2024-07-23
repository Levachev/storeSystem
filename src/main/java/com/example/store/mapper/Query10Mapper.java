package com.example.store.mapper;

import com.example.store.entity.Query;
import com.example.store.entity.Query10;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class Query10Mapper implements RowMapper<Query> {

    @Override
    public Query mapRow(ResultSet rs, int rowNum) throws SQLException {
        Query10 query = new Query10();
        query.setResult(rs.getInt("main result"));
        return query;
    }
}
