package com.joker.service;

import com.joker.pojo.Article;

import java.util.List;

/**
 * @author: Joker
 * @name: ArticleMapperService
 * @Date: 2020/7/20 15:34
 * @Describe: The service of article.
 **/
public interface ArticleMapperService {


    void insertArticle(Article article);

    List<Article> getArticle(String title);

    List<Article> getAllArticle();

}
