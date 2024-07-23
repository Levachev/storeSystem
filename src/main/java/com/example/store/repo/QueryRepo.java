package com.example.store.repo;

import com.example.store.entity.*;
import com.example.store.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public class QueryRepo {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public QueryRepo(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Query> query1(String name){
        return jdbcTemplate.query("SELECT count(*) AS amount, provider.name AS \"provider name\"\n" +
                "FROM provider\n" +
                "INNER JOIN \"order\" ON \"order\".provider_id = provider.id\n" +
                "INNER JOIN order_product ON order_product.order_id = \"order\".id\n" +
                "INNER JOIN product ON product.id=order_product.product_id\n" +
                "WHERE product.name=?\n" +
                "GROUP BY provider.name", new Object[]{name}, new Query1Mapper());
    }

    public List<Query> query1(String name, int amount, Date beginDate, Date endDate){
        return jdbcTemplate.query("SELECT count(*) AS amount, provider.name AS \"provider name\"\n" +
                        "FROM provider\n" +
                        "INNER JOIN \"order\" ON \"order\".provider_id = provider.id\n" +
                        "INNER JOIN order_product ON order_product.order_id = \"order\".id\n" +
                        "INNER JOIN product ON product.id=order_product.product_id\n" +
                        "WHERE product.name=? AND order_product.amount>? AND \"order\".date>=? AND \"order\".date<?\n" +
                        "GROUP BY provider.name", new Object[]{name, amount, beginDate, endDate},
                new Query1Mapper());
    }

    public List<Query> query2(String name, int amount){
        return jdbcTemplate.query("SELECT count(*) AS amount, buyer.firstname AS \"buyer firstname\", buyer.lastname AS \"buyer lastname\"\n" +
                        "FROM purchase\n" +
                        "INNER JOIN purchase_element ON purchase_element.purchase_id = purchase.id\n" +
                        "INNER JOIN store_product ON store_product.product_id = purchase_element.store_product_product_id AND store_product.store_id = purchase_element.store_product_store_id\n" +
                        "INNER JOIN product ON product.id=store_product.product_id\n" +
                        "INNER JOIN buyer ON buyer.id=purchase.buyer_id AND purchase.buyer_id IS NOT NULL\n" +
                        "WHERE product.name=? AND purchase_element.sold_amount>=?\n" +
                        "GROUP BY buyer.firstname, buyer.lastname", new Object[]{name, amount},
                new Query2Mapper());
    }

    public List<Query> query2(String name, Date beginDate, Date endDate){
        System.out.println("name "+name+"beginDate "+beginDate+"endDate "+endDate);
        return jdbcTemplate.query("SELECT count(*) AS amount, buyer.firstname AS \"buyer firstname\", buyer.lastname AS \"buyer lastname\"\n" +
                        "FROM purchase\n" +
                        "INNER JOIN purchase_element ON purchase_element.purchase_id = purchase.id\n" +
                        "INNER JOIN store_product ON store_product.product_id = purchase_element.store_product_product_id AND store_product.store_id = purchase_element.store_product_store_id\n" +
                        "INNER JOIN product ON product.id=store_product.product_id\n" +
                        "INNER JOIN buyer ON buyer.id=purchase.buyer_id AND purchase.buyer_id IS NOT NULL\n" +
                        "WHERE product.name=? AND purchase.date>=? AND purchase.date<?\n" +
                        "GROUP BY buyer.firstname, buyer.lastname", new Object[]{name, beginDate, endDate},
                new Query2Mapper());
    }

    public List<Query> query3(Long id){
        return jdbcTemplate.query("SELECT product.name AS \"product name\", store_product.amount AS \"store product amount\"\n" +
                        "FROM store\n" +
                        "INNER JOIN store_product ON store_product.store_id = store.id\n" +
                        "INNER JOIN product ON product.id=store_product.product_id\n" +
                        "WHERE store.id = ?", new Object[]{id},
                new Query3Mapper());
    }

    public List<Query> query4(String productName){
        return jdbcTemplate.query("SELECT product.name AS \"product name\", store_product.amount AS \"store product amount\", store_product.price AS \"store product price\"\n" +
                        "FROM store\n" +
                        "INNER JOIN store_product ON store.id=store_product.store_id\n" +
                        "INNER JOIN product ON product.id=store_product.product_id\n" +
                        "WHERE product.name=?", new Object[]{productName},
                new Query4Mapper());
    }

    public List<Query> query4(String productName, Long id){
        return jdbcTemplate.query("SELECT product.name AS \"product name\", store_product.amount AS \"store product amount\", store_product.price AS \"store product price\"\n" +
                        "FROM store\n" +
                        "INNER JOIN store_product ON store.id=store_product.store_id\n" +
                        "INNER JOIN product ON product.id=store_product.product_id\n" +
                        "WHERE product.name=? AND store.id=?", new Object[]{productName, id},
                new Query4Mapper());
    }

    public List<Query> query4(String productName, String type){
        return jdbcTemplate.query("SELECT product.name AS \"product name\", store_product.amount AS \"store product amount\", store_product.price AS \"store product price\"\n" +
                        "FROM store\n" +
                        "INNER JOIN store_product ON store.id=store_product.store_id\n" +
                        "INNER JOIN product ON product.id=store_product.product_id\n" +
                        "WHERE product.name=? AND store.type=?", new Object[]{productName, type},
                new Query4Mapper());
    }

    public List<Query> query5(Date beginDate, Date endDate){
        return jdbcTemplate.query("SELECT count(store_product.price) AS amount\n" +
                        "FROM purchase\n" +
                        "INNER JOIN purchase_element ON purchase_element.purchase_id = purchase.id\n" +
                        "INNER JOIN store_product ON store_product.product_id = purchase_element.store_product_product_id AND store_product.store_id = purchase_element.store_product_store_id\n" +
                        "INNER JOIN seller ON seller.id = purchase.seller_id\n" +
                        "INNER JOIN store ON store.id=purchase.store_id\n" +
                        "WHERE  purchase.date>=? AND purchase.date<?", new Object[]{beginDate, endDate},
                new Query5Mapper());
    }

    public List<Query> query5(Date beginDate, Date endDate, String type){
        return jdbcTemplate.query("SELECT count(store_product.price) AS amount\n" +
                        "FROM purchase\n" +
                        "INNER JOIN purchase_element ON purchase_element.purchase_id = purchase.id\n" +
                        "INNER JOIN store_product ON store_product.product_id = purchase_element.store_product_product_id AND store_product.store_id = purchase_element.store_product_store_id\n" +
                        "INNER JOIN seller ON seller.id = purchase.seller_id\n" +
                        "INNER JOIN store ON store.id=purchase.store_id\n" +
                        "WHERE  purchase.date>=? AND purchase.date<? AND store.type=?", new Object[]{beginDate, endDate, type},
                new Query5Mapper());
    }

    public List<Query> query6(Date beginDate, Date endDate, Long sellerId, Long storeId){
        return jdbcTemplate.query("SELECT count(store_product.price) AS amount\n" +
                        "FROM purchase\n" +
                        "INNER JOIN purchase_element ON purchase_element.purchase_id = purchase.id\n" +
                        "INNER JOIN store_product ON store_product.product_id = purchase_element.store_product_product_id AND store_product.store_id = purchase_element.store_product_store_id\n" +
                        "INNER JOIN seller ON seller.id = purchase.seller_id\n" +
                        "INNER JOIN store ON store.id=purchase.store_id\n" +
                        "WHERE purchase.date>=? AND purchase.date<? AND seller.id=? AND store.id=?", new Object[]{beginDate, endDate, sellerId, storeId},
                new Query5Mapper());
    }

    public List<Query> query7(String productName, Date beginDate, Date endDate){
        return jdbcTemplate.query("SELECT count(store_product.amount) AS \"store product amount\"\n" +
                        "FROM purchase\n" +
                        "INNER JOIN purchase_element ON purchase_element.purchase_id = purchase.id\n" +
                        "INNER JOIN store_product ON store_product.product_id = purchase_element.store_product_product_id AND store_product.store_id = purchase_element.store_product_store_id\n" +
                        "INNER JOIN product ON product.id=store_product.product_id\n" +
                        "INNER JOIN seller ON seller.id = purchase.seller_id\n" +
                        "INNER JOIN store ON store.id=purchase.store_id\n" +
                        "WHERE product.name=? AND purchase.date>=? AND purchase.date<?", new Object[]{productName, beginDate, endDate},
                new Query7Mapper());
    }

    public List<Query> query7(String productName, Date beginDate, Date endDate, Long id){
        return jdbcTemplate.query("SELECT count(store_product.amount) AS \"store product amount\"\n" +
                        "FROM purchase\n" +
                        "INNER JOIN purchase_element ON purchase_element.purchase_id = purchase.id\n" +
                        "INNER JOIN store_product ON store_product.product_id = purchase_element.store_product_product_id AND store_product.store_id = purchase_element.store_product_store_id\n" +
                        "INNER JOIN product ON product.id=store_product.product_id\n" +
                        "INNER JOIN seller ON seller.id = purchase.seller_id\n" +
                        "INNER JOIN store ON store.id=purchase.store_id\n" +
                        "WHERE product.name=? AND purchase.date>=? AND purchase.date<? AND store.id=?", new Object[]{productName, beginDate, endDate, id},
                new Query7Mapper());
    }

    public List<Query> query7(String productName, Date beginDate, Date endDate, String type){
        return jdbcTemplate.query("SELECT count(store_product.amount) AS \"store product amount\"\n" +
                        "FROM purchase\n" +
                        "INNER JOIN purchase_element ON purchase_element.purchase_id = purchase.id\n" +
                        "INNER JOIN store_product ON store_product.product_id = purchase_element.store_product_product_id AND store_product.store_id = purchase_element.store_product_store_id\n" +
                        "INNER JOIN product ON product.id=store_product.product_id\n" +
                        "INNER JOIN seller ON seller.id = purchase.seller_id\n" +
                        "INNER JOIN store ON store.id=purchase.store_id\n" +
                        "WHERE product.name=? AND purchase.date>=? AND purchase.date<? AND store.type=?", new Object[]{productName, beginDate, endDate, type},
                new Query7Mapper());
    }

    public List<Query> query8(){
        return jdbcTemplate.query("SELECT seller.firstname AS \"seller firstname\", seller.lastname AS \"seller lastname\", seller.salary AS \"seller salary\"\n" +
                        "FROM seller\n" +
                        "INNER JOIN store ON store.id=seller.store_id", new Object[]{},
                new Query8Mapper());
    }

    public List<Query> query8(String type){
        return jdbcTemplate.query("SELECT seller.firstname AS \"seller firstname\", seller.lastname AS \"seller lastname\", seller.salary AS \"seller salary\"\n" +
                        "FROM seller\n" +
                        "INNER JOIN store ON store.id=seller.store_id\n" +
                        "WHERE store.type=?", new Object[]{type},
                new Query8Mapper());
    }

    public List<Query> query8(Long id){
        return jdbcTemplate.query("SELECT seller.firstname AS \"seller firstname\", seller.lastname AS \"seller lastname\", seller.salary AS \"seller salary\"\n" +
                        "FROM seller\n" +
                        "INNER JOIN store ON store.id=seller.store_id\n" +
                        "WHERE store.id=?", new Object[]{id},
                new Query8Mapper());
    }

    public List<Query> query9(String productName, String providerName){
        return jdbcTemplate.query("SELECT product.name AS \"product name\", SUM(order_product.amount) AS amount\n" +
                        "FROM order_product \n" +
                        "INNER JOIN product ON product.id=order_product.product_id\n" +
                        "INNER JOIN \"order\" ON order_product.order_id = \"order\".id\n" +
                        "INNER JOIN provider ON provider.id=\"order\".provider_id\n" +
                        "WHERE product.name=? AND provider.name=?\n" +
                        "GROUP BY product.id", new Object[]{productName, providerName},
                new Query9Mapper());
    }

    public List<Query> query9(String productName, String providerName, Date beginDate, Date endDate){
        return jdbcTemplate.query("SELECT product.name AS \"product name\", SUM(order_product.amount) AS amount\n" +
                        "FROM order_product \n" +
                        "INNER JOIN product ON product.id=order_product.product_id\n" +
                        "INNER JOIN \"order\" ON order_product.order_id = \"order\".id\n" +
                        "INNER JOIN provider ON provider.id=\"order\".provider_id\n" +
                        "WHERE product.name=? AND provider.name=? AND \"order\".date>=? AND \"order\".date<?\n" +
                        "GROUP BY product.id",
                new Object[]{productName, providerName, beginDate, endDate},
                new Query9Mapper());
    }

    public List<Query> query10(Long id){
        return jdbcTemplate.query("WITH sale_sum_by_store AS(\n" +
                        "SELECT store.id AS sid, SUM(purchase_element.sold_amount*store_product.price) AS result\n" +
                        "FROM store\n" +
                        "INNER JOIN purchase ON purchase.store_id=store.id\n" +
                        "INNER JOIN purchase_element ON purchase_element.purchase_id=purchase.id\n" +
                        "INNER JOIN store_product ON store_product.product_id = purchase_element.store_product_product_id AND store_product.store_id = purchase_element.store_product_store_id\n" +
                        "WHERE store.id=? \n" +
                        "GROUP by store.id\n" +
                        "),\n" +
                        "area_sum AS(\n" +
                        "SELECT store.id AS sid, area AS result\n" +
                        "FROM store\n" +
                        "WHERE store.id=?\n" +
                        ")\n" +
                        "SELECT sale_sum_by_store.result/area_sum.result AS \"main result\"\n" +
                        "FROM store\n" +
                        "INNER JOIN sale_sum_by_store ON sale_sum_by_store.sid=store.id\n" +
                        "INNER JOIN area_sum ON area_sum.sid=store.id\n" +
                        "WHERE store.id=?",
                new Object[]{id, id, id},
                new Query10Mapper());
    }

    public List<Query> query10(Long id, int op){
        return jdbcTemplate.query("WITH sale_sum_by_store AS(\n" +
                        "SELECT store.id AS sid, SUM(purchase_element.sold_amount*store_product.price) AS result\n" +
                        "FROM store\n" +
                        "INNER JOIN purchase ON purchase.store_id=store.id\n" +
                        "INNER JOIN purchase_element ON purchase_element.purchase_id=purchase.id\n" +
                        "INNER JOIN store_product ON store_product.product_id = purchase_element.store_product_product_id AND store_product.store_id = purchase_element.store_product_store_id\n" +
                        "WHERE store.id=? \n" +
                        "GROUP BY store.id\n" +
                        "),\n" +
                        "counter_number AS(\n" +
                        "SELECT store.id AS sid, counter_number AS result\n" +
                        "FROM store\n" +
                        "WHERE store.id=?\n" +
                        ")\n" +
                        "SELECT sale_sum_by_store.result/counter_number.result AS \"main result\"\n" +
                        "FROM store\n" +
                        "INNER JOIN sale_sum_by_store ON sale_sum_by_store.sid=store.id\n" +
                        "INNER JOIN counter_number ON counter_number.sid=store.id\n" +
                        "WHERE store.id=?",
                new Object[]{id, id, id},
                new Query10Mapper());
    }

    public List<Query> query10(Long id, String type){
        return jdbcTemplate.query("WITH sale_sum_by_store AS(\n" +
                        "SELECT store.id AS sid, SUM(purchase_element.sold_amount*store_product.price) AS result\n" +
                        "FROM store\n" +
                        "INNER JOIN purchase ON purchase.store_id=store.id\n" +
                        "INNER JOIN purchase_element ON purchase_element.purchase_id=purchase.id\n" +
                        "INNER JOIN store_product ON store_product.product_id = purchase_element.store_product_product_id AND store_product.store_id = purchase_element.store_product_store_id\n" +
                        "WHERE store.id=? \n" +
                        "GROUP BY store.id\n" +
                        "),\n" +
                        "hall_count AS(\n" +
                        "SELECT store.id AS sid, store.hall_amount AS result\n" +
                        "FROM store\n" +
                        "WHERE store.id=?\n" +
                        ")\n" +
                        "SELECT sale_sum_by_store.result/hall_count.result AS \"main result\"\n" +
                        "FROM store\n" +
                        "INNER JOIN sale_sum_by_store ON sale_sum_by_store.sid=store.id\n" +
                        "INNER JOIN hall_count ON hall_count.sid=store.id\n" +
                        "WHERE store.type=?",
                new Object[]{id, id, type},
                new Query10Mapper());
        ///////////////////////////
    }

    public List<Query> query10(Long id, Long sellerId, String tmp){
        System.out.println("4 qur 10 "+id+" "+sellerId);
        return jdbcTemplate.query("WITH sale_sum_by_store AS(\n" +
                        "SELECT store.id AS sid, SUM(purchase_element.sold_amount*store_product.price) AS result\n" +
                        "FROM store\n" +
                        "INNER JOIN purchase ON purchase.store_id=store.id\n" +
                        "INNER JOIN purchase_element ON purchase_element.purchase_id=purchase.id\n" +
                        "INNER JOIN store_product ON store_product.product_id = purchase_element.store_product_product_id AND store_product.store_id = purchase_element.store_product_store_id\n" +
                        "WHERE store.id=? \n" +
                        "GROUP BY store.id\n" +
                        "),\n" +
                        "production_by_seller AS(\n" +
                        "SELECT store.id AS sid, COUNT(store_product.price) AS result\n" +
                        "FROM purchase\n" +
                        "INNER JOIN purchase_element ON purchase_element.purchase_id = purchase.id\n" +
                        "INNER JOIN store_product ON store_product.product_id = purchase_element.store_product_product_id AND store_product.store_id = purchase_element.store_product_store_id\n" +
                        "INNER JOIN store ON store.id=purchase.store_id\n" +
                        "WHERE purchase.seller_id=? AND store.id=?\n" +
                        "GROUP BY store.id\n" +
                        ")\n" +
                        "SELECT sale_sum_by_store.result/production_by_seller.result AS \"main result\"\n" +
                        "FROM store\n" +
                        "INNER JOIN sale_sum_by_store ON sale_sum_by_store.sid=store.id\n" +
                        "INNER JOIN production_by_seller ON production_by_seller.sid=store.id\n" +
                        "WHERE store.id=?",
                new Object[]{id, sellerId, id, id},
                new Query10Mapper());
    }

    public List<Query> query11(Long id, Date beginDate, Date endDate){
        return jdbcTemplate.query("SELECT store.id AS \"store id\", SUM(purchase_element.element_price)/(store.rent_payments+store.utilities+SUM(seller.salary))*(extract(month from ?::timestamp - ?::timestamp))\n" +
                        " AS result\n" +
                        "FROM store\n" +
                        "INNER JOIN seller ON seller.store_id=store.id\n" +
                        "INNER JOIN purchase ON purchase.store_id=store.id\n" +
                        "INNER JOIN purchase_element ON purchase_element.purchase_id=purchase.id\n" +
                        "INNER JOIN store_product ON store_product.product_id = purchase_element.store_product_product_id AND store_product.store_id = purchase_element.store_product_store_id\n" +
                        "WHERE purchase.date>=? AND purchase.date<? AND store.id=?\n" +
                        "GROUP BY store.id", new Object[]{endDate, beginDate, beginDate, endDate, id},
                new Query11Mapper());
    }

    public List<Query> query12(Long orderNumber){
        System.out.println("orderNumber "+orderNumber);
        return jdbcTemplate.query("SELECT product.name AS \"product name\", order_product.amount AS \"order product amount\", provider.name AS \"provider name\"\n" +
                        "FROM \"order\"\n" +
                        "INNER JOIN order_product ON order_product.order_id=\"order\".id\n" +
                        "INNER JOIN product ON product.id=order_product.product_id\n" +
                        "INNER JOIN provider ON \"order\".provider_id=provider.id\n" +
                        "WHERE \"order\".order_number=?", new Object[]{orderNumber},
                new Query12Mapper());
    }

    public List<Query> query13(String productName, Date beginDate, Date endDate){
        return jdbcTemplate.query("SELECT buyer.firstname AS \"buyer firstname\", buyer.lastname AS \"buyer lastname\"\n" +
                        "FROM purchase\n" +
                        "INNER JOIN buyer ON buyer.id=purchase.buyer_id AND purchase.buyer_id IS NOT NULL\n" +
                        "INNER JOIN purchase_element ON purchase_element.purchase_id = purchase.id\n" +
                        "INNER JOIN store_product ON store_product.product_id = purchase_element.store_product_product_id AND store_product.store_id = purchase_element.store_product_store_id\n" +
                        "INNER JOIN product ON product.id=store_product.product_id\n" +
                        "INNER JOIN store ON store.id=purchase.store_id\n" +
                        "WHERE product.name=? AND purchase.date>=? AND purchase.date<?", new Object[]{productName, beginDate, endDate},
                new Query13Mapper());
    }

    public List<Query> query13(String productName, Date beginDate, Date endDate, String type){
        System.out.println(beginDate);
        System.out.println(endDate);
        return jdbcTemplate.query("SELECT buyer.firstname AS \"buyer firstname\", buyer.lastname AS \"buyer lastname\"\n" +
                        "FROM purchase\n" +
                        "INNER JOIN buyer ON buyer.id=purchase.buyer_id AND purchase.buyer_id IS NOT NULL\n" +
                        "INNER JOIN purchase_element ON purchase_element.purchase_id = purchase.id\n" +
                        "INNER JOIN store_product ON store_product.product_id = purchase_element.store_product_product_id AND store_product.store_id = purchase_element.store_product_store_id\n" +
                        "INNER JOIN product ON product.id=store_product.product_id\n" +
                        "INNER JOIN store ON store.id=purchase.store_id\n" +
                        "WHERE product.name=? AND purchase.date>=? AND purchase.date<? AND store.type=?", new Object[]{productName, beginDate, endDate, type},
                new Query13Mapper());
    }

    public List<Query> query13(String productName, Date beginDate, Date endDate, Long id){
        System.out.println(beginDate);
        System.out.println(endDate);
        return jdbcTemplate.query("SELECT buyer.firstname AS \"buyer firstname\", buyer.lastname AS \"buyer lastname\"\n" +
                        "FROM purchase\n" +
                        "INNER JOIN buyer ON buyer.id=purchase.buyer_id AND purchase.buyer_id IS NOT NULL\n" +
                        "INNER JOIN purchase_element ON purchase_element.purchase_id = purchase.id\n" +
                        "INNER JOIN store_product ON store_product.product_id = purchase_element.store_product_product_id AND store_product.store_id = purchase_element.store_product_store_id\n" +
                        "INNER JOIN product ON product.id=store_product.product_id\n" +
                        "INNER JOIN store ON store.id=purchase.store_id\n" +
                        "WHERE product.name=? AND purchase.date>=? AND purchase.date<? AND store.id=?", new Object[]{productName, beginDate, endDate, id},
                new Query13Mapper());
    }

    public List<Query> query13(String productName){
        return jdbcTemplate.query("SELECT buyer.firstname AS \"buyer firstname\", buyer.lastname AS \"buyer lastname\"\n" +
                        "FROM purchase\n" +
                        "INNER JOIN buyer ON buyer.id=purchase.buyer_id AND purchase.buyer_id IS NOT NULL\n" +
                        "INNER JOIN purchase_element ON purchase_element.purchase_id = purchase.id\n" +
                        "INNER JOIN store_product ON store_product.product_id = purchase_element.store_product_product_id AND store_product.store_id = purchase_element.store_product_store_id\n" +
                        "INNER JOIN product ON product.id=store_product.product_id\n" +
                        "INNER JOIN store ON store.id=purchase.store_id\n" +
                        "WHERE product.name=?", new Object[]{productName},
                new Query13Mapper());
    }

    public List<Query> query13(String productName, Long id){
        return jdbcTemplate.query("SELECT buyer.firstname AS \"buyer firstname\", buyer.lastname AS \"buyer lastname\"\n" +
                        "FROM purchase\n" +
                        "INNER JOIN buyer ON buyer.id=purchase.buyer_id AND purchase.buyer_id IS NOT NULL\n" +
                        "INNER JOIN purchase_element ON purchase_element.purchase_id = purchase.id\n" +
                        "INNER JOIN store_product ON store_product.product_id = purchase_element.store_product_product_id AND store_product.store_id = purchase_element.store_product_store_id\n" +
                        "INNER JOIN product ON product.id=store_product.product_id\n" +
                        "INNER JOIN store ON store.id=purchase.store_id\n" +
                        "WHERE product.name=? AND store.id=?", new Object[]{productName, id},
                new Query13Mapper());
    }

    public List<Query> query13(String productName, String type){
        return jdbcTemplate.query("SELECT buyer.firstname AS \"buyer firstname\", buyer.lastname AS \"buyer lastname\"\n" +
                        "FROM purchase\n" +
                        "INNER JOIN buyer ON buyer.id=purchase.buyer_id AND purchase.buyer_id IS NOT NULL\n" +
                        "INNER JOIN purchase_element ON purchase_element.purchase_id = purchase.id\n" +
                        "INNER JOIN store_product ON store_product.product_id = purchase_element.store_product_product_id AND store_product.store_id = purchase_element.store_product_store_id\n" +
                        "INNER JOIN product ON product.id=store_product.product_id\n" +
                        "INNER JOIN store ON store.id=purchase.store_id\n" +
                        "WHERE product.name=? AND store.type=?", new Object[]{productName, type},
                new Query13Mapper());
    }

    public List<Query> query14(){
        return jdbcTemplate.query("WITH buyers_counter_store AS(\n" +
                        "\tSELECT buyer.id AS bid, COUNT(purchase.id) AS amount\n" +
                        "FROM purchase\n" +
                        "INNER JOIN buyer ON\n" +
                        "buyer.id=purchase.buyer_id AND purchase.buyer_id IS NOT NULL\n" +
                        "GROUP BY buyer.id\n" +
                        ")\n" +
                        "SELECT buyer.firstname AS \"buyer firstname\", buyer.lastname AS \"buyer lastname\"\n" +
                        "FROM buyers_counter_store\n" +
                        "INNER JOIN buyer ON buyer.id=buyers_counter_store.bid\n" +
                        "WHERE amount=(SELECT MAX(amount) FROM buyers_counter_store)", new Object[]{},
                new Query14Mapper());
    }

    public List<Query> query14(Long id){
        System.out.println("id "+id);
        return jdbcTemplate.query("WITH buyers_counter_store AS(\n" +
                        "\tSELECT buyer.id AS bid, COUNT(purchase.id) AS amount\n" +
                        "FROM purchase\n" +
                        "INNER JOIN buyer ON\n" +
                        "buyer.id=purchase.buyer_id AND purchase.buyer_id IS NOT NULL\n" +
                        "WHERE purchase.store_id=?\n" +
                        "GROUP BY buyer.id\n" +
                        ")\n" +
                        "SELECT buyer.firstname AS \"buyer firstname\", buyer.lastname AS \"buyer lastname\"\n" +
                        "FROM buyers_counter_store\n" +
                        "INNER JOIN buyer ON buyer.id=buyers_counter_store.bid\n" +
                        "WHERE amount=(SELECT MAX(amount) FROM buyers_counter_store)", new Object[]{id},
                new Query14Mapper());
    }

    public List<Query> query14(String type){
        System.out.println("type "+type);
        return jdbcTemplate.query("WITH buyers_counter_store AS(\n" +
                        "\tSELECT buyer.id AS bid, COUNT(purchase.id) AS amount\n" +
                        "FROM purchase\n" +
                        "INNER JOIN buyer ON\n" +
                        "buyer.id=purchase.buyer_id AND purchase.buyer_id IS NOT NULL\n" +
                        "INNER JOIN store ON store.id=purchase.store_id\n" +
                        "WHERE store.type=?\n" +
                        "GROUP BY buyer.id\n" +
                        ")\n" +
                        "SELECT buyer.firstname AS \"buyer firstname\", buyer.lastname AS \"buyer lastname\"\n" +
                        "FROM buyers_counter_store\n" +
                        "INNER JOIN buyer ON buyer.id=buyers_counter_store.bid\n" +
                        "WHERE amount=(SELECT MAX(amount) FROM buyers_counter_store)", new Object[]{type},
                new Query14Mapper());
    }

    public List<Query> query15(Date beginDate, Date endDate, String type){
        return jdbcTemplate.query("SELECT store.id AS \"store id\", SUM(purchase_element.element_price) AS \"trade turnover\"\n" +
                        "FROM purchase\n" +
                        "INNER JOIN store ON store.id=purchase.store_id\n" +
                        "INNER JOIN purchase_element ON purchase_element.purchase_id=purchase.id\n" +
                        "WHERE purchase.date>=? AND purchase.date<? AND store.type=?\n" +
                        "GROUP BY store.id", new Object[]{beginDate, endDate, type},
                new Query15Mapper());
    }

    public List<Query> query15(Date beginDate, Date endDate, Long id){
        return jdbcTemplate.query("SELECT store.id AS \"store id\", SUM(purchase_element.element_price) AS \"trade turnover\"\n" +
                        "FROM purchase\n" +
                        "INNER JOIN store ON store.id=purchase.store_id\n" +
                        "INNER JOIN purchase_element ON purchase_element.purchase_id=purchase.id\n" +
                        "WHERE purchase.date>=? AND purchase.date<? AND store.id=?\n" +
                        "GROUP BY store.id", new Object[]{beginDate, endDate, id},
                new Query15Mapper());
    }
}
