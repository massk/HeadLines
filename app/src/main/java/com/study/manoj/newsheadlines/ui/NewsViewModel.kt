package com.study.manoj.newsheadlines.ui

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import com.study.manoj.newsheadlines.dataAgent.DataManager
import com.study.manoj.newsheadlines.network.model.Article
import com.study.manoj.newsheadlines.network.model.Headlines
import io.reactivex.schedulers.Schedulers
import io.reactivex.Observable

class NewsViewModel(
        context: Application,
        private val dataManager: DataManager)
    : AndroidViewModel(context) {

    fun getArticlesFromDatabase(): Observable<Headlines?> {
        return Observable.defer { Observable.just(dataManager.selectArticles()) }
                .map { it: List<Article> ->
                    val headlines: Headlines? = Headlines(
                            "OK",
                            20,
                            it)
                    headlines
                }
                .subscribeOn(Schedulers.computation())
    }

    fun getArticlesFromNetwork(): Observable<Headlines?> {
        return dataManager.getArticles()
                .map { it: Headlines? ->
                    if (it?.articles != null) {
                        dataManager.deleteArticles()
                        dataManager.insertArticles(it.articles as MutableList<Article>)
                    }
                    it
                }
                .subscribeOn(Schedulers.io())
    }
}
