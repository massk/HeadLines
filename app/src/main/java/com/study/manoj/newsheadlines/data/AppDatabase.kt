package com.study.manoj.newsheadlines.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.study.manoj.newsheadlines.network.model.Article

@Database(entities = [Article::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase(){
    abstract fun articleDao() : ArticleDao
}