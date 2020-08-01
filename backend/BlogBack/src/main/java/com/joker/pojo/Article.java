package com.joker.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @author: Joker
 * @name: Article
 * @Date: 2020/7/16 16:36
 * @Describe: The JavaBean of article.
 **/
public class Article {

    private String title;
    private String pic_url;
    private String des;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private Date date;
    private String tags;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPic_url() {
        return pic_url;
    }

    public void setPic_url(String pic_url) {
        this.pic_url = pic_url;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Article() {
    }

    public Article(String title, String pic_url, String des, Date date, String tags) {
        this.title = title;
        this.pic_url = pic_url;
        this.des = des;
        this.date = date;
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "Article{" +
                "title='" + title + '\'' +
                ", pic_url='" + pic_url + '\'' +
                ", des='" + des + '\'' +
                ", date=" + date +
                ", tags='" + tags + '\'' +
                '}';
    }
}
