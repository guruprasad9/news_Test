package com.example.news_test.view.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.example.news_test.R
import com.example.news_test.model.articles
import com.example.news_test.utils.getprogressdrawble
import com.example.news_test.utils.loadimage
import com.example.news_test.view.activity.DetailsActivity
import kotlinx.android.synthetic.main.items_news.view.*

class NewslistAdapter(var news: ArrayList<articles>) :
    RecyclerView.Adapter<NewslistAdapter.NewsViewholder>() {


    fun updatenews(newcountries: List<articles>){

        news.clear()
        news.addAll(newcountries)
        notifyDataSetChanged()

    }

    fun <T : RecyclerView.ViewHolder> T.listen(event: (position: Int, type: String) -> Unit): T {
        itemView.setOnClickListener {
//            event.invoke(getAdapterPosition(), getItemViewType())


            val intent = Intent(it.context, DetailsActivity::class.java).apply {
                putExtra("title", news.get(position).title)
                putExtra("description", news.get(position).description)
                putExtra("urlToImage", news.get(position).urlToImage)
                putExtra("url", news.get(position).url)
                putExtra("content", news.get(position).content)
                putExtra("author", news.get(position).author)
            }
            it.context.startActivity(intent)


        }
        return this
    }



    class NewsViewholder(view: View) : RecyclerView.ViewHolder(view) {

        private val imageview=view.imageView
        private val newsnametext = view.title
        private val newsdesc=view.descriptiontext
        private val progressDrawable= getprogressdrawble(view.context)

        fun bind(news: articles) {

            newsnametext.text= news.title
            newsdesc.text=news.description
            imageview.loadimage(news.urlToImage,progressDrawable)

        }

    }




override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewholder {
    val inflater = LayoutInflater.from(parent!!.getContext())
    val view = inflater.inflate(R.layout.items_news, parent, false)
    return NewsViewholder(view).listen { pos, type ->
        //TODO do other stuff here
    }
}

    override fun getItemCount() = news.size

    override fun onBindViewHolder(holder: NewsViewholder, position: Int) {

        holder.bind(news[position])

    }



}