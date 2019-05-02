package br.edu.ifpr.stiehl.todolist.app

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import br.edu.ifpr.stiehl.aula7_sqlite.ui.TaskAdapterListener
import br.edu.ifpr.stiehl.todolist.R
import br.edu.ifpr.stiehl.todolist.db.AppDatabase
import br.edu.ifpr.stiehl.todolist.db.dao.TaskDao
import br.edu.ifpr.stiehl.todolist.entities.Task
import br.edu.ifpr.stiehl.todolist.ui.TaskAdapter

import kotlinx.android.synthetic.main.activity_todo.*
import kotlinx.android.synthetic.main.content_todo.*
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import br.edu.ifpr.stiehl.todolist.network.TasksService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class TodoActivity : AppCompatActivity(), TaskAdapterListener {

//    lateinit var taskDao: TaskDao
    lateinit var retrofit: Retrofit
    lateinit var service: TasksService
    lateinit var adapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo)
        setSupportActionBar(toolbar)

//        val db =
//            Room.databaseBuilder(
//                applicationContext,
//                AppDatabase::class.java,
//                "todo.db"
//            )
//                .allowMainThreadQueries()
//                .build()
//        taskDao = db.taskDao()

        configureRetrofit()

        btAdd.setOnClickListener {
            val position = adapter.createTask()
            listTask.scrollToPosition(position)
        }
    }

    fun configureRetrofit() {
        retrofit = Retrofit.Builder()
            .baseUrl("http://10.20.23.189:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        service = retrofit.create<TasksService>(TasksService::class.java)

        service.getAll().enqueue(object : Callback<List<Task>>{
            override fun onFailure(call: Call<List<Task>>, t: Throwable) {
                Log.e("ERRO", "ERRO", t)
            }

            override fun onResponse(call: Call<List<Task>>, response: Response<List<Task>>) {
                val tasks = response.body()
                if (tasks != null)
                    loadRecyclerView(tasks)
            }

        })
    }

    private fun loadRecyclerView(tasks: List<Task>) {
//        val tasks = taskDao.getAll()
        adapter = TaskAdapter(tasks.toMutableList(), this)
        listTask.adapter = adapter
        listTask.layoutManager = LinearLayoutManager(
            this,
            RecyclerView.VERTICAL, false
        )
    }

    override fun taskSaved(task: Task) {
        val call = if (task.id == 0L)
            service.createTask(task.title, task.description, task.done)
        else
            service.updateTask(task.id, task.title, task.description, task.done)

        call.enqueue(object: Callback<Task>{
            override fun onFailure(call: Call<Task>, t: Throwable) { }
            override fun onResponse(call: Call<Task>, response: Response<Task>) {
                val responseTask = response.body()
                if (responseTask != null) {
                    task.id = responseTask.id
                }
            }
        })
    }

    override fun taskRemoved(task: Task) {
        service.deleteTask(task.id).enqueue(object : Callback<Unit> {
            override fun onFailure(call: Call<Unit>, t: Throwable) {

            }

            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {

            }

        })
    }

}