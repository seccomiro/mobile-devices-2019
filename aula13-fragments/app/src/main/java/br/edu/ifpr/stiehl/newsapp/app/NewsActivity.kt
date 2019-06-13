package br.edu.ifpr.stiehl.newsapp.app

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import br.edu.ifpr.stiehl.newsapp.R
import kotlinx.android.synthetic.main.activity_news.*

class NewsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        navView.setupWithNavController(
            Navigation.findNavController(this, R.id.fragmentContent)
        )
        navView.setOnNavigationItemSelectedListener {
            NavigationUI.onNavDestinationSelected(it,
                Navigation.findNavController(this, R.id.fragmentContent))
        }
    }
}
