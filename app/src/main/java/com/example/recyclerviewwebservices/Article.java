package com.example.recyclerviewwebservices;

import java.util.ArrayList;

public class Article {

    private String title;
    private String author;

    public Article(String title, String author) {
        this.title = title;
        this.author= author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Article{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                '}';
    }

    //    public static ArrayList<Article> createArticleList(int numArticles, String title, String author){
//        ArrayList<Article> articles = new ArrayList<>();
//
//        for(int i=1; i<=numArticles; i++){
//            articles.add(new Article(title, author));
//        }
//
//        return articles;
//    }

}
