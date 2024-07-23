package com.example.store.mapper;

import com.example.store.entity.ProviderProduct;
import com.example.store.entity.Query;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProviderProductMapper implements RowMapper<Query> {
    @Override
    public ProviderProduct mapRow(ResultSet rs, int rowNum) throws SQLException {
        ProviderProduct providerProduct = new ProviderProduct();

        providerProduct.setProductId(rs.getLong("provider_id"));
        providerProduct.setProviderId(rs.getLong("product_id"));
        providerProduct.setAmount(rs.getInt("amount"));

        return providerProduct;
    }
}
