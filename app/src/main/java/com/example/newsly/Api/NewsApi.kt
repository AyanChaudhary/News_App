package com.example.newsly.Api

import com.example.newsly.Model.News
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

//https://newsapi.org/v2/top-headlines?country=us&category=business&apiKey=7b192c8f07604b2eb215585a3c47cfb6
const val API_KEY=""
interface NewsApi {
    @GET("v2/top-headlines?apiKey=$API_KEY")
    suspend fun getBreakingNews(@Query("country")country:String="in",@Query("page")pageNumber:Int=1):Response<News>

    @GET("v2/top-headlines?apiKey=$API_KEY")
    suspend fun searchForNews(@Query("q")searchQuery:String,@Query("page")pageNumber:Int=1):Response<News>
}
