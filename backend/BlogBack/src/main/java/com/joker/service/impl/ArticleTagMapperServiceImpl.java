package com.joker.service.impl;

import com.joker.mapper.ArticleTagMapper;
import com.joker.mapper.TagMapper;
import com.joker.pojo.Article_Tag;
import com.joker.service.ArticleTagMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: Joker
 * @name: ArticleTagMapperServiceImpl
 * @Date: 2020/7/22 16:43
 * @Describe:
 **/
@Service
public class ArticleTagMapperServiceImpl implements ArticleTagMapperService {

    @Autowired
    ArticleTagMapper mapper;

    @Override
    public void insertArticleTag(Article_Tag article_tag) {
        mapper.insertArticleTag(article_tag);
    }

    @Override
    public List<Article_Tag> getArticleTag(String tag_name, String article_name) {
        List<Article_Tag> result = mapper.getArticleTag(tag_name,article_name);
        return result;
    }

    @Override
    public List<Article_Tag> getArticleTag(String tag_name) {
        List<Article_Tag> result = mapper.getArticleTagByTag(tag_name);
        return result;
    }
}
