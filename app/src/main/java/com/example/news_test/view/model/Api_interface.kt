package com.example.news_test.model

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface Api_interface {

   @GET("v2/everything")
    fun getNews(@Query("q") name:String,
                   @Query("page") page:String,
                   @Query("apiKey") apiKey:String ): Single<News>


}