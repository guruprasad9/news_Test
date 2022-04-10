package com.example.news_test

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.example.news_test.model.Api_class
import com.example.news_test.model.News
import com.example.news_test.model.articles
import com.example.news_test.view.activity.viewmodel.ListviewModel
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.disposables.Disposable
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.plugins.RxJavaPlugins
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit

class ListviewModelTest {


    @get:Rule
var Rule= InstantTaskExecutorRule()


    @Mock
    lateinit var Newsservice: Api_class

    @InjectMocks
    var listviewmodel= ListviewModel()
    val Pagenumber =
        MutableLiveData<Int>()
    private var testsingle: Single<News>?=null

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
    }

    @Test
    suspend fun getnewssucess(){

        //mockdata
        val articles = articles("title","description","urlto image","url","content","author")
        val news= News(listOf(articles))
        Pagenumber.value=1;
         testsingle=Single.just(news)
        `when`(Newsservice.getnews(Pagenumber)).thenReturn(testsingle)
        listviewmodel.refresh()

       Assert.assertEquals(1,listviewmodel.news.value?.size)
       Assert.assertEquals(false,listviewmodel.newsloaderror.value)
       Assert.assertEquals(false,listviewmodel.loading.value)
    }


    @Before
    fun setupschedulers(){
        val immediate=object:Scheduler(){

            override fun scheduleDirect(run: Runnable?, delay: Long, unit: TimeUnit?): Disposable {
                return super.scheduleDirect(run, 0, unit)
            }


            override fun createWorker(): Worker {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

           return  ExecutorScheduler.ExecutorWorker(Executor { it.run() })
            }

        }
        RxJavaPlugins.setInitIoSchedulerHandler{scheduler -> immediate}
        RxJavaPlugins.setInitComputationSchedulerHandler { scheduler->immediate }
        RxJavaPlugins.setInitNewThreadSchedulerHandler { scheduler->immediate }
        RxJavaPlugins.setInitSingleSchedulerHandler { scheduler->immediate }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { scheduler->immediate }
    }




}