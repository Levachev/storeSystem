package com.example.store.mapper;

import com.example.store.entity.Query;
import com.example.store.entity.Query13;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class Query13Mapper implements RowMapper<Query> {

    @Override
    public Query mapRow(ResultSet rs, int rowNum) throws SQLException {
        Query13 query = new Query13();
        query.setFirstName(rs.getString("buyer firstname"));
        query.setLastName(rs.getString("buyer lastname"));
        return query;
    }
}
