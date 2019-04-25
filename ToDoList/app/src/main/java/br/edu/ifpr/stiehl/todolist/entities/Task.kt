package br.edu.ifpr.stiehl.todolist.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class Task(
    var title: String,
    var description: String,
    var done: Boolean
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}