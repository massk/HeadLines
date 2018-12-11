package com.study.manoj.newsheadlines.dependencyInjection

import com.study.manoj.newsheadlines.ui.NewHeadLinesMainActivity
import com.study.manoj.newsheadlines.ui.NewsActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {
    @ContributesAndroidInjector(modules = [(NewsActivityModule::class)])
    abstract fun bindNewsActivity(): NewHeadLinesMainActivity
}