package com.study.manoj.newsheadlines.network.model

import io.reactivex.Observable

interface ApiHelper {
    fun getArticles(): Observable<Headlines?>
}
