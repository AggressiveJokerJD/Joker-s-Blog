package com.joker.mapper;

import com.joker.pojo.Article_Tag;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: Joker
 * @name: ArticleTagMapper
 * @Date: 2020/7/22 16:44
 * @Describe:
 **/
public interface ArticleTagMapper {

    void insertArticleTag(Article_Tag article_tag);

    List<Article_Tag> getArticleTag(@Param("tag_name") String tag_name,@Param("article_name") String article_name);

    List<Article_Tag> getArticleTagByTag(@Param("tag_name") String tag_name);

}
