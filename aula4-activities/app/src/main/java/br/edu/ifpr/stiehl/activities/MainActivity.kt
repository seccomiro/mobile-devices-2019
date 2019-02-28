package br.edu.ifpr.stiehl.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("CICLO", "onCreate")

//        val txtHello: TextView = findViewById<TextView>(R.id.txtHello)
        txtHello.text = "Oi amiguinho!"
        txtWorld.text = "IUUUHUULL"

        btJoinha.setOnClickListener { fazAlgumaCoisa() }
        imgIcone.setOnClickListener { fazAlgumaCoisa() }
        imgIcone.setOnLongClickListener { cliqueLongo() }
    }

    private fun cliqueLongo(): Boolean {
        Toast
            .makeText(this, "LOOOOOOOONGOOOO", Toast.LENGTH_SHORT)
            .show()
        return true
    }

    private fun fazAlgumaCoisa() {
//        Toast
//            .makeText(this, "Fiz alguma coisa", Toast.LENGTH_SHORT)
//            .show()

        val bundle = Bundle()
        bundle.putString("nome", "Diego")

        val intent = Intent(this, OtherActivity::class.java)
        intent.putExtras(bundle)
        startActivity(intent)
    }

    override fun onStart() {
        super.onStart()
        Log.d("CICLO", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("CICLO", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("CICLO", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("CICLO", "onStop")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("CICLO", "onRestart")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("CICLO", "onDestroy")
    }

}
