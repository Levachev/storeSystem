package com.example.store.controller;

import com.example.store.entity.*;
import com.example.store.repo.CrudRepo;
import com.example.store.repo.QueryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/query")
public class QueryController {

    private final QueryRepo repo;
    private final CrudRepo crudRepo;

    @Autowired
    public QueryController(QueryRepo repo, CrudRepo crudRepo){
        this.repo=repo;
        this.crudRepo=crudRepo;
    }

    public static String getTableView(List<Query> result) {
        StringBuilder ret = new StringBuilder();
        if(result == null || result.isEmpty()){
            return "результаты не найдены в бд";
        }
        ret.append(result.getFirst().title()).append("\n").append("\n");

        for(Query query : result){
            ret.append(query.values()).append("\n");
        }

        return ret.toString();
    }

    @GetMapping
    public ModelAndView getQueryMenu(){
        return new ModelAndView("queryMenu.html");
    }

    @GetMapping(value = "/get_query")
    public ModelAndView getQueryPage(@RequestParam String query){
        System.out.println(query);
        switch (query) {
            case "query1", "query2" -> {
                return new ModelAndView(query+".html")
                        .addObject("prod_list", crudRepo.getProdList());
            }
            case "query3", "query8", "query14", "query15", "query11" -> {
                return new ModelAndView(query+".html")
                        .addObject("store_list", crudRepo.getStoreList());
            }
            case "query4", "query7", "query13" -> {
                return new ModelAndView(query+".html")
                        .addObject("prod_list", crudRepo.getProdList())
                        .addObject("store_list", crudRepo.getStoreList());
            }
            case "query6", "query10" -> {
                return new ModelAndView(query+".html")
                        .addObject("seller_list", crudRepo.getSellerList())
                        .addObject("store_list", crudRepo.getStoreList());
            }
            case "query9" -> {
                return new ModelAndView(query+".html")
                        .addObject("prod_list", crudRepo.getProdList())
                        .addObject("prov_list", crudRepo.getProvList());
            }
            case "query12" -> {
                return new ModelAndView(query+".html")
                        .addObject("order_list", crudRepo.getOrderList());
            }
        }
        return new ModelAndView(query+".html");
    }

    @GetMapping(value = "/query1", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String query1(@RequestParam Map<String, String> allRequestParams) throws UnsupportedEncodingException {
        System.out.println("tut q1");
        String name = null;
        Date beginDate = null, endDate = null;
        int amount = 0;
        for (var entry : allRequestParams.entrySet()) {
            switch (entry.getKey()) {
                case "name" -> name = entry.getValue();
                case "begindate" -> beginDate = Date.valueOf(entry.getValue());
                case "enddate" -> endDate = Date.valueOf(entry.getValue());
                case "amount" -> amount = Integer.parseInt(entry.getValue());
            }

        }
        System.out.println("name "+name);
        int paramSize = allRequestParams.size();
        List<Query> query = null;
        if(paramSize==1){
            query = repo.query1(name);
        }else if(paramSize==4){
            query = repo.query1(name, amount, beginDate, endDate);
        }

        System.out.println(getTableView(query));
        return getTableView(query);
    }

    @GetMapping(value = "/query2", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String query2(@RequestParam Map<String, String> allRequestParams) throws UnsupportedEncodingException {
        String name = null;
        Date beginDate = null, endDate = null;
        int amount = 0;
        for (var entry : allRequestParams.entrySet()) {
            System.out.println(entry.getKey());
            switch (entry.getKey()) {
                case "name" -> name = entry.getValue();
                case "begindate" -> beginDate = Date.valueOf(entry.getValue());
                case "enddate" -> endDate = Date.valueOf(entry.getValue());
                case "amount" -> amount = Integer.parseInt(entry.getValue());
            }
        }
        int paramSize = allRequestParams.size();
        List<Query> query = null;
        if(paramSize==2){
            query = repo.query2(name, amount);
        } else if(paramSize==3){
            query = repo.query2(name, beginDate, endDate);
        }

        System.out.println(getTableView(query));

        return getTableView(query);
    }

    @GetMapping(value = "/query3", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String query3(@RequestParam Long id) throws UnsupportedEncodingException {
        List<Query> query = repo.query3(id);
        System.out.println(getTableView(query));
        return getTableView(query);
    }

    @GetMapping(value = "/query4", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String query4(@RequestParam Map<String, String> allRequestParams) throws UnsupportedEncodingException {
        String productName = null;
        Long id = null;
        String type = null;
        for (var entry : allRequestParams.entrySet()) {
            switch (entry.getKey()) {
                case "pname" -> productName = entry.getValue();
                case "id" -> id = Long.valueOf(entry.getValue());
                case "type" -> type = entry.getValue();
            }
        }

        int paramSize = allRequestParams.size();
        List<Query> query = null;
        if(paramSize==1){
            query = repo.query4(productName);
        } else if(paramSize==2 && type==null){
            query = repo.query4(productName, id);
        } else if (paramSize == 2) {
            query = repo.query4(productName, type);
        }

        System.out.println(getTableView(query));
        return getTableView(query);
    }

    @GetMapping(value = "/query5", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String query5(@RequestParam Map<String, String> allRequestParams) throws UnsupportedEncodingException {
        String type = null;
        Date beginDate = null, endDate = null;
        for (var entry : allRequestParams.entrySet()) {
            switch (entry.getKey()) {
                case "type" -> type = entry.getValue();
                case "begindate" -> beginDate = Date.valueOf(entry.getValue());
                case "enddate" -> endDate = Date.valueOf(entry.getValue());
            }
        }
        int paramSize = allRequestParams.size();
        List<Query> query = null;
        if(paramSize==2){
            query = repo.query5(beginDate, endDate);
        } else if(paramSize==3){
            query = repo.query5(beginDate, endDate, type);
        }

        System.out.println(getTableView(query));
        return getTableView(query);
    }

    @GetMapping(value = "/query6", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String query6(@RequestParam Date begindate, @RequestParam Date enddate,
                         @RequestParam Long sellerId, @RequestParam Long storeId) throws UnsupportedEncodingException {
        List<Query> query = repo.query6(begindate, enddate, sellerId, storeId);
        System.out.println(getTableView(query));
        return getTableView(query);
    }

    @GetMapping(value = "/query7", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String query7(@RequestParam Map<String, String> allRequestParams) throws UnsupportedEncodingException {
        String type = null;
        Date beginDate = null, endDate = null;
        Long id = null;
        String product_name = null;
        for (var entry : allRequestParams.entrySet()) {
            switch (entry.getKey()) {
                case "type" -> type = entry.getValue();
                case "begindate" -> beginDate = Date.valueOf(entry.getValue());
                case "enddate" -> endDate = Date.valueOf(entry.getValue());
                case "pname" -> product_name = entry.getValue();
                case "id" -> id = Long.valueOf(entry.getValue());
            }
        }
        int paramSize = allRequestParams.size();
        List<Query> query = null;
        if(paramSize == 3){
            query = repo.query7(product_name, beginDate, endDate);
        } else if(paramSize == 4 && type == null){
            query = repo.query7(product_name, beginDate, endDate, id);
        } else if(paramSize == 4) {
            query = repo.query7(product_name, beginDate, endDate, type);
        }

        System.out.println(getTableView(query));
        return getTableView(query);
    }

    @GetMapping(value = "/query8", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String query8(@RequestParam Map<String, String> allRequestParams) throws UnsupportedEncodingException {
        String type = null;
        Long id = null;
        for (var entry : allRequestParams.entrySet()) {
            if(entry.getKey().equals("type")){
                type = entry.getValue();
            } else if(entry.getKey().equals("id")){
                id = Long.valueOf(entry.getValue());
            }
        }
        int paramSize = allRequestParams.size();
        List<Query> query;
        if(paramSize == 0){
            query = repo.query8();
        } else if(type == null){
            query = repo.query8(id);
        } else {
            query = repo.query8(type);
        }

        System.out.println(getTableView(query));
        return getTableView(query);
    }

    @GetMapping(value = "/query9", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String query9(@RequestParam Map<String, String> allRequestParams) throws UnsupportedEncodingException {
        String providerName = null;
        String productName = null;
        Date beginDate = null, endDate = null;
        for (var entry : allRequestParams.entrySet()) {
            switch (entry.getKey()) {
                case "pname" -> productName = entry.getValue();
                case "providername" -> providerName = entry.getValue();
                case "begindate" -> beginDate = Date.valueOf(entry.getValue());
                case "enddate" -> endDate = Date.valueOf(entry.getValue());
            }
        }
        int paramSize = allRequestParams.size();
        List<Query> query;
        if(paramSize == 2){
            query = repo.query9(productName, providerName);
        } else {
            query = repo.query9(productName, providerName, beginDate, endDate);
        }

        System.out.println(getTableView(query));
        return getTableView(query);
    }

    @GetMapping(value = "/query10", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String query10(@RequestParam Map<String, String> allRequestParams) throws UnsupportedEncodingException {
        String type = null;
        Long id = null;
        Long sellerId = null;
        int op = 0;
        for (var entry : allRequestParams.entrySet()) {
            switch (entry.getKey()) {
                case "seller_id" -> sellerId = Long.valueOf(entry.getValue());
                case "type" -> type = entry.getValue();
                case "id" -> id = Long.valueOf(entry.getValue());
                case "op" -> op = Integer.parseInt(entry.getValue());
            }
        }

        List<Query> query = null;
        if(op == 1) {
            query = repo.query10(id);
        } else if(op == 2) {
            query = repo.query10(id, op);
        } else if(op == 3) {
            query = repo.query10(id, type);
        } else if(op == 4) {
            query = repo.query10(id, sellerId, "tmp");
        }

        System.out.println(getTableView(query));
        return getTableView(query);
    }

    @GetMapping(value = "/query11", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String query11(@RequestParam Long id, @RequestParam Date begindate, @RequestParam Date enddate) throws UnsupportedEncodingException {
        List<Query> query = repo.query11(id, begindate, enddate);
        System.out.println(getTableView(query));
        return getTableView(query);
    }

    @GetMapping(value = "/query12", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String query12(@RequestParam Long ordernumber) throws UnsupportedEncodingException {
        List<Query> query = repo.query12(ordernumber);
        System.out.println(getTableView(query));
        return getTableView(query);
    }

    @GetMapping(value = "/query13", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String query13(@RequestParam Map<String, String> allRequestParams) throws UnsupportedEncodingException {
        String type=null;
        Long id = null;
        String productName = null;
        Date beginDate = null, endDate = null;
        int op=0;
        for (var entry : allRequestParams.entrySet()) {
            switch (entry.getKey()) {
                case "pname" -> productName = entry.getValue();
                case "id" -> id = Long.parseLong(entry.getValue());
                case "begindate" -> beginDate = Date.valueOf(entry.getValue());
                case "enddate" -> endDate = Date.valueOf(entry.getValue());
                case "type" -> type = entry.getValue();
                case "op" -> op = Integer.parseInt(entry.getValue());
            }
        }

        List<Query> query = null;
        if(op == 1) {
            query = repo.query13(productName, beginDate, endDate, id);
        } else if(op == 2) {
            query = repo.query13(productName, beginDate, endDate, type);
        } else if(op == 3) {
            query = repo.query13(productName, beginDate, endDate);
        } else if(op == 4) {
            query = repo.query13(productName, id);
        } else if(op == 5) {
            query = repo.query13(productName, type);
        } else if(op == 6) {
            query = repo.query13(productName);
        }

        System.out.println(getTableView(query));
        return getTableView(query);
    }

    @GetMapping(value = "/query14", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String query14(@RequestParam Map<String, String> allRequestParams) throws UnsupportedEncodingException {
        Long id = null;
        String type = null;
        for (var entry : allRequestParams.entrySet()) {
            if(entry.getKey().equals("id")){
                id = Long.valueOf(entry.getValue());
            } else if(entry.getKey().equals("type")){
                type = entry.getValue();
            }
        }
        int paramSize = allRequestParams.size();
        List<Query> query = null;
        if(paramSize == 0){
            query = repo.query14();
        } else if(paramSize==1 && type == null){
            query = repo.query14(id);
        } else if(paramSize==1){
            query = repo.query14(type);
        }

        System.out.println(getTableView(query));
        return getTableView(query);
    }

    @GetMapping(value = "/query15", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String query15(@RequestParam Map<String, String> allRequestParams) throws UnsupportedEncodingException {
        String type = null;
        Date beginDate = null, endDate = null;
        Long id = null;
        for (var entry : allRequestParams.entrySet()) {
            switch (entry.getKey()) {
                case "type" -> type = entry.getValue();
                case "begindate" -> beginDate = Date.valueOf(entry.getValue());
                case "enddate" -> endDate = Date.valueOf(entry.getValue());
                case "id" -> id = Long.valueOf(entry.getValue());
            }
        }
        List<Query> query;
        if(type == null){
            query = repo.query15(beginDate, endDate, id);
        } else {
            query = repo.query15(beginDate, endDate, type);
        }

        System.out.println(getTableView(query));
        return getTableView(query);
    }
}
