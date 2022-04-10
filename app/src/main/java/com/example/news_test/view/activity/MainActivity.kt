package com.example.news_test.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.lifecycle.Observer
import com.example.news_test.R
import com.example.news_test.view.activity.viewmodel.ListviewModel
import com.example.news_test.view.adapter.NewslistAdapter
import kotlinx.android.synthetic.main.main_activity.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    var pagenumber:Int= 1;
    lateinit var viewmodel: ListviewModel
    private val newsadapter =
        NewslistAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        viewmodel = ViewModelProviders.of(this).get(ListviewModel::class.java)
        refresh()
        list.apply {

            layoutManager = LinearLayoutManager(context)
            adapter = newsadapter

        }


        swiperefreshlayout.setOnRefreshListener {
            swiperefreshlayout.isRefreshing=false
            refresh()

        }
        observeviewmodel()
        val dateformat = SimpleDateFormat("dd/M/yyyy")
        val timeformat = SimpleDateFormat("hh:mm:ss")
        val currentDate = dateformat.format(Date())
        val currentTime = timeformat.format(Date())
        date.text=currentDate
        time.text=currentTime
        page.text ="Page : "+pagenumber

        next.setOnClickListener {
            pagenumber++
            page.text ="Page : "+pagenumber
            viewmodel.Pagenumber.value = pagenumber
            refresh()


        }
        back.setOnClickListener {
            pagenumber--
            page.text ="Page : "+pagenumber
            viewmodel.Pagenumber.value = pagenumber
            refresh()
        }
        back.visibility= View.GONE

    }

    private fun refresh() {
        CoroutineScope(Dispatchers.IO).launch {
            viewmodel.refresh()
        }
    }

    fun observeviewmodel() {


        viewmodel.news.observe(
            this,
            Observer { news -> news?.let {

                newsadapter.updatenews(it)
                list.visibility= View.VISIBLE

                if(viewmodel.Pagenumber.value==1){
                    back.visibility= View.GONE
                }else{
                    back.visibility= View.VISIBLE

                }

            } })

        viewmodel.newsloaderror.observe(
            this,
            Observer { iserror ->
                iserror?.let {
                    list_error.visibility = if (it) View.VISIBLE else View.GONE

                    if (it) {
                        progressBar.visibility = View.GONE
                    }

                }
            })

        viewmodel.loading.observe(this, Observer { isloading ->
            isloading?.let {
                progressBar.visibility = if (it) View.VISIBLE else View.GONE

                if (it) {
                    list_error.visibility = View.GONE
                    list.visibility = View.GONE
                }

            }
        })

    }
}
