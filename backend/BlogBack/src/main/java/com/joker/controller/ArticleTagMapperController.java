package com.joker.controller;

import com.joker.pojo.Article;
import com.joker.pojo.Article_Tag;
import com.joker.pojo.Tag;
import com.joker.service.impl.ArticleMapperServiceImpl;
import com.joker.service.impl.ArticleTagMapperServiceImpl;
import com.joker.service.impl.TagMapperServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author: Joker
 * @name: ArticleTagMapperController
 * @Date: 2020/7/24 16:36
 * @Describe:
 **/
@Controller
@CrossOrigin
@RequestMapping("/articletag")
public class ArticleTagMapperController {

    @Autowired
    ArticleMapperServiceImpl articleService;

    @Autowired
    ArticleTagMapperServiceImpl articleTagService;

    @Autowired
    TagMapperServiceImpl tagService;

    @RequestMapping(value = "/getArticleTag", method = RequestMethod.POST)
    @ResponseBody
    public Object getArticleTag(){
        List<Map<String,Object>> result = new LinkedList<>();
        List<Tag> tagList = tagService.getAllTag();
        for(Tag tag : tagList){
            Map<String,Object> articleTagItem = new HashMap<>();
            articleTagItem.put("tagname",tag.getTag_name());
            List<Map<String,Object>> articleTagListResult = new LinkedList<>();
            List<Article_Tag> articleTagList = articleTagService.getArticleTag(tag.getTag_name());
            for(Article_Tag article_tag : articleTagList){
                Map<String,Object> articleTag = new HashMap<>();
                Article article = articleService.getArticle(article_tag.getArticle_name()).get(0);
                articleTag.put("title",article.getTitle());
                articleTag.put("date",article.getDate());
                articleTag.put("url",article.getPic_url());
                articleTagListResult.add(articleTag);
            }
            articleTagItem.put("previews",articleTagListResult);
            result.add(articleTagItem);
        }
        return result;
    }

}
