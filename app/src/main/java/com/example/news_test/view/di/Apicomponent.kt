package com.example.news_test.di

import com.example.news_test.model.Api_class
import com.example.news_test.view.activity.viewmodel.ListviewModel
import dagger.Component


@Component(modules = [ApiModule::class])
interface Apicomponent {

fun inject(service: Api_class)

    fun inject(viewmodel: ListviewModel)

}


