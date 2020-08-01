package com.joker.pojo;

/**
 * @author: Joker
 * @name: Article_Tag
 * @Date: 2020/7/20 20:45
 * @Describe:
 **/
public class Article_Tag {

    private int id;
    private String tag_name;
    private String article_name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTag_name() {
        return tag_name;
    }

    public void setTag_name(String tag_name) {
        this.tag_name = tag_name;
    }

    public String getArticle_name() {
        return article_name;
    }

    public void setArticle_name(String article_name) {
        this.article_name = article_name;
    }

    public Article_Tag() {
    }

    public Article_Tag(int id, String tag_name, String article_name) {
        this.id = id;
        this.tag_name = tag_name;
        this.article_name = article_name;
    }

    @Override
    public String toString() {
        return "Article_Tag{" +
                "id=" + id +
                ", tag_name='" + tag_name + '\'' +
                ", article_name='" + article_name + '\'' +
                '}';
    }

}
