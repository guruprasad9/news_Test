package com.example.news_test.view.activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.news_test.R
import kotlinx.android.synthetic.main.activity_second.*

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)



        val title_intent: String? = intent.getStringExtra("title")
        val urlToImage: String? = intent.getStringExtra("urlToImage")
        val url_text: String? = intent.getStringExtra("url")
        val content_text: String? = intent.getStringExtra("content")
        val author_text: String? = intent.getStringExtra("author")
        name.text=title_intent;
        content.text=content_text;
        author.text=author_text;
        url.text=url_text;

        Glide.with(this)
            .load(urlToImage)
            .into(imageView)
        // set toolbar as support action bar
        setSupportActionBar(toolbar)

        supportActionBar?.apply {

            // show back button on toolbar
            // on back button press, it will navigate to parent activity
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }



        sharelink.setOnClickListener {

            val intent = Intent(Intent.ACTION_SEND)
            // Setting Intent type
            intent.type = "text/plain"
            // Give your message here
            intent.putExtra(Intent.EXTRA_TEXT, url_text)
            // Starting Whatsapp
            startActivity(intent)
        }
        url.setOnClickListener {

            val intent = Intent(Intent.ACTION_SEND)
            // Setting Intent type
            intent.type = "text/plain"
            // Give your message here
            intent.putExtra(Intent.EXTRA_TEXT, url_text)
            // Starting Whatsapp
            startActivity(intent)
        }


    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}


