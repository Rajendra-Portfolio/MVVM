package com.iamrajendra.newsdragger.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.iamrajendra.newsdragger.model.NewsArticles

@Dao
interface NewsArticlesDao {

    /**
     * Insert articles into the table
     */
    @Insert
    fun insertArticles(articles: List<NewsArticles>): List<Long>

    /**
     * Get all the articles from table
     */
    @Query("SELECT * FROM news_article")
    suspend fun getNewsArticles(): List<NewsArticles>
}