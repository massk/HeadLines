package com.study.manoj.newsheadlines.data

import com.study.manoj.newsheadlines.network.model.Article
import javax.inject.Inject

class AppDbHelper @Inject
constructor(private val appDatabase: AppDatabase) : DbHelper {

    override fun insertArticles(articles: MutableList<Article>): List<Long> {
        return appDatabase.articleDao().insertArticles(articles)
    }

    override fun selectArticles(): List<Article> {
        return appDatabase.articleDao().selectArticles()
    }

    override fun deleteArticles() {
        appDatabase.articleDao().deleteArticles()
    }
}