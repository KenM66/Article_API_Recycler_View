package com.example.recyclerviewwebservices;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;


import java.util.ArrayList;

@Entity
public class Article {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name= "title")
    private String title;
    @ColumnInfo(name= "author")
    private String author;

    public Article(int id, String title, String author) {
        this.title = title;
        this.author= author;
        this.id= id;
    }

    @Ignore
    public Article(String title, String author){
        this.title= title;
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

    public int getId(){
        return id;
    }


    @Override
    public String toString() {
        return "Article{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                '}';
    }



}
