package com.joker.controller;

import com.joker.service.impl.EssayMapperServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: Joker
 * @name:
 * @Date: 2020/7/15 20:45
 * @Describe:
 **/
@Controller
@RequestMapping("/essay")
public class EssayMapperController {

    @Autowired
    EssayMapperServiceImpl service;

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    @ResponseBody
    public Object Test(){
        Map<String, Object> results = new HashMap<>();
        results.put("message",service.test());
        results.put("code","good");
        return results;
    }

}
