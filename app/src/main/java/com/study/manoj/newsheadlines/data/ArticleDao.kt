package com.study.manoj.newsheadlines.data

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.study.manoj.newsheadlines.network.model.Article

@Dao
interface ArticleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArticles(articles: MutableList<Article>): List<Long>

    @Query("SELECT * FROM articles")
    fun selectArticles(): List<Article>

    @Query("DELETE FROM articles")
    fun deleteArticles()
}
