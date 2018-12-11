package com.study.manoj.newsheadlines.network.model

import com.study.manoj.newsheadlines.network.Config
import io.reactivex.Observable
import javax.inject.Inject

class AppApiHelper @Inject constructor(private val apiService: ApiService) : ApiHelper {

    override fun getArticles(): Observable<Headlines?> {
        return apiService.getArticles("in", Config.API_KEY)
    }
}