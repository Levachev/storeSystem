package com.example.store.mapper;

import com.example.store.entity.Query;
import com.example.store.entity.Store;
import com.example.store.entity.StoreOrganisation;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StoreOrganisationMapper implements RowMapper<StoreOrganisation> {
    @Override
    public StoreOrganisation mapRow(ResultSet rs, int rowNum) throws SQLException {
        StoreOrganisation storeOrganisation = new StoreOrganisation();

        storeOrganisation.setId(rs.getLong("id"));
        storeOrganisation.setName(rs.getString("name"));

        return storeOrganisation;
    }
}
