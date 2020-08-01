package com.joker.mapper;

import com.joker.pojo.Tag;

import java.util.List;

/**
 * @author: Joker
 * @name: TagMapper
 * @Date: 2020/7/20 20:48
 * @Describe:
 **/
public interface TagMapper {

    void insertTag(Tag tag);

    List<Tag> getTag(String tagName);

    List<Tag> getAllTag();

}
