package com.joker.controller;

import com.joker.pojo.Article;
import com.joker.pojo.Article_Tag;
import com.joker.pojo.Tag;
import com.joker.service.impl.ArticleMapperServiceImpl;
import com.joker.service.impl.ArticleTagMapperServiceImpl;
import com.joker.service.impl.TagMapperServiceImpl;
import com.joker.util.COS.MyCOSClient;
import com.vladsch.flexmark.ext.yaml.front.matter.AbstractYamlFrontMatterVisitor;
import com.vladsch.flexmark.ext.yaml.front.matter.YamlFrontMatterExtension;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.data.MutableDataSet;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author: Joker
 * @name: ArticleMapperController
 * @Date: 2020/7/20 17:16
 * @Describe: The controller of article.
 **/
@Controller
@CrossOrigin
@RequestMapping("/article")
public class ArticleMapperController {

    @Autowired
    ArticleMapperServiceImpl articleService;

    @Autowired
    TagMapperServiceImpl tagService;

    @Autowired
    ArticleTagMapperServiceImpl articleTagService;

    public MyCOSClient client = new MyCOSClient();

    @RequestMapping(value = "/insertArticle", method = RequestMethod.POST)
    @ResponseBody
    public String insertArticle(MultipartFile mdFile){
        try {
            //上传文件到腾讯云存储
            String fileName = mdFile.getOriginalFilename();
            java.io.File filetemp = new java.io.File("/WEB-INF/file/filetemp");
            FileUtils.copyInputStreamToFile(mdFile.getInputStream(), filetemp);
            client.uploadFile(filetemp, fileName,"article-1259719447");

            //读出上传文件的内容进行解析
            //将上传的md文件读出为字符串进行解析
            String mdString = new String(mdFile.getBytes(), StandardCharsets.UTF_8);
            //使用flexmark包进行处理
            DataHolder OPTIONS = new MutableDataSet()
                    .set(Parser.EXTENSIONS, Collections.singleton(YamlFrontMatterExtension.create()))
                    .toImmutable();
            Parser parser = Parser.builder(OPTIONS).build();
            AbstractYamlFrontMatterVisitor visitor = new AbstractYamlFrontMatterVisitor();
            Node document = parser.parse(mdString);
            visitor.visit(document);
            Map<String, List<String>> data = visitor.getData();

            //对处理之后的信息进行存储
            //存储articl信息至数据库
            Article article = new Article();
            List<String> tags = data.get("tags");
            String title = data.get("title").get(0);
            //判断文章是否重复上传
            if(articleService.getArticle(title).isEmpty()) {
                article.setTitle(title);
                article.setDes(data.get("des").get(0));
                article.setPic_url(data.get("pic_url").get(0));
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                article.setDate(format.parse(data.get("date").get(0)));
                String tagString = String.join(",", tags);
                article.setTags(tagString);
                //将文章信息上传数据库
                articleService.insertArticle(article);
            }

            //存储tags信息至数据库
            Tag tag = new Tag();
            for (String tagName : tags) {
                //防止重复插入
                if (tagService.getTag(tagName).isEmpty()) {
                    tag.setTag_name(tagName);
                    tagService.insertTag(tag);
                }
            }


            //存储article-tag信息至数据库
            for (String tagName : tags) {
                if(articleTagService.getArticleTag(tagName,title).isEmpty()) {
                    Article_Tag article_tag = new Article_Tag();
                    article_tag.setArticle_name(title);
                    article_tag.setTag_name(tagName);
                    articleTagService.insertArticleTag(article_tag);
                }
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return "Success";
    }

    @RequestMapping(value = "updateMdFile",method = RequestMethod.POST)
    @ResponseBody
    public String updateMdFile(MultipartFile mdFile) throws IOException {

        //删除原本的文件，重新上传新文件
        String fileName = mdFile.getOriginalFilename();
        java.io.File filetemp = new java.io.File("/WEB-INF/file/filetemp");
        FileUtils.copyInputStreamToFile(mdFile.getInputStream(), filetemp);
        client.deleteFile(fileName);
        client.uploadFile(filetemp, fileName,"article-1259719447");

        return "Success";
    }

    @RequestMapping(value = "/getContainer",method = RequestMethod.POST)
    @ResponseBody
    public String getContainer(@RequestBody Map<String,Object> request) throws IOException {
        String objectKey = (String)request.get("objectKey");
        String mdString = client.getFileContainer(objectKey+".md","article-1259719447");
        String[] mdContainer = mdString.split("---");
        StringBuilder container = new StringBuilder();
        for(int i = 2; i < mdContainer.length-1; i++){
            container.append(mdContainer[i]);
            container.append("---");
        }
        container.append(mdContainer[mdContainer.length-1]);
        mdString = container.toString();
        return mdString;
    }

    @RequestMapping(value = "/getArticleList", method = RequestMethod.POST)
    @ResponseBody
    public Object getArticleList(){
        List<Article> articleList = articleService.getAllArticle();
        List<Map<String,Object>> result = new LinkedList<>();
        int i = 1;
        for(Article article : articleList){
            Map<String,Object> temp = new HashMap<>();
            temp.put("id",i++);
            temp.put("title",article.getTitle());
            temp.put("pic_url",article.getPic_url());
            temp.put("des",article.getDes());
            temp.put("date",article.getDate());
            temp.put("tags",article.getTags().split(","));
            result.add(temp);
        }
        return result;
    }

    @RequestMapping(value = "/getTimeLine", method = RequestMethod.POST)
    @ResponseBody
    public Object getTimeLine(){

        List<Map<String,Object>> result = new LinkedList<>();
        List<Article> articleList = articleService.getAllArticle();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        Map<String,Object> timeInfo = new HashMap<>();
        Stack<String> timeOrder = new Stack<>();
        for(Article article : articleList){
            Date time = article.getDate();
            String sectionTime = format.format(time);
            if(!timeInfo.containsKey(sectionTime)){
                List<Map<String,Object>> timeArticles = new LinkedList<>();
                timeInfo.put(sectionTime,timeArticles);
                timeOrder.add(sectionTime);
            }
            Map<String,Object> timeArticle = new HashMap<>();
            timeArticle.put("title",article.getTitle());
            timeArticle.put("date",article.getDate());
            timeArticle.put("url",article.getPic_url());
            ((List<Map<String,Object>>)timeInfo.get(sectionTime)).add(timeArticle);
        }
        while (!timeOrder.isEmpty()){
            Map<String,Object> articleTimeLine = new HashMap<>();
            String sectionTime = timeOrder.pop();
            List<Map<String,Object>> sectionContain = (List<Map<String,Object>>)timeInfo.get(sectionTime);
            articleTimeLine.put("sectiontime",sectionTime);
            articleTimeLine.put("sectioncontain",sectionContain);
            result.add(articleTimeLine);
        }
        return result;

    }

}
