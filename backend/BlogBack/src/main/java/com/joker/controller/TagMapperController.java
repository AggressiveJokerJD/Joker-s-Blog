package com.joker.controller;

import com.joker.pojo.Tag;
import com.joker.service.impl.TagMapperServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.LinkedList;
import java.util.List;

/**
 * @author: Joker
 * @name: TagMapperController
 * @Date: 2020/7/24 15:53
 * @Describe:
 **/
@Controller
@CrossOrigin
@RequestMapping("/tag")
public class TagMapperController {

    @Autowired
    TagMapperServiceImpl service;

    @RequestMapping(value = "/getTagList", method = RequestMethod.POST)
    @ResponseBody
    public Object getTagList(){
        List<String> result = new LinkedList<>();
        List<Tag> tagList = service.getAllTag();
        for(Tag tag : tagList){
            result.add(tag.getTag_name());
        }
        return result;
    }
}
