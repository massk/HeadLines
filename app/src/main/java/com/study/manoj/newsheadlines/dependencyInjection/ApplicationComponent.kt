package com.study.manoj.newsheadlines.dependencyInjection

import com.study.manoj.newsheadlines.utils.HeadlinesApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    NetworkModule::class,
    ApplicationModule::class,
    ActivityBindingModule::class
])

interface ApplicationComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(headlinesApplication: HeadlinesApplication): Builder

        fun build(): ApplicationComponent
    }

    fun inject(headlinesApplication: HeadlinesApplication)
}