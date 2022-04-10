package com.example.news_test.view.activity.viewmodel

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.news_test.di.DaggerApicomponent
import com.example.news_test.model.Api_class
import com.example.news_test.model.News
import com.example.news_test.model.articles


import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.*
import okhttp3.Dispatcher
import javax.inject.Inject

class ListviewModel : ViewModel() {

    @Inject
    lateinit var newsService: Api_class


init {
    DaggerApicomponent.create().inject(this)
}


    private val disposable=CompositeDisposable()

    val news =
        MutableLiveData<List<articles>>() //view must subscribe to this variable to get notification of data change
    val newsloaderror =
        MutableLiveData<Boolean>() //transmit rue or false(check error in loading the data)
    val loading =
        MutableLiveData<Boolean>() // shows Listviewmodel is in the process of loading data or not
    val Pagenumber =
        MutableLiveData<Int>() // shows Listviewmodel is in the process of loading data or not

     suspend fun refresh() {
         sortList()
    }


    suspend fun sortList() = withContext(Dispatchers.Main) {
        // Heavy work
        loading.value=true

        disposable.add(
            newsService.getnews(Pagenumber)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<News>() {
                    override fun onSuccess(p0: News?) {
                        news.value = p0?.articles
                        newsloaderror.value = false
                        loading.value = false


                    }

                    override fun onError(p0: Throwable?) {

                        newsloaderror.value = true
                        loading.value = false

                    }

                })

        )
    }


    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}