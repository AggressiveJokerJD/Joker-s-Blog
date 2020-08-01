package com.joker.service.impl;

import com.joker.mapper.ArticleMapper;
import com.joker.pojo.Article;
import com.joker.service.ArticleMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: Joker
 * @name: ArticleMapperServiceImpl
 * @Date: 2020/7/20 15:34
 * @Describe: The implements of articleMapperService.
 **/
@Service
public class ArticleMapperServiceImpl implements ArticleMapperService {

    @Autowired
    ArticleMapper mapper;

    @Override
    public void insertArticle(Article article) {
        mapper.insertArticle(article);
    }

    @Override
    public List<Article> getArticle(String title) {
        List<Article> result = mapper.getArticle(title);
        return result;
    }

    @Override
    public List<Article> getAllArticle() {
        List<Article> result = mapper.getAllArticle();
        return result;
    }
}
