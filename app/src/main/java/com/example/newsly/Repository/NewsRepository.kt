package com.example.newsly.Repository

import com.example.newsly.Api.NewsInstance
import com.example.newsly.Model.Article
import com.example.newsly.db.ArticleDatabase

class NewsRepository(
    val db:ArticleDatabase
) {
    suspend fun getBreakingNews(country:String,page:Int)=
        NewsInstance.api.getBreakingNews(country,page)
    suspend fun searchForNews(searchQuery:String,page:Int)=
        NewsInstance.api.searchForNews(searchQuery,page)

    suspend fun upsert(article:Article)=db.articleDao().insertArticle(article)

    fun getSavedNews()=db.articleDao().getAllArticles()

    suspend fun deleteNews(article: Article)=db.articleDao().deleteArticle(article)
}