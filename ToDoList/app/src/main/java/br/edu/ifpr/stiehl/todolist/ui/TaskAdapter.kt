package br.edu.ifpr.stiehl.todolist.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.edu.ifpr.stiehl.todolist.R
import br.edu.ifpr.stiehl.todolist.entities.Task

class TaskAdapter(var tasks: MutableList<Task>) :
    RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    override fun getItemCount() = tasks.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        TaskViewHolder(LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_task_show, parent, false))

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.fillUI(tasks[position])
    }

    inner class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun fillUI(task: Task) {

        }

    }
}