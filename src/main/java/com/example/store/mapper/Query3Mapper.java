package com.example.store.mapper;

import com.example.store.entity.Query;
import com.example.store.entity.Query3;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Query3Mapper implements RowMapper<Query> {
    @Override
    public Query mapRow(ResultSet rs, int rowNum) throws SQLException {
        Query3 query3 = new Query3();
        query3.setAmount(rs.getInt("store product amount"));
        query3.setName(rs.getString("product name"));
        return query3;
    }
}
