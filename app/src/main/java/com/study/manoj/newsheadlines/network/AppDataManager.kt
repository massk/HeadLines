package com.study.manoj.newsheadlines.network

import com.study.manoj.newsheadlines.data.AppDbHelper
import com.study.manoj.newsheadlines.dataAgent.DataManager
import com.study.manoj.newsheadlines.network.model.AppApiHelper
import com.study.manoj.newsheadlines.network.model.Article
import com.study.manoj.newsheadlines.network.model.Headlines
import io.reactivex.Observable
import javax.inject.Inject

class  AppDataManager @Inject constructor(private val appApiHelper: AppApiHelper, private val appDbHelper: AppDbHelper) : DataManager {

    override fun getArticles(): Observable<Headlines?> {
        return appApiHelper.getArticles()
    }

    override fun insertArticles(articles: MutableList<Article>): List<Long> {
        return appDbHelper.insertArticles(articles)
    }

    override fun selectArticles(): List<Article> {
        return appDbHelper.selectArticles()
    }

    override fun deleteArticles() {
        appDbHelper.deleteArticles()
    }
}