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
import com.example.news_test.view.activity.SecondActivity
import kotlinx.android.synthetic.main.items_news.view.*

class NewslistAdapter(var news: ArrayList<articles>) :
    RecyclerView.Adapter<NewslistAdapter.CountryViewholder>() {


    fun updatenews(newcountries: List<articles>){

        news.clear()
        news.addAll(newcountries)
        notifyDataSetChanged()

    }

    fun <T : RecyclerView.ViewHolder> T.listen(event: (position: Int, type: String) -> Unit): T {
        itemView.setOnClickListener {
//            event.invoke(getAdapterPosition(), getItemViewType())


            val intent = Intent(it.context, SecondActivity::class.java).apply {
                putExtra("title", news.get(position).title)
                putExtra("description", news.get(position).description)
                putExtra("urlToImage", news.get(position).urlToImage)
            }
            it.context.startActivity(intent)


        }
        return this
    }



    class CountryViewholder(view: View) : RecyclerView.ViewHolder(view) {

        private val imageview=view.imageView
        private val countrynametext = view.name
        private val contrycapital=view.capital
        private val progressDrawable= getprogressdrawble(view.context)

        fun bind(country: articles) {

            countrynametext.text= country.title
            contrycapital.text=country.description
            imageview.loadimage(country.urlToImage,progressDrawable)

        }

    }



//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
//        CountryViewholder(
//            LayoutInflater.from(parent.context).inflate(R.layout.item_countries, parent, false)
//
//        )
override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewholder {
    val inflater = LayoutInflater.from(parent!!.getContext())
    val view = inflater.inflate(R.layout.items_news, parent, false)
    return CountryViewholder(view).listen { pos, type ->
//        val item = countries.get(pos).countryname
        //TODO do other stuff here
    }
}

    override fun getItemCount() = news.size

    override fun onBindViewHolder(holder: CountryViewholder, position: Int) {

        holder.bind(news[position])

    }



}