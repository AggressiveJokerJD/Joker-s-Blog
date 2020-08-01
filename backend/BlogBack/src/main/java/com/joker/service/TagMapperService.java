package com.joker.service;

import com.joker.pojo.Tag;

import java.util.List;

/**
 * @author: Joker
 * @name: TagMapperService
 * @Date: 2020/7/22 16:42
 * @Describe:
 **/
public interface TagMapperService {

    void insertTag(Tag tag);

    List<Tag> getTag(String tagName);

    List<Tag> getAllTag();

}
