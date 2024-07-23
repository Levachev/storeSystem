package com.example.store.repo;

import com.example.store.entity.*;
import com.example.store.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class CrudRepo {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CrudRepo(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    public void createProviderProduct(String providerName, String productName, int providerProductAmount) {
        GeneratedKeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement statement = con.prepareStatement("INSERT INTO provider (name) VALUES (?) ", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, providerName);
            return statement;
        }, holder);

        long providerId;
        if (holder.getKeys().size() > 1) {
            providerId = (Integer) holder.getKeys().get("id");
        } else {
            providerId = holder.getKey().longValue();
        }


        System.out.println("insert provider "+providerId);

        jdbcTemplate.update(con -> {
            PreparedStatement statement = con.prepareStatement("INSERT INTO product (name) VALUES (?) ", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, productName);
            return statement;
        }, holder);

        long productId;
        if (holder.getKeys().size() > 1) {
            productId = (Integer)holder.getKeys().get("id");
        } else {
            productId = holder.getKey().longValue();
        }

        System.out.println("insert product "+productId);

        jdbcTemplate.update("INSERT INTO provider_product (product_id, provider_id, amount)\n" +
                "VALUES (?, ?, ?)", productId, providerId, providerProductAmount);

        System.out.println("insert providerProduct");
    }

    @Transactional
    public void updateProviderProduct(Long product_id, Long providerId, int providerProductAmount) {
        jdbcTemplate.update("UPDATE provider_product SET amount=? WHERE product_id=? AND provider_id=?",
                providerProductAmount, product_id, providerId);

        System.out.println("update amount providerProduct");
    }

    @Transactional
    public void deleteProviderProduct(Long productId, Long providerId) {
        jdbcTemplate.update("DELETE FROM provider_product WHERE product_id=? AND provider_id=?",
                productId, providerId);

        System.out.println("delete provider Product");
    }

    @Transactional
    public List<Query> getProviderProduct(Long productId, Long providerId) {
        return jdbcTemplate.query("SELECT * FROM provider_product WHERE product_id=? AND provider_id=?",
                new Object[]{productId, providerId},
                new ProviderProductMapper());
    }

    @Transactional
    public void createStore(String storeOrganisationName,
                            int outletSize, int rentPayments,
                            int utilities, int counterNumber, int hallAmount,
                            int floorAmount, int sectionAmount, String type,
                            String address, int area){
        GeneratedKeyHolder holder = new GeneratedKeyHolder();

        jdbcTemplate.update(con -> {
            PreparedStatement statement = con.prepareStatement("INSERT INTO store_organisation (name) VALUES (?) ", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, storeOrganisationName);
            return statement;
        }, holder);
        long storeOrganisationId;
        if (holder.getKeys().size() > 1) {
            storeOrganisationId = (Integer)holder.getKeys().get("id");
        } else {
            storeOrganisationId = holder.getKey().longValue();
        }

        System.out.println("create store organisation with id - "+storeOrganisationId);

        jdbcTemplate.update("INSERT INTO store ( outlet_size, rent_payments," +
                "utilities, counter_number, hall_amount, floor_amount, section_amount," +
                "store_organisation_id, type, address, area)\n" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                outletSize, rentPayments, utilities, counterNumber, hallAmount,
                floorAmount, sectionAmount, storeOrganisationId, type, address, area);

        System.out.println("create store");
    }

    @Transactional
    public void updateStore(Long storeId, int outletSize, int rentPayments,
                            int utilities, int counterNumber, int hallAmount,
                            int floorAmount, int sectionAmount,
                            String address, int area) {
        jdbcTemplate.update("UPDATE store SET outlet_size=?, rent_payments=?, " +
                "utilities=?, counter_number=?, hall_amount=?, floor_amount=?, section_amount=?," +
                "address=?, area=?  WHERE id=?",
                outletSize, rentPayments, utilities, counterNumber, hallAmount, floorAmount,
                sectionAmount, address, area, storeId);

        System.out.println("update amount providerProduct");
    }

    @Transactional
    public void deleteStore(Long storeId) {
        jdbcTemplate.update("DELETE FROM store WHERE id=?",
                storeId);

        System.out.println("delete store");
    }

    @Transactional
    public List<Query> getStore(Long storeId) {
        return jdbcTemplate.query("SELECT * FROM store WHERE id=?",
                new Object[]{storeId},
                new StoreMapper());
    }

    public List<StoreOrganisation> getOrgList(){
        return jdbcTemplate.query("SELECT * FROM store_organisation",
                new Object[]{},
                new StoreOrganisationMapper());
    }

    public List<StoreOrganisation> getProvList(){
        return jdbcTemplate.query("SELECT * FROM provider",
                new Object[]{},
                new StoreOrganisationMapper());
    }

    public List<StoreOrganisation> getProdList(){
        return jdbcTemplate.query("SELECT * FROM product",
                new Object[]{},
                new StoreOrganisationMapper());
    }

    public List<Query> getStoreList(){
        return jdbcTemplate.query("SELECT * FROM store",
                new Object[]{},
                new StoreMapper());
    }

    public List<Seller> getSellerList(){
        return jdbcTemplate.query("SELECT * FROM seller",
                new Object[]{},
                new SellerMapper());
    }

    public List<Order> getOrderList(){
        return jdbcTemplate.query("SELECT * FROM \"order\"",
                new Object[]{},
                new OrderMapper());
    }

}
