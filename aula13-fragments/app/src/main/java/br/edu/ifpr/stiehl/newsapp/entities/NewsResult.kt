package br.edu.ifpr.stiehl.newsapp.entities

import com.google.gson.annotations.SerializedName

data class NewsResult(
    var status: String,
    var totalResults: Int,
    var articles: List<Article>
)