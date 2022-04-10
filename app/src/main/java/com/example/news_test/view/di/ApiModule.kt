package com.example.news_test.di


import com.example.news_test.model.Api_class
import com.example.news_test.model.Api_interface
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


@Module
class ApiModule {
    private val Baseurl="https://newsapi.org"


    @Provides
    fun providecountriesapi(): Api_interface {

        return Retrofit.Builder()
            .baseUrl(Baseurl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(Api_interface::class.java)
    }



   @Provides
   fun providesApiclass(): Api_class {
       return Api_class()
   }



}