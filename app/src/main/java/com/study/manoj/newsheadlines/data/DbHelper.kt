package com.study.manoj.newsheadlines.data

import com.study.manoj.newsheadlines.network.model.Article

interface DbHelper {
    fun insertArticles(articles: MutableList<Article>): List<Long>

    fun selectArticles(): List<Article>

    fun deleteArticles()
}
