package com.example.store.controller;

import com.example.store.entity.Query;
import com.example.store.entity.Store;
import com.example.store.repo.CrudRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/crud")
public class CrudController {

    private final CrudRepo repo;

    @Autowired
    public CrudController(CrudRepo repo){
        this.repo=repo;
    }

    @GetMapping("/all")
    public ModelAndView getCrudMenu(){
        return new ModelAndView("crudMenu.html");
    }

    @GetMapping("/crud_page")
    public ModelAndView getCreateEntityPage(@RequestParam String operation, @RequestParam String entity){
        if(operation.equals("update") || operation.equals("get") || operation.equals("delete")){
            if(entity.equals("provider_product")){
                return new ModelAndView(operation+"_"+entity+".html")
                        .addObject("prov_list", repo.getProvList())
                        .addObject("prod_list", repo.getProdList());
            }
        }
        if(entity.equals("store")){
            if(operation.equals("get") || operation.equals("delete")){
                return new ModelAndView(operation+"_"+entity+".html")
                        .addObject("store_list", repo.getStoreList());
            }
        }
        if(entity.equals("store_choose")){
            return new ModelAndView(operation+"_"+entity+".html")
                    .addObject("store_list", repo.getStoreList());
        }

        return new ModelAndView(operation+"_"+entity+".html");
    }

    @GetMapping("/create/provider_product")
    public String createProviderProduct(@RequestParam Map<String, String> allRequestParams) {
        String providerName = null;
        String productName = null;
        int amount = -1;
        for (var entry : allRequestParams.entrySet()) {
            switch (entry.getKey()) {
                case "providername" -> providerName = entry.getValue();
                case "productname" -> productName = entry.getValue();
                case "amount" -> amount = Integer.parseInt(entry.getValue());
            }

        }
        repo.createProviderProduct(providerName, productName, amount);
        return "ok";
    }

    @GetMapping("/update/provider_product")
    public String updateProviderProduct(@RequestParam Long productId, @RequestParam Long providerId, @RequestParam int amount){
        repo.updateProviderProduct(productId, providerId, amount);
        return "ok";
    }

    @GetMapping("/delete/provider_product")
    public String deleteProviderProduct(@RequestParam Long productId, @RequestParam Long providerId){
        repo.deleteProviderProduct(productId, providerId);
        return "ok";
    }

    @GetMapping(value = "/get/provider_product", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getProviderProduct(@RequestParam Long productId, @RequestParam Long providerId){
        List<Query> query = repo.getProviderProduct(productId, providerId);
        return QueryController.getTableView(query);
    }

    @GetMapping("/create/store")
    public String createStore(@RequestParam Map<String, String> allRequestParams){
        String storeOrganisationName = null;
        int outletSize = 0;
        int rentPayments = 0;
        int utilities = 0;
        int counterNumber = 0;
        int hallAmount = 0;
        int floorAmount = 0;
        int sectionAmount = 0;
        String type = null;
        String address = null;
        int area = 0;
        for (var entry : allRequestParams.entrySet()) {
            if(entry.getKey().equals("orgid")){
                Long.valueOf(entry.getValue());
            } else if(entry.getKey().equals("orgname")){
                storeOrganisationName = entry.getValue();
            } else if(entry.getKey().equals("sid")){
                Long.valueOf(entry.getValue());
            } else if(entry.getKey().equals("outl")){
                outletSize = Integer.parseInt(entry.getValue());
            } else if(entry.getKey().equals("rent")){
                rentPayments = Integer.parseInt(entry.getValue());
            } else if(entry.getKey().equals("util")){
                utilities = Integer.parseInt(entry.getValue());
            } else if(entry.getKey().equals("coun")){
                counterNumber = Integer.parseInt(entry.getValue());
            } else if(entry.getKey().equals("hall")){
                hallAmount = Integer.parseInt(entry.getValue());
            } else if(entry.getKey().equals("floor")){
                floorAmount = Integer.parseInt(entry.getValue());
            } else if(entry.getKey().equals("section")){
                sectionAmount = Integer.parseInt(entry.getValue());
            } else if(entry.getKey().equals("type")){
                type = entry.getValue();
            } else if(entry.getKey().equals("addr")){
                address = entry.getValue();
            } else if(entry.getKey().equals("area")){
                area = Integer.parseInt(entry.getValue());
            }
        }
        repo.createStore(storeOrganisationName, outletSize, rentPayments,
                utilities, counterNumber, hallAmount,
                floorAmount, sectionAmount, type, address, area);
        return "ok";
    }

    @GetMapping(value = "/update/choose/store", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ModelAndView chooseStore(@RequestParam Long id){
        var ret = new ModelAndView("update_store.html");
        List<Query> storeList = repo.getStore(id);
        if(storeList.isEmpty()){
            return new ModelAndView("error.html");
        }
        Store store = (Store) storeList.getFirst();
        ret.addObject("sid", id);
        ret.addObject("outl", store.getOutlet_size());
        ret.addObject("rent", store.getRent_payments());
        ret.addObject("util", store.getUtilities());
        ret.addObject("coun", store.getCounter_number());
        ret.addObject("hall", store.getHall_amount());
        ret.addObject("section", store.getSection_amount());
        ret.addObject("floor", store.getFloor_amount());
        ret.addObject("addr", store.getAddress().replaceAll(" ", "_"));
        System.out.println(store.getAddress());
        ret.addObject("area", store.getArea());

        return ret;
    }

    @GetMapping("/update/store")
    public String updateStore(@RequestParam Map<String, String> allRequestParams){
        Long storeId = null;
        int outletSize = 0;
        int rentPayments = 0;
        int utilities = 0;
        int counterNumber = 0;
        int hallAmount = 0;
        int floorAmount = 0;
        int sectionAmount = 0;
        String address = null;
        int area = 0;
        for (var entry : allRequestParams.entrySet()) {
            if(entry.getKey().equals("sid")){
                storeId = Long.valueOf(entry.getValue());
            } else if(entry.getKey().equals("outl")){
                outletSize = Integer.parseInt(entry.getValue());
            } else if(entry.getKey().equals("rent")){
                rentPayments = Integer.parseInt(entry.getValue());
            } else if(entry.getKey().equals("util")){
                utilities = Integer.parseInt(entry.getValue());
            } else if(entry.getKey().equals("coun")){
                counterNumber = Integer.parseInt(entry.getValue());
            } else if(entry.getKey().equals("hall")){
                hallAmount = Integer.parseInt(entry.getValue());
            } else if(entry.getKey().equals("floor")){
                floorAmount = Integer.parseInt(entry.getValue());
            } else if(entry.getKey().equals("section")){
                sectionAmount = Integer.parseInt(entry.getValue());
            } else if(entry.getKey().equals("addr")){
                address = entry.getValue();
            } else if(entry.getKey().equals("area")){
                area = Integer.parseInt(entry.getValue());
            }
        }
        repo.updateStore(storeId, outletSize, rentPayments, utilities, counterNumber,
                hallAmount, floorAmount, sectionAmount, address, area);
        return "ok";
    }

    @GetMapping("/delete/store")
    public String deleteStore(@RequestParam Long sid){
        repo.deleteStore(sid);
        return "ok";
    }

    @GetMapping(value = "/get/store", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getStore(@RequestParam Long sid){
        List<Query> query = repo.getStore(sid);
        return QueryController.getTableView(query);
    }
}
