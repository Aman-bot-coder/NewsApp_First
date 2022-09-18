package com.example.newsapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_views.view.*

class NewsListAdapter(private val listner: onItemClicked): RecyclerView.Adapter<NewsViewHolder>() {
    val items:ArrayList<News> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : NewsViewHolder{
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_views,parent,false)
        val viewHolder = NewsViewHolder(view)
        view.setOnClickListener{
            listner.onItemClicked(items[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        var currentItem = items[position]
        holder.title.text = currentItem.title
        holder.author.text = currentItem.author
        Glide.with(holder.itemView.context).load(currentItem.imageurl).into(holder.image)
    }

    override fun getItemCount(): Int {
        return items.size
    }
    fun updateNews(updateNews:ArrayList<News>){
        items.clear()
        items.addAll(updateNews)
        notifyDataSetChanged()

    }

}
class NewsViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
    var title:TextView = itemView.findViewById(R.id.title)
    var image:ImageView = itemView.image1
    var author:TextView = itemView.author
}
interface onItemClicked{
    fun onItemClicked(item: News)

}
