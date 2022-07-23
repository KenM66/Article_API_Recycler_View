package com.example.recyclerviewwebservices;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ArticleDao {

    @Query("select * from article")
    List<Article> getArticleList();
    @Insert
    void insertAll(List<Article> article);

}
