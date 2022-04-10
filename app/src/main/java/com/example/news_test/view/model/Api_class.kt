package com.example.news_test.model


import androidx.lifecycle.MutableLiveData
import com.example.news_test.di.DaggerApicomponent
import io.reactivex.Single
import javax.inject.Inject

class Api_class {


    @Inject
    lateinit var apiInterface:Api_interface

    init{

        DaggerApicomponent.create().inject(this)

    }

     fun getnews(page: MutableLiveData<Int>): Single<News> {

              return  apiInterface.getNews("tesla",page.value.toString(),"63186cc1ab7f43ea9d4ab56f51a71e43")

        }




}