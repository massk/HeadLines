package com.study.manoj.newsheadlines.network.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.study.manoj.newsheadlines.network.DataSource

@Entity(tableName = "articles")  // create SQLite table
@TypeConverters(JsonConverter::class)
data class Article(
    @PrimaryKey(autoGenerate = true)
    var id: Long,

    @SerializedName("source")
    @Expose
    @ColumnInfo(name = "source")
    var source: DataSource? = null,

    @SerializedName("author")
    @Expose
    @ColumnInfo(name = "author")
    var author: String? = null,

    @SerializedName("title")
    @Expose
    @ColumnInfo(name = "title")
    var title: String? = null,

    @SerializedName("description")
    @Expose
    @ColumnInfo(name = "description")
    var description: String? = null,

    @SerializedName("url")
    @Expose
    @ColumnInfo(name = "url")
    var url: String? = null,

    @SerializedName("urlToImage")
    @Expose
    @ColumnInfo(name = "urlToImage")
    var urlToImage: String? = null,

    @SerializedName("publishedAt")
    @Expose
    @ColumnInfo(name = "publishedAt")
    var publishedAt: String? = null
)
