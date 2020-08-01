package com.joker.service.impl;

import com.joker.mapper.ArticleTagMapper;
import com.joker.mapper.TagMapper;
import com.joker.pojo.Tag;
import com.joker.service.TagMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: Joker
 * @name: TagMapperServiceImpl
 * @Date: 2020/7/22 16:43
 * @Describe:
 **/
@Service
public class TagMapperServiceImpl implements TagMapperService {

    @Autowired
    TagMapper mapper;

    @Override
    public void insertTag(Tag tag) {
        mapper.insertTag(tag);
    }

    @Override
    public List<Tag> getTag(String tagName) {
        List<Tag> result = mapper.getTag(tagName);
        return result;
    }

    @Override
    public List<Tag> getAllTag() {
        List<Tag> result = mapper.getAllTag();
        return result;
    }
}
