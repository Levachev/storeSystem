package com.example.store.controller;

import com.example.store.entity.Test;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/test")
    public ModelAndView test(){
        List<Test> params = new ArrayList<>();
        params.add(new Test(1, "11"));
        params.add(new Test(2, "22"));
        params.add(new Test(3, "33"));
        params.add(new Test(4, "44"));
//        List<String> params = new ArrayList<>();
//        params.add("11");
//        params.add("22");
//        params.add("33");
//        params.add("44");
        var tmp = new ModelAndView("test.html");
        tmp.addObject("params", params);
        tmp.addObject("name", "hellotyruyrturu");
        return tmp;
    }
}
