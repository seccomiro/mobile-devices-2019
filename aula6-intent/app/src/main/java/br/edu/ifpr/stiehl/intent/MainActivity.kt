package br.edu.ifpr.stiehl.intent

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    //    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btLigar.setOnClickListener {
            ligarMasPedirPermissaoPrimeiro()
        }

        btBrowser.setOnClickListener {
            val url = Uri.parse("http://paranagua.ifpr.edu.br")
            val intent = Intent(Intent.ACTION_VIEW, url)
            startActivity(intent)
        }

        btSMS.setOnClickListener {
            val uri = Uri.parse("sms:041999994444")
            val smsIntent = Intent(Intent.ACTION_SENDTO, uri)
            smsIntent.putExtra("sms_body", "Olha a mensagem!")
            startActivity(smsIntent)
        }

        btCompartilhar.setOnClickListener {
            val shareIntent = Intent(Intent.ACTION_SEND)

            with(shareIntent) {
                type = "text/plain"
                putExtra(Intent.EXTRA_SUBJECT, "Compartilhar")
                putExtra(Intent.EXTRA_TEXT, "Olha lá o treco!")
            }

            startActivity(shareIntent)
        }
    }

    private fun ligarMasPedirPermissaoPrimeiro() {
        if (ActivityCompat.checkSelfPermission(
                this, Manifest.permission.CALL_PHONE
            ) != PackageManager.PERMISSION_GRANTED
        )
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.CALL_PHONE), 1
            )
        else
            ligar()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == 1)
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                ligar()
            else
                Toast.makeText(this, "Precisamos da sua permissão, meu amigo!", Toast.LENGTH_SHORT).show()
    }

    @SuppressLint("MissingPermission")
    private fun ligar() {
        val uri = Uri.parse("tel:041999994444")
        val intent = Intent(Intent.ACTION_CALL, uri)
        startActivity(intent)
    }
}