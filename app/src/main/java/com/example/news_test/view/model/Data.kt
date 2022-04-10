package com.example.news_test.model

import com.google.gson.annotations.SerializedName

data class News(
    @SerializedName("articles")
    val articles: List<articles>?
)

data class articles(
    @SerializedName("title")
    val title: String?,

    @SerializedName("description")
    val description: String?,

    @SerializedName("urlToImage")
    val urlToImage: String?,

    @SerializedName("url")
    val url: String?,

    @SerializedName("content")
    val content: String? ,

    @SerializedName("author")
    val author: String?

)
