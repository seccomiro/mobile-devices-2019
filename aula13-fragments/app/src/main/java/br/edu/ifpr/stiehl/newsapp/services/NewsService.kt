package br.edu.ifpr.stiehl.newsapp.services

import br.edu.ifpr.stiehl.newsapp.entities.NewsResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface NewsService {
    @Headers("Accept: application/json")
    @GET("top-headlines")
    fun getNews(
        @Query("q")
        busca: String,
        @Query("country")
        pais: String = "br",
        @Query("apiKey")
        apiKey: String = "a8e9258d73934726966be7f4f51c16c3"
    ): Call<NewsResult>
}