package com.study.manoj.newsheadlines.dependencyInjection

import android.app.Application
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import android.support.customtabs.CustomTabsIntent
import android.support.v4.content.ContextCompat
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton
import com.study.manoj.newsheadlines.R
import com.study.manoj.newsheadlines.data.AppDatabase
import com.study.manoj.newsheadlines.data.AppDbHelper
import com.study.manoj.newsheadlines.data.DbHelper
import com.study.manoj.newsheadlines.dataAgent.DataManager
import com.study.manoj.newsheadlines.network.AppDataManager
import com.study.manoj.newsheadlines.network.Config
import com.study.manoj.newsheadlines.network.model.ApiHelper
import com.study.manoj.newsheadlines.network.model.AppApiHelper
import com.study.manoj.newsheadlines.utils.HeadlinesApplication


@Module
class ApplicationModule {

    @Provides
    @ApplicationContext
    internal fun provideContext(newsApplication: HeadlinesApplication): Context {
        return newsApplication.applicationContext
    }

    @Provides
    internal fun provideApplication(newsApplication: HeadlinesApplication): Application {
        return newsApplication
    }

    @Provides
    internal fun provideCompositeDisposable(): CompositeDisposable {
        return CompositeDisposable()
    }

    @Provides
    fun provideCustomTabsIntent(@ApplicationContext context: Context): CustomTabsIntent {
        return CustomTabsIntent.Builder()
                .setShowTitle(true)
                .setToolbarColor(ContextCompat.getColor(context, R.color.colorPrimary))
                .setSecondaryToolbarColor(ContextCompat.getColor(context, R.color.colorPrimaryDark))
                .addDefaultShareMenuItem()
                .build()
    }

    @Provides
    @BaseUrl
    internal fun provideBaseUrl(): String {
        return Config.BASE_URL
    }

    @Provides
    @Singleton
    fun provideRoomDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, Config.DB_NAME)
                .setJournalMode(RoomDatabase.JournalMode.TRUNCATE)
                .build()
    }

    @Provides
    @Singleton
    internal fun provideDataManager(appDataManager: AppDataManager): DataManager {
        return appDataManager
    }

    @Provides
    @Singleton
    internal fun provideDbHelper(appDbHelper: AppDbHelper): DbHelper {
        return appDbHelper
    }

    @Provides
    @Singleton
    internal fun provideApiHelper(appApiHelper: AppApiHelper): ApiHelper {
        return appApiHelper
    }

}