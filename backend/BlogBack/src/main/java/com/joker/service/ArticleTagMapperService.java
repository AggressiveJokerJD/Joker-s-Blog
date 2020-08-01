package com.joker.service;

import com.joker.pojo.Article_Tag;

import java.util.List;

/**
 * @author: Joker
 * @name: ArticleTagMapperService
 * @Date: 2020/7/22 16:42
 * @Describe:
 **/
public interface ArticleTagMapperService {

    void insertArticleTag(Article_Tag article_tag);

    List<Article_Tag> getArticleTag(String tag_name, String article_name);

    List<Article_Tag> getArticleTag(String tag_name);

}
