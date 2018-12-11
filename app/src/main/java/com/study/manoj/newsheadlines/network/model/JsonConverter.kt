package com.study.manoj.newsheadlines.network.model

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.study.manoj.newsheadlines.network.DataSource

class JsonConverter {
    @TypeConverter
    fun sourceToString(source: DataSource?): String? {
        if (source == null) {
            return null
        }
        val gson = Gson()
        val type = object : TypeToken<DataSource>() {

        }.type
        return gson.toJson(source, type)
    }

    @TypeConverter
    fun stringToSource(sourceString: String?): DataSource? {
        if (sourceString == null) {
            return null
        }
        val gson = Gson()
        val type = object : TypeToken<DataSource>() {

        }.type
        return gson.fromJson<DataSource>(sourceString, type)
    }
}
