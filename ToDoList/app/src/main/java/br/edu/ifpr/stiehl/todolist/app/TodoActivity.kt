package br.edu.ifpr.stiehl.todolist.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity;
import br.edu.ifpr.stiehl.todolist.R

import kotlinx.android.synthetic.main.activity_todo.*

class TodoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo)
        setSupportActionBar(toolbar)


    }

}
