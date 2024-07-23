package com.example.store.mapper;

import com.example.store.entity.Query;
import com.example.store.entity.Query5;
import com.example.store.entity.Query8;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Query8Mapper implements RowMapper<Query> {
    @Override
    public Query mapRow(ResultSet rs, int rowNum) throws SQLException {
        Query8 query = new Query8();
        query.setSalary(rs.getInt("seller salary"));
        query.setName(rs.getString("seller firstname"));
        query.setLastname(rs.getString("seller lastname"));
        return query;
    }
}
