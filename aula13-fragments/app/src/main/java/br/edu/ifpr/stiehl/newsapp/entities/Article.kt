package br.edu.ifpr.stiehl.newsapp.entities

import java.io.Serializable
import java.util.*

data class Article(
    var source: Source?,
    var title: String?,
    var url: String?,
    var urlToImage: String?,
    var description: String?,
    var author: String?,
    var publishedAt: Date
) : Serializable