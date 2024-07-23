package com.example.store.mapper;

import com.example.store.entity.Query;
import com.example.store.entity.Query14;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class Query14Mapper implements RowMapper<Query> {

    @Override
    public Query mapRow(ResultSet rs, int rowNum) throws SQLException {
        Query14 query = new Query14();
        query.setFirstName(rs.getString("buyer firstname"));
        query.setLastName(rs.getString("buyer lastname"));
        return query;
    }
}
