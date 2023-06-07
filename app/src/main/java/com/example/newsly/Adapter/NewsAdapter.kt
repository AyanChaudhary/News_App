package com.example.newsly.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsly.Model.Article
import com.example.newsly.R
import com.example.newsly.databinding.ItemViewBinding
import kotlinx.android.synthetic.main.item_view.view.iv_newsImg
import kotlinx.android.synthetic.main.item_view.view.tv_description
import kotlinx.android.synthetic.main.item_view.view.tv_title

class NewsAdapter:RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    inner class NewsViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)

    private val differCallBack= object : DiffUtil.ItemCallback<Article>(){
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url==newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem==newItem
        }
    }
    val differ = AsyncListDiffer(this,differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_view,parent,false))
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val article=differ.currentList[position]
        holder.itemView.apply {
            Glide.with(this).load(article.urlToImage).into(iv_newsImg)
            tv_title.text=article.title
            tv_description.text=article.description
            setOnClickListener{
                onItemClickListener?.let { it(article) }
            }
        }
    }

    private var onItemClickListener : ((Article)-> Unit)?=null

    fun setOnItemClickListener(listener : ((Article)->Unit)){
        onItemClickListener=listener
    }
}