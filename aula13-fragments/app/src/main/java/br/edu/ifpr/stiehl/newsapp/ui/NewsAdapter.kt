package br.edu.ifpr.stiehl.newsapp.ui

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.edu.ifpr.stiehl.newsapp.R
import br.edu.ifpr.stiehl.newsapp.entities.Article
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_article.view.*
import java.text.SimpleDateFormat

class NewsAdapter(var articles: List<Article>) :
    RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {

    override fun getItemCount() = articles.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ArticleViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_article, parent, false)
        )

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.fillUI(articles[position])
    }

    inner class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun fillUI(article: Article) {
            itemView.lblTitle.text = article.title
            itemView.lblAuthor.text = article.author
            itemView.lblDescription.text = article.description
            val f = SimpleDateFormat("dd/MM/yyyy hh:mm")
            itemView.lblDate.text = f.format(article.publishedAt)
            Picasso
                .get()
                .load(article.urlToImage)
                .into(itemView.imgArticle)

            itemView.setOnClickListener {
                val uri = Uri.parse(article.url)
                val intent = Intent(Intent.ACTION_VIEW, uri)
                itemView.context.startActivity(intent)
            }
        }


    }
}