package com.example.newsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import kotlinx.android.synthetic.main.activity_home.*
import kotlin.math.log



class HomeActivity : AppCompatActivity(), onItemClicked {
    private lateinit var madapter:NewsListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        supportActionBar?.hide()
        recyclerView.layoutManager = LinearLayoutManager(this)
        fetchData()
        madapter = NewsListAdapter(this)
        recyclerView.adapter = madapter

    }
    private fun fetchData(){
        val url = "https://newsapi.org/v2/top-headlines?country=in&category=business&apiKey=e47d293a815449df81da29f572ec7064"

        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
            Response.Listener{
                val newsJsonArray = it.getJSONArray("articles")
                val newsArray = ArrayList<News>()
                for (i in 0 until newsJsonArray.length()){
                    val newsJsonObject = newsJsonArray.getJSONObject(i)
                    val news = News(
                        newsJsonObject.getString("title"),
                        newsJsonObject.getString("author"),
                        newsJsonObject.getString("url"),
                        newsJsonObject.getString("urlToImage"),
                    )
                    newsArray.add(news)
                }
                madapter.updateNews(newsArray)

            },
            Response.ErrorListener{
            }
        )
        MySingleton.getInstance(this,).addToRequestQueue(jsonObjectRequest)
    }

    override fun onItemClicked(item: News) {
        Toast.makeText(this,"$item has been clicked ",Toast.LENGTH_LONG).show()
    }

}

