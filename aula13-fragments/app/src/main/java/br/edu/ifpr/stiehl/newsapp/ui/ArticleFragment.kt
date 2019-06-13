package br.edu.ifpr.stiehl.newsapp.ui


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.edu.ifpr.stiehl.newsapp.R
import br.edu.ifpr.stiehl.newsapp.entities.Article
import kotlinx.android.synthetic.main.fragment_article.*


class ArticleFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_article, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val article = arguments?.getSerializable("article") as Article

        webView.loadUrl(article.url)

        lblArticleTitle.text = article.title
    }

}
