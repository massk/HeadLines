package com.study.manoj.newsheadlines.ui

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import com.study.manoj.newsheadlines.dependencyInjection.ApplicationContext
import dagger.Module
import dagger.Provides

@Module
class NewsActivityModule {
    @Provides
    fun provideNewsAdapter(): NewsAdapter {
        return NewsAdapter(ArrayList())
    }

    @Provides
    fun provideLinearLayoutManager(@ApplicationContext context: Context): LinearLayoutManager {
        return LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }
}