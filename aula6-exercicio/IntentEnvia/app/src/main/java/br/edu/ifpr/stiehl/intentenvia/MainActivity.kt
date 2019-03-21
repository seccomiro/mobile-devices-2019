package br.edu.ifpr.stiehl.intentenvia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

val MEDIA_PICK = 99

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btEnviar.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, MEDIA_PICK)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intentResposta: Intent?) {
        val uri = intentResposta?.data
//        Log.d("IMAGEM", uri.toString())
        val intent = Intent().apply {
            action = Intent.ACTION_SEND
            type = "image/*"
            putExtra(Intent.EXTRA_STREAM, uri)
        }
        startActivity(Intent.createChooser(intent, "Selecione uma aplicação..."))
    }
}
