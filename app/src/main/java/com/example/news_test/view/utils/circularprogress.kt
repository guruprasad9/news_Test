package com.example.news_test.utils

import android.content.Context
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.news_test.R

fun getprogressdrawble(context: Context): CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth = 10f
        centerRadius = 50f
        start()
    }
}

fun ImageView.loadimage(url:String?,circularProgressDrawable: CircularProgressDrawable){

    val options=RequestOptions()
    .placeholder(circularProgressDrawable)
        .error(R.mipmap.ic_launcher_round)


    Glide.with(this.context)
        .setDefaultRequestOptions(options)
        .load(url)
        .into(this)
}