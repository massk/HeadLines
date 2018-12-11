package com.study.manoj.newsheadlines.network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class DataSource(

        @SerializedName("id")
        @Expose
        var id: Any? = null,
        @SerializedName("name")
        @Expose
        var name: String? = null

)