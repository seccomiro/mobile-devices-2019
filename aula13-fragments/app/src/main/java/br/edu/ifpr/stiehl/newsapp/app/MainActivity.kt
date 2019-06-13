package br.edu.ifpr.stiehl.newsapp.app

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.SearchView
import br.edu.ifpr.stiehl.newsapp.R
import br.edu.ifpr.stiehl.newsapp.entities.Article
import br.edu.ifpr.stiehl.newsapp.entities.NewsResult
import br.edu.ifpr.stiehl.newsapp.services.NewsService
import br.edu.ifpr.stiehl.newsapp.ui.NewsAdapter
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    lateinit var service: NewsService
    lateinit var adapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        configureRetrofit()

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                loadNews(query!!)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                loadNews(newText!!)
                return true
            }
        })

        loadNews()
    }

    private fun configureRetrofit() {
        val gson = GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        service = retrofit.create<NewsService>(NewsService::class.java)
    }

    private fun loadNews(query: String = "") {

        val country =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                resources.configuration.locales[0].country.toLowerCase()
            else
                resources.configuration.locale.country.toLowerCase()

        service.getNews(query, country).enqueue(object : Callback<NewsResult> {
            override fun onFailure(call: Call<NewsResult>, t: Throwable) {
                Log.e("ERRO", t.message, t)
            }

            override fun onResponse(call: Call<NewsResult>, response: Response<NewsResult>) {
                val articles = response.body()?.articles
                if (articles != null)
                    loadRecyclerView(articles)
            }

        })
    }

    private fun loadRecyclerView(articles: List<Article>) {
        if (listArticles != null) {
            adapter = NewsAdapter(articles)
            listArticles.adapter = adapter
        }
    }
}
