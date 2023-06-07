package com.example.newsly.UI

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsly.Model.Article
import com.example.newsly.Model.News
import com.example.newsly.Repository.NewsRepository
import com.example.newsly.Util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class NewsViewModel(val repository:NewsRepository) : ViewModel() {
    //for fragments so they can subscribe to the live data as observers
    val breakingNews:MutableLiveData<Resource<News>> = MutableLiveData()
    var breakingNewsPageNumber=1
    var breakingNewsResponse : News?=null

    val searchNews:MutableLiveData<Resource<News>> = MutableLiveData()
    var searchNewsPageNumber=1
    var searchNewsResponse : News?=null


    init {
        getBreakingNews("in")

    }
    fun getBreakingNews(country:String)=viewModelScope.launch {
        breakingNews.postValue(Resource.Loading())
        val response=repository.getBreakingNews(country,breakingNewsPageNumber)
        breakingNews.postValue(handleBreakingNewsResponse(response))
    }
    fun searchNews(queryString:String)=viewModelScope.launch {
        searchNews.postValue(Resource.Loading())
        val response=repository.searchForNews(queryString,searchNewsPageNumber)
        searchNews.postValue(handleSearchNewsResponse(response))
    }

    private fun handleBreakingNewsResponse(response: Response<News>) : Resource<News>{
        if(response.isSuccessful){
            response.body()?.let { resultResponse->
                breakingNewsPageNumber++
                if(breakingNewsResponse==null)breakingNewsResponse=resultResponse
                else {
                    val oldArticles=breakingNewsResponse?.articles
                    val newArticles=resultResponse.articles
                    oldArticles?.addAll(newArticles)
                }
                return Resource.Success(breakingNewsResponse?:resultResponse)
            }
        }
        return Resource.Error(response.message())
    }
    private fun handleSearchNewsResponse(response: Response<News>) : Resource<News>{
        if(response.isSuccessful){
            response.body()?.let { resultResponse->
                searchNewsPageNumber++
                if(searchNewsResponse==null)searchNewsResponse=resultResponse
                else {
                    val oldArticles=searchNewsResponse?.articles
                    val newArticles=resultResponse.articles
                    oldArticles?.addAll(newArticles)
                }
                return Resource.Success(searchNewsResponse?:resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    fun saveArticle(article: Article)=viewModelScope.launch {
        repository.upsert(article)
    }

    fun getArticles()=repository.getSavedNews()

    fun deleteArticle(article: Article)=viewModelScope.launch {
        repository.deleteNews(article)
    }
}