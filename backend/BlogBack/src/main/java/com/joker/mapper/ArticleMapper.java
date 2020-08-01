package com.joker.mapper;

import com.joker.pojo.Article;

import java.util.List;

/**
 * @author: Joker
 * @name: ArticleMapper
 * @Date: 2020/7/20 15:32
 * @Describe: The mapper interfce of article.
 **/
public interface ArticleMapper {

    void insertArticle(Article article);

    List<Article> getArticle(String title);

    List<Article> getAllArticle();

}
