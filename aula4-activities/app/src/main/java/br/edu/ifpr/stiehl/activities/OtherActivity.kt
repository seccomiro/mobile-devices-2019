package br.edu.ifpr.stiehl.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_other.*

class OtherActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_other)

        button.text = if (savedInstanceState == null)
            intent.extras.getString("nome")
        else
            savedInstanceState.getString("nome")

        button.setOnClickListener { button.text = "CLICOU AQUI" }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("nome", button.text.toString())
    }
}
